package com.casetext.fnv;

import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class FNVTest {

    @org.junit.Test
    public void test() throws Exception {

        String allGoodMen = "Now is the time for all good men to come to the aid of the country.";
        String declaration = "the 78\nand 56\nfor 28\nour 26\ntheir 20\nhas 20\nthem 14\nthat 12\nthese 12\npeople 10\nwhich 10\nhave 10\nall 10\nstates 8\nwith 8\nlaws 8\nare 8\nhis 8\nthey 6\nfrom 6\ngovernment 6\nright 6\nsuch 6\namong 4\npowers 4\nshould 4\nnew 4\nmost 4\nbeen 4\ncolonies 4\nassent 4\nlarge 4\ntime 4\nindependent 4\nfree 4\ncongress 2\ndeclaration 2\nunited 2\namerica 2\nwhen 2\nbecomes 2\nnecessary 2\npolitical 2\nequal 2\nnature 2\nmankind 2\ndeclare 2\ncauses 2\nseparation 2\nhold 2\nmen 2\nrights 2\nhappiness 2\ngovernments 2\nconsent 2\nany 2\nform 2\nalter 2\nits 2\nwill 2\nlong 2\nthemselves 2\nabolishing 2\nforms 2\nusurpations 2\nsame 2\nobject 2\nabsolute 2\noff 2\nnecessity 2\nhistory 2\ngreat 2\nbritain 2\nrepeated 2\ntyranny 2\nover 2\nthis 2\nworld 2\nrefused 2\npublic 2\ngood 2\npass 2\nunless 2\nsuspended 2\nother 2\nwould 2\nlegislature 2\nonly 2\nlegislative 2\nbodies 2\npurpose 2\ninto 2\ndissolved 2\nothers 2\nstate 2\nwithout 2\nendeavoured 2\nrefusing 2\nhither 2\nconditions 2\njustice 2\nestablishing 2\noffices 2\nout 2\npeace 2\narmies 2\nlegislatures 2\nrender 2\npower 2\njurisdiction 2\nforeign 2\nacts 2\npretended 2\ntrial 2\ninhabitants 2\ncases 2\ntransporting 2\nseas 2\nrule 2\ndeclaring 2\nhere 2\nprotection 2\nwar 2\nagainst 2\nlives 2\ncircumstances 2\nages 2\ntotally 2\nfriends 2\nbrethren 2\nwhose 2\nevery 2\nmay 2\ntherefore 2\nought 2";

        assertEquals("'' should be 'bGInLge7AUJiuCF1YpXFjQ'", "bGInLge7AUJiuCF1YpXFjQ", FNV.hash(""));
        assertEquals("'a' should be '0ijLaW8ajK94kStwTkqJZA'", "0ijLaW8ajK94kStwTkqJZA", FNV.hash("a"));
        assertEquals("'aa' should be 'CICVRLqrG-laoHMwVbaZJw'", "CICVRLqrG-laoHMwVbaZJw", FNV.hash("aa"));
        assertEquals("'hello world' should be 'bBVXmf3I7sS5FSOAjncmtw'", "bBVXmf3I7sS5FSOAjncmtw", FNV.hash("hello world"));
        assertEquals("allGoodMen should be 'UbOOWhy3VrMOicJCTfUw8w'", "UbOOWhy3VrMOicJCTfUw8w", FNV.hash(allGoodMen));
        assertEquals("Declaration should be 'jqTeG2smiMPNKGsxmksCqA'", "jqTeG2smiMPNKGsxmksCqA", FNV.hash(declaration));

    }
}