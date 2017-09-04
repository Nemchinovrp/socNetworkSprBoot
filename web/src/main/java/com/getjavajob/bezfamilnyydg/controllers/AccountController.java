package com.getjavajob.bezfamilnyydg.controllers;

import com.getjavajob.bezfamilnyydg.common.util.Initializer;
import com.getjavajob.bezfamilnyydg.common.util.SessionHelper;
import com.getjavajob.bezfamilnyydg.models.*;
import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.getjavajob.bezfamilnyydg.common.Constants.*;
import static com.getjavajob.bezfamilnyydg.common.util.Initializer.createDTOAccount;
import static com.getjavajob.bezfamilnyydg.common.util.Initializer.getAvatarFromAccount;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/CreateAccount")
    public ModelAndView showCreateAccountPage() {
        return new ModelAndView(CREATE_PAGE);
    }

    @RequestMapping("/EditAccount")
    public ModelAndView showEditAccountPage(HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(EDIT_ACCOUNT);
        modelAndView.addObject(AVATAR, Initializer.getAvatarFromAccount((Account) session.getAttribute(CURRENT_ACCOUNT),
                request.getContextPath()));
        return modelAndView;
    }

    @RequestMapping(value = "/CreateAccountLogic", headers = {"content-type=multipart/form-data"})
    public String createAccountLogic(@ModelAttribute Account account, HttpServletRequest request) {

        //prepare account
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        preparePhonesForCreate(account);

        //create in DB
        Account createdAccount = accountService.createAccount(account);

        //prepare model and view
        SessionHelper.addInfoForLoggedUserInSession(request, createdAccount);
        return "redirect:/PersonalPage";

    }

    @RequestMapping(value = "/EditAccountLogic", headers = {"content-type=multipart/form-data"})
    public String editAccountLogic(HttpSession session, @ModelAttribute Account account) {

        //prepare account
        // if password has not been changed
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
            // if password has not been changed
        } else {
            Account currentAccount = (Account) session.getAttribute(CURRENT_ACCOUNT);
            account.setPassword(currentAccount.getPassword());
        }
        // if avatar has not changed, then get old avatar from current account
        if (account.getAvatar() == null || account.getAvatar().length == 0) {
            Account currentAccount = (Account) session.getAttribute(CURRENT_ACCOUNT);
            account.setAvatar(currentAccount.getAvatar());
        }
        //prepare phones
        preparePhonesForUpdate(account);

        //edit account in DB
        accountService.update(account);

        //set current edited account
        session.setAttribute(CURRENT_ACCOUNT, account);

        return "redirect:/PersonalPage";
    }

    @RequestMapping("/PersonalPage")
    public ModelAndView showPersonalPage(HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(PERSONAL_PAGE);

        Account accountFromSession = (Account) session.getAttribute(CURRENT_ACCOUNT);
        if (accountFromSession != null) {
            modelAndView.addObject(ACCOUNT_ID_FROM_REQUEST, accountFromSession.getId());
            modelAndView.addObject(AVATAR, Initializer.getAvatarFromAccount(accountFromSession, request.getContextPath()));
            modelAndView.addObject(IS_FRIENDS, true);
        } else {
            Account currentAccount = accountService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if (currentAccount != null) {
                session.setAttribute(CURRENT_ACCOUNT, currentAccount);
                modelAndView.addObject(ACCOUNT_ID_FROM_REQUEST, currentAccount.getId());
                modelAndView.addObject(AVATAR, Initializer.getAvatarFromAccount(currentAccount, request.getContextPath()));
                modelAndView.addObject(IS_FRIENDS, true);
            }
        }

        return modelAndView;
    }

    @RequestMapping("/PageFromSearch")
    public ModelAndView showPersonalPageFromSearch(@RequestParam("id") int id, HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(PERSONAL_PAGE);
        Account accountForView = accountService.getById(id);
        if (accountForView != null) {
            modelAndView.addObject(ACCOUNT_ID_FROM_REQUEST, accountForView.getId());
            modelAndView.addObject(CURRENT_ACCOUNT, accountForView);
            modelAndView.addObject(AVATAR, Initializer.getAvatarFromAccount(accountForView, request.getContextPath()));

            Account authorizedAccount = (Account) session.getAttribute(CURRENT_ACCOUNT);
            if (authorizedAccount != null) {
                boolean isSubscribed = accountService.isExistRequestToFriends(authorizedAccount.getId(), id);
                boolean isFriends = accountService.isFriends(authorizedAccount.getId(), id);
                modelAndView.addObject(IS_FRIENDS, isFriends);
                modelAndView.addObject(IS_SUBSCRIBED, isSubscribed);
            }
        }

        return modelAndView;
    }

    @RequestMapping("/ShowFriends")
    public ModelAndView showFriends(HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(DISPLAY_AVATAR_AND_LINKS);
        Account authorizedAccount = (Account) session.getAttribute(CURRENT_ACCOUNT);
        Map<DTOAccount, String> accountAvatarMap = new HashMap<>();

        if (authorizedAccount != null) {
            //prepare data for view
            Set<Friendship> friendships = accountService.getFriendships(authorizedAccount.getId());
            // maps account and its avatar in string base 64 representation
            Account friend;
            for (Friendship friendship : friendships) {
                friend = friendship.getFriend();
                accountAvatarMap.put(createDTOAccount(friend, true, request.getContextPath()), getAvatarFromAccount(friend, request.getContextPath()));
            }
        }

        modelAndView.addObject(DTO_ACCOUNT_AVATAR_MAP, accountAvatarMap);
        return modelAndView;
    }

    @RequestMapping("/ShowTakenRequests")
    public ModelAndView showTakenRequests(HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(DISPLAY_AVATAR_AND_LINKS);
        Account authorizedAccount = (Account) session.getAttribute(CURRENT_ACCOUNT);
        Map<DTOAccount, String> accountAvatarMap = new HashMap<>();

        if (authorizedAccount != null) {
            //prepare data for view
            Set<RequestToFriends> requestsToFriends = accountService.getTakenRequestsToFriends(authorizedAccount.getId());
            // maps account and its avatar in string base 64 representation
            Account from;
            for (RequestToFriends requestToFriends : requestsToFriends) {
                from = requestToFriends.getFrom();
                accountAvatarMap.put(createDTOAccount(from, true, request.getContextPath()), getAvatarFromAccount(from, request.getContextPath()));
            }
        }

        modelAndView.addObject(DTO_ACCOUNT_AVATAR_MAP, accountAvatarMap);
        return modelAndView;
    }

    @RequestMapping("/Unsubscribe")
    public ModelAndView unsubscribe(@RequestParam("currentAccId") int accIdFrom,
                                    @RequestParam("accIdForUnsubscribe") int accIdTo, HttpSession session, HttpServletRequest request) {
        accountService.unsubscribeAccount(accIdFrom, accIdTo);
        return showPersonalPageFromSearch(accIdTo, session, request);
    }

    @RequestMapping("/Game")
    public String displayGame() {
        return GAME;
    }

    @RequestMapping("/RemoveFromFriends")
    public ModelAndView deleteFriendships(@RequestParam("currentAccId") int id,
                                          @RequestParam("accIdForRemovedFromFriends") int idOfFriend, HttpSession session, HttpServletRequest request) {
        accountService.deleteFriendships(id, idOfFriend);
        return showPersonalPageFromSearch(idOfFriend, session, request);
    }

    @RequestMapping("/SendRequest")
    public ModelAndView subscribe(@RequestParam("currentAccId") int accIdFrom,
                                  @RequestParam("accIdForSubscribe") int accIdTo, HttpSession session, HttpServletRequest request) {
        accountService.subscribe(accIdFrom, accIdTo);
        return showPersonalPageFromSearch(accIdTo, session, request);
    }

    @RequestMapping("/ShowSentRequests")
    public ModelAndView showSentRequests(HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(DISPLAY_AVATAR_AND_LINKS);
        Account authorizedAccount = (Account) session.getAttribute(CURRENT_ACCOUNT);
        Map<DTOAccount, String> accountAvatarMap = new HashMap<>();

        if (authorizedAccount != null) {
            //prepare data for view
            Set<RequestToFriends> requestsToFriends = accountService.getSentRequestsToFriends(authorizedAccount.getId());
            // maps account and its avatar in string base 64 representation
            Account to;
            for (RequestToFriends requestToFriends : requestsToFriends) {
                to = requestToFriends.getTo();
                accountAvatarMap.put(createDTOAccount(to, true, request.getContextPath()), getAvatarFromAccount(to, request.getContextPath()));
            }
        }

        modelAndView.addObject(DTO_ACCOUNT_AVATAR_MAP, accountAvatarMap);
        return modelAndView;
    }


    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(byte[].class,
                new ByteArrayMultipartFileEditor());

        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

            public void setAsText(String value) {
                setValue(LocalDate.parse(value));
            }

        });
    }

    private void preparePhonesForCreate(Account account) {
        Set<PersonalPhone> personalPhones = account.getPersonalPhoneNumbers();
        Set<WorkPhone> workPhones = account.getWorkPhoneNumbers();

        if (personalPhones != null) {
            for (PersonalPhone personalPhone : personalPhones) {
                personalPhone.setAccount(account);
            }
        }
        if (workPhones != null) {
            for (WorkPhone workPhone : workPhones) {
                workPhone.setAccount(account);
            }
        }
    }

    private void preparePhonesForUpdate(Account account) {
        Set<PersonalPhone> personalPhones = account.getPersonalPhoneNumbers();
        Set<WorkPhone> workPhones = account.getWorkPhoneNumbers();

        if (personalPhones != null) {
            for (PersonalPhone personalPhone : personalPhones) {
                personalPhone.setAccount(account);
            }
        } else {
            Set<PersonalPhone> phonesFromDB = accountService.getById(account.getId()).getPersonalPhoneNumbers();
            account.setPersonalPhoneNumbers(phonesFromDB);
            account.getPersonalPhoneNumbers().clear();
        }
        if (workPhones != null) {
            for (WorkPhone workPhone : workPhones) {
                workPhone.setAccount(account);
            }
        } else {
            Set<WorkPhone> phonesFromDB = accountService.getById(account.getId()).getWorkPhoneNumbers();
            account.setWorkPhoneNumbers(phonesFromDB);
            account.getWorkPhoneNumbers().clear();
        }
    }
}
