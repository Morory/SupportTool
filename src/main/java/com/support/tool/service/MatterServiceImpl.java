package com.support.tool.service;

import com.support.tool.domain.Account;
import com.support.tool.domain.AccountAdapter;
import com.support.tool.domain.Matter;
import com.support.tool.repository.MatterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MatterServiceImpl implements IMatterService {

    private final MatterRepository matterRepository;

    @Override
    public ResponseEntity<Page<Matter>> getMatters(Pageable pageable) {
        Page<Matter> matters = matterRepository.findAllByStatus(pageable, 'Y');
        return new ResponseEntity<>(matters, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public String getMatterWithAccount(AccountAdapter accountAdapter, Model model, int id) {
        Account writer;
        Account viewer;
        boolean isAdmin  = false;
        boolean isWriter = false;
        Optional<Matter> optionalMatter = matterRepository.findById(id);
        if(optionalMatter.isPresent()) {
            Matter matter = optionalMatter.get();
            writer = matter.getAccount();
            viewer = accountAdapter.getAccount();

            if(viewer.getId() == writer.getId()) {
                isWriter = true;
            }
            if("ROLE_ADMIN".equals(viewer.getRole())) {
                isAdmin = true;
            }

            model.addAttribute("matter", matter);
            model.addAttribute("isWriter", isWriter);
            model.addAttribute("isAdmin", isAdmin);
        }
        return "matter/show";
    }

    @Override
    public void getMatter(Model model, int id) {
        Optional<Matter> optionalMatter = matterRepository.findById(id);
        if(optionalMatter.isPresent()) {
            Matter matter = optionalMatter.get();
            model.addAttribute("matter", matter);
        }

    }

    @Override
    @Transactional
    public String registerMatter(AccountAdapter accountAdapter, Matter matter) {
        Account account = accountAdapter.getAccount();
        matter.setAccount(account);
        matter.setUsername(account.getUsername());
        matterRepository.save(matter);
        return "redirect:/";
    }

    @Override
    @Transactional
    public ResponseEntity<Void> modifyMatter(int id, Matter matter) {
        Optional<Matter> optionalMatter = matterRepository.findById(id);
        if(!optionalMatter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Matter origin = optionalMatter.get();
        if(matter.getTitle() != null) {
            origin.setTitle(matter.getTitle());
        }
        if(matter.getContent() != null) {
            origin.setContent(matter.getContent());
        }
        origin.setCompleteStatus(matter.getCompleteStatus());
        matterRepository.save(origin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> removeMatter(int id) {
        Optional<Matter> optionalMatter = matterRepository.findById(id);
        if(!optionalMatter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Matter matter = optionalMatter.get();
        matter.setStatus('N');
        matterRepository.save(matter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
