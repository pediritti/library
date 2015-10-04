package com.pediritti.library.service;

import com.pediritti.library.business.book.command.BookCommand;
import com.pediritti.library.business.borrow.BorrowFactory;
import com.pediritti.library.business.borrow.BorrowToDtoMapper;
import com.pediritti.library.business.borrow.command.BorrowCommand;
import com.pediritti.library.business.borrow.command.ReturnCommand;
import com.pediritti.library.business.borrow.query.BorrowingByBookQuery;
import com.pediritti.library.business.borrow.query.BorrowingQuery;
import com.pediritti.library.business.user.command.UserCommand;
import com.pediritti.library.domain.*;
import com.pediritti.library.dtos.result.BorrowingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BorrowingService {

    @Autowired
    private UserCommand userCommand;
    @Autowired
    private BookCommand bookCommand;
    @Autowired
    private BorrowingQuery borrowingQuery;
    @Autowired
    private BorrowCommand borrowCommand;
    @Autowired
    private ReturnCommand returnCommand;
    @Autowired
    private BorrowingByBookQuery borrowingByBookQuery;
    @Autowired
    private BorrowToDtoMapper borrowToDtoMapper;

    @Transactional
    public List<BorrowingDTO> listActiveBorrowings(long userId) {
        User user = getUser(userId);
        List<Borrowed> borrowed = borrowingQuery.find(user);
        return borrowToDtoMapper.map(borrowed);
    }

    @Transactional
    public void borrowBook(long userId, long bookId) {
        User user = getUser(userId);
        Book book = getBook(bookId);
        Borrowed borrowed = BorrowFactory.createBorrowed(user, book);
        borrowCommand.save(borrowed);
    }

    @Transactional
    public void returnBook(long userId, long bookId) {
        Book book = getBook(bookId);

        Optional<Borrowed> borrowedOptional = borrowingByBookQuery.find(book);
        if(borrowedOptional.isPresent()) {
            Borrowed borrowed = borrowedOptional.get();
            Returned returned = BorrowFactory.createReturned(borrowed);
            returnCommand.setReturned(borrowed, returned);
        } else {
            throw new NoSuchElementException("Borrowed not found for book: " + bookId);
        }
    }

    private Book getBook(long bookId) {
        Optional<Book> bookOptional = bookCommand.find(bookId);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new NoSuchElementException("Book not found with id: " + bookId);
        }
    }

    private User getUser(long userId) {
        Optional<Person> personOptional = userCommand.find(userId);
        if(personOptional.isPresent()) {
            return (User) personOptional.get();
        } else {
            throw new NoSuchElementException("User not found with id: " + userId);
        }
    }

}
