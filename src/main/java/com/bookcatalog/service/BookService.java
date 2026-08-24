package com.bookcatalog.service;
import com.bookcatalog.dto.BookRequest;
import com.bookcatalog.dto.BookResponse;
import com.bookcatalog.dto.BookUpdateRequest;
import com.bookcatalog.exception.BookNotFoundException;
import com.bookcatalog.exception.PageExceedException;
import com.bookcatalog.model.Book;
import com.bookcatalog.repository.BookRepository;
import com.bookcatalog.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Optional<BookResponse> addBook(BookRequest bookRequest) {
       Book book=new Book(this.bookRepository.findAll().size()+1,bookRequest.getBookName(),
               bookRequest.getAuthorName(),bookRequest.getTags(),bookRequest.getTotalPages(),
               0,LocalDate.now(),LocalDateTime.now());

        this.bookRepository.save(book);
        return Optional.ofNullable(convertToResponse(book));
    }

    public Optional<List<BookResponse>> getAllBooks() {
        List<Book> books=this.bookRepository.findAll();
        List<BookResponse> bookResponse=books.stream().map(this::convertToResponse)
                .collect(Collectors.toList());
        return Optional.of(bookResponse);
    }

    public Optional<BookResponse> getBookById(Integer id) {
        return bookRepository.findAll().stream().filter(b->
                Objects.equals(b.getBookId(), id)).findFirst().map(this::convertToResponse);
    }

    private Book getBookByIdUtil(Integer id){
        return bookRepository.findAll().stream().filter(b-> Objects.equals(b.getBookId(), id)).
                findFirst().orElseThrow(()->new BookNotFoundException("No book found"));
    }

    public Optional<BookResponse> updateBook(Integer id, BookUpdateRequest bookUpdateRequest) {
        Optional<Book> bookResponse=Optional.of(getBookByIdUtil(id)).map(book->{
            book.setTags(bookUpdateRequest.getTags());
            book.setPagesRead(bookUpdateRequest.getPagesRead());
            return book;
        });
        return Optional.ofNullable(convertToResponse(bookResponse.get()));
    }

    public Optional<BookResponse> deleteBook(Integer id){
        Optional<Book> book=this.bookRepository.findAll().stream().filter(b-> Objects.equals(b.getBookId(), id))
                .findFirst();
        book.ifPresent(b->this.bookRepository.delete(book.get()));
        return Optional.ofNullable(convertToResponse(book.get()));
    }

    public Optional<BookResponse> getBookByAuthor(String authorName){
        Optional<Book> bookResponse = this.bookRepository.findAll().stream().filter(b -> Objects.equals(b.getAuthorName(), authorName)).findFirst();
        return Optional.ofNullable(convertToResponse(bookResponse.get()));
    }

    public Optional<List<BookResponse>> getCompletedBook(String readingStatus){
        List<Book> books = this.bookRepository.findAll();
        List<BookResponse> getCompletedBook=books.stream().map(this::convertToResponse)
                .filter(bookResponse-> Objects.equals(bookResponse.getReadingStatus(), readingStatus)).collect(Collectors.toList());
        return Optional.of(getCompletedBook);
    }

    public Optional<List<BookResponse>> getInProgressBook(String readingStatus){
        List<Book> books = this.bookRepository.findAll();
        List<BookResponse> getInProgressBook=books.stream().map(this::convertToResponse)
                        .filter(bookResponse-> Objects.equals(bookResponse.getReadingStatus(), readingStatus)).collect(Collectors.toList());
        return Optional.of(getInProgressBook);
    }

    public Optional<List<BookResponse>> getUnread(String readingStatus){
        List<Book> books = this.bookRepository.findAll();
        List<BookResponse> getUnreadBook=books.stream().map(this::convertToResponse)//this::convertToResponse=book->convertToResponse(book)
                .filter(bookResponse-> Objects.equals(bookResponse.getReadingStatus(), readingStatus)).collect(Collectors.toList());
        return Optional.of(getUnreadBook);
    }

    public BookResponse convertToResponse(Book book){
        if(book.getTotalPages()<book.getPagesRead()){
            throw new PageExceedException("Pages read cannot be greater than Total Page");
        }

        Double progressPercentage= ResponseUtil.getProgressPercentage(book.getPagesRead(),book.getTotalPages());
        String readingStatus=ResponseUtil.getReadingStatus(book.getPagesRead(),book.getTotalPages());

        return new BookResponse(book.getBookId(), book.getBookName(),
                book.getAuthorName(),book.getTags(),book.getTotalPages(),
                book.getPagesRead(),progressPercentage,readingStatus,"Book Created Successfully");
    }
}

