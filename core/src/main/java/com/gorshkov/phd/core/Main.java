package com.gorshkov.phd.core;

import com.gorshkov.phd.core.constants.Source;
import com.gorshkov.phd.core.message.Message;
import com.gorshkov.phd.core.message.MessageFactory;

/**
 * @author Nikita
 */
public class Main {
    public static void main(String[] args) {
        Message msg = MessageFactory.createMessage(Source.TWITTER, "");
        System.out.println(msg);
    }
}
