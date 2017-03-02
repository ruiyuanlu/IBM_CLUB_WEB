package com.istc.Service.EntityDAO.EntityDAOInterfaces;

import com.istc.Entities.Entity.President;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
public interface PresidentDAO<E extends President, PK extends Serializable> extends MemberDAO<President, String>{
}
