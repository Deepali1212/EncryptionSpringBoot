package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

@Service
public class EncryptionService {
    public String encrypt(String message, String sercretKey, String type) {

        String encrypt = "";
        try {

            //String jsonData = new String("{\"txnId\":\"ZAMT0001\", \"agentMob\":\"9350210028\",\"clientAgentId\":\"ZAM100002\", \"agentName\":\"AmitSharma\", \"dateOfBirth\":\"04/11/1979\", \"partnerId\":\"1225\", \"gender\":\"F\", \"aadhaarToken\":\"5552amisha49\", \"fatherName\":\"chander pal sharma\", \"handicapped\":\"0\", \"shopName\":\"jinicart\",\"pancard\":\"BCNPS0822E\", \"address\":\"K 106 krishna gali no 8 maujpur\", \"city\":\"delhi\", \"district\":\"north east delhi\", \"state\":\"delhi\", \"pinCode\":\"110053\", \"altOccupationType\":\"Private\", \"highestQualification\":\"BA\", \"isCorporate\":\"1\", \"activityFrom\":\"19/05/2021\", \"allocationIFSC\":\"ICIC0001135\", \"agentType\":\"1\", \"minCashHandlingLimit\":\"50000\", \"course\":\"None\", \"passingDate\":\"05/10/2010\", \"expFromDate\":\"05/10/2000\", \"expToDate\":\"05/10/2000\", \"deviceName\":\"Computer core 2\", \"deviceCode\":\"12\", \"givenDate\":\"05/10/2010\", \"connectivityType\":\"Mobile\", \"connectivityProvider\":\"1\", \"providerPhoneNum\":\"9350210028\", \"primarySSA\":\"delhi\",\"primaryVillegeCode\":\"40335000\", \"primaryPinCode\":\"110053\", \"primarySunday\":\"1\", \"primaryMonday\":\"1\", \"primaryTuesday\":\"1\", \"primaryWednesday\":\"1\", \"primaryThursday\":\"1\", \"primaryFriday\":\"1\", \"primarySaturday\":\"1\", \"secondaryVillegeCode\":\"40335000\", \"secondaryVillegeDetails\":\"kdfjhgkdf\", \"secondarySunday\":\"1\", \"secondaryMonday\":\"1\", \"secondaryTuesday\":\"1\", \"secondaryWednesday\":\"1\",\"secondaryThursday\":\"1\",\"secondaryFriday\":\"1\",\"secondarySaturday\":\"1\", \"remunMonth\":\"1\", \"remunYear\":\"2020\", \"corporatedId\":\"46\", \"channel\":\"1\", \"nbfcStatus\":\"0\", \"hashKey\":\"4d2c9f484c94bae27205ac2f45bb29f5\"}");

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestOfPassword = md.digest(sercretKey.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            byte[] iv = Arrays.copyOf(digestOfPassword, 16);

            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            if (type.equalsIgnoreCase("encrypt")) {
                cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
                //Message is data
                byte[] plainTextBytes = message.getBytes(StandardCharsets.UTF_8);
                byte[] buf = cipher.doFinal(plainTextBytes);
                byte[] base64Bytes = Base64.getEncoder().encode(buf);
                encrypt = new String(base64Bytes);
                return encrypt;
            }
            if (type.equalsIgnoreCase("decrypt")) {
                cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
                byte[] plainText = cipher.doFinal(Base64.getDecoder()
                        .decode(message));
                return new String(plainText);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    public String getAESEncryption(String text, String key, String type) throws Exception {
        try {
            if (type.equalsIgnoreCase("decrypt")) {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] keyBytes = new byte[16];
                byte[] b = key.getBytes("UTF-8");
                int len = b.length;
                if (len > keyBytes.length) len = keyBytes.length;
                System.arraycopy(b, 0, keyBytes, 0, len);
                SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] cipherText = decoder.decode(text.getBytes("UTF8"));
                String decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");
                return decryptedText;
                //       BASE64Decoder decoder = new BASE64Decoder();
                // byte[] results = cipher.doFinal(decoder.decodeBuffer(text));
                //return new String(results, "UTF-8");
            } else {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] keyBytes = new byte[16];
                byte[] b = key.getBytes("UTF-8");
                int len = b.length;
                if (len > keyBytes.length) len = keyBytes.length;
                System.arraycopy(b, 0, keyBytes, 0, len);
                SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
                byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
                //  BASE64Encoder encoder = new BASE64Encoder();
                Base64.Encoder encoder = Base64.getEncoder();
                String encryptedText = encoder.encodeToString(results);
              //  return encoder.encode(results);
                return encryptedText;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
