package dialog_action;


import dialogs.*;
import entity.*;
import conventer.Valid_Session;

import javax.swing.*;
import java.awt.event.*;


public class CRUD_Concrete_Search extends CRUD_Show {
    private Dialog_Secondary dialogSearch;

    public CRUD_Concrete_Search(Dialog_Main parentalDialog, Class<? extends IEntity> currentClass){
        super(parentalDialog, currentClass);
    }

    public void actionPerformed(ActionEvent e) {
        prepareSearchData();
        addEventSearch();
        dialogSearch.setVisible(true);
    }

    private void prepareSearchData(){
        dialogSearch = new Dialog_Secondary("Филтрование", 300, 430, true);

        dialogSearch.addLabel(200, 30,50, 20, "Поле для фильтрования:");
        dialogSearch.addComboBox(false,200, 30,50, 50);
        choseDefCols(dialogSearch);
        dialogSearch.addLabel(200, 30,50, 100, "Значение для фильтрования:");
        dialogSearch.addTextField(200, 30,50, 130);
        dialogSearch.addLabel(200, 30,50, 180, "Знак для фильтрования:");
        dialogSearch.addRadioButtonGroupe(200, 20,50, 210, new String[]{"=", ">", "<", "<>"});

        dialogSearch.addButton(200, 30,50, 340, "Фильтровать");

    }


    private void addEventSearch() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choseWhereStr();
                if(!choseMessageOk()) return;
                getData();
                dialogSearch.dispose();
            }
        };
        dialogSearch.buttons.get(0).addActionListener(actionListener);
    }

    private void choseWhereStr(){
        String apos = "";

        switch (currentClass.getName()) {
            case "entity.CategoryIEntity":
                apos = prepareWhereString(CategoryIEntity.COLUMNS_BY_RUS, CategoryIEntity.COLUMNS, CategoryIEntity.IS_STR); break;
            case "entity.HaircutIEntity":
                apos = prepareWhereString(HaircutIEntity.COLUMNS_BY_RUS, HaircutIEntity.COLUMNS, HaircutIEntity.IS_STR); break;
            case "entity.PurchaseIEntity":
                apos = prepareWhereString(PurchaseIEntity.COLUMNS_BY_RUS, PurchaseIEntity.COLUMNS, PurchaseIEntity.IS_STR); break;
            case "entity.MasterIEntity":
                apos = prepareWhereString(MasterIEntity.COLUMNS_BY_RUS, MasterIEntity.COLUMNS, MasterIEntity.IS_STR); break;
            case "entity.UserIEntity":
                apos = prepareWhereString(UserIEntity.COLUMNS_BY_RUS, UserIEntity.COLUMNS, UserIEntity.IS_STR); break;
            case "entity.SalonIEntity":
                apos = prepareWhereString(SalonIEntity.COLUMNS_BY_RUS, SalonIEntity.COLUMNS, SalonIEntity.IS_STR); break;
        }
        where += " " + getSimbolSearch() + " ? @@@" + dialogSearch.textFields.get(0).getText() + "@@@" + apos;
    }

    private String prepareWhereString(String colsRus[], String cols[], boolean isStr[]){
        String selected = dialogSearch.comboBoxes.get(0).getSelectedItem().toString();
        if(selected.equals(colsRus[0])) where = "id";
        for (int i = 1; i < colsRus.length; i++) {
            if(selected.equals(colsRus[i])) {
                where = cols[i-1];
                if(isStr[i-1]) return "false";
            }
        }
        return "true";
    }

    private String getSimbolSearch() {
        for (int i = 0; i < dialogSearch.radioBoxGroups.get(0).length; i++)
            if(dialogSearch.radioBoxGroups.get(0)[i].isSelected()){
                return dialogSearch.radioBoxGroups.get(0)[i].getText();
            }
        return "=";
    }

    private boolean choseMessageOk(){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return messageIntOk(CategoryIEntity.IS_STR, false);
            case "entity.HaircutIEntity":      return messageIntOk(HaircutIEntity.IS_STR, true);
            case "entity.PurchaseIEntity":     return messageIntOk(PurchaseIEntity.IS_STR, false);
            case "entity.MasterIEntity":     return messageIntOk(MasterIEntity.IS_STR, false);
            case "entity.UserIEntity":         return messageIntOk(UserIEntity.IS_STR, false);
            case "entity.SalonIEntity":    return messageIntOk(SalonIEntity.IS_STR, false);
        }
    }

    private boolean messageIntOk(boolean isStr[], boolean isHaircut){
        int selecterIndex = dialogSearch.comboBoxes.get(0).getSelectedIndex();
        boolean isIntColumns = selecterIndex == 0;
        for (int i = 0; i < isStr.length; i++) {
            if(!isStr[i]){
                isIntColumns = isIntColumns || selecterIndex == i+1;
            }
        }

        return checkCulumns(isIntColumns, isHaircut, selecterIndex);
    }

    private boolean checkCulumns(boolean isIntColumns, boolean isHaircut, int selecterIndex){
        if(isIntColumns) {
            if (!Valid_Session.isInt(dialogSearch.textFields.get(0).getText())) {
                String mes = "Введено не целое число для числового поля!";
                JOptionPane.showMessageDialog(dialogSearch, mes, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        } else if(isHaircut && selecterIndex == 3){
            if (!Valid_Session.isDouble(dialogSearch.textFields.get(0).getText())) {
                String mes = "Введено не число для числового поля!";
                JOptionPane.showMessageDialog(dialogSearch, mes, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        }
        return true;
    }

}
