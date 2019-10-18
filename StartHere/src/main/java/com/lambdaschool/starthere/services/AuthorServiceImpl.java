package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    // findAll - paging and sorting
    @Override
    public List<Authors> findAll(Pageable pageable)
    {
        List<Authors> authorList = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(authorList::add);
        return authorList;
    }

    // save
    @Override
    public void save(Authors author)
    {
        authorRepository.save(author);
    }
}