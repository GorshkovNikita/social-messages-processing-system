package com.gorshkov.phd.core.message;

import com.gorshkov.phd.core.constants.Source;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

/**
 * Фабрика создания сообщений из разных источников.
 * Предполагается, что сообщение закодировано в каком-то виде в строке
 * @author Nikita
 */
public class MessageFactory {
    public static Message createMessage(Source source, String messageString) {
        switch (source) {
            case TWITTER:
                return createTwitterMessage(messageString);
            case VK:
                return createVkMessage(messageString);
            default:
                return null;
        }
    }

    private static Message createTwitterMessage(String tweetJson) {
        try {
            Status status = TwitterObjectFactory.createStatus(tweetJson);
            return new TwitterMessage(status);
        } catch (TwitterException e) {
            // возможно в последствии сделать DummyMessage, если будет необходимо
            return null;
        }
    }

    private static Message createVkMessage(String messageString) {
        return new VkMessage();
    }
}
