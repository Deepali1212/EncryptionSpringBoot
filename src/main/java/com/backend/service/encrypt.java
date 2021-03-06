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

public class encrypt{

    public static String encrypt(String message, String sercretKey) {

        String encrypt = "";
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestOfPassword = md.digest(sercretKey.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            byte[] iv = Arrays.copyOf(digestOfPassword, 16);

            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

            //Message is data
            byte[] plainTextBytes = message.getBytes(StandardCharsets.UTF_8);
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.getEncoder().encode(buf);
            encrypt = new String(base64Bytes);

            return encrypt;
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String... arg){
        String enc = encrypt.encrypt("{\"txnId\":\"ZAMT0001\",\"agentMob\":\"9350210028\",\"clientAgentId\":\"ZAM100002\",\"agentName\":\"AmitSharma\",\"dateOfBirth\":\"04/11/1979\",\"partnerId\":\"1225\",\"gender\":\"F\",\"aadhaarToken\":\"5552amisha49\",\"fatherName\":\"chander pal sharma\",\"handicapped\":\"0\",\"shopName\":\"jinicart\",\"pancard\":\"BCNPS0822E\",\"address\":\"K 106 krishna gali no 8 maujpur\",\"city\":\"delhi\",\"district\":\"north east delhi\",\"state\":\"delhi\",\"pinCode\":\"110053\",\"altOccupationType\":\"Private\",\"highestQualification\":\"BA\", \"isCorporate\":\"1\",\"activityFrom\":\"19/05/2021\",\"allocationIFSC\":\"ICIC0001135\",\"agentType\":\"1\",\"minCashHandlingLimit\":\"50000\",\"course\":\"None\",\"passingDate\":\"05/10/2010\",\"expFromDate\":\"05/10/2000\",\"expToDate\":\"05/10/2000\",\"deviceName\":\"Computer core 2\",\"deviceCode\":\"12\",\"givenDate\":\"05/10/2010\",\"connectivityType\":\"Mobile\", \"connectivityProvider\":\"1\",\"providerPhoneNum\":\"9350210028\",\"primarySSA\":\"delhi\",\"primaryVillegeCode\":\"40335000\",\"primaryPinCode\":\"110053\",\"primarySunday\":\"1\",\"primaryMonday\":\"1\",\"primaryTuesday\":\"1\",\"primaryWednesday\":\"1\",\"primaryThursday\":\"1\",\"primaryFriday\":\"1\",\"primarySaturday\":\"1\",\"secondaryVillegeCode\":\"40335000\",\"secondaryVillegeDetails\":\"kdfjhgkdf\",\"secondarySunday\":\"1\",\"secondaryMonday\":\"1\",\"secondaryTuesday\":\"1\",\"secondaryWednesday\":\"1\",\"secondaryThursday\":\"1\",\"secondaryFriday\":\"1\",\"secondarySaturday\":\"1\",\"remunMonth\":\"1\",\"remunYear\":\"2020\",\"corporatedId\":\"46\",\"channel\":\"1\",\"nbfcStatus\":\"0\",\"hashKey\":\"4d2c9f484c94bae27205ac2f45bb29f5\"}","69b5674a58c16abfd74b69ae3187f931");
        System.out.println(enc);
    }

}
