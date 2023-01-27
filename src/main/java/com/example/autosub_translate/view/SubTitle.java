/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.autosub_translate.view;

/**
 *
 * @author ANH_TUAN
 */
public class SubTitle {
    private int id;
    private String startTime;
    private String endTime;
    private String text;
    private String originalText;

    public SubTitle(){
    }
    
    public SubTitle(int id, String startTime, String endTime, String text, String originalText) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.text = text;
        this.originalText = originalText;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }
    
    
    
}
