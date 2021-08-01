package com.support.tool.service;

import com.support.tool.domain.AccountAdapter;
import com.support.tool.domain.Matter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface IMatterService {

    ResponseEntity<Page<Matter>> getMatters(Pageable pageable);

    String getMatterWithAccount(AccountAdapter accountAdapter, Model model, int id);

    void getMatter(Model model, int id);

    String registerMatter(AccountAdapter accountAdapter, Matter matter);

    ResponseEntity<Void> modifyMatter(int id, Matter matter);

    ResponseEntity<Void> removeMatter(int id);
}
