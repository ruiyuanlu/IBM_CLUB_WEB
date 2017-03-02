package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Message;
import com.istc.Service.BaseDAO.BaseDAOImpl;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MessageDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by lurui on 2017/2/21 0021.
 */
@Repository("messageDAO")
public class MessageDAOImpl extends BaseDAOImpl<Message, Integer> implements MessageDAO{
}
