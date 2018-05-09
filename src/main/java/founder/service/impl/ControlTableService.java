package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.IControlTableDao;
import founder.dao.IControlTreeDao;
import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.IControlTableService;
import founder.service.IControlTreeService;
import founder.service.common.*;

@Service("controlTreeService")
public class ControlTableService extends AbstractService<Control_Table> implements IControlTableService {

    @Resource(name="controlTableDao")
    private IControlTableDao dao;
    public ControlTableService() {
        super();
    }
	@Override
	protected IOperations<Control_Table> getDao() {
		return this.dao;
	}
 
 
}