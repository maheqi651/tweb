package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("usersDao")
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

    public UserDao() {
        super();
        setClazz(User.class);
    }
}