package kr.or.ddit.vo;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="att_no")
@NoArgsConstructor //마이바티스는 기본 생성자를 필요로 한다.
@ToString(exclude="file") //2진 데이터는 제외하고 문자열로
public class AttatchVO implements Serializable{
	private transient MultipartFile file;
	
	public AttatchVO(MultipartFile file) {
		super();
		this.file = file;
		this.att_filename = file.getOriginalFilename();
		this.att_savename = file.getUniqueSaveName();
		this.att_contenttype = file.getContentType();
		this.att_size = file.getFileSize();
				
	}
	private String att_contenttype;
	private Integer att_downcount;
	private String att_savename;
	private String att_filename;
	private Long att_size;
	private Integer bo_no;
	private Integer att_no;
	
	//미들 티어에 저장할 메서드
	public void saveTo(File saveFolder) throws IOException {
		file.transferTo(new File(saveFolder, att_savename));
	}
	
	
	
	
}
