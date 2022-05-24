package com.View;

import com.Helper.Config;
import com.Helper.DBConnector;
import com.Helper.Helper;
import com.Model.Admin;
import com.Model.Hotel;
import com.Model.HotelType;

import javax.swing.*;


public class HotelAddGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_hotel_add_top;
    private JPanel pnl_hotel_add_left;
    private JPanel pnl_hotel_add_right;
    private JTextField fld_hotel_name;
    private JComboBox cmb_hotel_star;
    private JTextArea txtarea_hotel_property;
    private JTextArea txtarea_hotel_address;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_email;
    private JTextArea txtarea_hotel_seasons;
    private JButton btn_hotel_add;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;

    private String select_star;
    private int added_hotel_id; //yeni eklenen hotelin id sini tutacak ve buna pansiyon türü ile sezonları ekleyecek

    private final Admin admin;

    public HotelAddGUI(Admin admin){
        this.admin = admin;
        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()), Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        radioButton1.setText(Helper.hotelType("1"));
        radioButton2.setText(Helper.hotelType("2"));
        radioButton3.setText(Helper.hotelType("3"));
        radioButton4.setText(Helper.hotelType("4"));
        radioButton5.setText(Helper.hotelType("5"));
        radioButton6.setText(Helper.hotelType("6"));
        radioButton7.setText(Helper.hotelType("7"));


        select_star = cmb_hotel_star.getSelectedItem().toString();

        btn_hotel_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isAreaEmpty(txtarea_hotel_property) ||
                    Helper.isAreaEmpty(txtarea_hotel_address) || Helper.isFieldEmpty(fld_hotel_phone) ||
                    Helper.isFieldEmpty(fld_hotel_email) || Helper.isAreaEmpty(txtarea_hotel_seasons) ||
                    (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() &&
                            !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected() &&
                            !radioButton7.isSelected())){

                Helper.showMsg("fill");
            }
            else {
                String name = fld_hotel_name.getText();
                String star = (String) cmb_hotel_star.getSelectedItem();
                String property = txtarea_hotel_property.getText();
                String address = txtarea_hotel_address.getText();
                String phone = fld_hotel_phone.getText();
                String email = fld_hotel_email.getText();
                String season = txtarea_hotel_seasons.getText();

                if (Hotel.add(name, star, property ,address, phone, email)){
                    Hotel addedHotel = Hotel.getFetch(email);
                    added_hotel_id = addedHotel.getId();

                    for (int i = 1; i<=7; i++){
                        switch (i){
                            case 1:
                                if (radioButton1.isSelected()){
                                    HotelType.add(radioButton1.getText(), added_hotel_id);
                                }
                                break;
                            case 2:
                                if (radioButton2.isSelected()){
                                    HotelType.add(radioButton2.getText(), added_hotel_id);
                                }
                                break;
                            case 3:
                                if (radioButton3.isSelected()){
                                    HotelType.add(radioButton3.getText(), added_hotel_id);
                                }
                                break;
                            case 4:
                                if (radioButton4.isSelected()){
                                    HotelType.add(radioButton4.getText(), added_hotel_id);
                                }
                                break;
                            case 5:
                                if (radioButton5.isSelected()){
                                    HotelType.add(radioButton5.getText(), added_hotel_id);
                                }
                                break;
                            case 6:
                                if (radioButton6.isSelected()){
                                    HotelType.add(radioButton6.getText(), added_hotel_id);
                                }
                                break;
                            case 7:
                                if (radioButton7.isSelected()){
                                    HotelType.add(radioButton7.getText(), added_hotel_id);
                                }
                                break;
                        }
                    }
                }

                Helper.showMsg("done");
                fld_hotel_name.setText(null);
                cmb_hotel_star.setSelectedIndex(0);
                txtarea_hotel_property.setText(null);
                txtarea_hotel_address.setText(null);
                fld_hotel_phone.setText(null);
                fld_hotel_email.setText(null);
                txtarea_hotel_seasons.setText(null);
                radioButton1.setSelected(false);
                radioButton2.setSelected(false);
                radioButton3.setSelected(false);
                radioButton4.setSelected(false);
                radioButton5.setSelected(false);
                radioButton6.setSelected(false);
                radioButton6.setSelected(false);


            }


        });




    }





    public static void main(String[] args){
        HotelAddGUI h = new HotelAddGUI(new Admin(4,"asd","asd","asd","admin"));
    }




}
