package com.getjavajob.bezfamilnyydg.dao;

import com.getjavajob.bezfamilnyydg.models.FriendshipLight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface FriendshipLightRepository extends CrudRepository<FriendshipLight, Integer> {
    FriendshipLight findByAccIdAndAccIdFriend(int accId, int accIdFriend);
}
