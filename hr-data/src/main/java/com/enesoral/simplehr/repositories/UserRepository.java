package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
