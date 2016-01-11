package com.casetext.hashes.fnv;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

public class FNV {

    public static String hash(String inp){

        return hash(128, inp, Charset.forName("UTF-8"));

    }

    public static String hash(Integer bits, String inp) {

        return hash(bits, inp, Charset.forName("UTF-8"));

    }

    public static String hash(Integer bits, String inp, Charset charset) {

        FNVParams params = new FNVParams(bits);
        byte[] bytes = inp.getBytes(charset);
        BigInteger digest = params.offset;
        for (byte b : bytes) {
            digest = digest.xor(BigInteger.valueOf((long) b));
            digest = digest.multiply(params.prime);
            digest = digest.mod(params.m);
        }
        byte[] result = digest.toByteArray();
        while (result.length > bits / 8) {
            result = Arrays.copyOfRange(result, 1, result.length);
        }
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(result);

    }

}
