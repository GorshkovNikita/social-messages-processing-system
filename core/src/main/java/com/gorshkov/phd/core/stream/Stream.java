package com.gorshkov.phd.core.stream;

import com.gorshkov.phd.core.message.Message;

/**
 * @author Nikita
 */
public interface Stream {
    Message getNextMessage();
}
