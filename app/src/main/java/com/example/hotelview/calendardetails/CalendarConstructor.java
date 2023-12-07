package com.example.hotelview.calendardetails;

public class CalendarConstructor {
    private String selectedCheckInDate, selectedCheckOutDate;
    private int numNights, numPersons;

    public CalendarConstructor(String selectedCheckInDate, String selectedCheckOutDate, int numNights, int numPersons) {
        this.selectedCheckInDate = selectedCheckInDate;
        this.selectedCheckOutDate = selectedCheckOutDate;
        this.numNights = numNights;
        this.numPersons = numPersons;
    }

    public String getSelectedCheckInDate() {
        return selectedCheckInDate;
    }

    public void setSelectedCheckInDate(String selectedCheckInDate) {
        this.selectedCheckInDate = selectedCheckInDate;
    }

    public String getSelectedCheckOutDate() {
        return selectedCheckOutDate;
    }

    public void setSelectedCheckOutDate(String selectedCheckOutDate) {
        this.selectedCheckOutDate = selectedCheckOutDate;
    }

    public int getNumNights() {
        return numNights;
    }

    public void setNumNights(int numNights) {
        this.numNights = numNights;
    }

    public int getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }
}