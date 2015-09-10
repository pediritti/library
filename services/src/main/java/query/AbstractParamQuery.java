package query;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public abstract class AbstractParamQuery<E, P>{

    @PersistenceContext
    protected EntityManager entityManager;
    protected ParameterExpression<P> parameter;
    protected TypedQuery<E> typedQuery;

    protected void init(Class<E> entityClass, Class<P> parameterClass, String fieldName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        parameter = criteriaBuilder.parameter(parameterClass);
        CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        Root<E> table = query.from(entityClass);
        query.select(table).where(criteriaBuilder.equal(table.get(fieldName), parameter));
        typedQuery = entityManager.createQuery(query);
    }
}
