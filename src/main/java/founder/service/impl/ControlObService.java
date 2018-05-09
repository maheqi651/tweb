package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.IControlObDao;
import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.IControlObService;
import founder.service.IUserService;
import founder.service.common.*;

@Service("controlObService")
public class ControlObService extends AbstractService<Control_Ob> implements IControlObService 
{
    @Resource(name="controlObDao")
    private IControlObDao dao;
    public ControlObService() {
        super();
    }
    @Override
    protected IOperations<Control_Ob> getDao() 
    {
        return this.dao;
    }
}