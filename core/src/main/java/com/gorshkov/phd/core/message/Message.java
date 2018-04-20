package com.gorshkov.phd.core.message;

import com.gorshkov.phd.core.constants.Language;
import com.gorshkov.phd.core.constants.Source;

import java.sql.Timestamp;

/**
 * @author Nikita
 */
public interface Message {
    String getId();
    String getText();
    Timestamp getCreateDate();
    Language getLanguage();
    Source getSource();
}
