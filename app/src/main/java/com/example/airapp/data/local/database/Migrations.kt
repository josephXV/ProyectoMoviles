package com.example.airapp.data.local.database

import androidx.room3.migration.Migration
import androidx.sqlite.execSQL

val MIGRATION_1_2 = Migration(1, 2) { connection ->
    connection.execSQL(
        """
        CREATE TABLE IF NOT EXISTS `customers` (
            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `name` TEXT NOT NULL,
            `email` TEXT NOT NULL,
            `phone` TEXT NOT NULL,
            `address` TEXT NOT NULL,
            `isActive` INTEGER NOT NULL
        )
        """.trimIndent()
    )
    connection.execSQL(
        "CREATE UNIQUE INDEX IF NOT EXISTS `index_customers_email` ON `customers` (`email`)"
    )

    connection.execSQL(
        """
        CREATE TABLE IF NOT EXISTS `service_ticket_headers` (
            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `ticketNumber` TEXT NOT NULL,
            `customerId` INTEGER NOT NULL,
            `technicianId` INTEGER NOT NULL,
            `createdAt` INTEGER NOT NULL,
            `status` TEXT NOT NULL,
            `description` TEXT NOT NULL,
            FOREIGN KEY(`customerId`) REFERENCES `customers`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
            FOREIGN KEY(`technicianId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
        )
        """.trimIndent()
    )
    connection.execSQL(
        "CREATE UNIQUE INDEX IF NOT EXISTS `index_service_ticket_headers_ticketNumber` ON `service_ticket_headers` (`ticketNumber`)"
    )
    connection.execSQL(
        "CREATE INDEX IF NOT EXISTS `index_service_ticket_headers_customerId` ON `service_ticket_headers` (`customerId`)"
    )
    connection.execSQL(
        "CREATE INDEX IF NOT EXISTS `index_service_ticket_headers_technicianId` ON `service_ticket_headers` (`technicianId`)"
    )

    connection.execSQL(
        """
        CREATE TABLE IF NOT EXISTS `ticket_details` (
            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `ticketId` INTEGER NOT NULL,
            `description` TEXT NOT NULL,
            `quantity` INTEGER NOT NULL,
            `unitPrice` REAL NOT NULL,
            FOREIGN KEY(`ticketId`) REFERENCES `service_ticket_headers`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
        )
        """.trimIndent()
    )
    connection.execSQL(
        "CREATE INDEX IF NOT EXISTS `index_ticket_details_ticketId` ON `ticket_details` (`ticketId`)"
    )
}

/** Renombra los estados de tiquete: ABIERTO -> NUEVO, CERRADO -> FINALIZADO. */
val MIGRATION_2_3 = Migration(2, 3) { connection ->
    connection.execSQL("UPDATE `service_ticket_headers` SET `status` = 'NUEVO' WHERE `status` = 'ABIERTO'")
    connection.execSQL("UPDATE `service_ticket_headers` SET `status` = 'FINALIZADO' WHERE `status` = 'CERRADO'")
}
