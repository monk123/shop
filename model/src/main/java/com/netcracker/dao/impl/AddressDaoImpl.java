package com.netcracker.dao.impl;

import com.netcracker.dao.AddressDao;
import com.netcracker.pojo.Address;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

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
}
