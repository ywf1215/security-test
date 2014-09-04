package com.wyang.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignatureException;

import org.apache.commons.codec.binary.Hex;

public class DigitalSignature {

	public static void main(String[] args) throws Exception {
		
		String before = "asdf";
		byte[] plainText = before.getBytes("UTf8");
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();
		
		Signature sig = Signature.getInstance("SHA1WithRSA");
		sig.initSign(key.getPrivate());
		sig.update(plainText);
		byte[] signature = sig.sign();
		
		String after1 = Hex.encodeHexString(signature);
		System.out.println("after signed by private key: " + after1);
		
		sig.initVerify(key.getPublic());
		sig.update(plainText);
		try {
			if (sig.verify(signature)) {
				System.out.println("signature correct");
			} else {
				System.out.println("signature failure");
			}
		} catch (SignatureException e) {
			System.out.println("signature failure");
		}
	}

}
