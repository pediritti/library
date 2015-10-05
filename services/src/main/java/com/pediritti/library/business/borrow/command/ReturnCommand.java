package com.pediritti.library.business.borrow.command;


import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.domain.Returned;

public interface ReturnCommand {
    void setReturned(Borrowed borrowed, Returned returned);
}
