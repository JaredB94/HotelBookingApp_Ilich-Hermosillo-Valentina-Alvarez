    package com.example.hotelview.hoteldetails;

    public class HotelDetailsConstructor {
        private String txtTitleHotel, txtDescription, txtAmenitiesD, txtExactLocation;
        private int detailID;

        public HotelDetailsConstructor(String txtTitleHotel, String txtDescription, String txtAmenitiesD, String txtExactLocation, int detailID) {
            this.txtTitleHotel = txtTitleHotel;
            this.txtDescription = txtDescription;
            this.txtAmenitiesD = txtAmenitiesD;
            this.txtExactLocation = txtExactLocation;
            this.detailID = detailID;
        }

        public String getTxtTitleHotel() {
            return txtTitleHotel;
        }

        public void setTxtTitleHotel(String txtTitleHotel) {
            this.txtTitleHotel = txtTitleHotel;
        }

        public String getTxtDescription() {
            return txtDescription;
        }

        public void setTxtDescription(String txtDescription) {
            this.txtDescription = txtDescription;
        }

        public String getTxtAmenitiesD() {
            return txtAmenitiesD;
        }

        public void setTxtAmenitiesD(String txtAmenitiesD) {
            this.txtAmenitiesD = txtAmenitiesD;
        }

        public String getTxtExactLocation() {
            return txtExactLocation;
        }

        public void setTxtExactLocation(String txtExactLocation) {
            this.txtExactLocation = txtExactLocation;
        }

        public int getDetailID() {
            return detailID;
        }

        public void setDetailID(int detailID) {
            this.detailID = detailID;
        }
    }