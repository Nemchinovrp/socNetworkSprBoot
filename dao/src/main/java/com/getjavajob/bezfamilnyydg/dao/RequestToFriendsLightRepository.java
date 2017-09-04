package com.getjavajob.bezfamilnyydg.dao;

import com.getjavajob.bezfamilnyydg.models.RequestToFriendsLight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RequestToFriendsLightRepository extends CrudRepository<RequestToFriendsLight, Integer> {
    RequestToFriendsLight findByAccIdFromAndAccIdTo(int accIdFrom, int accIdTo);
}