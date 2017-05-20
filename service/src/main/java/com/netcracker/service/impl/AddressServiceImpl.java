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

@Log
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, Long> implements AddressService {

    private AddressDao addressDao;

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    @Transactional
    public List getAddressDataByUserId(Long id) {
        return addressDao.getAddressDataByUserId(id);
    }

    @Override
    @Transactional
    public String getAddressByUsername(String username) {
        return addressDao.getAddressByUsername(username);
    }

    @Override
    @Transactional
    public void add(User user) {
        Set<User> users = new HashSet<>();
        users.add(user);

        Address address = addressDao.getEntityById(user.getAddress().getId());

        user.setAddress(address);
    }
}
