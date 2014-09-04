package com.wyang.security;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class MessageDigestExample {

	public static void main(String[] args) throws Exception {
		String beforeDigest = "asdf";;
		System.out.println("Before digest: " + beforeDigest);
		
		byte[] plainText = beforeDigest.getBytes("UTF8");
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainText);
		
		byte[] afterDigest = messageDigest.digest();
		System.out.println("After digest: " + Hex.encodeHexString(afterDigest));
	}
	
}
	