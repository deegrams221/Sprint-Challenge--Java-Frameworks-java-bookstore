package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // findAll - paging and sorting
    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll(pageable).iterator()
                .forEachRemaining(list::add);
        return list;
    }

    // updateBook
    @Transactional
    @Override
    public Book updateBook(Book book, long id) {
        Book currentBook = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (book.getBooktitle() != null) {
            currentBook.setBooktitle(book.getBooktitle());
        }
        if (book.getISBN() != null) {
            currentBook.setISBN(book.getISBN());
        }
        if (book.getAuthors() != null && book.getAuthors().size() > 0) {
            currentBook.setAuthors(book.getAuthors());
        }
        if (book.getCopy() < 0) {
            currentBook.setCopy(book.getCopy());
        }

        bookRepository.save(currentBook);
        return currentBook;
    }

    // delete
    @Override
    public void delete(long id) {
        if (bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }

    }

    // assignAuthor
    @Transactional
    @Override
    public void assignAuthor(long bookid, long authorid)
    {
        Book currentBook = bookRepository.findById(bookid)
                .orElseThrow(EntityNotFoundException::new);
        currentBook.getAuthors().add(authorRepository.findById(authorid)
                .orElseThrow(EntityNotFoundException::new));
    }

    // save
    @Override
    public void save(Book book)
    {
        bookRepository.save(book);
    }
}
