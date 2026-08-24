package com.bookcatalog.repository;

import com.bookcatalog.model.Book;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository {
    private List<Book> books = new ArrayList<>();

    public Book save(Book book){
         this.books.add(book);
         return book;
    }

    public List<Book> findAll(){
        return this.books;
    }

    public void delete(Book book){
        this.books.remove(book);
    }

}
