import java.util.Scanner;

public class VigenereCipher {

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();

        int j = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) {
                char shift = key.charAt(j % key.length());
                ciphertext.append((char) (((c - 'A' + shift - 'A') % 26) + 'A'));
                j++;
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();

        int j = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if (Character.isLetter(c)) {
                char shift = key.charAt(j % key.length());
                plaintext.append((char) (((c - 'A' - (shift - 'A') + 26) % 26) + 'A'));
                j++;
            } else {
                plaintext.append(c); 
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}