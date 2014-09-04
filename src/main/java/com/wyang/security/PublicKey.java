package com.wyang.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;

public class PublicKey {

	public static void main(String[] args) throws Exception {
		
		String before = "asdf";
		byte[] palinText = before.getBytes("UTF8");
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		System.out.println("Encrypted by public key...");
		cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
		
		byte[] cipherText = cipher.doFinal(palinText);
		String after1 = Hex.encodeHexString(cipherText);
		System.out.println("Encrypted by public key" + after1);
		
		System.out.println("Decrypted by private key...");
		cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
		byte[] newPlainText = cipher.doFinal(cipherText);
		
		String after2 = new String(newPlainText, "UTF8");
		System.out.println("Decrypted by private key: " + after2);
	}

}
