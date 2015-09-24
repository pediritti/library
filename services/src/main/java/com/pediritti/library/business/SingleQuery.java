package com.pediritti.library.business;

public interface SingleQuery<E, P> {

    E find(P param);

}
