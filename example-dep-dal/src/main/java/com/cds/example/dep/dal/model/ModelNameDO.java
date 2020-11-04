/**
 * @Project example-dep-dal
 * @package com.cds.example.dep.dal.model
 * @Class ModelNameDO.java
 * @Date [date]
 * @Copyright [copyright]
 */
package com.cds.example.dep.dal.model;

import com.cds.base.dal.model.GeneralModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description [name]DO
 * @Notes 未填写备注
 * @author [author]
 * @Date [date]
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModelNameDO extends GeneralModel {

    private static final long serialVersionUID = 1L;

    private String status;
    private String type;
}