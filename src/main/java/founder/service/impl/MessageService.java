package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.IMessageDao;
import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.IMessageService;
import founder.service.IUserService;
import founder.service.common.*;

@Service("userService")
public class MessageService extends AbstractService<Message> implements IMessageService {

    @Resource(name="messageDao")
    private IMessageDao dao;
    
    public MessageService() {
        super();
    }

    @Override
    protected IOperations<Message> getDao() 
    {
        return this.dao;
    }
}