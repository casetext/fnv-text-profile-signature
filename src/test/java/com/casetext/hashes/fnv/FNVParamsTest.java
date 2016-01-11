package com.casetext.hashes.fnv;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class FNVParamsTest {

    @org.junit.Test
    public void test() throws Exception {

        FNVParams params = new FNVParams(128);

        BigInteger expectedPrime = new BigInteger("0000000001000000000000000000013B", 16);
        BigInteger expectedOffset = new BigInteger("6C62272E07BB014262B821756295C58D", 16);

        assertEquals("It should have the expected prime", expectedPrime, params.prime);
        assertEquals("It should have the expected offset", expectedOffset, params.offset);

    }

}