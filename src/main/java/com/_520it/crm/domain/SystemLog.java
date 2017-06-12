package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter@ObjectProp("系统日志")
public class SystemLog {
    private Long id;
    @ObjectProp("操作者")
    private Employee opUser;
    @ObjectProp("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date opTime;
    @ObjectProp("操作IP")
    private String opIp;
    @ObjectProp("操作表达式")
    private String function;
    @ObjectProp("参数")
    private String params;

    @Override
    public String toString() {
        return "SystemLog{" +
                "id=" + id +
                ", opUser=" + opUser.getId() +
                ", opTime=" + opTime +
                ", opIp='" + opIp + '\'' +
                ", function='" + function + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
