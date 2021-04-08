package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import kr.or.ddit.annotation.FirstAnnotation;

/**
 * 스트림 : 연속성을 가진 일련의 데이터 흐름이면서 데이터의 전송 단방향 통로
 * 
 * 스트림의 종류
 * 1. 전송 데이터 크기
 * 		1) byte stream(1 byte) : XXXInputStream / XXXOutputStream
 * 			ex) FileInputStream / FileOutputStream (1차)
 * 				  SocketInputStream / SocketOutputStream
 * 				  ByteInputStream / ByteOutputStream - 인메모리
 * 
 * 		2) character stream(1 char) : XXXReader / XXXWriter
 * 			ex) FileReader / FileWriter (1차)
 * 				   StringReader / StringWriter -인메모리
 * 2. Stream 생성 방법
 * 		1) 1차 stream(단일형 스트림) : 매체를 대상으로 직접 생성(개방)하는 스트림
 * 		2) 2차 stream(연결형 스트림) : 1차 스트림을 대상으로 연결하는 보조형 스트림
 * 			1차 스트림이 만들어진 후 생성 가능. 속도 개선 / 파일 필터링 등의 목적으로 사용
 * 			ex) BufferedStream - BufferedReader 
 * 					filteredStream - DataInputStream
 * 					Object stream - ObjectInputStream (Only Serializable 인터페이스를 임플먼트 하고 있는 객체를 대상으로 직렬화 / 역직렬화)
 * 		직렬화(Serialization) : 객체를 전송하거나 기록하기 위해 바이트 배열의 형태로 변환하는 작업 
 * 		역직렬화(DeSerialization) : 기록되어있거나 전송된 바이트 배열로부터 원래 객체를 복원하는 작업
 * 
 * 		** 매체로부터 데이터를 읽어들이는 단계
 * 		1.  매체를 어플리케이션 내에서 핸들링할 수 있는 객체로 생성
 * 			ex) new File(file system path), new ServerSocket(port)
 * 		2. 읽어들이기 위한 스트림 생성
 * 			new FileInputStream(file), socket.getInputStream()
 * 			new InputStreamReader()(2차. 어댑터의 역활. 데이터를 변환해준다.)
 * 			new BufferedReader()
 * 		3. stream end 까지 읽기 반복(EOF, -1, null)
 * 		4. 자원 release : close()
 * 
 */
@FirstAnnotation(value = "stream", number=2)
public class StreamDesc {

	public static void main(String[] args) throws IOException {
//		
//		String path = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
//		File folder = new File("d:/contents");
//		File writeFile = new File(folder, "googlelogo_color_272x92dp.png");
//		
//		URL url = new URL(path);
//		System.out.println(url);
//		URLConnection conn = url.openConnection(); //소켓을 개방
//		
//		try(
//		InputStream is = conn.getInputStream(); //is.read()는 1바이트씩 읽음
//		FileOutputStream fos = new FileOutputStream(writeFile);
//		){
//			byte[] buffer = new byte[1024];
//			int cnt = -1;
//			while ((cnt = is.read(buffer)) != -1) {
//				fos.write(buffer, 0, cnt);
//			}
//		}
		
		readClassPathResource();
	}
	
	
	
	private static void readFileSystemResource() {
		StreamDesc de = new StreamDesc();
//		d:/contents/오래된 노래_utf8.txt
		File folder = new File("d:/contents");
		File readFile = new File(folder, "오래된 노래.txt"); //VM은 기본 인코딩을 utf-8로 사용한다. 
		String temp = null;
//		try(Closable interface의 객체 생성){} catch{} finally{} - 1.7 이후의 버전만 사용 가능
		try (
//		FileReader reader = new FileReader(readFile);
				FileInputStream fis = new FileInputStream(readFile); // byte로 읽어들임
				InputStreamReader reader = new InputStreamReader(fis, "MS949"); // byte를 char로 변환
				BufferedReader br = new BufferedReader(reader);// char
		) {
			while ((temp = br.readLine()) != null) {
				System.out.println(temp);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	private static void readClassPathResource() {
		String qualifiedName = "/kr/or/ddit/io/another day.txt";//   앞에 / 가 있어야 전체  클래스 패스부터 찾는다.
//	ClassLoader loader = Thread.currentThread().getContextClassLoader(); //자신의 취가 없으므로 클래스패스에서 모두 찾음 
//	URL url = loader.getResource(qualifiedName);
	URL url = StreamDesc.class.getResource(qualifiedName); //type자체. 현재 파일의 경로부터 시작함
	
	
	System.out.println(url);
	String temp = null;
	try (
			InputStream is = StreamDesc.class.getResourceAsStream(qualifiedName);//1차. 
//			FileReader reader = new FileReader(readFile);
//			FileInputStream fis = new FileInputStream(is); // byte로 읽어들임
			InputStreamReader reader = new InputStreamReader(is); // byte를 char로 변환
			BufferedReader br = new BufferedReader(reader);// char
			) {
				while ((temp = br.readLine()) != null) {
					System.out.println(temp);
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
	}
	
	
	
}
