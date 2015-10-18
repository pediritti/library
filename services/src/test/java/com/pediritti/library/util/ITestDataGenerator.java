package com.pediritti.library.util;


import com.pediritti.library.business.author.AuthorFactory;
import com.pediritti.library.business.book.BookFactory;
import com.pediritti.library.business.borrow.BorrowFactory;
import com.pediritti.library.business.user.PersonFactory;
import com.pediritti.library.domain.*;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.input.PersonInputDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
public class ITestDataGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Person createPerson(PersonInputDTO dto) {
        Person person = PersonFactory.createPerson(dto.getFirstName(), dto.getLastName(),
                dto.getPassword(), dto.getEmail(), dto.getBirth(), dto.isAdmin());
        entityManager.persist(person);
        return person;
    }

    @Transactional
    public Author createAuthor(AuthorInputDTO dto) {
        Author author = AuthorFactory.createNew(dto.getFirstName(), dto.getLastName());
        entityManager.persist(author);
        return author;
    }

    @Transactional
    public Book createBook(BookInputDTO dto, Author author) {
        Book book = BookFactory.createNew(author, dto.getIsbn(), dto.getTitle(), dto.getIssueDate());
        entityManager.persist(book);
        return book;
    }

    @Transactional
    public Borrow createBorrowing(Borrower borrower, Book book) {
        Borrowed borrowed = BorrowFactory.createBorrowed(borrower, book);
        entityManager.persist(borrowed);
        return borrowed;
    }

    @Transactional
    public void wipeAll() {
        Query borrowedDelete = entityManager.createNativeQuery("DELETE FROM Borrowed");
        Query returnedDelete = entityManager.createNativeQuery("DELETE FROM Returned");
        Query borrowDelete = entityManager.createNativeQuery("DELETE FROM Borrow");
        Query bookDelete = entityManager.createNativeQuery("DELETE FROM Book");
        Query authorDelete = entityManager.createNativeQuery("DELETE FROM Author");
        Query adminDelete = entityManager.createNativeQuery("DELETE FROM Admin");
        Query borrowerDelete = entityManager.createNativeQuery("DELETE FROM Borrower");

        borrowedDelete.executeUpdate();
        returnedDelete.executeUpdate();
        borrowDelete.executeUpdate();
        bookDelete.executeUpdate();
        authorDelete.executeUpdate();
        adminDelete.executeUpdate();
        borrowerDelete.executeUpdate();
    }


}
