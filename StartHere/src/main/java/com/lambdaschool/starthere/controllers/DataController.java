package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/data")
public class DataController
{
    // logging
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private BookService bookService;

    // Adding custom swagger documentation for update book
    @ApiOperation(value = "Updates book details", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Book updated successfully",
                    response = void.class),
            @ApiResponse(code = 500,
                    message = "Error updating book",
                    response = ErrorDetail.class)
    })

    //   http://localhost:2019/data/books/{id}
    // PUT /data/books/{id} - updates a books info (Title, Copyright, ISBN) but
    // does NOT have to assign authors to the books.
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updateBook,
            @PathVariable
                    long id, HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
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

    //   http://localhost:2019/data/books/{bookid}/authors/{authorid}
    // POST /data/books/{bookid}/authors/{authorid} - assigns a book already in the system
    // (bookid) to an author already in the system (authorid) (see how roles are handled for users)
    @PostMapping(value = "/books/{bookid}/authors/{authorid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> assignBookToAuthor(
            @ApiParam(value = "Book Id",
                    required = true, example = "1")
            @PathVariable long bookid,
            @ApiParam(value = "Author.java Id",
                    required = true, example = "1")
            @PathVariable long authorid,
                HttpServletRequest request) throws URISyntaxException
    {
        // logging
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.assignBookToAuthor(bookid, authorid);
        return new ResponseEntity<>(null, HttpStatus.OK);
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

    //   http://localhost:2019/data/books/{bookid}
    // DELETE /data/books/{id} - deletes a book and the book author combinations -
    // but does not delete the author records.
    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<?> deleteBookById(
            @ApiParam(value = "Book Id",
                    required = true, example = "1")
            @PathVariable long bookid,
            HttpServletRequest request)
    {
        // logging
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}