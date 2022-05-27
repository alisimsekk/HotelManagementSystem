package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;


public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JTabbedPane tab_admin;
    private JPanel pnl_hotel_list;
    private JScrollPane scrl_hotel_list;
    private JTable tbl_hotel_list;
    private JPanel pnl_hotel_top;
    private JButton btn_hotel_add;
    private JScrollPane scrl_hotel_list_left;
    private JTable tbl_hotel_type;
    private JPanel pnl_room_list;
    private JPanel pnl_hotel_bottom;
    private JScrollPane scrl_hotel_list_right;
    private JTable tbl_hotel_season;
    private JPanel pnl_room_top;
    private JPanel pnl_bottom;
    private JScrollPane scrl_room_list;
    private JTable tbl_room_list;
    private JScrollPane scrl_room_property;
    private JTable tbl_room_property;
    private JScrollPane scrl_room_price;
    private JTable tbl_room_price;

    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type;
    private Object[] row_hotel_type;
    private int select_hotel_id;

    DefaultTableModel mdl_hotel_season;
    private Object[] row_hotel_season;

    DefaultTableModel mdl_room_list;
    private Object[] row_room_list;

    DefaultTableModel mdl_room_properties;
    private Object[] row_room_properties;
    private int select_room_id;

    DefaultTableModel mdl_room_price;
    private Object[] row_room_price;



    private final Admin admin;

    public AdminGUI(Admin admin){
        this.admin = admin;
        add(wrapper);
        setSize(1200,600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);


//hotel tablosu kodları başlangıcı
        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_list = {"id", "Hotel Adı", "Yıldız", "Tesis Özellikleri", "Adres", "Telefon", "E-mail"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(75);


//pansiyon tiplerini ve sezonları listelemek için hotel id sini alma.
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                select_hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
            }
            catch (Exception ex){

            }
            loadHotelTypeModel(select_hotel_id);
            loadHotelSeasonModel(select_hotel_id);
            select_hotel_id = 0;
        });

//hotel tablosu kodları bitişi

//hotel konaklama tablosu(yarım pansiyon, tam pansiyon, herşey dahil vs) kodları başlangıcı
        mdl_hotel_type = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_type = {"Pansiyon Tipleri"};
        mdl_hotel_type.setColumnIdentifiers(col_hotel_type);
        row_hotel_type = new Object[col_hotel_type.length];
        //loadHotelTypeModel();
        tbl_hotel_type.setModel(mdl_hotel_type);
        tbl_hotel_type.getTableHeader().setReorderingAllowed(false);

//hotel konaklama tablosu(yarım pansiyon, tam pansiyon, herşey dahil vs) kodları bitişi

//hotel sezon tablosu kodları başlangıcı
        mdl_hotel_season = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_season = {"Dönem Başlangıcı", "Dönem Bitişi"};
        mdl_hotel_season.setColumnIdentifiers(col_hotel_season);
        row_hotel_season = new Object[col_hotel_season.length];
        //loadHotelSeasonModel();
        tbl_hotel_season.setModel(mdl_hotel_season);
        tbl_hotel_season.getTableHeader().setReorderingAllowed(false);

//hotel sezon tablosu kodları başlangıcı


//oda tablosu kodları başlangıcı
        mdl_room_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_list = {"id", "Hotel Adı", "Oda Tipi", "Stok", "Yetişkin Fiyatı(1.Dönem)", "Çocuk Fiyatı(1.Dönem)", "Yetişkin Fiyatı(2.Dönem)","Çocuk Fiyatı(2.Dönem)"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        loadRoomListModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(75);
//oda tablosu kodları bitişi

//oda özellikleri tablosu kodları başlangıcı
        mdl_room_properties = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_properties = {"Oda Özellikleri"};
        mdl_room_properties.setColumnIdentifiers(col_room_properties);
        row_room_properties = new Object[col_room_properties.length];
        //loadRoomPropertiesModel();
        tbl_room_property.setModel(mdl_room_properties);
        tbl_room_property.getTableHeader().setReorderingAllowed(false);

//oda özelliklerini listelemek için tıklanınca oda id sini alma.
        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                select_room_id = Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString());
            }
            catch (Exception ex){

            }
            loadRoomPropertiesModel(select_room_id);
            loadRoomPriceModel(select_room_id);
            select_room_id = 0;
        });

