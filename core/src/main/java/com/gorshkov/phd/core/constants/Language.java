package com.gorshkov.phd.core.constants;

/**
 * @author Nikita
 */
public enum Language {
    UNKNOWN,
    ENGLISH,
    RUSSIAN;

    public static Language getLanguageFromCode(String code) {
        switch (code) {
            case "ru":
                return RUSSIAN;
            case "en":
                return ENGLISH;
            default:
                return UNKNOWN;
        }
    }

    public String getCodeFromLanguage() {
        switch (this) {
            case ENGLISH:
                return "en";
            case RUSSIAN:
                return "ru";
            default:
                return "";
        }
    }

}
