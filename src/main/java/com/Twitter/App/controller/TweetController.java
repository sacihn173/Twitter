package com.Twitter.App.controller;

import com.Twitter.App.models.Tweet;
import com.Twitter.App.respositories.TweetRepo;
import com.Twitter.App.respositories.UserRepo;
import com.Twitter.App.utils.NullAwareBeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tweet")
public class TweetController {

    @Autowired
    private TweetRepo tweetRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/create")
    public ResponseEntity<Tweet> createTweet(@RequestBody Tweet tweet) {
        tweet.setTimePosted(new Date());
        tweet.setUser(userRepo.findById(tweet.getUserId()).orElse(null));
        tweetRepo.save(tweet);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<List<Tweet>> getTweetsByUserId(@RequestParam Integer userId) {
        return ResponseEntity.ok(tweetRepo.findByUserId(userId));
    }

    @GetMapping("/get")
    public ResponseEntity<Tweet> getTweetById(@RequestParam Integer tweetId) {
        return ResponseEntity.ok(tweetRepo.findById(tweetId).orElse(null));
    }

    /**
     * Updates a tweet by copying only 'Not Null' values
     */
    @PutMapping("/update")
    public ResponseEntity<Tweet> updateTweet(@RequestBody Tweet tweet) throws InvocationTargetException, IllegalAccessException {
        if(tweet.getTweetId() == null)
            return ResponseEntity.badRequest().body(null);
        Tweet existingTweet = tweetRepo.findById(tweet.getTweetId()).orElse(null);
        if(existingTweet == null)
            return ResponseEntity.badRequest().body(null);

        BeanUtilsBean notNull = new NullAwareBeanUtils();
        notNull.copyProperties(existingTweet, tweet);
        if(tweet.getUserId() != null)
            existingTweet.setUser(userRepo.findById(existingTweet.getUserId()).orElse(null));
        tweetRepo.save(existingTweet);
        return ResponseEntity.ok(existingTweet);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTweet(@RequestParam Integer tweetId) {
        tweetRepo.deleteById(tweetId);
        return ResponseEntity.ok("Successfully Deleted");
    }

}
