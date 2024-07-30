
package com.mycompany.filedecryptor;

import java.io.*;
import java.util.Scanner;

public class FileDecryptor {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter encrypted file name (example, “out.txt”): ");
        String encryptedFileName = input.nextLine();
        System.out.print("Enter output file name for the decrypted content (example, “toReal.txt”): ");
        String decryptedFileName = input.nextLine();
        System.out.print("Enter decryption key: ");
        int key = input.nextInt();

        boolean success = decryptFile(encryptedFileName, decryptedFileName, key);
        if (success) {
            System.out.println("File decryption completed successfully.");
        } else {
            System.out.println("File decryption failed.");
        }
        
        
    }
    
    public static boolean decryptFile(String encryptedFileName, String decryptedFileName, int key) {
        File encryptedFile = new File(encryptedFileName);
        if (!encryptedFile.exists()) {
            System.out.println("Encrypted file does not exist.");
            return false;
        }
        
        try (Scanner fileScanner = new Scanner(encryptedFile);
             PrintWriter fileWriter = new PrintWriter(decryptedFileName)) {
            while (fileScanner.hasNextLine()) {
                String encryptedText = fileScanner.nextLine();
                String decryptedText = decryptString(encryptedText, key);
                fileWriter.println(decryptedText);
            }
        } catch (IOException e) {
            System.out.println("An error occurred during file processing: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public static String decryptString(String encryptedString, int aKey) {
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < encryptedString.length(); i++) {
            char decryptedChar = (char) (encryptedString.charAt(i) - aKey);
            decryptedString.append(decryptedChar);
        }
        return decryptedString.toString();
    }
}
