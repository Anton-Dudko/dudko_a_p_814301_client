package dialog_action;


import dialogs.*;
import entity.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CRUD_Concrete_Ordering extends CRUD_Show {
    private Dialog_Secondary dialodSort;

    public CRUD_Concrete_Ordering(Dialog_Main parentalDialog, Class<? extends IEntity> currentClass){
        super(parentalDialog, currentClass);
    }

    public void actionPerformed(ActionEvent e) {
            prepareSortData();
            addEventSort();
            dialodSort.setVisible(true);
    }

    private void prepareSortData(){
        dialodSort = new Dialog_Secondary("Упорядочивание", 300, 200, true);

        dialodSort.addLabel(200, 30,50, 20, "Поле для упорядовачивания:");
        dialodSort.addComboBox(false,200, 30,50, 50);
        choseDefCols(dialodSort);
        dialodSort.addCheckBox(200, 30,50, 80,"DESC", false);

        dialodSort.addButton(200, 30,50, 110, "Упорядочить");
    }



    private void addEventSort() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choseOrderByString();
                getData();
                dialodSort.dispose();
            }
        };
        dialodSort.buttons.get(0).addActionListener(actionListener);
    }

    private void choseOrderByString(){
        switch (currentClass.getName()) {
            case "entity.CategoryIEntity":     prepareOrderByString(CategoryIEntity.COLUMNS_BY_RUS, CategoryIEntity.COLUMNS); break;
            case "entity.HaircutIEntity":      prepareOrderByString(HaircutIEntity.COLUMNS_BY_RUS, HaircutIEntity.COLUMNS); break;
            case "entity.PurchaseIEntity":     prepareOrderByString(PurchaseIEntity.COLUMNS_BY_RUS, PurchaseIEntity.COLUMNS); break;
            case "entity.MasterIEntity":     prepareOrderByString(MasterIEntity.COLUMNS_BY_RUS, MasterIEntity.COLUMNS); break;
            case "entity.UserIEntity":         prepareOrderByString(UserIEntity.COLUMNS_BY_RUS, UserIEntity.COLUMNS); break;
            case "entity.SalonIEntity":    prepareOrderByString(SalonIEntity.COLUMNS_BY_RUS, SalonIEntity.COLUMNS); break;
        }
        if(dialodSort.checkBoxes.get(0).isSelected()) orderBy += " desc";
    }

    private void prepareOrderByString(String colsRus[], String cols[]){
        String selected = dialodSort.comboBoxes.get(0).getSelectedItem().toString();
        if(selected.equals(colsRus[0])) orderBy = "id";
        for (int i = 1; i < colsRus.length; i++) {
            if(selected.equals(colsRus[i])) orderBy = cols[i-1];
        }
    }

}
