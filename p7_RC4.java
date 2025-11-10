import java.util.Scanner;

public class p7_RC4 {
    private byte[] S = new byte[256];
    private int x = 0;
    private int y = 0;
    public p7_RC4(byte[] key) {
        init(key);
    }
    private void init(byte[] key) {
        int keyLength = key.length;
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keyLength]) & 0xFF;
            swap(i, j);
        }
    }
    private void swap(int i, int j) {
        byte temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }
    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ keyItem());
        }
        return ciphertext;
    }
    private byte keyItem() {
        x = (x + 1) & 0xFF;
        y = (y + S[x]) & 0xFF;
        swap(x, y);
        return S[(S[x] + S[y]) & 0xFF];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a key for RC4 encryption (e.g., mysecretkey):");
        String keyString = scanner.nextLine();
        byte[] key = keyString.getBytes();

        p7_RC4 rc4 = new p7_RC4(key);

        String plaintext = "Hello World";
        System.out.println("Original Text: " + plaintext);

        byte[] ciphertext = rc4.encrypt(plaintext.getBytes());
        System.out.println("Encrypted Text: " + new String(ciphertext));

        byte[] decryptedText = rc4.encrypt(ciphertext); 
        System.out.println("Decrypted Text: " + new String(decryptedText));

        scanner.close();
    }
}