package com.netcracker.service.impl;

import com.netcracker.dao.AddressDao;
import com.netcracker.pojo.Address;
import com.netcracker.pojo.User;
import com.netcracker.service.AddressService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link AddressService} and extend {@link BaseServiceImpl}
 *
 * @author Ayupov Vadim
 */
@Log
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, Long> implements AddressService {

    private AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
}
