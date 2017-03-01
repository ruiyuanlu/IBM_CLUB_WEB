package Service.EntityService;

import Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class MemberService {
    private MemberService memberService = DAOFactory.getInstance("MemberDAO", MemberDAO.class);
}
