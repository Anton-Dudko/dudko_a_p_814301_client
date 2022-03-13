package dialog_construction;


import entity.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Admin_Dialog extends Just_Dialog {
    @Override
    public void prepareFrameDialog(){
        frameDialog.setSize(800, 470);
        frameDialog.setTitle(frameDialog.getTitle() + " Админ Барбершопа Style :)");
        frameDialog.addButton(500, 30,150, 30, "Категории (Управлять)");
        frameDialog.addButton(500, 30,150, 80, "Стрижки (Управлять)");
        frameDialog.addButton(500, 30,150, 130, "Брони (Управлять)");
        frameDialog.addButton(500, 30,150, 180, "Мастера (Управлять)");
        frameDialog.addButton(500, 30,150, 230, "Барбершопы (Управлять)");
        frameDialog.addButton(500, 30,150, 280, "Мастера Стрижек (Управлять)");
        frameDialog.addButton(500, 30,150, 330, "Категории Пользователей (Управлять)");
        frameDialog.addButton(500, 30,150, 380, "Отмена (Выполнить)");
    }

    @Override
    public void setEventsForButtons() {
        setEventCategory();
        setEventHaircut();
        setEventPurchase();
        setEventMaster();
        setEventSalon();
        setEventMasterHaircut();
        setEventCategoryUser();
        setEventExit();
    }

    protected void setEventCategory(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, CategoryIEntity.class, "\"Категориии\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(0).addActionListener(actionListener);
    }

    protected void setEventHaircut(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, HaircutIEntity.class, "\"Стрижки\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(1).addActionListener(actionListener);
    }

    protected void setEventPurchase(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, PurchaseIEntity.class, "\"Брони\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(2).addActionListener(actionListener);
    }

    protected void setEventMaster(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, MasterIEntity.class, "\"Мастера\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(3).addActionListener(actionListener);
    }

    //protected void setEventUser(){}

    protected void setEventSalon(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, SalonIEntity.class, "\"Барбершопы\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(4).addActionListener(actionListener);
    }

    protected void setEventMasterHaircut(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, TransferIEntity.class, "\"Мастера-Стрижки\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(5).addActionListener(actionListener);
    }

    protected void setEventCategoryUser(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Just_Product_Type categoryDialog = new Just_Product_Type(frameDialog, ModeratorIEntity.class, "\"Категории-Пользователи\"");
                categoryDialog.create();
            }
        };
        frameDialog.buttons.get(6).addActionListener(actionListener);
    }

    protected ActionListener setEventExit(){
        ActionListener actionListener = super.setEventExit();
        frameDialog.buttons.get(7).addActionListener(actionListener);
        return actionListener;
    }
}
