package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.MessageDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/21 0021.
 */
@Service("messageService")
public class MessageService {
    @Resource
    private transient MessageDAO messageDAO;
}
