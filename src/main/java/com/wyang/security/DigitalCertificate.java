package com.wyang.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

public class DigitalCertificate {

	public static void main(String[] args) throws Exception {
		
		String pass = "keystore";
		FileInputStream in2 = new FileInputStream("C:/BocsoftKeyLib");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(in2,  pass.toCharArray());
		String alias = "TestCertification";
		Certificate c = ks.getCertificate(alias);
		System.out.println("Certification info: " + c.toString());
		
		X509Certificate t = (X509Certificate) c;
		System.out.println("Version: " + t.getVersion());
		System.out.println("Serial: " + t.getSerialNumber().toString(16));
		System.out.println("Entity: " + t.getSubjectDN());
		System.out.println("Signer: " + t.getIssuerDN());
		System.out.println("Expire: " + t.getNotBefore());
		System.out.println("Sinature Algorithm: " + t.getSigAlgName());
		
		byte[] sig = t.getSignature();
		java.security.PublicKey pk = t.getPublicKey();
		byte[] pkenc = pk.getEncoded();
		System.out.println("Public Key: ");
		for(int i=0;i<pkenc.length;i++){  
            System.out.print(pkenc[i]+",");     
        }  
        System.err.println(); 
        
        Date TimeNow=new Date();        
        t.checkValidity(TimeNow);     
        System.out.println(""); 
        
	}

}
