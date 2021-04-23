package kr.or.ddit.container.resource;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class ResourceLoadingDesc {

	public static void main(String[] args) throws IOException {
		Resource cpr = new ClassPathResource("log4j2.xml");// classpath이후의 경로
		System.out.println(cpr.getFile().exists());// true

		Resource fsr = new FileSystemResource("D:/contents/오래된 노래_utf8.txt");
		System.out.println(fsr.exists());// true

		UrlResource urlr = new UrlResource(
				"https://www.google.com/logos/doodles/2021/earth-day-2021-6753651837108909-vacta.gif");
		System.out.println(urlr.contentLength());// 320242

//		container자체가 resourceloader의 역할을 한다.
		ConfigurableApplicationContext container = new ClassPathXmlApplicationContext();
		cpr = container.getResource("classpath:log4j2.xml");
		System.out.println(cpr);
		
		fsr = container.getResource("file://D:/contents/오래된 노래_utf8.txt");
		System.out.println(fsr);
		
		urlr = (UrlResource)container.getResource(	"https://www.google.com/logos/doodles/2021/earth-day-2021-6753651837108909-vacta.gif");
		
	}
}
