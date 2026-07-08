package com.taufik.bookmanagement.service;

import com.taufik.bookmanagement.entity.Book;
import com.taufik.bookmanagement.exception.ResourceNotFoundException;
import com.taufik.bookmanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublishedDate(bookDetails.getPublishedDate());
        return bookRepository.save(book);
    }

    public Book partialUpdate(Long id, Map<String, Object> updates) {
        Book book = getBookById(id);
        updates.forEach((key, value) -> {
            try {
                Field field = Book.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(book, value);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Invalid field: " + key);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Cannot update field: " + key);
            }
        });
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}