package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.President;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.PresidentDAO;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
 */
@Repository("presidentDAO")
public class PresidentDAOImpl<E extends President, PK extends Serializable> extends MemberDAOImpl<President, String> implements PresidentDAO<E, PK> {
}
