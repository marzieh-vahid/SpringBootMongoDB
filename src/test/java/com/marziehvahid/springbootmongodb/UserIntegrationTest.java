package com.marziehvahid.springbootmongodb;

import com.marziehvahid.springbootmongodb.model.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMongoDbApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    private static final String ENDPOINT = "/api";
    private static final String CREATE_ENDPOINT = ENDPOINT + "/create";

    @Autowired
    private TestRestTemplate restTemplate;
    private static BigInteger id;
    private static final User newUser = new User();

    @Before
    public void setup() {

        newUser.setName("Marzieh");
        newUser.setLastName("Vahid");

    }

    @Test
    public void test_add_user_success() {

        ResponseEntity<User> user = restTemplate.postForEntity(CREATE_ENDPOINT,
                new HttpEntity<>(newUser),
                User.class);
        id = user.getBody() != null ? user.getBody().getId() : null;

        Assertions.assertEquals(user.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(user.getBody().getName(), newUser.getName());
        Assertions.assertEquals(user.getBody().getLastName(), newUser.getLastName());
    }


    @Test
    public void test_delete_user_success() {
        ResponseEntity<String> response = restTemplate.exchange(ENDPOINT + "/" + id,
                HttpMethod.DELETE,
                null,
                String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void test_find_user_failure() {
        ResponseEntity<User> user = restTemplate.getForEntity(ENDPOINT + "/" + id,
                User.class);

        assertNotEquals(user.getStatusCode(), HttpStatus.OK);
    }



    @Test
    public void test_update_user_failure() {
        newUser.setName("New name");
        ResponseEntity<User> user = restTemplate.exchange(ENDPOINT + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(newUser),
                User.class);

        assertNotEquals(user.getStatusCode(), HttpStatus.OK);
    }


}
