/**
 * @Project example-dep-dal
 * @package com.cds.example.dep.dal.dao
 * @Class TableNameDAO.java
 * @Date [date]
 * @Copyright [copyright]
 */
package com.cds.example.dep.dal.dao;

import java.util.List;

import com.cds.base.dal.dao.MyBatisBaseDAO;
import com.cds.example.dep.dal.model.TableNameDO;
import com.cds.example.dep.dal.model.TableNameDOExample;

/**
 * @Description [name]DAO
 * @Notes 未填写备注
 * @author [author]
 * @Date [date]
 */
public interface TableNameDAO extends MyBatisBaseDAO<TableNameDO, Integer, TableNameDOExample> {

    boolean contains(TableNameDO value);

    TableNameDO detail(TableNameDO value);

    List<TableNameDO> queryPagingList(TableNameDO params, int startIndex, int pageSize);

    int queryPagingCount(TableNameDO params);
}