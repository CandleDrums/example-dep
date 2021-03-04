/**
 * @Project example-dep-biz
 * @package com.cds.dep.example.biz.impl
 * @Class ModelNameServiceImpl.java
 * @Date [date]
 * @Copyright [copyright]
 */
package com.cds.dep.example.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cds.api.example.model.ModelNameVO;
import com.cds.base.biz.service.impl.BaseServiceImpl;
import com.cds.base.dal.dao.BaseDAO;
import com.cds.dep.example.biz.ModelNameService;
import com.cds.dep.example.dal.dao.ModelNameDAO;
import com.cds.dep.example.dal.model.ModelNameDO;

/**
 * @Description [name]Service实现
 * @Notes 未填写备注
 * @author [author]
 * @Date [date]
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
    timeout = TransactionDefinition.TIMEOUT_DEFAULT)
public class ModelNameServiceImpl extends BaseServiceImpl<ModelNameVO, ModelNameDO> implements ModelNameService {

    @Autowired
    private ModelNameDAO tableDAO;

    @Override
    protected BaseDAO<ModelNameDO> getDAO() {
        return tableDAO;
    }

}