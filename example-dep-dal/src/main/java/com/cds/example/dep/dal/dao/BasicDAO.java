package com.cds.example.dep.dal.dao;

import java.io.Serializable;

import org.apache.ibatis.annotations.Mapper;

import com.cds.base.dal.dao.BaseDAO;
import com.cds.example.dep.dal.model.BasicDO;
import com.cds.example.dep.dal.model.BasicDOExample;

@Mapper
public interface BasicDAO extends BaseDAO<BasicDO, Serializable, BasicDOExample> {}