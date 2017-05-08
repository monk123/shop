package com.netcracker.pojo;

import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Simple JavaBean object that represents an Address
 *
 * @author Vadim Ayupov
 * @version 1.0
 */

@NoArgsConstructor @Log
@Entity @Table(name = "addresses")
public class Address extends BaseEntity {
    private static final Long serialVersionUID = 2L;

    @Column(name = "COUNTRY")
    @Getter @Setter
    private String country;

    @Column(name = "REGION")
    @Getter @Setter
    private String region;

    @Column(name = "CITY")
    @Getter @Setter
    private String city;

    @Column(name = "STREET")
    @Getter @Setter
    private String street;

    public Address(String country, String region, String city, String street) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;

        Address address = (Address) o;

        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (region != null ? !region.equals(address.region) : address.region != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return street != null ? street.equals(address.street) : address.street == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
