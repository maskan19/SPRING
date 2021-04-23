package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validator.InsertGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="buyer_id")
@ToString(of = {"buyer_id","buyer_name","buyer_lgu"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyerVO implements Serializable{
	private int rnum;
	private String buyer_id;
	private String buyer_name;
	private String buyer_lgu;
	private String buyer_bank;
	private String buyer_bankno;
	private String buyer_bankname;
	private String buyer_zip;
	private String buyer_add1;
	private String buyer_add2;
	private String buyer_comtel;
	private String buyer_fax;
	private String buyer_mail;
	private String buyer_charger;
	private String buyer_telext;
	
	@NotBlank(groups=InsertGroup.class)
	private String buyer_img;
	
	private String lprod_nm;
	
}
