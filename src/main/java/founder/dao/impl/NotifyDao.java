package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.INotifyDao;
import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("notifyDao")
public class NotifyDao extends AbstractHibernateDao<Notify> implements INotifyDao {

    public NotifyDao() {
        super();
        setClazz(Notify.class);
    }
}