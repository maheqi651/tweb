package founder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import founder.dao.IControlTreeDao;
import founder.dao.IUserDao;
import founder.dao.common.IOperations;
import founder.model.*;
import founder.service.IControlTreeService;
import founder.service.common.*;

@Service("controlTreeService")
public class ControlTreeService extends AbstractService<Control_Tree> implements IControlTreeService {

    @Resource(name="controlTreeDao")
    private IControlTreeDao dao;
    public ControlTreeService() {
        super();
    }
	@Override
	protected IOperations<Control_Tree> getDao() {
		return this.dao;
	}
 
 
}