package com.getjavajob.bezfamilnyydg.dao;

import com.getjavajob.bezfamilnyydg.models.RequestToFriendsLight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:spring-dao-overrides.xml"})
public class RequestToFriendsRepositoryTest {
    @Autowired
    private RequestToFriendsLightRepository requestToFriendsLightRepository;
    @Autowired
    private DataSource dataSource;

    @Before
    public void init() throws InterruptedException, SQLException, FileNotFoundException {
        //initializingData(dataSource);
    }

    @After
    public void after() throws InterruptedException, SQLException, FileNotFoundException {
        //deleteAllAfterTest(dataSource);
    }

    @Test
    public void isExistRequestToFriends() {
        RequestToFriendsLight requestToFriendsLight = requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(1, 2);
        assertEquals(true, requestToFriendsLight != null);
    }

    @Test
    public void isExistRequestToFriends2() {
        RequestToFriendsLight requestToFriendsLight = requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(123, 324);
        assertEquals(false, requestToFriendsLight != null);
    }

    @Test
    public void unsubscribeAccount() {
        requestToFriendsLightRepository.delete(requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(1, 2));
        RequestToFriendsLight requestToFriendsLight = requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(1, 2);
        assertEquals(false, requestToFriendsLight != null);
    }

    @Test
    public void subscribe() {
        requestToFriendsLightRepository.save(new RequestToFriendsLight(3, 4));
        RequestToFriendsLight requestToFriendsLight = requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(3, 4);
        assertEquals(true, requestToFriendsLight != null);
    }
}
