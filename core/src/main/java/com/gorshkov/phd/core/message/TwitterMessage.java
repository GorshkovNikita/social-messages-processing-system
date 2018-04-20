package com.gorshkov.phd.core.message;

import com.gorshkov.phd.core.constants.Language;
import com.gorshkov.phd.core.constants.Source;
import twitter4j.Status;

import java.sql.Timestamp;

public class TwitterMessage implements Message {
    private Status status;

    TwitterMessage(Status status) {
        this.status = status;
    }

    @Override
    public String getId() {
        return Long.toString(status.getId());
    }

    @Override
    public String getText() {
        return status.getText();
    }

    @Override
    public Timestamp getCreateDate() {
        return new Timestamp(status.getCreatedAt().getTime());
    }

    @Override
    public Language getLanguage() {
        return Language.getLanguageFromCode(status.getLang());
    }

    @Override
    public Source getSource() {
        return Source.TWITTER;
    }

    public Status getStatus() {
        return status;
    }
}
