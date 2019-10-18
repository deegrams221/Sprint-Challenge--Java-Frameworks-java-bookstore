package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService
{
    // findAll - paging and sorting
    List<Authors> findAll(Pageable pageable);

    // save
    void save(Authors author);
}
