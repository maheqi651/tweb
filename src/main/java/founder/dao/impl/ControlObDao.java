package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.IControlObDao;
import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("controlObDao")
public class ControlObDao extends AbstractHibernateDao<Control_Ob> implements IControlObDao {

    public ControlObDao() {
        super();
        setClazz(Control_Ob.class);
    }
}