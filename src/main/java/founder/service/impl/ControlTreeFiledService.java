package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.IControlTreeFiledDao;
import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.IControlTreeFiledService;
import founder.service.IUserService;
import founder.service.common.*;

@Service("controlTreeFiledService")
public class ControlTreeFiledService extends AbstractService<Control_Tree_Filed> implements IControlTreeFiledService {

    @Resource(name="controlTreeFiledDao")
    private IControlTreeFiledDao dao;
    
    public ControlTreeFiledService() {
        super();
    }

    @Override
    protected IOperations<Control_Tree_Filed> getDao() 
    {
        return this.dao;
    }
}