package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.IApplyTableDao;
import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.IApplyTableService;
import founder.service.IUserService;
import founder.service.common.*;

@Service("applyTableService")
public class ApplyTableService extends AbstractService<Apply_Table> implements IApplyTableService {

    @Resource(name="applyTableDao")
    private IApplyTableDao dao;
    
    public ApplyTableService() {
        super();
    }
    @Override
    protected IOperations<Apply_Table> getDao() 
    {
        return this.dao;
    }
}