package com._520it.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class CashRecordQueryObject extends QueryObject {
    private String number;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

   @Override
    public String toString() {
        return "CashRecordQueryObject{" +
                "number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
