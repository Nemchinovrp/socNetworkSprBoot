package com.getjavajob.bezfamilnyydg.service.interfaces;

import com.getjavajob.bezfamilnyydg.dao.AccountRepository;
import com.getjavajob.bezfamilnyydg.dao.FriendshipLightRepository;
import com.getjavajob.bezfamilnyydg.dao.RequestToFriendsLightRepository;
import com.getjavajob.bezfamilnyydg.dao.RoleRepository;
import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.Friendship;
import com.getjavajob.bezfamilnyydg.models.RequestToFriends;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface AccountService {
    String ROLE_USER = "ROLE_USER";

    Account getById(int accId);

    Account getByEmail(String email);

    @Transactional
    Account createAccount(Account account);

    @Transactional
    void update(Account accountForUpdate);

    @Transactional
    void delete(Account account);

    List<Account> searchInNameSurname(String pattern);

    Set<Friendship> getFriendships(int id);

    Set<RequestToFriends> getTakenRequestsToFriends(int id);

    Set<RequestToFriends> getSentRequestsToFriends(int id);

    void setAccountRepository(AccountRepository accountRepository);

    void setRequestToFriendsLightRepository(RequestToFriendsLightRepository requestToFriendsLightRepository);

    void setFriendshipLightRepository(FriendshipLightRepository friendshipLightRepository);

    void setRoleRepository(RoleRepository roleRepository);

    int countOfSearchedAccounts(String pattern);

    List<Account> searchInNameSurnameForPagination(String pattern, int numberOfPage, int maxResult);

    boolean isExistRequestToFriends(int idAccFrom, int idAccTo);

    boolean isFriends(int accId, int accIdFriend);

    /**
     * @param accIdFrom id of account which has subscribe for id accIdTo
     * @param accIdTo   id for which account with accIdFrom has subscribe
     */
    @Transactional
    void unsubscribeAccount(int accIdFrom, int accIdTo);

    @Transactional
    void subscribe(int accIdFrom, int accIdTo);

    @Transactional
    void deleteFriendships(int id, int idOfFriend);
}
