package Core;

import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.util.encoders.Base64;

import java.security.SecureRandom;

public class HashHelper {
    private static final int N = 16384;  // CPU/memory cost factor
    private static final int r = 8;      // Block size
    private static final int p = 1;      // Parallelization factor
    private static final int dkLen = 32; // Length of the derived key

    // Hash a password
    public static String hashPassword(String password) {
        // Generate a random salt
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        // Hash the password using SCrypt
        byte[] hashedPassword = SCrypt.generate(password.getBytes(), salt, N, r, p, dkLen);

        // Combine salt and hashed password for storage
        return Base64.toBase64String(salt) + ":" + Base64.toBase64String(hashedPassword);
    }

    // Check if a password matches the hashed password
    public static boolean checkPassword(String rawPassword, String storedHash) {
        String[] parts = storedHash.split(":");

        if (parts.length != 2) {
            System.out.println("Invalid stored hash");
            return false;
        }

        byte[] salt;
        byte[] hashedPassword;

        try {
            salt = Base64.decode(parts[0]);
            hashedPassword = Base64.decode(parts[1]);
        }

        catch (Exception e) {
            System.out.println("Base64 decoding failed");
            return false;
        }

        // Hash the input password with the same salt
        byte[] checkHash = SCrypt.generate(rawPassword.getBytes(), salt, N, r, p, dkLen);

        // Compare the hashes
        return java.util.Arrays.equals(hashedPassword, checkHash);
    }
}
