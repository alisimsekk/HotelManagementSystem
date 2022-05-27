package com.Model;

import com.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomProperties {

    private int id;
    private String property;
    private int room_id;

    private Room room;

    public RoomProperties(int id, String property, int room_id) {
        this.id = id;
        this.property = property;
        this.room_id = room_id;
        this.room = Room.getFetch(room_id);
    }

    public RoomProperties() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setPropery(String property) {
        this.property = property;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

//oda özellilerini oda id sine db den alan metod
    public static ArrayList<RoomProperties> getListByRoomID( int id){
        ArrayList<RoomProperties> roomPropertiesList = new ArrayList<>();
        RoomProperties obj;
        String query = "SELECT * FROM room_properties WHERE room_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new RoomProperties();
                obj.setPropery(rs.getString("property"));
                roomPropertiesList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomPropertiesList;
    }





}
