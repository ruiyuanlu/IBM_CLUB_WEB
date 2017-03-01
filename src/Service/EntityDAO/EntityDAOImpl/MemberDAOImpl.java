package Service.EntityDAO.EntityDAOImpl;

import Entities.Entity.Member;
import Entities.Entity.Person;
import Service.EntityDAO.EntityDAOInterfaces.MemberDAO;

import java.io.Serializable;

/**
 * Created by lurui on 2017/2/4 0004.
         */
public class MemberDAOImpl<E extends Member, PK extends Serializable> extends PersonDAOImpl<Member, String> implements MemberDAO<E, PK>{
}
