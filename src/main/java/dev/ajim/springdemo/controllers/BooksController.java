package dev.ajim.springdemo.controllers;

import dev.ajim.springdemo.models.Book;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BooksController {
    
    List<Book> books = new ArrayList<>();
    @GetMapping()
    public List<Book> getBooks(){
        books.add(new Book("Jonathan", "React One"));
        books.add(new Book("Jimmy", "Java First"));
        
        return this.books;
    }

    @GetMapping("/?page=")
    public List<Book> getPage(){
        books.add(new Book("Jonathan", "React One"));
        books.add(new Book("Jimmy", "Java First"));

        return this.books;
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable String id){
        String id1 = id;
        return id1;
    }
   @PostMapping()
   @ResponseStatus(HttpStatus.ACCEPTED)
    public Content saveBook(@RequestBody Content content){
        if(content == null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "No value provided");
        return content;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateBook(@RequestBody Content content, @PathVariable String id){
        if(content == null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "No value provided");
        return id + ": Successfully update";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteBook(@PathVariable String id){
        return id + ": Deleted Successfully";
    }
}
