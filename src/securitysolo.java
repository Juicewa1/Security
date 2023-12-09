import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class securitysolo {

    public static void main(String[] args) {

        Map<Character, Character> spacePersonAlphabet = new HashMap<>();
        spacePersonAlphabet.put('a', '~');
        spacePersonAlphabet.put('b', '!');
        spacePersonAlphabet.put('c', '@');
        spacePersonAlphabet.put('d', '#');
        spacePersonAlphabet.put('e', '$');
        spacePersonAlphabet.put('f', '%');
        spacePersonAlphabet.put('g', '^');
        spacePersonAlphabet.put('h', '&');
        spacePersonAlphabet.put('i', '*');
        spacePersonAlphabet.put('j', '(');
        spacePersonAlphabet.put('k', ')');
        spacePersonAlphabet.put('l', '_');
        spacePersonAlphabet.put('m', '+');
        spacePersonAlphabet.put('n', '}');
        spacePersonAlphabet.put('o', '{');
        spacePersonAlphabet.put('p', '|');
        spacePersonAlphabet.put('q', ':');
        spacePersonAlphabet.put('r', ';');
        spacePersonAlphabet.put('s', '<');
        spacePersonAlphabet.put('t', '>');
        spacePersonAlphabet.put('u', ',');
        spacePersonAlphabet.put('v', '.');
        spacePersonAlphabet.put('w', '?');
        spacePersonAlphabet.put('x', '[');
        spacePersonAlphabet.put('y', ']');
        spacePersonAlphabet.put('z', '/');


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string: ");
        String englishString = scanner.nextLine();

        String spacePersonString = convertToSpacePerson(spacePersonAlphabet, englishString);
        System.out.println("Space Person String: " + spacePersonString);


        String hashValue = calculateSHA256(spacePersonString);
        System.out.println("SHA256 Hash: " + hashValue);

        String caesarResult = caesarCipher(englishString, 5);
        System.out.println("Caesar Cipher: " + caesarResult);


        System.out.println("Brute Force:");
        bruteForceCaesarCipher(englishString);
    }

    private static String convertToSpacePerson(Map<Character, Character> alphabet, String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char convertedChar = alphabet.getOrDefault(Character.toLowerCase(c), c);
            result.append(convertedChar);
        }
        return result.toString();
    }

    private static String calculateSHA256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char shiftedChar = (char) (((c - base + shift) % 26) + base);
                result.append(shiftedChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static void bruteForceCaesarCipher(String text) {
        for (int shift = 0; shift < 26; shift++) {
            String result = caesarCipher(text, shift);
            System.out.println("Shift " + shift + ": " + result);
        }
    }
}
