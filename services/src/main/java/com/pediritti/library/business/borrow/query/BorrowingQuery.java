package com.pediritti.library.business.borrow.query;

import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.domain.User;

import java.util.List;

public interface BorrowingQuery {
    List<Borrowed> find(User user);
}
