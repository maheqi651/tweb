package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.IControlTreeFiledDao;
import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("controlTreeFiledDao")
public class ControlTreeFiledDao extends AbstractHibernateDao<Control_Tree_Filed> implements IControlTreeFiledDao {

    public ControlTreeFiledDao() {
        super();
        setClazz(Control_Tree_Filed.class);
    }
}