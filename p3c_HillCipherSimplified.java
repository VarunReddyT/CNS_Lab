import java.util.Scanner;

public class p3c_HillCipherSimplified {

    // Encrypt function
    static String encrypt(String p, int[][] k) {
        int x = p.charAt(0) - 'A';
        int y = p.charAt(1) - 'A';

        char c1 = (char) (((k[0][0]*x + k[0][1]*y) % 26) + 'A');
        char c2 = (char) (((k[1][0]*x + k[1][1]*y) % 26) + 'A');

        return "" + c1 + c2;
    }

    // Decrypt function
    static String decrypt(String c, int[][] k) {
        int det = (k[0][0]*k[1][1] - k[0][1]*k[1][0]) % 26;
        if (det < 0) det += 26;

        // Find inverse of determinant
        int detInv = 0;
        for (int i = 1; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                detInv = i;
                break;
            }
        }

        // Inverse matrix
        int[][] inv = new int[2][2];
        inv[0][0] =  k[1][1] * detInv % 26;
        inv[0][1] = -k[0][1] * detInv % 26;
        inv[1][0] = -k[1][0] * detInv % 26;
        inv[1][1] =  k[0][0] * detInv % 26;

        for (int i=0;i<2;i++)
            for (int j=0;j<2;j++)
                if (inv[i][j] < 0) inv[i][j] += 26;

        int x = c.charAt(0) - 'A';
        int y = c.charAt(1) - 'A';

        char p1 = (char) (((inv[0][0]*x + inv[0][1]*y) % 26) + 'A');
        char p2 = (char) (((inv[1][0]*x + inv[1][1]*y) % 26) + 'A');

        return "" + p1 + p2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] key = new int[2][2];
        System.out.println("Enter 2x2 key matrix:");
        for (int i=0;i<2;i++)
            for (int j=0;j<2;j++)
                key[i][j] = sc.nextInt();

        System.out.print("Enter plaintext (2 letters): ");
        String plaintext = sc.next().toUpperCase();

        String cipher = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + cipher);

        System.out.println("Decrypted Text: " + decrypt(cipher, key));

        sc.close();
    }
}
