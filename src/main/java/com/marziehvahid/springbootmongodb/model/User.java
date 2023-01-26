package com.marziehvahid.springbootmongodb.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/*
 *  @author Marzieh Vahid (marzieh.vahid@gmail.com)
 */
@Data
@NoArgsConstructor
@Document
public class User {

    @Id
    private BigInteger id;

    private String name, lastName;

    private Set<Address> addresses = new HashSet<>();

    private LocalDate creationDate = LocalDate.now();

    @Field("email")
    @Indexed(unique = true)
    private EmailAddress emailAddress;

}
