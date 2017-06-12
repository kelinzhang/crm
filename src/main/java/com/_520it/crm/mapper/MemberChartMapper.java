package com._520it.crm.mapper;


import com._520it.crm.domain.Member;
import com._520it.crm.vo.MemberVO;

import java.util.List;

public interface MemberChartMapper {


    List<MemberVO> memberChartByLine();
}
