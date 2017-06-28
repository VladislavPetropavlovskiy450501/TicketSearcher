package com.vp3000r.ticketsearcher;

/**
 * Created by P on 21.06.17.
 */
public class Trip {
    private String mCityNameFrom;
    private String mCityNameTo;
    private String mIataFrom;
    private String mIataTo;
    private String mAirportNameFrom;
    private String mAirportNameTo;
    private String mDepDate;
    private String mDepTime;
    private String mArrDate;
    private String mArrTime;
    private int mDuration;
    private String mDurationStr;
    private String mAirlineCode;
    private String mAirlineName;
    private String mFlightNumber;
    private String mAircraft;
    private String mAirlineCode2;
    private String mAirlineName2;
    private String mFlightNumber2;
    private String mAircraft2;
    private int mPrice;
    private String mPriceCurrency;
    private String mCode;

    public Trip(String cityNameFrom,   String cityNameTo, String iataFrom, String iataTo, String airportNameFrom, String airportNameTo, String depDate, String depTime, String arrDate, String arrTime,
            int duration, String durationStr, String airlineCode, String airlineName, String flightNumber, String aircraft, String airlineCode2, String airlineName2, String flightNumber2, String aircraft2,
            int price, String priceCurrency, String code){
        this.mCityNameFrom = cityNameFrom;
        this.mCityNameTo = cityNameTo;
        this.mIataFrom = iataFrom;
        this.mIataTo = iataTo;
        this.mAirportNameFrom = airportNameFrom;
        this.mAirportNameTo = airportNameTo;
        this.mDepTime = depTime;
        this.mDepDate = depDate;
        this.mArrDate = arrDate;
        this.mArrTime = arrTime;
        this.mDuration = duration;
        this.mDurationStr = durationStr;
        this.mAirlineCode = airlineCode;
        this.mAirlineName = airlineName;
        this.mFlightNumber = flightNumber;
        this.mAircraft = aircraft;
        this.mAirlineCode2 = airlineCode2;
        this.mAirlineName2 = airlineName2;
        this.mFlightNumber2 = flightNumber2;
        this.mAircraft2 = aircraft2;
        this.mPrice = price;
        this.mPriceCurrency = priceCurrency;
        this.mCode = code;
    }



    public String getCityNameFrom() {

        return mCityNameFrom;
    }
    public String getCityNameTo() {

        return mCityNameTo;
    }
    public String getIataFrom() {

        return mIataFrom;
    }
    public String getIataTo() {

        return mIataTo;
    }
    public String getAirportNameFrom() {

        return mAirportNameFrom;
    }
    public String getAirportNameTo() {

        return mAirportNameTo;
    }
    public String getDepTime() {

        return mDepTime;
    }
    public String getDepDate() {

        return mDepDate;
    }
    public String getArrTime() {

        return mArrTime;
    }
    public String getArrDate() {

        return mArrDate;
    }
    public int getDuration() {

        return mDuration;
    }
    public String getDurationStr() {

        return mDurationStr;
    }
    public String getAirlineCode() {

        return mAirlineCode;
    }
    public String getAirlineCode2() {

        return mAirlineCode2;
    }
    public String getAirlineName() {

        return mAirlineName;
    }
    public String getAirlineName2() {

        return mAirlineName2;
    }
    public String getFlightNumber() {

        return mFlightNumber;
    }
    public String getFlightNumber2() {

        return mFlightNumber2;
    }
    public String getAircraft() {

        return mAircraft;
    }
    public String getAircraft2() {

        return mAircraft2;
    }

    public int getPrice() {

        return mPrice;
    }
    public String getPriceCurrency() {

        return mPriceCurrency;
    }
    public String getCode() {

        return mCode;
    }


}
