package kr.or.ddit.container.various;

import java.io.File;

import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VariousDIVO {

	private String str;
	private int number;
	private double dbNumber;
	private char ch;
	private boolean boolData;
	
//	파일시스템 리소스로 제한된다
//	private File file;
	private Resource cpr;
	private Resource fsr;
	private Resource urlr;
	
}
