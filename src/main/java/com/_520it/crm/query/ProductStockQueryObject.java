package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductStockQueryObject extends QueryObject {
    private String proSn;
    private String proName;
    private String kind;
    private String brand;
    private String supplier;

    public String getKind(){
        return this.kind;
    }

    @Override
    public String toString() {
        return "ProductStockQueryObject{" +
                "proSn=" + proSn +
                ", proName=" + proName +
                ", kind=" + kind +
                ", brand=" + brand +
                ", supplier=" + supplier +
                '}';
    }
}
