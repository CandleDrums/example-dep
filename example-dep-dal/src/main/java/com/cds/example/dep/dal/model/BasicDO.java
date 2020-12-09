package com.cds.example.dep.dal.model;

import com.cds.base.dal.model.BasicModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BasicDO extends BasicModel {

    /**
     * 编号
     */
    private String modelNameNum;

    private static final long serialVersionUID = 1L;
}