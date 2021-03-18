package kr.or.ddit.enumpkg;

public enum MimeType {
	JSON("application/json;charset=utf-8"), 
	XML("application/xml;charset=utf-8"), 
	PLAIN("text/plain;charset=utf-8"), 
	HTML("text/html;charset=utf-8");
	
	private MimeType(String mime) {
		this.mime = mime;
	}
	
	private String mime;
	public String getMime() {
		return mime;
	}
	
	// 상수값을 찾음
	public static MimeType searchMimeType(String accept) {
		accept = accept.toUpperCase();
		MimeType searched = HTML;
		for(MimeType tmp : values()) {
			if(accept.contains(tmp.name())) {
				searched = tmp;
				break;
			}
		}
		return searched;
	}
	// Mime 텍스트 자체를 찾고
	public static String getMimeText(String accept) {		
		return searchMimeType(accept).getMime();
	}
	
}
