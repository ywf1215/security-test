package com.wyang.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Hex;

public class PrivateKey {

	public static void main(String[] args) throws Exception {

		String before = "asdf";
		byte[] plainText = before.getBytes("UTF-8");
		
		System.out.println("Start to generate AES key.");
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		Key key = keyGen.generateKey();
		System.out.println("Finish generating AES key." + key);
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		System.out.println("encrypt with private key");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] cipherText = cipher.doFinal(plainText);
		
		String after1 = Hex.encodeHexString(cipherText);
		System.out.println("encrypted by private key: " + after1);
		
		System.out.println("decrypted by private key...");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] newPlainText = cipher.doFinal(cipherText);
		
		String after2 = new String(newPlainText, "UTF8");
		System.out.println("decrypted by key: " + after2);
		
	}

}
