package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class AppTest {
    public AppTest() {
    }

    @Test
    public void testApp() {
        assertTrue(true);
    }

    @Test
    public void testMore() {
        assertTrue(true);
    }

    @Test
    public void encryptDecrypt() {
        try {
            // Add BouncyCastle as a security provider if not already present
            if (java.security.Security.getProvider("BC") == null) {
                java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            }

            String input = "Hello, BouncyCastle!";
            byte[] inputBytes = input.getBytes("UTF-8");

            // Generate AES key
            javax.crypto.KeyGenerator keyGen = javax.crypto.KeyGenerator.getInstance("AES", "BC");
            keyGen.init(128);
            javax.crypto.SecretKey key = keyGen.generateKey();

            // Generate IV
            byte[] iv = new byte[16];
            new java.security.SecureRandom().nextBytes(iv);
            javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(iv);

            // Encrypt
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] encrypted = cipher.doFinal(inputBytes);

            // Decrypt
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decrypted = cipher.doFinal(encrypted);
            String output = new String(decrypted, "UTF-8");

            assertTrue(input.equals(output));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Exception during encryption/decryption: " + e.getMessage(), false);
        }
    }
}
