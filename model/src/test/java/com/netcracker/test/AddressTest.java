package com.netcracker.test;

import com.netcracker.dao.AddressDao;
import com.netcracker.pojo.Address;
import com.netcracker.pojo.User;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/model-spring-test.xml")
@Rollback
@Transactional
public class AddressTest {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void testSaveUser() {
        int size = addressDao.getAllEntities().size();

        Address address = new Address();
        address.setCountry("Country save test");
        address.setRegion("Region save test");
        address.setCity("City save test");
        address.setStreet("Street save test");

        addressDao.save(address);

        assertTrue(size < addressDao.getAllEntities().size());
    }

    @Test
    public void testUpdateUser() {
        Address address = new Address();
        address.setCountry("Country update test");
        address.setRegion("Region update test");
        address.setCity("City update test");
        address.setStreet("Street update test");

        addressDao.save(address);

        address.setCountry("Country update test");

        Address addressTest = addressDao.getEntityById(address.getId());
        addressDao.update(address);

        assertEquals("Country update test", addressTest.getCountry());
    }

    @Test
    public void testGetUserById() {
        Address address = new Address();
        address.setCountry("Country findCategoryByCategoryName test");
        address.setRegion("Region findCategoryByCategoryName test");
        address.setCity("City findCategoryByCategoryName test");
        address.setStreet("Street findCategoryByCategoryName test");

        addressDao.save(address);

        Address addressTest = addressDao.getEntityById(address.getId());
        assertEquals(address, addressTest);
    }

    @Test
    public void testGetAllAddresses() {
        assertEquals(0, addressDao.getAllEntities().size());

        List<Address> addresses = Arrays.asList(
                new Address("Address Country 1", "Address 1 region", "City_1", "street_1"),
                new Address("Address Country 2", "Address 2 region", "City_2", "street_2"),
                new Address("Address Country 3", "Address 3 region", "City_3", "street_3"),
                new Address("Address Country 4", "Address 4 region", "City_4", "street_4")
        );

        addresses.forEach(address -> addressDao.save(address));

        List<Address> addressesTest = addressDao.getAllEntities();
        assertEquals(4, addressDao.getAllEntities().size());

        addressesTest.forEach(address -> assertTrue(addresses.contains(address)));
    }

    @Test
    public void testDeleteAddress() {
        Address address = new Address();
        address.setCountry("Country delete test");
        address.setRegion("Region delete test");
        address.setCity("City delete test");
        address.setStreet("Street delete test");

        addressDao.save(address);

        assertEquals(address, addressDao.getEntityById(address.getId()));

        addressDao.delete(address.getId());

        assertNull(addressDao.getEntityById(address.getId()));
    }
}