//oda özellikleri tablosu kodları bitişi


//oda fiyatları tablosu kodları başlangıcı
        mdl_room_price = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_price = {"Konaklama Dönemi", "Yetişkin Fiyatı(12+)", "Çocuk Fiyatı(4-11)"};
        mdl_room_price.setColumnIdentifiers(col_room_price);
        row_room_price = new Object[col_room_price.length];
        //loadRoomPriceModel(select_room_id);
        tbl_room_price.setModel(mdl_room_price);
        tbl_room_price.getTableHeader().setReorderingAllowed(false);

//oda fiyatları tablosu kodları bitişi





//admin panel çıkış butonu
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI logGUI = new LoginGUI();

        });

//otel yönetim sayfası otel ekle butonu
        btn_hotel_add.addActionListener(e -> {
            HotelAddGUI hotelAdd = new HotelAddGUI(admin);
            hotelAdd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelModel();
                    tbl_hotel_list.getSelectionModel().clearSelection();
                }
            });
        });



    }


    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getList()){
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getProperty();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getEmail();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    private void loadHotelTypeModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type.getModel();
        clearModel.setRowCount(0);
        int i;
        for (HotelType obj : HotelType.getListByHotelID(id)){
            i = 0;
            row_hotel_type[i++] = obj.getType();
            mdl_hotel_type.addRow(row_hotel_type);
        }
    }

    private void loadHotelSeasonModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_season.getModel();
        clearModel.setRowCount(0);
        int i;
        for (HotelSeason obj : HotelSeason.getListByHotelID(id)){
            i = 0;
            row_hotel_season[i++] = obj.getSeason_start();
            row_hotel_season[i++] = obj.getSeason_end();
            mdl_hotel_season.addRow(row_hotel_season);
        }
    }

    private void loadRoomListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Room obj : Room.getList()){
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = Hotel.getFetch(obj.getHotel_id()).getName();
            row_room_list[i++] = obj.getRoom_type();
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = obj.getAdult_price1();
            row_room_list[i++] = obj.getChild_price1();
            row_room_list[i++] = obj.getAdult_price2();
            row_room_list[i++] = obj.getChild_price2();
            mdl_room_list.addRow(row_room_list);
        }
    }

    private void loadRoomPropertiesModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_property.getModel();
        clearModel.setRowCount(0);
        int i;
        for (RoomProperties obj : RoomProperties.getListByRoomID(id)){
            i = 0;
            row_room_properties[i++] = obj.getProperty();
            mdl_room_properties.addRow(row_room_properties);
        }
    }

    private void loadRoomPriceModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_price.getModel();
        clearModel.setRowCount(0);
        int selRoom_hotel_id =Room.getFetch(id).getHotel_id();

        int i;
        int count = 0;
        for (HotelSeason obj : HotelSeason.getListByHotelID(selRoom_hotel_id)){
            count++;
            if (count == 1){
                i = 0;
                row_room_price[i++] = obj.getSeason_start() + " - " + obj.getSeason_end();
                row_room_price[i++] = Room.getFetchByHotelID(obj.getHotel_id()).getAdult_price1();
                row_room_price[i++] = Room.getFetchByHotelID(obj.getHotel_id()).getChild_price1();
                mdl_room_price.addRow(row_room_price);
            }
            else if (count ==2){
                i = 0;
                row_room_price[i++] = obj.getSeason_start() + " - " + obj.getSeason_end();
                row_room_price[i++] = Room.getFetchByHotelID(obj.getHotel_id()).getAdult_price2();
                row_room_price[i++] = Room.getFetchByHotelID(obj.getHotel_id()).getChild_price2();
                mdl_room_price.addRow(row_room_price);
            }


        }
    }




}
