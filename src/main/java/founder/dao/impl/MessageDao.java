package founder.dao.impl;

import org.springframework.stereotype.Repository;

import founder.dao.IMessageDao;
import founder.dao.IUserDao;
import founder.dao.common.AbstractHibernateDao;
import founder.model.*;

@Repository("messageDao")
public class MessageDao extends AbstractHibernateDao<Message> implements IMessageDao {
    public MessageDao() {
        super();
        setClazz(Message.class);
    }
}