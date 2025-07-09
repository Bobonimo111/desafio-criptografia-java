package com.william.criptografia.service;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Value;

@Component
public class CriptografadorAES {


    private SecretKey SecretKey;

    private Cipher cipher;

    // Não entendo muito bem oq ta rolando mais ta bonito
    CriptografadorAES(@Value("${private-key}")String privatKeyString) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        this.SecretKey = new SecretKeySpec(privatKeyString.getBytes(), "AES");
    }

    public byte[] encriptyMessage(String text) throws InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException,
            UnsupportedEncodingException {
        this.cipher.init(Cipher.ENCRYPT_MODE, this.SecretKey);
        byte[] encriptyMessage = this.cipher.doFinal(text.getBytes());
        System.out.println("Bytes da mensagem : "+ encriptyMessage);
        return encriptyMessage;
    }

    public String decryptMessage(String text) throws Exception {
        this.cipher.init(Cipher.DECRYPT_MODE, this.SecretKey);
        byte[] decryptMessage = this.cipher.doFinal(text.getBytes());
        return new String(decryptMessage,"UTF-8");
    }
}
