package com.backend.service;

import org.apache.commons.codec.binary.Hex;

import java.security.SecureRandom;

public class CryptoUtils {

    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    // hex representation
    public static String hex(byte[] bytes) {
        return String.valueOf(Hex.encodeHex(bytes));
    }

}
