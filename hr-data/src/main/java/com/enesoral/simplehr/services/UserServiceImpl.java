package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FileService fileService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User object) {
        object.setPassword(bCryptPasswordEncoder.encode(object.getPassword()));
        return userRepository.save(object);
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName).orElse(null);
    }

    @Override
    public boolean setAndUploadResume(User user, File resume) {
        String fileName = user.getUsername() + "-resume";
        if(!fileService.uploadResume(resume, fileName)) {
            return false;
        }
        user.setResumeDirectory(FileServiceImpl.uploadDirectory + fileName);
        update(user);
        return true;
    }

    @Override
    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        return findByUsername(userDetail.getUsername());
    }

    private User update(User user) {
        return userRepository.save(user);
    }
}
