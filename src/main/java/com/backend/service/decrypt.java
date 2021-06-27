package com.backend.service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import java.util.Arrays;
import java.util.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;

public class decrypt{

    public static String decrypt(String message, String sercretKey) {

        String decrypt = "";
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestOfPassword = md.digest(sercretKey.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            byte[] iv = Arrays.copyOf(digestOfPassword, 16);

            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(message));
            decrypt = new String(plainText);

            System.out.println("decrypted string: " + decrypt);
            return decrypt;
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }


    public static void main(String... arg){
        String enc = decrypt.decrypt("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","69b5674a58c16abfd74b69ae3187f931");
        System.out.println(enc);
    }

}
