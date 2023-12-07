package com.example.hotelview.paymentdetails;

public class PaymentConstructor {
    private String
            txtHolderName, displayHotelName, displayRoomType, displayCheckInDate, displayCheckOutDate,
            displayNumP, displayNumNight,
            displayExpiration, txtCardNumber, txtCvc, displayFinalPrice;

    public PaymentConstructor(String txtHolderName, String displayHotelName, String displayRoomType, String displayCheckInDate, String displayCheckOutDate, String displayNumP, String displayNumNight, String displayExpiration, String txtCardNumber, String txtCvc, String displayFinalPrice) {
        this.txtHolderName = txtHolderName;
        this.displayHotelName = displayHotelName;
        this.displayRoomType = displayRoomType;
        this.displayCheckInDate = displayCheckInDate;
        this.displayCheckOutDate = displayCheckOutDate;
        this.displayNumP = displayNumP;
        this.displayNumNight = displayNumNight;
        this.displayExpiration = displayExpiration;
        this.txtCardNumber = txtCardNumber;
        this.txtCvc = txtCvc;
        this.displayFinalPrice = displayFinalPrice;
    }

    public String getTxtHolderName() {
        return txtHolderName;
    }

    public void setTxtHolderName(String txtHolderName) {
        this.txtHolderName = txtHolderName;
    }

    public String getDisplayHotelName() {
        return displayHotelName;
    }

    public void setDisplayHotelName(String displayHotelName) {
        this.displayHotelName = displayHotelName;
    }

    public String getDisplayRoomType() {
        return displayRoomType;
    }

    public void setDisplayRoomType(String displayRoomType) {
        this.displayRoomType = displayRoomType;
    }

    public String getDisplayCheckInDate() {
        return displayCheckInDate;
    }

    public void setDisplayCheckInDate(String displayCheckInDate) {
        this.displayCheckInDate = displayCheckInDate;
    }

    public String getDisplayCheckOutDate() {
        return displayCheckOutDate;
    }

    public void setDisplayCheckOutDate(String displayCheckOutDate) {
        this.displayCheckOutDate = displayCheckOutDate;
    }

    public String getDisplayNumP() {
        return displayNumP;
    }

    public void setDisplayNumP(String displayNumP) {
        this.displayNumP = displayNumP;
    }

    public String getDisplayNumNight() {
        return displayNumNight;
    }

    public void setDisplayNumNight(String displayNumNight) {
        this.displayNumNight = displayNumNight;
    }

    public String getDisplayExpiration() {
        return displayExpiration;
    }

    public void setDisplayExpiration(String displayExpiration) {
        this.displayExpiration = displayExpiration;
    }

    public String getTxtCardNumber() {
        return txtCardNumber;
    }

    public void setTxtCardNumber(String txtCardNumber) {
        this.txtCardNumber = txtCardNumber;
    }

    public String getTxtCvc() {
        return txtCvc;
    }

    public void setTxtCvc(String txtCvc) {
        this.txtCvc = txtCvc;
    }

    public String getDisplayFinalPrice() {
        return displayFinalPrice;
    }

    public void setDisplayFinalPrice(String displayFinalPrice) {
        this.displayFinalPrice = displayFinalPrice;
    }
}