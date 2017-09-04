package com.getjavajob.bezfamilnyydg.dao;

import com.getjavajob.bezfamilnyydg.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import static com.getjavajob.bezfamilnyydg.dao.utilsForTestDAO.UtilsForTest.initAccount;
import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:spring-dao-overrides.xml"})
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
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
    public void getAll() {
        List<Account> accountsFromDB = (List<Account>) accountRepository.findAll();
        assertEquals(4, accountsFromDB.size());
    }

    @Test
    public void updateAccount1() {
        Account accountForUpdate = accountRepository.findOne(1);
        accountForUpdate.setName("updatedName");
        accountForUpdate.getPersonalPhoneNumbers().add(new PersonalPhone("+7(222)222-22-22", accountForUpdate));
        accountForUpdate.getWorkPhoneNumbers().add(new WorkPhone("+7(222)222-22-22", accountForUpdate));

        accountRepository.save(accountForUpdate);

        Account accountFromDB = accountRepository.findOne(1);
        assertEquals(accountForUpdate, accountFromDB);
    }

    @Test
    public void updateAccount2() {
        Account accountForUpdate = accountRepository.findOne(1);
        accountForUpdate.setName("updatedName");
        accountForUpdate.getPersonalPhoneNumbers().removeAll(accountForUpdate.getPersonalPhoneNumbers());
        accountForUpdate.getWorkPhoneNumbers().removeAll(accountForUpdate.getWorkPhoneNumbers());

        accountRepository.save(accountForUpdate);

        Account accountFromDB = accountRepository.findOne(1);
        assertEquals(accountForUpdate, accountFromDB);
    }

    @Test
    public void countOfSearchedAccounts() {
        int countOfRecords = accountRepository.countSearchedRecordsByNameOrSurname("%num%", "%num%");
        assertEquals(2, countOfRecords);
    }

    @Test
    public void searchInNameSurnameForPagination1() {
        Pageable pageRequest = new PageRequest(0, 4);
        List<Account> searchedAccounts = accountRepository.findByNameLikeOrSurnameLikeAllIgnoreCase("%num%", "%num%", pageRequest);
        assertEquals(2, searchedAccounts.size());
    }

    @Test
    public void getByEmail() {
        Account accountFromDB = accountRepository.findByEmail("sergey@mail.ru");
        assertEquals(initAccount(), accountFromDB);
    }

    @Test
    public void getById() {
        Account accountFromDB = accountRepository.findOne(1);
        assertEquals(accountFromDB, initAccount());
    }

    @Test
    public void deleteByAccount() {
        Account forRemove = accountRepository.findOne(1);
        accountRepository.delete(forRemove);
        Account accountAfterRemove = accountRepository.findOne(1);
        assertEquals(null, accountAfterRemove);
    }

    @Test
    public void getTakenRequestsToFriends() {
        Account account = accountRepository.findOne(1);
        assertEquals(1, account.getTakenRequestsToFriends().size());
    }

    @Test
    public void getSentRequestsToFriends() {
        Account account = accountRepository.findOne(1);
        assertEquals(1, account.getSentRequestsToFriends().size());
    }

    @Test
    public void getFriendships() {
        Account account = accountRepository.findOne(1);
        assertEquals(1, account.getFriendships().size());
    }

    @Test
    public void create() {
        Account forCreate = initAccount();
        String newEmail = "newEmail";
        forCreate.setEmail(newEmail);
        Account accountFromDB = accountRepository.save(forCreate);

        Account expected = initAccount();
        expected.setEmail(newEmail);
        assertEquals(expected, accountFromDB);
    }

    @Test
    public void searchInNameSurname() {
        List<Account> accounts = accountRepository.findByNameLikeOrSurnameLikeAllIgnoreCase("%Sergey%", "%Sergey");
        assertEquals(1, accounts.size());
    }


}
