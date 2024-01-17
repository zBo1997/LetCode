package jasypt;
 
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
 
public class EncTest {
 
        public static void main(String[] args) {
            String password = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKXIMBYdDW";
 
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(password);

            String decryptedText = encryptor.decrypt("3WF2vihyxvnkfFJ+TlwSp6RZKRkdQPsN");
            System.out.println("Decrypted Text: " + decryptedText);
        }
}