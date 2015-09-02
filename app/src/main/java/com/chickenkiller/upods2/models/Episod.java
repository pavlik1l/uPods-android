package com.chickenkiller.upods2.models;

import com.chickenkiller.upods2.interfaces.IPlayableTrack;
import com.chickenkiller.upods2.utils.GlobalUtils;

/**
 * Created by alonzilberman on 8/31/15.
 */
public class Episod extends Track implements IPlayableTrack{
    private String summary;
    private String length;
    private String duration;
    private String btnDownloadText;
    private String date;

    public Episod(){
        super();
        this.summary="";
        this.length="";
        this.duration="";
        this.btnDownloadText="";
        this.date="";
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return GlobalUtils.formatDateToUS(date);
    }

    @Override
    public String getAudeoUrl() {
        return mp3Url;
    }

    public String getBtnDownloadText() {
        return btnDownloadText;
    }

    public void setBtnDownloadText(String btnDownloadText) {
        this.btnDownloadText = btnDownloadText;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }

}