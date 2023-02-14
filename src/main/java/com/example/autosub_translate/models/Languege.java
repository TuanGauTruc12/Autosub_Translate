package com.example.autosub_translate.models;

/**
 *
 * @author ANH_TUAN
 */
public class Languege {
    private String languege;
    private String languegeCode;

    public Languege(String languegeCode, String languege) {
        this.languege = languege;
        this.languegeCode = languegeCode;
    }

    public String getLanguege() {
        return languege;
    }

    public void setLanguege(String languege) {
        this.languege = languege;
    }

    public String getLanguegeCode() {
        return languegeCode;
    }

    public void setLanguegeCode(String languegeCode) {
        this.languegeCode = languegeCode;
    }

    @Override
    public String toString() {
        return languege;
    }
}
