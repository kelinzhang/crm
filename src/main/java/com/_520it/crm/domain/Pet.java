package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter
@ObjectProp("宠物")
public class Pet {
    public static final Integer GENNER_MALE = 1;
    public static final Integer GENNER_FEMALE = 2;
    public static final Integer GENNER_UNKONW= 0;
    private Long id;
    @ObjectProp("宠物名")
    private String petName;
    @ObjectProp("性别")
    private Integer sex;
    @ObjectProp("宠物生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @ObjectProp("血统")
    private String ancestry;
    @ObjectProp("毛色")
    private String color;
    @ObjectProp("图片")
    private String pic;
    @ObjectProp("品种")
    private PetBreed breed;
    @ObjectProp("会员")
    private Member member;
    public Member getMember1(){
        return member;
    }
    public Member getMember2(){
        return member;
    }

}
