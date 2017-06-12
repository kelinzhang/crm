package com._520it.crm.util;

import com._520it.crm.domain.SystemLog;
import com._520it.crm.service.ISystemLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SystemLogAspect {

    @Autowired
    private ISystemLogService systemLogService;

    //记录日志
    public void writeLog(JoinPoint joinPoint){
        try{
            //创建日志
            SystemLog log = new SystemLog();
            log.setOpUser(UserContext.getCurrentUser());
            log.setOpTime(new Date());
            //获取操作IP
            HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            String ip = request.getRemoteAddr();
            log.setOpIp(ip);
            //获取操作表达式
            String clasName = joinPoint.getTarget().getClass().getName();
            if("com._520it.crm.service.impl.SystemLogServiceImpl".equals(clasName)){
                return;
            }
            String function = clasName +":"+joinPoint.getSignature().getName();
            log.setFunction(function);
            //获取参数
            ObjectMapper mapper = new ObjectMapper();
            log.setParams(mapper.writeValueAsString(joinPoint.getArgs()));
            systemLogService.insert(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
