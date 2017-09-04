package com.getjavajob.bezfamilnyydg.dao;

import com.getjavajob.bezfamilnyydg.models.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

    @Query("SELECT count(acc.name) FROM Account acc WHERE " +
            "UPPER(acc.name) LIKE UPPER(CONCAT('%',:name, '%')) OR " +
            "UPPER(acc.surname) LIKE UPPER(CONCAT('%',:surname, '%'))")
    int countSearchedRecordsByNameOrSurname(@Param("name") String name, @Param("surname") String surname);

    List<Account> findByNameLikeOrSurnameLikeAllIgnoreCase(String name, String surname, Pageable pageRequest);

    List<Account> findByNameLikeOrSurnameLikeAllIgnoreCase(String name, String surname);

    Account findByEmail(String email);
}
