package com.support.tool.controller;

import com.support.tool.domain.Account;
import com.support.tool.service.IAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class AccountController {

    private final IAccountService accountService;

    // 메인 화면 출력
    @GetMapping("/")
    public String index() {
        log.info("Index called");
        return "account/index";
    }

    // 로그인 화면 출력
    @GetMapping("/loginForm")
    public String loginForm() {
        log.info("LoginForm called");
        return "account/login";
    }

    // 회원가입 화면 출력
    @GetMapping("/joinForm")
    public String joinForm() {
        log.info("JoinForm called");
        return "account/join";
    }

    // 회원가입 수행
    @PostMapping("/join")
    public String join(Account user) {
        log.info("Join called");
        return accountService.join(user);
    }
}
