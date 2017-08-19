package com.salesforce.industries.persistence;
import java.util.List;

import com.salesforce.industries.exception.DAOException;
import com.salesforce.industries.persistence.model.Address;



public interface AddressDAO {
    public List<Address> selectAll() throws DAOException;

}
