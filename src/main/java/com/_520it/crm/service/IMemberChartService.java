package com._520it.crm.service;


import com._520it.crm.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IMemberChartService {

    List<MemberVO> memberChartByLine();
}
