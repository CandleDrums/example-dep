/**
 * @Project example-dep-biz
 * @package com.cds.example.dep.biz.service.impl
 * @Class TableNameServiceImpl.java
 * @Date [date]
 * @Copyright [copyright]
 */
package com.cds.example.dep.biz.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cds.api.example.model.TableNameVO;
import com.cds.base.biz.service.impl.GeneralServiceImpl;
import com.cds.base.dal.dao.BaseDAO;
import com.cds.base.util.bean.BeanUtils;
import com.cds.base.util.bean.CheckUtils;
import com.cds.example.dep.biz.service.TableNameService;
import com.cds.example.dep.dal.dao.TableNameDAO;
import com.cds.example.dep.dal.model.TableNameDO;
import com.cds.example.dep.dal.model.TableNameDOExample;
import com.cds.example.dep.dal.model.TableNameDOExample.Criteria;

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
    public List<TableNameVO> findList(List<String> numList) {
        TableNameDOExample example = new TableNameDOExample();
        example.createCriteria().andNumIn(numList);
        List<TableNameDO> list = tableDAO.selectByExample(example);
        if (CheckUtils.isEmpty(list)) {
            return null;
        }
        List<TableNameVO> resultList = new ArrayList<TableNameVO>();
        BeanUtils.copyListProperties(list, resultList, TableNameVO.class);
        return resultList;
    }

    @Override
    public TableNameVO modify(TableNameVO value) {
        TableNameDOExample example = new TableNameDOExample();
        TableNameDO record = new TableNameDO();
        BeanUtils.copyProperties(value, record);
        Criteria criteria = example.createCriteria();
        criteria.andNumEqualTo(value.getNum());
        // OptimisticLockerInterceptor看看是否适用
        if (value.getVersion() != null) {
            criteria.andVersionEqualTo(value.getVersion());
            record.setVersion(value.getVersion() + 1);
            record.setUpdateDate(new Date());
        }
        int success = tableDAO.updateByExampleSelective(record, example);
        if (success <= 0) {
            return null;
        }
        return this.detail(value.getNum());
    }

    @Override
    public boolean delete(String num) {
        TableNameDOExample example = new TableNameDOExample();
        example.createCriteria().andNumEqualTo(num);
        TableNameDO record = new TableNameDO();
        record.setDeleted(true);
        record.setUpdateDate(new Date());
        return tableDAO.updateByExampleSelective(record, example) == 0;
    }

    @Override
    public TableNameVO detail(String num) {
        TableNameDOExample example = new TableNameDOExample();
        example.createCriteria().andNumEqualTo(num);
        List<TableNameDO> resultList = tableDAO.selectByExample(example);
        if (CheckUtils.isEmpty(resultList) || resultList.size() == 0) {
            return null;
        }
        TableNameDO tableNameDO = resultList.get(0);
        TableNameVO result = new TableNameVO();
        BeanUtils.copyProperties(tableNameDO, result);
        return result;
    }

    @Override
    protected BaseDAO<TableNameDO, Serializable, TableNameDOExample> getDAO() {
        return tableDAO;
    }

}