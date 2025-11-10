import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class p10_MD5 {

    public static void main(String[] args) {
        String input = "Hello, World!";
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            System.out.println("MD5 Digest: " + sb.toString());
            
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm not found: " + e.getMessage());
        }
    }
}