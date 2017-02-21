package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Minister;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
public interface MinisterDAO<E extends Minister, PK extends Serializable> extends MemberDAO<Minister, String>{
}
