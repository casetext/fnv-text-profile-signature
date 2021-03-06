/**
 * Copyright 2015 Casetext, Inc.
 * 
 * Derived from TextProfileSignature.java, as distributed with Apache
 * SOLR, which was licensed to the Apache Software Foundation (ASF) under 
 * one or more contributor license agreements.  See the NOTICE file 
 * located at Apache Solr's github for information regarding copyright
 * ownership: https://github.com/apache/solr/blob/branch-1.1/NOTICE.txt
 * 
 * Casetext licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.casetext.hashes.textprofile;

import com.casetext.hashes.fnv.FNV;

import java.util.*;

public class TextProfileSignature {

    private float quantRate;
    private float minTokenLen;

    private LinkedHashMap<String, Token> tokens = new LinkedHashMap<>();
    private StringBuilder curToken = new StringBuilder();
    private int maxFreq = 0;

    public TextProfileSignature(float quantizationRate, int minimumTokenLength) {
        quantRate = quantizationRate;
        minTokenLen = minimumTokenLength;
    }

    public TextProfileSignature() {
        this(0.01f, 2);
    }

    public void addText(String content) {
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                curToken.append(Character.toLowerCase(c));
            } else {
                if (curToken.length() > 0) {
                    checkToken();
                    curToken.setLength(0);
                }
            }
        }
        checkToken();
    }

    public void reset() {
        tokens = new LinkedHashMap<>();
        curToken = new StringBuilder();
        maxFreq = 0;
    }

    public String getSignature() {
        Iterator<Token> it = tokens.values().iterator();
        ArrayList<Token> profile = new ArrayList<>();
        // calculate the QUANT value
        int quant = Math.round(maxFreq * quantRate);
        if (quant < 2) {
            if (maxFreq > 1)
                quant = 2;
            else
                quant = 1;
        }
        while (it.hasNext()) {
            Token t = it.next();
            // round down to the nearest QUANT
            t.cnt = (t.cnt / quant) * quant;
            // discard the frequencies below the QUANT
            if (t.cnt < quant) {
                continue;
            }
            profile.add(t);
        }
        Collections.sort(profile, new TokenComparator());
        StringBuilder newText = new StringBuilder();
        it = profile.iterator();
        while (it.hasNext()) {
            Token t = it.next();
            if (newText.length() > 0)
                newText.append("\n");
            newText.append(t.toString());
        }
        return FNV.hash(newText.toString());
    }

    private void checkToken() {
        if (curToken.length() > minTokenLen) {
            // add it
            String s = curToken.toString();
            Token tok = tokens.get(s);
            if (tok == null) {
                tok = new Token(0, s);
                tokens.put(s, tok);
            }
            tok.cnt++;
            if (tok.cnt > maxFreq)
                maxFreq = tok.cnt;
        }
    }

//    public String getSignature(String content) {
//
//        LinkedHashMap<String, Token> tokens = new LinkedHashMap<>();
//        StringBuilder curToken = new StringBuilder();
//        int maxFreq = 0;
//
//        for (int i = 0; i < content.length(); i++) {
//            char c = content.charAt(i);
//            if (Character.isLetterOrDigit(c)) {
//                curToken.append(Character.toLowerCase(c));
//            } else {
//                if (curToken.length() > 0) {
//                    if (curToken.length() > minTokenLen) {
//                        // add it
//                        String s = curToken.toString();
//                        Token tok = tokens.get(s);
//                        if (tok == null) {
//                            tok = new Token(0, s);
//                            tokens.put(s, tok);
//                        }
//                        tok.cnt++;
//                        if (tok.cnt > maxFreq)
//                            maxFreq = tok.cnt;
//                    }
//                    curToken.setLength(0);
//                }
//            }
//        }
//        // check the last token
//        if (curToken.length() > minTokenLen) {
//            // add it
//            String s = curToken.toString();
//            Token tok = tokens.get(s);
//            if (tok == null) {
//                tok = new Token(0, s);
//                tokens.put(s, tok);
//            }
//            tok.cnt++;
//            if (tok.cnt > maxFreq)
//                maxFreq = tok.cnt;
//        }
//        Iterator<Token> it = tokens.values().iterator();
//        ArrayList<Token> profile = new ArrayList<>();
//        // calculate the QUANT value
//        int quant = Math.round(maxFreq * quantRate);
//        if (quant < 2) {
//            if (maxFreq > 1)
//                quant = 2;
//            else
//                quant = 1;
//        }
//        while (it.hasNext()) {
//            Token t = it.next();
//            // round down to the nearest QUANT
//            t.cnt = (t.cnt / quant) * quant;
//            // discard the frequencies below the QUANT
//            if (t.cnt < quant) {
//                continue;
//            }
//            profile.add(t);
//        }
//        Collections.sort(profile, new TokenComparator());
//        StringBuilder newText = new StringBuilder();
//        it = profile.iterator();
//        while (it.hasNext()) {
//            Token t = it.next();
//            if (newText.length() > 0)
//                newText.append("\n");
//            newText.append(t.toString());
//        }
//        return FNV.hash(newText.toString());
//    }

    private static class Token {
        public int cnt;
        public String val;

        public Token(int cnt, String val) {
            this.cnt = cnt;
            this.val = val;
        }

        @Override
        public String toString() {
            return val + " " + cnt;
        }
    }

    private static class TokenComparator implements Comparator<Token> {
        @Override
        public int compare(Token t1, Token t2) {
            return t2.cnt - t1.cnt;
        }
    }

}
