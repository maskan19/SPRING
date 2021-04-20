package kr.or.ddit.mvc.filter.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 파트를 래핑한다
 */
public class MultipartFile {

	private Part adaptee;
	private String originalFilename;
	private String uniqueSaveName;
	private boolean empty;

	public MultipartFile(Part adaptee) {
		super();
		this.adaptee = adaptee;
		String disposition = adaptee.getHeader("Content-Disposition");
		int idx = disposition.indexOf("\"", disposition.indexOf("filename="));// filename= 이후부터 \를 찾아라
		if (idx != -1) {
			originalFilename = disposition.substring(idx).replace("\"", "");
		}

		empty = StringUtils.isBlank(originalFilename);// 원본파일의 존재 여부

		this.uniqueSaveName = UUID.randomUUID().toString();
	}

	/**
	 * dest의 이름 체계 그대로 저장
	 * 
	 * @param dest 경로
	 * @throws IOException
	 */
	public void transferTo(File dest) throws IOException {
		this.uniqueSaveName = dest.getName();
		adaptee.write(dest.getAbsolutePath());
	}

	/**
	 * 
	 * @return 현재 마임의 타입
	 */
	public String getContentType() {
		return adaptee.getContentType();
	}

	/**
	 * 
	 * @return Part의 크기
	 */
	public long getFileSize() {
		return adaptee.getSize();
	}

	public String getOriginalFilename() {// getter
		return originalFilename;
	}

	/**
	 * 
	 * @return 파트의 이름
	 */
	public String getName() {
		return adaptee.getName();
	}

	public String getUniqueSaveName() {
		return uniqueSaveName;
	}

	public InputStream getInputStream() throws IOException {
		return adaptee.getInputStream();
	}

	/**
	 * 
	 * @param saveFolder saveFolder에 uniqueSaveName으로 저장
	 * 
	 */
	public void saveTo(File saveFolder) throws IOException {
		File saveFile = new File(saveFolder, uniqueSaveName);
		adaptee.write(saveFile.getAbsolutePath());// 파일의 경로를 포함한 전체 quailifiedName을 주어야함
	}

//	/**
//	 * 
//	 * @param saveFolder saveFolder에 uniqueSaveName으로 저장
//	 * 
//	 */
//	public void deleteAt(File saveFolder, String fileName) throws IOException {
//		File deleteFile = new File(saveFolder, fileName);
//		System.out.println("multipart");
//		deleteFile.delete();
//	}

	public boolean isEmpty() {
		return empty;
	}

	public byte[] getBytes() throws IOException {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream(); InputStream is = getInputStream();) {
			IOUtils.copy(is, os);
			// IOUtils.toByteArray(is);
			return os.toByteArray();
		}
	}

}
