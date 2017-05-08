package com.netcracker.dao.impl;

import com.netcracker.dao.AddressDao;
import com.netcracker.pojo.Address;
import com.netcracker.pojo.User;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implementation of {@link AddressDao} interface
 * Extends of {@link BaseDaoImpl} class
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log
@Repository
public class AddressDaoImpl extends BaseDaoImpl<Address, Long> implements AddressDao {

    @Override
    public List<String> getAddressDataByUserId(Long id) {
        Session session = currentSession();
        Query query = session.createQuery("select a from Address a where user.id=:id")
                .setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public String getAddressByUsername(String username) {
        Session session = currentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);
        Root<Address> root = criteria.from(Address.class);
        criteria.select(root.get("country")).where(builder.equal(root.get("username"), username));
        return session.createQuery(criteria).list().get(0);
    }
}
