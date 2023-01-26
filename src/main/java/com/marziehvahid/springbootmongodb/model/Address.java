package com.marziehvahid.springbootmongodb.model;

import lombok.Getter;
import org.springframework.util.Assert;

/*
 *  @author Marzieh Vahid (marzieh.vahid@gmail.com)
 */
public record Address( String street, String city, String country) {

    public Address {

        Assert.hasText(street, "street cannot be empty");
        Assert.hasText(city, "city cannot be empty");
        Assert.hasText(country, "country cannot be empty");

    }
}
