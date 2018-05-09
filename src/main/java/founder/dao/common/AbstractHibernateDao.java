package founder.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements IOperations<T> {
    
    private Class<T> clazz;
    
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = Preconditions.checkNotNull(clazzToSet);
    }
    
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public final T findOne(final long id) {
        return (T)getCurrentSession().get(clazz, id);
    }

    @Override
    public final List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public final void create(final T entity) {
         Preconditions.checkNotNull(entity);
         // getCurrentSession().persist(entity);
         getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public final T update(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().update(entity);
        return entity;
        //return (T)getCurrentSession().merge(entity);
    }

    @Override
    public final void delete(final T entity) {
         Preconditions.checkNotNull(entity);
         getCurrentSession().delete(entity);
    }

    @Override
    public final void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }
    @Override
    public List<T> findList(String Condition,Map<String,String> parms,long start,long end)
    {
    	Query query=getCurrentSession().createQuery(Condition);
    	if(parms!=null)
    	{
    		Set<String> keys=parms.keySet();
    		for(String key:keys)
    		{
    			query.setParameter(key,parms.get(key));
    		}
    	}
    	if(start>=0)
    		query.setFirstResult((int)start);
    	if(end>=0)
    		query.setMaxResults((int)(end-start));
    	return query.list();
    }
    
}