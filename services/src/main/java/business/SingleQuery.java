package business;

public interface SingleQuery<E, P> {

    E find(P param);

}
