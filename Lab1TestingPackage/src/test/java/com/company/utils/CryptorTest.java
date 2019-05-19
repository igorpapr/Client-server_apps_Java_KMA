package com.company.utils;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class CryptorTest {
    @Test
    public void checkEncr_Decr(){
        String s = "Hello WORLD, KMA";
        byte[] encrypted = Cryptor.encrypt(s.getBytes(StandardCharsets.UTF_16BE));
        byte[] decrypted = Cryptor.decrypt(encrypted);
        Assert.assertEquals("Hello WORLD, KMA", new String(decrypted,StandardCharsets.UTF_16BE));
        Assert.assertNotNull(decrypted);
        Assert.assertNotEquals(0, decrypted.length);
    }
}
