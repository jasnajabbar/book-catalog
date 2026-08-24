package com.bookcatalog.controller;
import com.bookcatalog.dto.BookRequest;
import com.bookcatalog.dto.BookResponse;
import com.bookcatalog.dto.BookUpdateRequest;
import com.bookcatalog.exception.BookNotFoundException;
import com.bookcatalog.exception.PageExceedException;
import com.bookcatalog.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/books")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest bookRequest){
        Optional<BookResponse> response=this.bookService.addBook(bookRequest);
        return response.map(b->ResponseEntity.status(HttpStatus.CREATED).body(response.get()))
                .orElseThrow();
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        Optional<List<BookResponse>> response=this.bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id){
        Optional<BookResponse> bookResponse=this.bookService.getBookById(id);
        return bookResponse.map(b->ResponseEntity.status(HttpStatus.OK).body(bookResponse.get()))
                .orElseThrow(()->new BookNotFoundException("Book Not Found"));
    }

    @PatchMapping(value = "/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Integer id,@RequestBody BookUpdateRequest bookUpdateRequest){
        Optional<BookResponse> bookResponse=this.bookService.updateBook(id,bookUpdateRequest);
        return bookResponse.map(b->ResponseEntity.status(HttpStatus.OK).body(bookResponse.get()))
                .orElseThrow(()->new BookNotFoundException("Book Not Found"));
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Integer id){
        Optional<BookResponse> bookResponse=this.bookService.deleteBook(id);
        return bookResponse.map(b->ResponseEntity.status(HttpStatus.OK).body(bookResponse.get()))
                .orElseThrow(()->new BookNotFoundException("Book Not Found"));
    }

    @GetMapping(value = "/books/author/{authorName}")
    public ResponseEntity<BookResponse> getBookByAuthor(@PathVariable String authorName){
        Optional<BookResponse> bookResponse=this.bookService.getBookByAuthor(authorName);
        return bookResponse.map(b->ResponseEntity.status(HttpStatus.OK).body(bookResponse.get()))
                .orElseThrow();
    }

    @GetMapping(value = "/books/completed/{readingStatus}")
    public ResponseEntity<List<BookResponse>> getCompletedBook(@PathVariable String readingStatus){
        Optional<List<BookResponse>> bookResponse=this.bookService.getCompletedBook(readingStatus);
        return bookResponse.map(b->ResponseEntity.status(HttpStatus.OK).body(bookResponse.get()))
                .orElseThrow();
    }

    @GetMapping(value = "/books/inprogress/{readingStatus}")
    public ResponseEntity<List<BookResponse>> getInProgressBook(@PathVariable String readingStatus){
        Optional<List<BookResponse>> bookResponse=this.bookService.getInProgressBook(readingStatus);
        return bookResponse.map(b->ResponseEntity.status(HttpStatus.OK).body(bookResponse.get()))
                .orElseThrow();
    }

    @GetMapping(value = "/books/unread/{readingStatus}")
    public ResponseEntity<List<BookResponse>> getInUnreadBook(@PathVariable String readingStatus){
        Optional<List<BookResponse>> bookResponse=this.bookService.getUnread(readingStatus);
        return bookResponse.map(b->ResponseEntity.status(HttpStatus.OK).body(bookResponse.get()))
                .orElseThrow();
    }
}
