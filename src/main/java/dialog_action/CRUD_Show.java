package dialog_action;

import sockets.Data_Prerunner;
import dialogs.*;
import entity.*;
import swing_tables.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

abstract class CRUD_Show implements ActionListener {
    protected Dialog_Main parentalDialog;
    protected String orderBy = "";
    protected String where = "";
    protected Class<? extends IEntity> currentClass;

    public CRUD_Show(Dialog_Main parentalDialog, Class<? extends IEntity> currentClass){
        super();
        this.parentalDialog = parentalDialog;
        this.currentClass = currentClass;
    }


    protected void choseDefCols(Dialog_Secondary dialog){
        switch (currentClass.getName()) {
            case "entity.CategoryIEntity":     addDefCols(dialog, CategoryIEntity.COLUMNS_BY_RUS); break;
            case "entity.HaircutIEntity":      addDefCols(dialog, HaircutIEntity.COLUMNS_BY_RUS); break;
            case "entity.PurchaseIEntity":     addDefCols(dialog, PurchaseIEntity.COLUMNS_BY_RUS); break;
            case "entity.MasterIEntity":     addDefCols(dialog, MasterIEntity.COLUMNS_BY_RUS); break;
            case "entity.UserIEntity":         addDefCols(dialog, UserIEntity.COLUMNS_BY_RUS); break;
            case "entity.SalonIEntity":    addDefCols(dialog, SalonIEntity.COLUMNS_BY_RUS); break;
        }
    }

    private void addDefCols(Dialog_Secondary dialog, String items[]){
        for (int i = 0; i < items.length; i++) {
            dialog.comboBoxes.get(0).addItem(items[i]);
        }
    }

    protected void getData(){
        switch (currentClass.getName()) {
            case "entity.CategoryIEntity":     getDataCategory(); break;
            case "entity.HaircutIEntity":      getDataHaircut(); break;
            case "entity.PurchaseIEntity":     getDataPurchase(); break;
            case "entity.MasterIEntity":     getDataMaster(); break;
            case "entity.UserIEntity":         getDataUser(); break;
            case "entity.SalonIEntity":    getDataSalon(); break;
        }
    }

    private void getDataCategory(){
        ArrayList<IEntity> result;
        if((result = Data_Prerunner.getCategorys(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Ошибка сортировки!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new CategoryTM(result, true));
        }
    }

    private void getDataHaircut(){
        ArrayList<IEntity> result;
        if((result = Data_Prerunner.getHaircuts(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Ошибка сортировки!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new HaircutTM(result, true));
        }
    }


    private void getDataPurchase(){
        ArrayList<IEntity> result;
        if((result = Data_Prerunner.getPurchases(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Ошибка сортировки!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new PurchaseTM(result, true));
        }
    }


    private void getDataMaster(){
        ArrayList<IEntity> result;
        if((result = Data_Prerunner.getMasters(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Ошибка сортировки!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new MasterTM(result, true));
        }
    }

    private void getDataUser(){
        ArrayList<IEntity> result;
        if((result = Data_Prerunner.getUsers(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Ошибка сортировки!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new UserTM(result, true));
        }
    }

    private void getDataSalon(){
        ArrayList<IEntity> result;
        if((result = Data_Prerunner.getSalons(orderBy, where)) == null){
            JOptionPane.showMessageDialog(parentalDialog, "Ошибка сортировки!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        } else {
            parentalDialog.tables.get(0).setModel(new SalonTM(result, true));
        }
    }

}
