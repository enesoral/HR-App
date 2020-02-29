package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.User;

import java.io.File;

public interface UserService extends CrudService<User, Long> {
    User findByUsername(String userName);
    User getLoggedUser();
    boolean setAndUploadResume(User user, File resume);
}
