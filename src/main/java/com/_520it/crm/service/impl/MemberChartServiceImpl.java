package com._520it.crm.service.impl;


import com._520it.crm.mapper.MemberChartMapper;
import com._520it.crm.service.IMemberChartService;
import com._520it.crm.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberChartServiceImpl implements IMemberChartService
{
    @Autowired
    private MemberChartMapper memberChartMapper;
    @Override
    public List<MemberVO> memberChartByLine() {
        return memberChartMapper.memberChartByLine();
    }
}
