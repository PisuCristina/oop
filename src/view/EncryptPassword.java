package view;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
    public static String getEncryption(String input)
    {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return toHexString(md.digest(input.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean comparePassword(String password, String hash) {
        return getEncryption(password).equals(hash);
    }

    private static String toHexString(byte[] hash)
    {

        BigInteger number = new BigInteger(1, hash);


        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
