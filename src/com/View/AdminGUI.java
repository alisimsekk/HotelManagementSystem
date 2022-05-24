package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Admin;
import com.Model.Hotel;
import com.Model.HotelSeason;
import com.Model.HotelType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type;
    private Object[] row_hotel_type;
    private int select_hotel_id;

    DefaultTableModel mdl_hotel_season;
    private Object[] row_hotel_season;

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
            select_hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
            loadHotelTypeModel();
            loadHotelSeasonModel();

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

        Object[] col_hotel_season = {"Konaklama Dönemleri"};
        mdl_hotel_season.setColumnIdentifiers(col_hotel_season);
        row_hotel_season = new Object[col_hotel_season.length];
        //loadHotelSeasonModel();
        tbl_hotel_season.setModel(mdl_hotel_season);
        tbl_hotel_season.getTableHeader().setReorderingAllowed(false);

//hotel sezon tablosu kodları başlangıcı




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

    private void loadHotelTypeModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type.getModel();
        clearModel.setRowCount(0);
        int i;
        for (HotelType obj : HotelType.getListByHotelID(select_hotel_id)){
            i = 0;
            row_hotel_type[i++] = obj.getType();
            mdl_hotel_type.addRow(row_hotel_type);
        }
    }

    private void loadHotelSeasonModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_season.getModel();
        clearModel.setRowCount(0);
        int i;
        for (HotelSeason obj : HotelSeason.getListByHotelID(select_hotel_id)){
            i = 0;
            row_hotel_season[i++] = obj.getSeason();
            mdl_hotel_season.addRow(row_hotel_season);
        }
    }




}
