package edu.xidian.sselab.bdms.controller;

import edu.xidian.sselab.bdms.domain.User;
import edu.xidian.sselab.bdms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    UserRepository repository;
    
    @GetMapping("")
    public Page<User> queryByPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return repository.findAll(new PageRequest(page, size));
    }
    
    @PutMapping("")
    public User save(User user) {
        return repository.save(user);
    }
    
    @PostMapping("/{uid}")
    public User edit(@PathVariable Long uid, User user) {
        user.setId(uid);
        return repository.save(user);
    }
    
    @DeleteMapping("/{uid}")
    public void delete(@PathVariable Long uid) {
        repository.delete(uid);
    }
    
}
