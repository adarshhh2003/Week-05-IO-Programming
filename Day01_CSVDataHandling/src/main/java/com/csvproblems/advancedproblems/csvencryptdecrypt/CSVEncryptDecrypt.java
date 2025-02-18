package com.csvproblems.advancedproblems.csvencryptdecrypt;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CSVEncryptDecrypt {
    private static final String CSV_FILE = "src/main/java/com/csvproblems/advancedproblems/csvencryptdecrypt/employees.csv";
    private static final String ENCRYPTED_CSV_FILE = "src/main/java/com/csvproblems/advancedproblems/csvencryptdecrypt/employees_encrypted.csv";
    private static final String DECRYPTED_CSV_FILE = "src/main/java/com/csvproblems/advancedproblems/csvencryptdecrypt/employees_decrypted.csv";

    // Secret Key for AES Encryption (16 characters for 128-bit AES)
    private static final String SECRET_KEY = "MySuperSecretKey";

    // Encrypt a string using AES
    private static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting: " + e.getMessage());
        }
    }

    // Decrypt a string using AES
    private static String decrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting: " + e.getMessage());
        }
    }

    // Encrypt sensitive fields (Email, Salary) and write to a new CSV file
    public static void encryptCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ENCRYPTED_CSV_FILE))) {

            String headerLine = reader.readLine();
            if (headerLine != null) {
                writer.write(headerLine + "\n"); // Write header
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    fields[2] = encrypt(fields[2]); // Encrypt Email
                    fields[3] = encrypt(fields[3]); // Encrypt Salary
                }
                writer.write(String.join(",", fields) + "\n");
            }
            System.out.println(" CSV file encrypted: " + ENCRYPTED_CSV_FILE);
        } catch (IOException e) {
            System.out.println("Error processing CSV file: " + e.getMessage());
        }
    }

    // Decrypt sensitive fields (Email, Salary) and write to a new CSV file
    public static void decryptCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ENCRYPTED_CSV_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(DECRYPTED_CSV_FILE))) {

            String headerLine = reader.readLine();
            if (headerLine != null) {   
                writer.write(headerLine + "\n"); // Write header
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    fields[2] = decrypt(fields[2]); // Decrypt Email
                    fields[3] = decrypt(fields[3]); // Decrypt Salary
                }
                writer.write(String.join(",", fields) + "\n");
            }
            System.out.println(" CSV file decrypted: " + DECRYPTED_CSV_FILE);
        } catch (IOException e) {
            System.out.println("Error processing encrypted CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        encryptCSV();  // Encrypt CSV file
        decryptCSV();  // Decrypt CSV file
    }
}
