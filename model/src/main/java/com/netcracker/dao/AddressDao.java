package com.netcracker.dao;

import com.netcracker.pojo.Address;

import java.util.List;

public interface AddressDao extends BaseDao<Address, Long> {

    List getAddressDataByUserId(Long id);

    String getAddressByUsername(String username);
}
