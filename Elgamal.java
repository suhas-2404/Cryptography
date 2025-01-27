import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Elgamal {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter prime number (p): ");
        BigInteger p = scanner.nextBigInteger();
        System.out.print("Enter primitive root modulo p (e1): ");
        BigInteger e1 = scanner.nextBigInteger();
    
        System.out.print("Enter private key (d): ");
        BigInteger d = scanner.nextBigInteger();
        
        BigInteger e2 = e1.modPow(d, p);
        
        System.out.print("Enter random integer (r): ");
        BigInteger r = scanner.nextBigInteger();
        scanner.nextLine(); 
        
        System.out.print("Enter plaintext message: ");
        String message = scanner.nextLine();
        
        List<BigInteger> c1List = new ArrayList<>();
        List<BigInteger> c2List = new ArrayList<>();
        
        for (char ch : message.toCharArray()) {
            BigInteger m = BigInteger.valueOf((int) ch);
            
            BigInteger c1 = e1.modPow(r, p);
            BigInteger c2 = (m.multiply(e2.modPow(r, p))).mod(p);
            
            c1List.add(c1);
            c2List.add(c2);
        }
        
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < c1List.size(); i++) {
            BigInteger s = c1List.get(i).modPow(d, p);
            BigInteger sInverse = s.modInverse(p);
            BigInteger decryptedInt = (c2List.get(i).multiply(sInverse)).mod(p);
            decryptedMessage.append((char) decryptedInt.intValue());
        }
        
        System.out.println("Public key (e1, e2, p): (" + e1 + ", " + e2 + ", " + p + ")");
        System.out.println("Private key d: " + d);
        System.out.println("Ciphertext (c1, c2): ");
        for (int i = 0; i < c1List.size(); i++) {
            System.out.println("(" + c1List.get(i) + ", " + c2List.get(i) + ")");
        }
        System.out.println("Decrypted message: " + decryptedMessage.toString());
        
        scanner.close();
    }
}
