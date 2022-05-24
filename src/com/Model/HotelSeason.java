package com.Model;

import com.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelSeason {

    private int id;
    private String season;
    private int hotel_id;

    private Hotel hotel;

    public HotelSeason(int id, String season, int hotel_id) {
        this.id = id;
        this.season = season;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public HotelSeason() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public static ArrayList<HotelSeason> getList(){
        ArrayList<HotelSeason> hotelSeasonList = new ArrayList<>();
        HotelSeason obj;
        String query = "SELECT * FROM season";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery(query);
            while (rs.next()){
                obj = new HotelSeason();
                obj.setSeason(rs.getString("season"));
                hotelSeasonList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelSeasonList;
    }


//konaklama sezonlarının hotel id sine db den alan metod
    public static ArrayList<HotelSeason> getListByHotelID( int id){
        ArrayList<HotelSeason> hotelSeasonList = new ArrayList<>();
        HotelSeason obj;
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new HotelSeason();
                obj.setSeason(rs.getString("season"));
                hotelSeasonList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelSeasonList;
    }

    public static boolean add(String season, int hotel_id){
        return true;
    }

}
