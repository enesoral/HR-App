package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.User;

public interface UserService extends CrudService<User, Long> {
    User findByUsername(String userName);
}
