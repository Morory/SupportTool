package com.support.tool.service;

import com.support.tool.domain.Account;
import com.support.tool.domain.AccountAdapter;
import com.support.tool.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService, UserDetailsService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final HttpServletRequest request;

    @Override
    public String join(Account account) {
        account.setRole("ROLE_USER");
        String rawPassword = account.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        account.setPassword(encPassword);
        accountRepository.save(account);
        return "redirect:/loginForm";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException(username);
        }
        request.getSession().setAttribute("username", username);
        return new AccountAdapter(account);
    }
}
