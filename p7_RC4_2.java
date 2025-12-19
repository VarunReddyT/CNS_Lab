import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class p7_RC4_2 {

    // Generate RC4 key
    public static SecretKey generateKey(int keySize) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("ARCFOUR");
        keyGen.init(keySize); // 40, 64, 128 bits are typical
        return keyGen.generateKey();
    }

    // Encrypt using RC4
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("ARCFOUR");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt using RC4
    public static String decrypt(String ciphertext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("ARCFOUR");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decrypted);
    }

    public static void main(String[] args) {
        try {
            // Generate key
            SecretKey secretKey = generateKey(128);

            String plaintext = "Hello, World!";
            System.out.println("Original Text: " + plaintext);

            // Encrypt
            String encrypted = encrypt(plaintext, secretKey);
            System.out.println("Encrypted Text: " + encrypted);

            // Decrypt
            String decrypted = decrypt(encrypted, secretKey);
            System.out.println("Decrypted Text: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}