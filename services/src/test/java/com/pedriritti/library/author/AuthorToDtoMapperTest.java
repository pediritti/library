package com.pedriritti.library.author;


import com.pediritti.library.business.author.AuthorToDtoMapper;
import com.pediritti.library.domain.Author;
import com.pediritti.library.dtos.result.AuthorDTO;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthorToDtoMapperTest {

    private AuthorToDtoMapper underTest;

    @Before
    public void setUp() {
        underTest = new AuthorToDtoMapper();
    }

    @Test
    public void testMapping() {
        long id = 10L;
        String firstName = "first";
        String lastName = "last";

        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        AuthorDTO result = underTest.map(author);

        assertEquals(id, result.getId());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
    }
}
