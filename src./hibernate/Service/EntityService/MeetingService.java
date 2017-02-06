package Service.EntityService;

import Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import Utilities.DAOFactory;

/**
 * Created by lurui on 2017/2/5 0005.
 */
public class MeetingService {
    private MeetingDAO meetingDAO = DAOFactory.getInstance("MeetingDAO", MeetingDAO.class);
}
