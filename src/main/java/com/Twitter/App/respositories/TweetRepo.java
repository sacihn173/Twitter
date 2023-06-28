package com.Twitter.App.respositories;

import com.Twitter.App.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepo extends JpaRepository<Tweet, Integer> {

    @Query(
            value = "select * from tweets where user_id = :userId",
            nativeQuery = true
    )
    List<Tweet> findByUserId(Integer userId);

}
