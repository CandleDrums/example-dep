/**
 * @Project example-dep-biz
 * @Package com.cds.example.dep.biz.service.impl
 * @Class BasicServiceImpl.java
 * @Date Nov 5, 2020 6:39:27 PM
 * @Copyright (c) 2020 CandleDrums.com All Right Reserved.
 */
package com.cds.example.dep.biz.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cds.api.example.model.BasicVO;
import com.cds.base.biz.service.impl.BaseServiceImpl;
import com.cds.base.dal.dao.BaseDAO;
import com.cds.example.dep.biz.service.BasicService;
import com.cds.example.dep.dal.dao.BasicDAO;
import com.cds.example.dep.dal.model.BasicDO;
import com.cds.example.dep.dal.model.BasicDOExample;

/**
 * @Description TODO 填写描述信息
 * @Notes 未填写备注
 * @author liming
 * @Date Nov 5, 2020 6:39:27 PM
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
    timeout = TransactionDefinition.TIMEOUT_DEFAULT)
public class BasicServiceImpl extends BaseServiceImpl<BasicVO, BasicDO, BasicDOExample> implements BasicService {
    @Autowired
    private BasicDAO basicDAO;

    @Override
    protected BaseDAO<BasicDO, Serializable, BasicDOExample> getDAO() {
        return basicDAO;
    }

}
