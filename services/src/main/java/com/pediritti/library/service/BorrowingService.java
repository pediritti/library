package com.pediritti.library.service;

import com.pediritti.library.business.ToDtoMapper;
import com.pediritti.library.business.book.command.BookCommand;
import com.pediritti.library.business.borrow.BorrowFactory;
import com.pediritti.library.business.borrow.command.BorrowCommand;
import com.pediritti.library.business.borrow.command.ReturnCommand;
import com.pediritti.library.business.borrow.query.BorrowingByBookQuery;
import com.pediritti.library.business.borrow.query.BorrowingQuery;
import com.pediritti.library.business.user.command.PersonCommand;
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
    private PersonCommand personCommand;
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
    private ToDtoMapper<Borrowed, BorrowingDTO> borrowToDtoMapper;

    @Transactional
    public List<BorrowingDTO> listActiveBorrowings(long personId) {
        Borrower user = getBorrower(personId);
        List<Borrowed> borrowed = borrowingQuery.find(user);
        return borrowToDtoMapper.map(borrowed);
    }

    @Transactional
    public void borrowBook(long borrowerId, long bookId) {
        Borrower borrower = getBorrower(borrowerId);
        Book book = getBook(bookId);
        Borrowed borrowed = BorrowFactory.createBorrowed(borrower, book);
        borrowCommand.save(borrowed);
    }

    @Transactional
    public void returnBook(long borrowerId, long bookId) {
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

    private Borrower getBorrower(long personId) {
        Optional<Person> personOptional = personCommand.find(personId);
        if(personOptional.isPresent()) {
            return (Borrower) personOptional.get();
        } else {
            throw new NoSuchElementException("Person not found with id: " + personId);
        }
    }

}
