package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.INotifyDao;
import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.INotifyService;
import founder.service.IUserService;
import founder.service.common.*;

@Service("notifyService")
public class NotifyService extends AbstractService<Notify> implements INotifyService {
    @Resource(name="notifyDao")
    private INotifyDao dao;
    
    public NotifyService() 
    {
        super();
    }
    @Override
    protected IOperations<Notify> getDao() 
    {
        return this.dao;
    }
}