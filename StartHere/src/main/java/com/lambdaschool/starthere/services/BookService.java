package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
    // findAll - paging and sorting
    List<Book> findAll(Pageable pageable);

    // updateBook
    Book updateBook(Book book, long id);

    // delete
    void delete(long id);

    // assignAuthor
    void assignAuthor(long bookid, long authorid);

    // save
    void save(Book book);
}
