package com.example.airapp.security

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordHasher {
    private const val ALGORITHM = "PBKDF2WithHmacSHA256"
    private const val ITERATIONS = 600_000
    private const val KEY_LENGTH = 256
    private const val SALT_LENGTH = 16

    fun hash(password: String): String {

        val salt = ByteArray(SALT_LENGTH)

        SecureRandom().nextBytes(salt)

        val spec = PBEKeySpec(
            password.toCharArray(),
            salt,
            ITERATIONS,
            KEY_LENGTH
        )

        val hash = try {
            SecretKeyFactory
                .getInstance(ALGORITHM)
                .generateSecret(spec)
                .encoded
        } finally {
            spec.clearPassword()
        }

        val saltBase64 =
            Base64.encodeToString(
                salt,
                Base64.NO_WRAP
            )

        val hashBase64 =
            Base64.encodeToString(
                hash,
                Base64.NO_WRAP
            )

        return "$saltBase64:$hashBase64"
    }

    fun verify(
        password: String,
        storedPasswordHash: String
    ): Boolean {

        val parts = storedPasswordHash.split(":")

        if (parts.size != 2) {
            return false
        }

        val salt =
            Base64.decode(
                parts[0],
                Base64.NO_WRAP
            )

        val storedHash =
            Base64.decode(
                parts[1],
                Base64.NO_WRAP
            )

        val spec = PBEKeySpec(
            password.toCharArray(),
            salt,
            ITERATIONS,
            KEY_LENGTH
        )

        val generatedHash = try {
            SecretKeyFactory
                .getInstance(ALGORITHM)
                .generateSecret(spec)
                .encoded
        } finally {
            spec.clearPassword()
        }

        return storedHash.contentEquals(generatedHash)
    }
}