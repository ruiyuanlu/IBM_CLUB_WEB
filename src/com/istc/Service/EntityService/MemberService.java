package com.istc.Service.EntityService;

import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import com.istc.Utilities.DAOFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("memberService")
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    @Resource
    private transient MemberService memberService;
}
