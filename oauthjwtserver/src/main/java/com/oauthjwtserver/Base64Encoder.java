package com.oauthjwtserver;

import org.apache.commons.codec.binary.Base64;

public class Base64Encoder {
	
	public static void main(String[] args) {
		String token=base64Encoder("admin-client"+":"+"admin-secret");
		System.out.println(token);
	}
		private static String base64Encoder(String message) {
			return new String(Base64.encodeBase64(message.getBytes()));
		}
		
	}


