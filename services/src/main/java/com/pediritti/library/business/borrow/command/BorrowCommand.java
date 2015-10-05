package com.pediritti.library.business.borrow.command;


import com.pediritti.library.domain.Borrowed;

public interface BorrowCommand {
    void save(Borrowed borrowed);
}
