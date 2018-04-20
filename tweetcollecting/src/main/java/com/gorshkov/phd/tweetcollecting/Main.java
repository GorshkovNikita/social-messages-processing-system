package com.gorshkov.phd.tweetcollecting;

import com.gorshkov.phd.core.constants.Source;
import com.gorshkov.phd.core.message.Message;
import com.gorshkov.phd.core.message.MessageFactory;

public class Main {
    public static void main(String[] args) {
        TwitterStreamConnection.getInstance().getClient().connect();

        while (true) {
            if (TwitterStreamConnection.getInstance().getClient().isDone()) {
                System.out.println(
                        "Client connection closed unexpectedly: " +
                        TwitterStreamConnection.getInstance().getClient().getExitEvent().getMessage()
                );
                break;
            }
            String msg = TwitterStreamConnection.getNextMessage();
            if (msg == null) {
                System.out.println("Did not receive a message in 1 second");
            }
            else {
                Message message = MessageFactory.createMessage(Source.TWITTER, msg);
                if (message != null)
                    System.out.println(message.getText());
            }
        }
        TwitterStreamConnection.getInstance().getClient().stop();
    }
}
