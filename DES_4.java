// 4.Write a java program to implement the DES algorithm logic?

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DES_4 {

    // Method to generate a secret key for DES
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); // DES uses a 56-bit key size
        return keyGenerator.generateKey();
    }

    // Method to encrypt data using the DES algorithm
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt data using the DES algorithm
    public static String decrypt(String ciphertext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Generate a secret key for DES
            SecretKey secretKey = generateKey();

            // Plain text to be encrypted
            String plaintext = "Hello, World!";
            System.out.println("Original Text: " + plaintext);

            // Encrypt the plain text
            String encryptedText = encrypt(plaintext, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the encrypted text
            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}