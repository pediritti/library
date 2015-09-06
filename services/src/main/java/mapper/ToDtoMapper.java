package mapper;


import java.util.List;

public interface ToDtoMapper<E, D> {

    D map(E entity);

    List<D> map(List<E> entities);

}
