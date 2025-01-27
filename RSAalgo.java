
import java.util.*;
import java.math.*;


class RSA{

    BigInteger e,d,n;

    void getkeys(int bitlen){

        Random r=new Random();
        BigInteger p=BigInteger.probablePrime(bitlen, r);
        BigInteger q=BigInteger.probablePrime(bitlen, r);

        n=p.multiply(q);

        BigInteger phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e=BigInteger.probablePrime(bitlen/2, r);
        d=e.modInverse(phi);
    }
    BigInteger encrypt(BigInteger P){
        return P.modPow(e, n);
    }
    BigInteger decrypt(BigInteger C){
        return C.modPow(d, n);
    }

}

public class RSAalgo {
    public static void main(String[] args) {
        RSA rsa=new RSA();
    int bitlen=1024;

    Scanner sc=new Scanner(System.in);

    rsa.getkeys(bitlen);

    System.out.println("Enter the message to be encypted:");
    BigInteger P=new BigInteger(sc.next().getBytes());

    BigInteger C=rsa.encrypt(P);
    
    System.out.println("Encypted message is: "+C.longValue());

    BigInteger d=rsa.decrypt(C);

    System.out.println("Decrypted message is: "+new String(d.toByteArray()));
     sc.close();
    }
    
}
