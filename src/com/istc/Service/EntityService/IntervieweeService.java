package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.IntervieweeDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/25 0025.
 */
@Service("IntervieweeService")
@Transactional(rollbackFor = Exception.class)
public class IntervieweeService {
    @Resource
    private transient IntervieweeDAO intervieweeDAO;
}
