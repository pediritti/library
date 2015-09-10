package query;

import java.util.List;

public interface ResultListQuery <E, P> {

    List<E> find(P param);

}
