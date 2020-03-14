package com.enesoral.simplehr.repositories;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long>, PagingAndSortingRepository<Application, Long> {
    Page<Application> findAllByUser(User user, Pageable pageable);
}
