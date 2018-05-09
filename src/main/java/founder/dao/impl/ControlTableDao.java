package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.IControlTableDao;
import founder.dao.IControlTreeDao;
import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("controlTableDao")
public class ControlTableDao extends AbstractHibernateDao<Control_Table> implements IControlTableDao {

    public ControlTableDao() {
        super();
        setClazz(Control_Table.class);
    }
}