package com.pediritti.library.business;

import java.util.List;

public interface ResultListQuery <E, P> {

    List<E> find(P param);

}
