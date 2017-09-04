package com.getjavajob.bezfamilnyydg.controllers;

import com.getjavajob.bezfamilnyydg.common.Constants;
import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.DTOAccount;
import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.getjavajob.bezfamilnyydg.common.Constants.*;
import static com.getjavajob.bezfamilnyydg.common.util.Initializer.*;

@Controller
public class Search {
    private static final int COUNT_ACCOUNTS_PER_PAGE = 2;
    @Autowired
    private AccountService accountService;

    @RequestMapping("/Search")
    public ModelAndView searchLogic(@RequestParam(PATTERN_FROM_SEARCH) String pattern, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SEARCH_RESULT);
        if (pattern != null && !pattern.equals("")) {
            //prepare data for view
            long countOfAccountsInDB = accountService.countOfSearchedAccounts(pattern);
            Map<DTOAccount, String> accountAvatarMap = new HashMap<>();
            if (countOfAccountsInDB > 0) {
                List<Account> accounts = accountService.searchInNameSurnameForPagination(pattern, 0, COUNT_ACCOUNTS_PER_PAGE);
                // maps account and its avatar in string base 64 representation
                for (Account account : accounts) {
                    accountAvatarMap.put(createDTOAccount(account, true, request.getContextPath()), getAvatarFromAccount(account, request.getContextPath()));
                }
            }

            modelAndView.addObject(DTO_ACCOUNT_AVATAR_MAP, accountAvatarMap);
            modelAndView.addObject(Constants.COUNT_ACCOUNTS_PER_PAGE, COUNT_ACCOUNTS_PER_PAGE);
            modelAndView.addObject(COUNT_OF_RESULT_SEARCH_PAGES, ((int) Math.ceil((double) countOfAccountsInDB / COUNT_ACCOUNTS_PER_PAGE)));
            modelAndView.addObject(CURRENT_PATTERN_FOR_SEARCH, pattern);
        } else {

        }

        return modelAndView;
    }

    @RequestMapping("/searchAutocomplete")
    @ResponseBody
    public List<DTOAccount> searchForAutocomplete(@RequestParam("pattern") String pattern, HttpServletRequest request) {
        List<Account> accounts = accountService.searchInNameSurname(pattern);
        List<DTOAccount> dtoAccounts = createListOfDTOAccounts(accounts, true, request.getContextPath());
        return dtoAccounts;
    }

    @RequestMapping("/nextResultOfSearch")
    @ResponseBody
    public List<DTOAccount> searchForPagination(@RequestParam("pattern") String pattern, @RequestParam("start") int start,
                                                @RequestParam("maxResult") int maxResult, HttpServletRequest request) {
        List<Account> accounts = accountService.searchInNameSurnameForPagination(pattern, start, maxResult);
        List<DTOAccount> dtoAccounts = createListOfDTOAccounts(accounts, false, request.getContextPath());
        return dtoAccounts;
    }
}
