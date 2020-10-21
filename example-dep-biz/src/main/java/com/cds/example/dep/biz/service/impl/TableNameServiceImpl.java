/**
 * @Project example-dep-biz
 * @package com.cds.example.dep.biz.service.impl
 * @Class TableNameServiceImpl.java
 * @Date [date]
 * @Copyright [copyright]
 */
package com.cds.example.dep.biz.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cds.api.example.model.TableNameVO;
import com.cds.base.biz.service.impl.GeneralServiceImpl;
import com.cds.base.dal.dao.BaseDAO;
import com.cds.example.dep.biz.service.TableNameService;
import com.cds.example.dep.dal.dao.TableNameDAO;
import com.cds.example.dep.dal.model.TableNameDO;
import com.cds.example.dep.dal.model.TableNameDOExample;

/**
 * @Description [name]Service实现
 * @Notes 未填写备注
 * @author [author]
 * @Date [date]
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
    timeout = TransactionDefinition.TIMEOUT_DEFAULT)
public class TableNameServiceImpl extends GeneralServiceImpl<TableNameVO, TableNameDO, TableNameDOExample>
    implements TableNameService {

    @Autowired
    private TableNameDAO tableDAO;

    @Override
    protected BaseDAO<TableNameDO, Serializable, TableNameDOExample> getDAO() {
        return tableDAO;
    }

}