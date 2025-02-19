package edu.masanz.da.ta.utils;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Security {

    /**
     * Generates a random salt.
     * @return the generated salt
     */
    public static String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[24];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hashes the given word using a SHA1 hash algorithm.
     * @param word the word to hash
     * @return the hashed word
     */
    public static String hash(String word) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(word.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Validates a hashed password with a salt.
     * @param password the password to validate
     * @param salt the salt used to hash the password
     * @param hpwsalt the hashed password with the salt
     * @return true if the password is valid, false otherwise
     */
    public static boolean validateHashedPasswordSalt(String password, String salt, String hpwsalt) {
        String hashedPassword = hash(password + salt);
        return hashedPassword.equals(hpwsalt);
    }

    private static void test() {
        Console console = System.console();
        console.printf("USER SIGN IN\n");
        String username = console.readLine("Enter username: ");
        String password = new String(console.readPassword("Enter password: "));
        String salt = generateSalt();
        console.printf("Generated salt: %s\n", salt);
        String hashedPassword = hash(password + salt);
        console.printf("Hashed password: %s\n", hashedPassword);
        console.printf("USER AUTH\n");
        console.printf("Username: %s\n", username);
        String inputPassword = new String(console.readPassword("Enter password: "));
        String hashedInputPassword = hash(inputPassword + salt);
        if (hashedPassword.equals(hashedInputPassword)) {
            console.printf("AUTHENTICATED\n");
        } else {
            console.printf("NOT AUTHENTICATED\n");
        }
    }

    private static void generateFormatedUserData() {
        String txt = "\"%s,%s,%s,USER\", // %s";
        String names[] = {"Silvia", "Mikel", "Javi", "Alba", "Aitor", "Ane", "Jon", "Amaia", "Iker", "Maite"};
        String pws[] = {"1234", "4567", "7890", "0987", "7654", "4321", "1357", "2468", "3690", "1590"};
        for (int i = 0; i < pws.length; i++) {
            String name = names[i];
            String pw = pws[i];
            String sal = generateSalt();
            String hashPwSal = hash(pw + sal);
            System.out.println(String.format(txt, name, sal, hashPwSal, pw));
        }
    }

    public static void main(String[] args) {
        // test();// No funciona en el IDE, solo en consola por la clase Console
        generateFormatedUserData();// Genera las cadenas de texto para Ini.USUARIOS
    }

}
