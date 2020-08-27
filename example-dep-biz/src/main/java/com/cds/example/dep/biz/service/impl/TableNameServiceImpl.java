/**
 * @Project example-dep-biz
 * @package com.cds.example.dep.biz.service.impl
 * @Class TableNameServiceImpl.java
 * @Date [date]
 * @Copyright [copyright]
 */
package com.cds.example.dep.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cds.api.example.model.TableNameVO;
import com.cds.base.common.exception.BusinessException;
import com.cds.base.util.bean.BeanUtils;
import com.cds.base.util.bean.CheckUtils;
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
public class TableNameServiceImpl implements TableNameService {

    @Autowired
    private TableNameDAO tableDAO;

    @Override
    public TableNameVO modify(TableNameVO value) {
        TableNameDOExample example = new TableNameDOExample();
        example.createCriteria().andNumEqualTo(value.getNum());
        TableNameDO record = new TableNameDO();
        BeanUtils.copyProperties(value, record);
        int success = tableDAO.updateByExampleSelective(record, example);
        if (success <= 0) {
            return null;
        }
        return this.detail(value.getNum());
    }

    @Override
    public TableNameVO detail(String num) {
        TableNameDOExample example = new TableNameDOExample();
        example.createCriteria().andNumEqualTo(num);
        List<TableNameDO> resultList = tableDAO.selectByExample(example);
        if (CheckUtils.isEmpty(resultList)) {
            return null;
        }
        TableNameDO tableNameDO = resultList.get(0);
        TableNameVO result = new TableNameVO();
        BeanUtils.copyProperties(tableNameDO, result);
        return result;
    }

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
    public boolean delete(String num) {
        TableNameDOExample example = new TableNameDOExample();
        example.createCriteria().andNumEqualTo(num);
        return tableDAO.deleteByExample(example) > 0;
    }

    @Override
    public int deleteAll(List<String> numList) {
        TableNameDOExample example = new TableNameDOExample();
        example.createCriteria().andNumIn(numList);
        return tableDAO.deleteByExample(example);
    }

    @Override
    public TableNameVO save(TableNameVO value) {

        TableNameDO record = new TableNameDO();
        BeanUtils.copyProperties(value, record);
        int success = tableDAO.insertSelective(record);
        if (success <= 0) {
            return null;
        }
        return this.detail(value.getNum());
    }

    @Override
    public int saveAll(List<TableNameVO> valueList) {
        if (CheckUtils.isEmpty(valueList)) {
            return 0;
        }
        int count = 0;
        for (TableNameVO value : valueList) {
            TableNameDO record = new TableNameDO();
            BeanUtils.copyProperties(value, record);
            int success = tableDAO.insertSelective(record);
            if (success <= 0) {
                throw new BusinessException("保存失败");
            }
            count++;
        }
        return count;
    }

    @Override
    public boolean contains(TableNameVO value) {
        TableNameDO doValue = new TableNameDO();
        BeanUtils.copyProperties(value, doValue);
        List<TableNameVO> list = this.queryAll(value);
        if (CheckUtils.isNotEmpty(list) && list.size() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public TableNameVO detail(TableNameVO value) {
        List<TableNameVO> list = this.queryAll(value);

        if (CheckUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TableNameVO> queryAll(TableNameVO params) {
        return this.queryPagingList(params, 0, Integer.MAX_VALUE);
    }

    @Override
    public List<TableNameVO> queryPagingList(TableNameVO params, int startIndex, int pageSize) {
        TableNameDO doParams = new TableNameDO();
        BeanUtils.copyProperties(params, doParams);
        List<TableNameDO> list = tableDAO.queryPagingList(doParams, startIndex, pageSize);
        if (CheckUtils.isEmpty(list)) {
            return null;
        }
        List<TableNameVO> resultList = new ArrayList<TableNameVO>();
        BeanUtils.copyListProperties(list, resultList, TableNameVO.class);
        return resultList;
    }

    @Override
    public int queryPagingCount(TableNameVO params) {
        TableNameDO doParams = new TableNameDO();
        BeanUtils.copyProperties(params, doParams);
        return tableDAO.queryPagingCount(doParams);
    }

}