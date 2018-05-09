package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.IApplyTableDao;
import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("applyTableDao")
public class ApplyTableDao extends AbstractHibernateDao<Apply_Table> implements IApplyTableDao {

    public ApplyTableDao() {
        super();
        setClazz(Apply_Table.class);
    }
}