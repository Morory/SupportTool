package com.support.tool.controller;

import com.support.tool.domain.AccountAdapter;
import com.support.tool.domain.Matter;
import com.support.tool.service.IMatterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matter")
@AllArgsConstructor
@Slf4j
public class MatterController {

    private final IMatterService matterService;

    // 안건 목록 정보 리턴
    @GetMapping("/list")
    public ResponseEntity<Page<Matter>> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("Matter List called");
        return matterService.getMatters(pageable);
    }

    // 안건 단건 조회 화면 출력
    @GetMapping("/show/{id}")
    public String show(@AuthenticationPrincipal AccountAdapter accountAdapter, Model model, @PathVariable int id) {
        log.info("Matter Show called");
        return matterService.getMatterWithAccount(accountAdapter, model, id);
    }

    // 안건 등록 화면 출력
    @GetMapping("/register")
    public String registerForm() {
        log.info("Matter RegisterForm called");
        return "matter/register";
    }

    // 안건 등록 수행
    @PostMapping("/register")
    public String register(@AuthenticationPrincipal AccountAdapter accountAdapter, Matter matter) {
        log.info("Matter Register called");
        return matterService.registerMatter(accountAdapter, matter);
    }

    @GetMapping("/modify/{id}")
    public String modifyForm(Model model, @PathVariable int id) {
        log.info("Matter ModifyForm called");
        matterService.getMatter(model, id);
        return "matter/modify";
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Void> modify(@PathVariable int id, @RequestBody Matter matter) {
        log.info("Matter Modify called");
        return matterService.modifyMatter(id, matter);
    }

    @GetMapping("/remove/{id}")
    public String removeForm(Model model, @PathVariable int id) {
        log.info("Matter RemoveForm called");
        matterService.getMatter(model, id);
        return "matter/remove";
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        log.info("Matter Remove called");
        return matterService.removeMatter(id);
    }
}
