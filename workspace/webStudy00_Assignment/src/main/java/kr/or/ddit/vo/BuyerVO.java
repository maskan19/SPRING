package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(of="buyer_id")
public class BuyerVO implements Serializable{
	@NotBlank
	private String buyer_id;
	@NotBlank
	private String buyer_name;
	@NotBlank
	private String buyer_lgu;
	private String buyer_bank;
	private String buyer_bankno;
	private String buyer_bankname;
	private String buyer_zip;
	private String buyer_add1;
	private String buyer_add2;
	@NotBlank
	private String buyer_comtel;
	@NotBlank
	private String buyer_fax;
	@NotBlank
	private String buyer_mail;
	private String buyer_charger;
	private String buyer_telext;
	
}
