package com.getjavajob.bezfamilnyydg.service;

import com.getjavajob.bezfamilnyydg.dao.AccountRepository;
import com.getjavajob.bezfamilnyydg.dao.FriendshipLightRepository;
import com.getjavajob.bezfamilnyydg.dao.RequestToFriendsLightRepository;
import com.getjavajob.bezfamilnyydg.dao.RoleRepository;
import com.getjavajob.bezfamilnyydg.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService implements com.getjavajob.bezfamilnyydg.service.interfaces.AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RequestToFriendsLightRepository requestToFriendsLightRepository;
    @Autowired
    private FriendshipLightRepository friendshipLightRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void setRequestToFriendsLightRepository(RequestToFriendsLightRepository requestToFriendsLightRepository) {
        this.requestToFriendsLightRepository = requestToFriendsLightRepository;
    }

    @Override
    public void setFriendshipLightRepository(FriendshipLightRepository friendshipLightRepository) {
        this.friendshipLightRepository = friendshipLightRepository;
    }

    @Override
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> searchInNameSurname(String pattern) {
        String patternForQuery = preparePatternForQuery(pattern);
        return accountRepository.findByNameLikeOrSurnameLikeAllIgnoreCase(patternForQuery, patternForQuery);
    }

    @Override
    public Account getById(int accId) {
        return accountRepository.findOne(accId);
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account createAccount(Account account) {
        setDefaultRoles(account);
        return accountRepository.save(account);
    }

    @Override
    public void update(Account accountForUpdate) {
        if (accountForUpdate.getId() == 0) {
            Account accountFromDB = accountRepository.findByEmail(accountForUpdate.getEmail());
            if (accountFromDB != null) {
                accountForUpdate.setId(accountFromDB.getId());
            }
        }
        accountRepository.save(accountForUpdate);
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public int countOfSearchedAccounts(String pattern) {
        String patternForQuery = preparePatternForQuery(pattern);
        return accountRepository.countSearchedRecordsByNameOrSurname(patternForQuery, patternForQuery);
    }

    @Override
    public List<Account> searchInNameSurnameForPagination(String pattern, int numberOfPage, int maxResult) {
        String patternForQuery = preparePatternForQuery(pattern);
        return accountRepository.findByNameLikeOrSurnameLikeAllIgnoreCase(patternForQuery, patternForQuery, new PageRequest(numberOfPage, maxResult));
    }

    @Override
    public Set<Friendship> getFriendships(int id) {
        Account account = accountRepository.findOne(id);
        return account.getFriendships();
    }

    @Override
    public Set<RequestToFriends> getTakenRequestsToFriends(int id) {
        Account account = accountRepository.findOne(id);
        return account.getTakenRequestsToFriends();
    }

    @Override
    public Set<RequestToFriends> getSentRequestsToFriends(int id) {
        Account account = accountRepository.findOne(id);
        return account.getSentRequestsToFriends();
    }

    @Override
    public boolean isExistRequestToFriends(int idAccFrom, int idAccTo) {
        return requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(idAccFrom, idAccTo) != null;
    }

    @Override
    public boolean isFriends(int accId, int accIdFriend) {
        return friendshipLightRepository.findByAccIdAndAccIdFriend(accId, accIdFriend) != null;
    }

    @Override
    public void unsubscribeAccount(int id, int idForUnsubscribe) {
        requestToFriendsLightRepository.delete(requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(id, idForUnsubscribe));
    }

    @Override
    public void subscribe(int accIdFrom, int accIdTo) {
        boolean isExistResponsedRequest = requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(accIdTo, accIdFrom) != null;
        if (isExistResponsedRequest) {
            //delete subscribes because has response friend request and add friendships
            requestToFriendsLightRepository.delete(requestToFriendsLightRepository.findByAccIdFromAndAccIdTo(accIdTo, accIdFrom));
            friendshipLightRepository.save(new FriendshipLight(accIdFrom, accIdTo));
            friendshipLightRepository.save(new FriendshipLight(accIdTo, accIdFrom));
        } else {
            requestToFriendsLightRepository.save(new RequestToFriendsLight(accIdFrom, accIdTo));
        }
    }

    @Override
    public void deleteFriendships(int id, int idOfFriend) {
        friendshipLightRepository.delete(friendshipLightRepository.findByAccIdAndAccIdFriend(id, idOfFriend));
        friendshipLightRepository.delete(friendshipLightRepository.findByAccIdAndAccIdFriend(idOfFriend, id));
        requestToFriendsLightRepository.save(new RequestToFriendsLight(idOfFriend, id));
    }

    private static String preparePatternForQuery(String pattern) {
        return "%" + pattern + "%";
    }

    private void setDefaultRoles(Account account) {
        Role role = roleRepository.findByName(ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);
    }
}
