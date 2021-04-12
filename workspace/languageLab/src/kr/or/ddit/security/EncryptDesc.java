package kr.or.ddit.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * encoding(부호화) : 저장이나 전송을 위해 매체가 인지할 수 있는 방식으로 데이터의 표현방식을 바꾸는 작업
 * UrlEncoding(Percent encoding), Base64
 * 데이터 변환이 주 목적.
 * 
 * encrypting(암호화) : 허가되지 않은 사용자(key의 소유 여부로 구분)의 데이터 접근을 막기 위해 데이터를 변환하는 작업
 * 단방향 암호화(해시함수) : 복호화 불가능한 암호화{@link MessageDigest} 
 * 		SHA-512(암호문의 길이가 64Byte)
 * 			2^512가지 경우의 수를 표현할 수 있음 -> 512가지 이상의 경우를 주입하면 다른 값이 같은 키를 가져 충돌한다.
 * 			** 비둘기 집의 원리
 * 		해시키 : 입력데이터의 길이는 다양하지만 출력되는 데이터의 길이는 모두 동일한 구조.
 * 
 * 양방향 암호화 : 키를 소유한 경우 복호화 가능한 암호화(전송 데이터에 주로 사용됨. 전송할 때 암호화 받을 때 복호화)
 * 		Cipher
 * 		대칭키 암호화 방식 : 하나의 동일 키(비밀키)로 암복호화를 수행
 * 			AES-128(16byte), AES-256(32byte) : 괄호는 키의 길이
 * 		비대칭키 암호화 방식 : 한 쌍의 키(개인키/공개키)를 통해 암복호화 수행
 * 			- 대칭키는 키가 데이터와 함께 전송되는 문제가 있어 키를 보내지 않기 위해 한 쌍으로 만들어버림
 * 			개인키(전자 서명)와 공개키가 한 쌍이 되어 암호문이 개인키로 식별될 수 있음(누가 썻는지 알 수 있다)
 * 			단점 : 서버에 부하가 걸림(복잡해서 시간이 오래 걸림). 용량이 큰 경우 대칭키 암호화 방식이 선호된다.
 * 
 * https는 대칭키와 비대칭키를 모두 사용 
 * 
 */
public class EncryptDesc {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
		String plain = "월요일 싫어";
		Cipher cipher = Cipher.getInstance("RSA");
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");//한 쌍의 키를 만들어냄
		keyPairGen.initialize(2048);//키의 길이 설정
		KeyPair keyPair = keyPairGen.generateKeyPair();

		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);//개인키로 암호화
		
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.encodeBase64String(encrypted);
		System.out.println(encoded);
		//KwjexVc6dasu1W+VaQmSssg+7TT8zFLeIIlnwKOocAouNwFJunpklYDYjTMhRSGdAyhxUjr6OkfURfoogozP7vMPPFJjKHNfSv5yPGuN0GRfyMieyL0JO6I+CEGCjeS/cHLovrkp5MmlJwKTFtfjflD0Ur590lNH6tfT/9Pzro09W7OtaXdAFTS6QxWc9HKozhRM/PR9Sh60VUEmaJVPOeQZ//YD+zAr8p+K9KaEE7gfwjnFigsX6LUhuFyHvltJiGGumfqlrSdEkzJt9n7TwXmJ1IMSLWn0y1KJfffooJxlZQZIE+gaqiN4XJmNPTWYFLUBINhVEBL64GHcXctkeA==
		//em1qSmiWS/FthoWNMbtLsJ8JW2+X5YfO99yrjHuVApa3lclb2NUm6iQ/UhyE7+rox0NSVK0VDNbCRP3ybyasAfLuVMG4/le2xzK5I1tasQJ2/kZGkZbAWsAVOc/RWzNO49lIPXPjqkx6buFs2J8yz3RyPAlGFZsmfYfH3skIdKXRDHPVafIaFW+50CGgsnMudv6yIPdCLuRATV7USWSRTQSN4UzUeKwTy4Z3SwrDOW8hI98KLDIlxTDmwAIGKmrCRuW8YTesv4DVhnBoDyoFetN55sw1yyqQP4MWQi1F7X3gk1Y6OSaTiT7w285emi3EqXG6FJiosmnBKaZQgQwyYA==
