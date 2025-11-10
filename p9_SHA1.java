import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class p9_SHA1 {

    public static void main(String[] args) {
        String input = "Hello, World!";
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            System.out.println("SHA-1 Digest: " + sb.toString());
            
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-1 algorithm not found: " + e.getMessage());
        }
    }
}