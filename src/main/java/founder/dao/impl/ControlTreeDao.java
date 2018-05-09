package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.IControlTreeDao;
import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("controlTreeDao")
public class ControlTreeDao extends AbstractHibernateDao<Control_Tree> implements IControlTreeDao {

    public ControlTreeDao() {
        super();
        setClazz(Control_Tree.class);
    }
}