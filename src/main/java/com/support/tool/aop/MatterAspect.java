package com.support.tool.aop;

import com.support.tool.util.IEmailUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class MatterAspect {

    private final IEmailUtil emailUtil;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.support.tool.service.MatterServiceImpl.registerMatter(..))")
    public void sendEmailAfterRegisterMatter() {
        log.info("sendEmailAfterRegisterMatter called");

        emailUtil.sendEmail("gusdn6082@gmail.com", "Support Tool: 새로운 안건이 등록되었습니다.", "내용 : ");
    }
}
