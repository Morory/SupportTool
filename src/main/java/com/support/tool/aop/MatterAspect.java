package com.support.tool.aop;

import com.support.tool.domain.Matter;
import com.support.tool.util.IEmailUtil;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@AllArgsConstructor
public class MatterAspect {

    private final IEmailUtil emailUtil;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.support.tool.service.MatterServiceImpl.registerMatter(..))")
    public void sendEmailAfterRegisterMatter(JoinPoint joinPoint) {
        log.info("sendEmailAfterRegisterMatter called");

        Object[] args = joinPoint.getArgs();

        Matter matter = (Matter)args[1];

        emailUtil.sendEmail("bizformlee@gmail.com", "Support Tool: 새로운 안건이 등록되었습니다.",
                  "일시 : " + LocalDateTime.now() + "\n" +
                        "제목 : " + matter.getTitle() + "\n" +
                          "작성자 : " + matter.getUsername() + "\n" +
                          "내용 : " + matter.getContent() + "\n" +
                          "위와 같은 내용으로 새로운 안건이 등록되었으니 확인바랍니다. - SupportTool"
        );
    }
}