//		실행시마다 다른 결과
		
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] decoded = Base64.decodeBase64(encoded);
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.println(new String (decrypted));//월요일 싫어
		
		
		
		
	}
	
	
	
	
	public static void encodeTest(String plain) throws IOException  {
//		1. UrlEncoding(Percent encoding)  : 프로그램이 이해할 수 있는 방식으로바꾸고 
		String encoded = URLEncoder.encode(plain, "utf-8");// UnsupportedEncodingException
		System.out.println(encoded);// %EC%9B%94%EC%9A%94%EC%9D%BC+%EC%8B%AB%EC%96%B4

		String decoded = URLDecoder.decode(encoded, "utf-8");
		System.out.println(decoded);// 월요일 싫어

//		2. Base64
		byte[] binary = plain.getBytes();// 바이트 배열로 변환
		encoded = Base64.encodeBase64String(binary);// 바이트 배열을 스트링으로 변환
		System.out.println(encoded);// 7JuU7JqU7J28IOyLq+yWtA==
		byte[] decodedBinary = Base64.decodeBase64(encoded);
		System.out.println(new String(decodedBinary));// 월요일 싫어
		
	}
	
	public static String encryptSha152(String plain) throws NoSuchAlgorithmException {
		//단방향 암호화(messageDigest)
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] input = plain.getBytes();
		byte[] encrypted = md.digest(input);
		System.out.println(encrypted.length *8);//512
		
		String encoded = Base64.encodeBase64String(encrypted);
		System.out.println(encoded);//lvqZjMYPg133lZ2tVgfk547GKndTZJsxmeIQ+XlTjocGbLpN9uZdTTS2mDjVHQ1BrFa8tEmcm87rNd41oH22Sg==
//		String savedPass= "lvqZjMYPg133lZ2tVgfk547GKndTZJsxmeIQ+XlTjocGbLpN9uZdTTS2mDjVHQ1BrFa8tEmcm87rNd41oH22Sg==";
		//복호화가 불가능하다
//		System.out.println(savedPass.equals(encoded)?"인증 성공" : "인증 실패");//인증 성공
		
		return encoded; 
	}
	
	//대칭키 AES
	public static void aesEncryptTest(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, KeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] iv = new byte[128/8];
//		System.out.println(Arrays.toString(iv));
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");//난수를 발생시킬 객체를 만듬
		//PKCS11 : JDK가 지원하지 않음
		random.nextBytes(iv);//강제로 데이터를 넣음
		
//		System.out.println(Arrays.toString(iv));
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");//AES방식으로 key를 생성
		
		keyGen.init(128);//키의 길이는 128비트로 제한
		SecretKey key = keyGen.generateKey();
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//타입 안정성이 떨어짐 /NoPadding : 패딩 입력 안 함
	//암호화인지 복호화인지를 결정
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);//ivSpec을 사용해 암호화
//		InvalidKeyException: 키의 길이가 맞지 않을 때 발생할 수 있음
		byte[]input = plain.getBytes();
		byte[] encrypted =  cipher.doFinal(input); //IllegalBlockSizeException 마지막 블럭의 사이즈가 다른 경우
		String encoded = Base64.encodeBase64String(encrypted);
		System.out.println(encoded);
		//hyKeZbZMJYfJHolQcmrQbytX0FExb5+RpK+HQAV040k=
		//U8xfnHJEzA8dvpmmDL/OJaNIyh26A7JZTjoJQEscIPY=
		//계속해서 새 키를 발급받음
		
		//디코딩>복호화>문자열
		byte[] decoded = Base64.decodeBase64(encoded);//encrypted와 동일
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.println(new String(decrypted));//CBC모드이기 때문에 초기화벡터(initialization vector)가 필요

	}
	
	
	
}
