package com.engineering.dokkan.data.models;

public class Notification {
    private String notification_name;
    private String notificaton_info;
    private String notification_date;
    private   int notificaton_image;

    public Notification(String notification_name, String notificaton_info, int notificaton_image, String notification_date) {
        this.notification_name = notification_name;
        this.notificaton_info = notificaton_info;
        this.notificaton_image = notificaton_image;
        this.notification_date = notification_date;
    }

    public String getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(String notification_date) {
        this.notification_date = notification_date;
    }

    public String getNotification_name() {
        return notification_name;
    }

    public void setNotification_name(String notification_name) {
        this.notification_name = notification_name;
    }

    public String getNotificaton_info() {
        return notificaton_info;
    }

    public void setNotificaton_info(String notificaton_info) {
        this.notificaton_info = notificaton_info;
    }

    public int getNotificaton_image() {
        return notificaton_image;
    }

    public void setNotificaton_image(int notificaton_image) {
        this.notificaton_image = notificaton_image;
    }
}
