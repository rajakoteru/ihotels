/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihotels.cassandra.beans;

/**
 *
 * @author Madan KN
 */
public class Destination {
 
    private String hotelIds;
    private String hotelCount;

    /**
     * @return the hotelIds
     */
    public String getHotelIds() {
        return hotelIds;
    }

    /**
     * @param hotelIds the hotelIds to set
     */
    public void setHotelIds(String hotelIds) {
        this.hotelIds = hotelIds;
    }

    /**
     * @return the hotelCount
     */
    public String getHotelCount() {
        return hotelCount;
    }

    /**
     * @param hotelCount the hotelCount to set
     */
    public void setHotelCount(String hotelCount) {
        this.hotelCount = hotelCount;
    }
}
