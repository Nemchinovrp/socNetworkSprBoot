package com.getjavajob.bezfamilnyydg.dao;

import com.getjavajob.bezfamilnyydg.models.FriendshipLight;
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
public class FriendshipLightRepositoryTest {
    @Autowired
    private FriendshipLightRepository friendshipLightRepository;
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
    public void isFriends() {
        FriendshipLight friendshipLight = friendshipLightRepository.findByAccIdAndAccIdFriend(1, 2);
        assertEquals(true, friendshipLight != null);
    }

    @Test
    public void isFriends2() {
        FriendshipLight friendshipLight = friendshipLightRepository.findByAccIdAndAccIdFriend(1213, 2123);
        assertEquals(false, friendshipLight != null);
    }

    @Test
    public void deleteFriendships() {
        FriendshipLight friendshipLight = friendshipLightRepository.findByAccIdAndAccIdFriend(1, 2);
        friendshipLightRepository.delete(friendshipLight);
        assertEquals(false, friendshipLightRepository.findByAccIdAndAccIdFriend(1, 2) != null);
    }

    @Test
    public void addFriendships() {
        friendshipLightRepository.save(new FriendshipLight(3, 4));
        assertEquals(true, friendshipLightRepository.findByAccIdAndAccIdFriend(3, 4) != null);
    }
}
