package com.netcracker.service;

import com.netcracker.pojo.Address;

import java.util.List;

public interface AddressService extends BaseService<Address, Long> {

    List getAddressDataByUserId(Long id);
}
