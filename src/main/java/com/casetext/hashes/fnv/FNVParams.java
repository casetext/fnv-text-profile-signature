package com.casetext.hashes.fnv;

import java.math.BigInteger;

public class FNVParams {


    public BigInteger m;
    public BigInteger prime;
    public BigInteger offset;

    public FNVParams(Integer bits) {

        int a = 0;
        int b = 0;

        switch (bits) {
            case 32:
                a = 24;
                b = 0x93;
                offset = new BigInteger("811C9DC5", 16);
                break;
            case 64:
                a = 40;
                b = 0xb3;
                offset = new BigInteger("CBF29CE484222325", 16);
                break;
            case 128:
                a = 88;
                b = 0x3b;
                offset = new BigInteger("6C62272E07BB014262B821756295C58D", 16);
                break;
            case 256:
                a = 168;
                b = 0x63;
                offset = new BigInteger("DD268DBCAAC550362D98C384C4E576CCC8B1536847B6BBB31023B4C8CAEE0535", 16);
                break;
            case 512:
                a = 334;
                b = 0x57;
                offset = new BigInteger("B86DB0B1171F4416DCA1E50F309990ACAC87D059C90000000000000000000D21" +
                                        "E948F68A34C192F62EA79BC942DBE7CE182036415F56E34BAC982AAC4AFE9FD9", 16);
                break;
            case 1024:
                a = 680;
                b = 0x8d;
                offset = new BigInteger("0000000000000000005F7A76758ECC4D32E56D5A591028B74B29FC4223FDADA1" +
                                        "6C3BF34EDA3674DA9A21D9000000000000000000000000000000000000000000" +
                                        "000000000000000000000000000000000000000000000000000000000004C6D7" +
                                        "EB6E73802734510A555F256CC005AE556BDE8CC9C6A93B21AFF4B16C71EE90B3", 16);
                break;
        }
        m = new BigInteger("2").pow(bits);
        prime = (new BigInteger("2").pow(a)).add(new BigInteger("2").pow(8)).add(new BigInteger(Integer.toString(b)));

    }


}
