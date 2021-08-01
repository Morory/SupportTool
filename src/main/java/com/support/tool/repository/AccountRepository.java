package com.support.tool.repository;

import com.support.tool.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

// 기본적인 crud (mebc) 는 jpa 가 들고있음
// jpa 를 상속하면 기본적으로 IoC됨
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // select * from user where username = ?
    Account findByUsername(String username);
}
