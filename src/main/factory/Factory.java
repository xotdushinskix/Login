package factory;

import dao.CustomerDao;
import daoImpl.CustomerDaoImpl;

/**
 * Created by FromxSoul on 08.06.2016.
 */
public class Factory {

    private static CustomerDao customerDao = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }


    public static synchronized CustomerDao getCustomerDao() {
        if (customerDao == null) {
            customerDao = new CustomerDaoImpl();
        }
        return customerDao;
    }


}
