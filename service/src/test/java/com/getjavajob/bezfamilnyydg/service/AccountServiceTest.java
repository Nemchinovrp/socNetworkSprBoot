package com.getjavajob.bezfamilnyydg.service;

import com.getjavajob.bezfamilnyydg.dao.AccountRepository;
import com.getjavajob.bezfamilnyydg.dao.FriendshipLightRepository;
import com.getjavajob.bezfamilnyydg.dao.RequestToFriendsLightRepository;
import com.getjavajob.bezfamilnyydg.dao.RoleRepository;
import com.getjavajob.bezfamilnyydg.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.verification.VerificationMode;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static com.getjavajob.bezfamilnyydg.service.UtilForTest.initAccount;
import static com.getjavajob.bezfamilnyydg.service.interfaces.AccountService.ROLE_USER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    private static AccountRepository accountRepositoryMock;
    private static RequestToFriendsLightRepository requestToFriendsLightRepositoryMock;
    private static FriendshipLightRepository friendshipLightRepositoryMock;
    private static RoleRepository roleRepositoryMock;
    private static com.getjavajob.bezfamilnyydg.service.interfaces.AccountService accountServiceInstance;

    @BeforeClass
    public static void beforeClass() {
        accountRepositoryMock = mock(AccountRepository.class);
        requestToFriendsLightRepositoryMock = mock(RequestToFriendsLightRepository.class);
        friendshipLightRepositoryMock = mock(FriendshipLightRepository.class);
        roleRepositoryMock = mock(RoleRepository.class);
        accountServiceInstance = new AccountService();
    }

    @Before
    public void before() {
        accountServiceInstance.setAccountRepository(accountRepositoryMock);
        accountServiceInstance.setRequestToFriendsLightRepository(requestToFriendsLightRepositoryMock);
        accountServiceInstance.setFriendshipLightRepository(friendshipLightRepositoryMock);
        accountServiceInstance.setRoleRepository(roleRepositoryMock);
    }

    @After
    public void after() {
        reset(accountRepositoryMock);
        reset(requestToFriendsLightRepositoryMock);
        reset(friendshipLightRepositoryMock);
        reset(roleRepositoryMock);
    }

    @Test
    public void getAccountById() {
        int accId = 1;
        Account account = new Account();

        when(accountRepositoryMock.findOne(accId)).thenReturn(account);

        Account accountFromDB = accountServiceInstance.getById(accId);

        verify(accountRepositoryMock).findOne(accId);
        assertEquals(account, accountFromDB);
    }

    @Test
    public void getTakenRequestsToFriends() {
        int id = 1;
        Account account = initAccount();
        account.setTakenRequestsToFriends(new HashSet<>());

        when(accountRepositoryMock.findOne(id)).thenReturn(account);

        Set<RequestToFriends> requestsToFriendsFromDB = accountServiceInstance.getTakenRequestsToFriends(id);

        verify(accountRepositoryMock).findOne(id);
        assertEquals(0, requestsToFriendsFromDB.size());
    }

    @Test
    public void getSentRequestsToFriends() {
        int id = 1;
        Account account = initAccount();
        account.setSentRequestsToFriends(new HashSet<>());

        when(accountRepositoryMock.findOne(id)).thenReturn(account);

        Set<RequestToFriends> requestsToFriendsFromDB = accountServiceInstance.getSentRequestsToFriends(id);

        verify(accountRepositoryMock).findOne(id);
        assertEquals(0, requestsToFriendsFromDB.size());
    }

    @Test
    public void getByEmail() {
        String email = "email";
        Account account = new Account();

        when(accountRepositoryMock.findByEmail(email)).thenReturn(account);

        Account accountFromDB = accountServiceInstance.getByEmail(email);

        verify(accountRepositoryMock).findByEmail(email);
        assertEquals(account, accountFromDB);
    }

    @Test
    public void createAccount() {
        Account account = initAccount();
        Role role = new Role();
        role.setName(ROLE_USER);

        when(accountRepositoryMock.save(account)).thenReturn(account);
        when(roleRepositoryMock.findByName(ROLE_USER)).thenReturn(role);

        Account accountFromDB = accountServiceInstance.createAccount(account);

        verify(accountRepositoryMock).save(account);
        verify(roleRepositoryMock).findByName(ROLE_USER);
        assertEquals(account, accountFromDB);
    }

    @Test
    public void isExistRequestToFriends() {
        boolean expectedRes = false;
        int accIdFrom = 1;
        int accIdTo = 2;

        when(requestToFriendsLightRepositoryMock.findByAccIdFromAndAccIdTo(accIdFrom, accIdTo)).thenReturn(null);

        boolean res = accountServiceInstance.isExistRequestToFriends(accIdFrom, accIdTo);

        verify(requestToFriendsLightRepositoryMock).findByAccIdFromAndAccIdTo(accIdFrom, accIdTo);
        assertEquals(expectedRes, res);
    }

    @Test
    public void isFriends() {
        boolean expectedRes = true;
        int accId = 1;
        int accIdFriend = 2;

        when(friendshipLightRepositoryMock.findByAccIdAndAccIdFriend(accId, accIdFriend)).thenReturn(new FriendshipLight(accId, accIdFriend));

        boolean res = accountServiceInstance.isFriends(accId, accIdFriend);

        verify(friendshipLightRepositoryMock).findByAccIdAndAccIdFriend(accId, accIdFriend);
        assertEquals(expectedRes, res);
    }

    @Test
    public void editAccount() {
        Account account = initAccount();

        accountServiceInstance.update(account);

        verify(accountRepositoryMock).save(account);
    }

    @Test
    public void unsubscribeAccount() {
        int accIdFrom = 1;
        int accIdTo = 2;
        RequestToFriendsLight requestToFriendsLight = new RequestToFriendsLight(accIdFrom, accIdTo);

        when(requestToFriendsLightRepositoryMock.findByAccIdFromAndAccIdTo(accIdFrom, accIdTo)).thenReturn(requestToFriendsLight);

        accountServiceInstance.unsubscribeAccount(accIdFrom, accIdTo);

        verify(requestToFriendsLightRepositoryMock).findByAccIdFromAndAccIdTo(accIdFrom, accIdTo);
        verify(requestToFriendsLightRepositoryMock).delete(requestToFriendsLight);
    }

    @Test
    public void subscribe() {
        int accIdFrom = 1;
        int accIdTo = 2;
        RequestToFriendsLight requestToFriendsLight = new RequestToFriendsLight(accIdTo, accIdFrom);
        RequestToFriendsLight requestToFriendsLight2 = new RequestToFriendsLight(accIdFrom, accIdTo);
        FriendshipLight friendshipLight = new FriendshipLight(accIdTo, accIdFrom);
        FriendshipLight friendshipLight2 = new FriendshipLight(accIdFrom, accIdTo);

        when(requestToFriendsLightRepositoryMock.findByAccIdFromAndAccIdTo(accIdTo, accIdFrom)).thenReturn(requestToFriendsLight);

        accountServiceInstance.subscribe(accIdFrom, accIdTo);

        verify(friendshipLightRepositoryMock).save(friendshipLight);
        verify(friendshipLightRepositoryMock).save(friendshipLight2);
        verify(requestToFriendsLightRepositoryMock).delete(requestToFriendsLight);
        verify(requestToFriendsLightRepositoryMock, times(2)).findByAccIdFromAndAccIdTo(accIdTo, accIdFrom);
    }

    @Test
    public void subscribe2() {
        int accIdFrom = 1;
        int accIdTo = 2;
        RequestToFriendsLight requestToFriendsLight = new RequestToFriendsLight(accIdFrom, accIdTo);

        when(requestToFriendsLightRepositoryMock.findByAccIdFromAndAccIdTo(accIdTo, accIdFrom)).thenReturn(null);

        accountServiceInstance.subscribe(accIdFrom, accIdTo);

        verify(requestToFriendsLightRepositoryMock).save(requestToFriendsLight);
    }

    @Test
    public void searchInNameSurnameForPagination() {
        String pattern = "a";
        String preparedPattern = "%a%";
        int numberOfPage = 0;
        int max = 2;
        List<Account> expectedAccounts = new ArrayList<>();
        PageRequest pageRequest = new PageRequest(numberOfPage, max);

        when(accountRepositoryMock.findByNameLikeOrSurnameLikeAllIgnoreCase(preparedPattern, preparedPattern, pageRequest)).thenReturn(expectedAccounts);

        List<Account> actualAccounts = accountServiceInstance.searchInNameSurnameForPagination(pattern, numberOfPage, max);

        verify(accountRepositoryMock).findByNameLikeOrSurnameLikeAllIgnoreCase(preparedPattern, preparedPattern, pageRequest);
        assertEquals(expectedAccounts, actualAccounts);
    }

    @Test
    public void getFriendships() {
        int id = 1;
        Account account = initAccount();
        account.setFriendships(new HashSet<>());

        when(accountRepositoryMock.findOne(id)).thenReturn(account);

        Set<Friendship> friendshipsFromDB = accountServiceInstance.getFriendships(id);

        verify(accountRepositoryMock).findOne(id);
        assertEquals(0, friendshipsFromDB.size());
    }

    @Test
    public void countOfSearchedAccounts() {
        String pattern = "a";
        String preparedPattern = "%a%";
        int expectedResult = 2;

        when(accountRepositoryMock.countSearchedRecordsByNameOrSurname(preparedPattern, preparedPattern)).thenReturn(expectedResult);

        int actualResult = accountServiceInstance.countOfSearchedAccounts(pattern);

        verify(accountRepositoryMock).countSearchedRecordsByNameOrSurname(preparedPattern, preparedPattern);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteAccount() {

        Account account = initAccount();

        accountServiceInstance.delete(account);

        verify(accountRepositoryMock).delete(account);

    }

    @Test
    public void searchInNameSurname() {
        String pattern = "a";
        String preparedPattern = "%a%";
        List<Account> accounts = Arrays.asList(new Account());

        when(accountRepositoryMock.findByNameLikeOrSurnameLikeAllIgnoreCase(preparedPattern, preparedPattern)).thenReturn(accounts);

        List<Account> accountsFromDB = accountServiceInstance.searchInNameSurname(pattern);

        verify(accountRepositoryMock).findByNameLikeOrSurnameLikeAllIgnoreCase(preparedPattern, preparedPattern);
        assertEquals(accounts, accountsFromDB);
    }
}
