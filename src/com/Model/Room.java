package com.Model;

import com.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private String room_type;
    private int stock;
    private int adult_price1;
    private int adult_price2;
    private int child_price1;
    private int child_price2;
    private int hotel_id;

    private Hotel hotel;

    public Room(int id, String room_type, int stock, int adult_price1, int child_price1, int adult_price2, int child_price2, int hotel_id) {
        this.id = id;
        this.room_type = room_type;
        this.stock = stock;
        this.adult_price1 = adult_price1;
        this.child_price1 = child_price1;
        this.adult_price2 = adult_price2;
        this.child_price2 = child_price2;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public Room(int id, String room_type, int stock, int adult_price1, int child_price1, int hotel_id) {
        this.id = id;
        this.room_type = room_type;
        this.stock = stock;
        this.adult_price1 = adult_price1;
        this.child_price1 = child_price1;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public Room() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAdult_price1() {
        return adult_price1;
    }

    public void setAdult_price1(int adult_price1) {
        this.adult_price1 = adult_price1;
    }

    public int getAdult_price2() {
        return adult_price2;
    }

    public void setAdult_price2(int adult_price2) {
        this.adult_price2 = adult_price2;
    }

    public int getChild_price1() {
        return child_price1;
    }

    public void setChild_price1(int child_price1) {
        this.child_price1 = child_price1;
    }

    public int getChild_price2() {
        return child_price2;
    }

    public void setChild_price2(int child_price2) {
        this.child_price2 = child_price2;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static ArrayList<Room> getList(){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setRoom_type(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setAdult_price1(rs.getInt("adult_price1"));
                obj.setChild_price1(rs.getInt("child_price1"));
                obj.setAdult_price2(rs.getInt("adult_price2"));
                obj.setChild_price2(rs.getInt("child_price2"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }


//oda özelliklerini oda id ye göre yazmak için gerekli metod
    public static Room getFetch(int id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Room(rs.getInt("id"), rs.getString("room_type"), rs.getInt("stock"), rs.getInt("adult_price1"),
                        rs.getInt("child_price1"), rs.getInt("adult_price2"), rs.getInt("child_price2"), rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Room getFetchByHotelID(int id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Room(rs.getInt("id"), rs.getString("room_type"), rs.getInt("stock"), rs.getInt("adult_price1"),
                        rs.getInt("child_price1"), rs.getInt("adult_price2"), rs.getInt("child_price2"), rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }



}
