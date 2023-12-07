package com.example.hotelview.hotelmenudetails;

public class HotelMenuConstructor {
    private String hotelName, hotelAddress;
    private int hotelPicture, hotelId;
    private int hotelPriceNight;



    public HotelMenuConstructor(String hotelName, String hotelAddress, int hotelPriceNight, int hotelPicture, int hotelId) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelPriceNight = hotelPriceNight;
        this.hotelPicture = hotelPicture;
        this.hotelId = hotelId;

    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public int getHotelPriceNight() {
        return hotelPriceNight ;
    }

    public void setHotelPriceNight(int hotelPriceNight) {
        this.hotelPriceNight = hotelPriceNight;
    }

    public int getHotelPicture() {
        return hotelPicture;
    }

    public void setHotelPicture(int hotelPicture) {
        this.hotelPicture = hotelPicture;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
