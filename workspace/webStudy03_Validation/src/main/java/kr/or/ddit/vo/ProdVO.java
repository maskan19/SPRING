package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validator.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@EqualsAndHashCode(of="prod_id")
@ToString(of= {"prod_id", "prod_name", "prod_lgu"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdVO implements Serializable {
	private int rnum;
	@NotBlank(groups=UpdateGroup.class)
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String lprod_nm;
	private String prod_buyer;
	@NotNull
	@Min(0)
	private Integer prod_cost;
	@Min(0)
	private Integer prod_price;
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Integer prod_totalstock;
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	
	private BuyerVO buyer; // has a(1:1) 관계
	
}







