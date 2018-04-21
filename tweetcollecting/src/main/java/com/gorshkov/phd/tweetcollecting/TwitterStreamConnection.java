package com.gorshkov.phd.tweetcollecting;

import com.gorshkov.phd.core.constants.Language;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TwitterStreamConnection {
    private static TwitterStreamConnection instance;
    private BlockingQueue<String> messageQueue;
    private BasicClient client;

    private TwitterStreamConnection() {
        this.messageQueue = new LinkedBlockingQueue<>(10000);
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        endpoint.languages(new ArrayList<>(Arrays.asList(Language.RUSSIAN.getCodeFromLanguage())));
        endpoint.trackTerms(new ArrayList<>(
                Arrays.asList("фитнес", "#fitness", "#фитнес", "тренировка", "#тренировка", "#фитнесклуб", "#ЗОЖ")
        ));
//        Location usa = new Location(new Location.Coordinate(-123.730725, 24.323892), new Location.Coordinate(-62.844275, 48.555015));
//        Location newYork = new Location(new Location.Coordinate(-77.505715, 38.615968), new Location.Coordinate(-73.289025, 41.207485));
//        Location california = new Location(new Location.Coordinate(-125.895387, 31.452910), new Location.Coordinate(-119.488694, 42.239864));
//        List<Location> locations = new ArrayList<>();
//        locations.add(usa);
//        endpoint.locations(locations);
        endpoint.stallWarnings(false);
        Authentication auth = new OAuth1(
                TwitterConfig.CONSUMER_KEY, TwitterConfig.CONSUMER_SECRET,
                TwitterConfig.TOKEN, TwitterConfig.TOKEN_SECRET
        );
        this.client = new ClientBuilder()
                .name("sampleExampleClient")
                .hosts(Constants.STREAM_HOST)
                .endpoint(endpoint)
                .authentication(auth)
                .processor(new StringDelimitedProcessor(messageQueue))
                .build();
    }

    public static String getNextMessage() {
        try {
            if (instance == null)
                throw new RuntimeException("Singleton has to be created");
            return instance.messageQueue.poll(1, TimeUnit.SECONDS);
        }
        catch (InterruptedException ex) {
            return null;
        }
    }

//    public static TwitterStreamConnection getInstance() throws RuntimeException {
//        if (instance != null)
//            throw new RuntimeException("Singleton has been already created");
//        instance = new TwitterStreamConnection(
//                TwitterConfig.CONSUMER_KEY, TwitterConfig.CONSUMER_SECRET,
//                TwitterConfig.TOKEN, TwitterConfig.TOKEN_SECRET);
//        return instance;
//    }

    public static TwitterStreamConnection getInstance() throws RuntimeException {
        if (instance == null)
            instance = new TwitterStreamConnection();
        return instance;
    }

    public BlockingQueue<String> getMessageQueue() {
        return messageQueue;
    }

    public BasicClient getClient() {
        return client;
    }
}
