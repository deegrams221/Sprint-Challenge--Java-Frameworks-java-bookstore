package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BookController
{
    // logging
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    // Adding custom swagger documentation for list all Authors with paging/sorting
    @ApiOperation(value = "Return all Books",
            response = Book.class,
            responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = "page",
                dataType = "integer",
                paramType = "query",
                value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size",
                dataType = "integer",
                paramType = "query",
                value = "Number of records per page."),
            @ApiImplicitParam(name = "sort",
                allowMultiple = true,
                dataType = "string",
                paramType = "query",
                value = "Sorting criteria in the format: property(,asc|desc). "
                        + "Default sort order is ascending. "
                        + "Multiple sort criteria are supported.")})

    // GET /books/books - returns a JSON object list of all the books, thier sections, and their authors.
    @GetMapping(value = "/books/books")
    public ResponseEntity<?> findAllBooks(Pageable pageable, HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }


    // Adding custom swagger documentation for update book
    @ApiOperation(value = "Update a book",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Book Information Updated",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Update Book",
                    response = ErrorDetail.class)})

    // PUT /data/books/{id} - updates a books info (Title, Copyright, ISBN) but
    // does NOT have to assign authors to the books.
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(
            // Adding custom swagger params for update book
            @ApiParam(value = "Book Id",
                    required = true,
                    example = "1")
            @PathVariable
                    long id,
            @RequestBody
                    Book book,
            HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.updateBook(book, id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Adding custom swagger documentation for assigning a book
    @ApiOperation(value = "Assigns a book already in the system",
            notes = "Book Id will be sent to the location header",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "Book assigned",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Assign Book",
                    response = ErrorDetail.class)})

    // POST /data/books/{bookid}/authors/{authorid} - assigns a book already in the system
    // (bookid) to an author already in the system (authorid) (see how roles are handled for users)
    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> matchBookWithAuthor(
            // Adding custom swagger params for assigning book
            @ApiParam(value = "Book Id",
                    required = true,
                    example = "1")
            @PathVariable
                    long id,
            @RequestBody
                    Authors authors,
            HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.assignAuthor(id, authors.getAuthorid());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Adding custom swagger documentation for deleting a book
    @ApiOperation(value = "Delete a book",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201,
            message = "Book Deleted",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete Book",
                    response = ErrorDetail.class)})

    // DELETE /data/books/{id} - deletes a book and the book author combinations -
    // but does not delete the author records.
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBook(
            // Adding custom swagger params for delete book
            @ApiParam(value = "Book Id",
                    required = true,
                    example = "1")
            @PathVariable
                    long id,
            HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
