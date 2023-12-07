package com.example.hotelview.roomdetails;

public class RoomDetailsConstructor {
    private String txtRoomType, RoomDetails;
    private int NumPeople;
    private int RoomSize;

    public RoomDetailsConstructor(String txtRoomType,  int NumPeople, int RoomSize, String RoomDetails) {
        this.txtRoomType = txtRoomType;
        this.NumPeople = NumPeople;
        this.RoomSize = RoomSize;
        this.RoomDetails = RoomDetails;
    }

    public String getTxtRoomType() {
        return txtRoomType;
    }

    public void setTxtRoomType(String txtRoomType) {
        this.txtRoomType = txtRoomType;
    }

    public int getNumPeople() {
        return NumPeople;
    }

    public void setNumPeople(int numPeople) {
        NumPeople = numPeople;
    }

    public int getRoomSize() {
        return RoomSize;
    }

    public String getRoomDetails() {
        return RoomDetails;
    }

    public void setRoomDetails(String roomDetails) {
        RoomDetails = roomDetails;
    }

    public void setRoomSize(int roomSize) {
        RoomSize = roomSize;
    }
}
