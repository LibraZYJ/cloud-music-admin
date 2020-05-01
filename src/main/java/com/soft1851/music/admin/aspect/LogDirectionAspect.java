package com.soft1851.music.admin.aspect;

import com.soft1851.music.admin.annotation.LogDirection;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Yujie_Zhao
 * @Date: 2020/4/30 20:41
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class LogDirectionAspect {

    @Pointcut("@annotation(logDirection)")
    public void doLogDirection(LogDirection logDirection){

    }

    @Around(value = "doLogDirection(logDirection)",argNames = "pjp,logDirection")
    public Object doBefore(ProceedingJoinPoint pjp,LogDirection logDirection) throws  Throwable{
        if (!"".equals(logDirection.direction())) {
            log.info(logDirection.direction()+"接口被调用");
            return  pjp.proceed();
        }
        return "接口信息不规范";
    }
}
