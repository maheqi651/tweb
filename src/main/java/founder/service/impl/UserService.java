package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.IUserService;
import founder.service.common.*;

@Service("userService")
public class UserService extends AbstractService<User> implements IUserService {

    @Resource(name="usersDao")
    private IUserDao dao;
    
    public UserService() {
        super();
    }

    @Override
    protected IOperations<User> getDao() 
    {
        return this.dao;
    }
}