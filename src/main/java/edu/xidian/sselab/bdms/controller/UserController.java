package edu.xidian.sselab.bdms.controller;

import edu.xidian.sselab.bdms.domain.User;
import edu.xidian.sselab.bdms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    UserRepository repository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @GetMapping("")
    public Page<User> queryByPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return repository.findAll(new PageRequest(page, size));
    }
    
    @PutMapping("")
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
    
    @PostMapping("/{uid}")
    public User edit(@PathVariable Long uid, User user) {
        User mergeUser = repository.findOne(uid);
        mergeUser.setUsername(user.getUsername());
        mergeUser.setAge(user.getAge());
        mergeUser.setTelephone(user.getTelephone());
        if (!"".equals(user.getPassword())) {
            mergeUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        return repository.save(mergeUser);
    }
    
    @DeleteMapping("/{uid}")
    public void delete(@PathVariable Long uid) {
        repository.delete(uid);
    }
    
}
