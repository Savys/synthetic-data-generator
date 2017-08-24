package org.stg.persistence;
import java.util.List;

import org.stg.exception.DAOException;
import org.stg.persistence.model.Address;



public interface AddressDAO {
    public List<Address> selectAll() throws DAOException;

}
