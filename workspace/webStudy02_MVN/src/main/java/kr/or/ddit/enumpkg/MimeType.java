package kr.or.ddit.enumpkg;

import javax.activation.MimetypesFileTypeMap;

public enum MimeType {
	JSON("application/json;charset=utf-8"), XML("application/xml;charset=utf-8"), PLAIN("text/plain;charset=utf-8"),
	HTML("text/html;charset=utf-8");

	private String mime;

	public String getMime() {
		return mime;
	}

	private MimeType(String mime) {
		this.mime = mime;
	}
	
	public static MimeType searchMimeType(String accept) {
		accept = accept.toUpperCase();
		MimeType searched = HTML;
		for( MimeType tmp: values()) {
			if(accept.contains(tmp.name())) {
				searched = tmp;
				break;
			}
		}return searched;
	}
	public static String getMimeText(String accept) {
		
		return searchMimeType(accept).getMime();
		
	}
}
