package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQueryObject extends QueryObject {
	  private String keyword;
	  private String brandName;
	  private String kindName;
}
