package dialog_construction;

import sockets.Data_Prerunner;
import dialog_action.*;
import entity.*;
import swing_tables.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class User_Dialog extends Just_Dialog {
    private ArrayList<IEntity> allHaircuts;

    @Override
    public void prepareFrameDialog(){
        if((allHaircuts = Data_Prerunner.getHaircuts("", "")) == null) {
            JOptionPane.showMessageDialog(frameDialog, "Ошибка получения данных!", "Проверте корректность, ПОЖАЛУЙСТА",JOptionPane.PLAIN_MESSAGE);
            frameDialog.close();
        }
        frameDialog.setTitle(frameDialog.getTitle() + " Пользователь!");

        frameDialog.addTable(750, 200, 25, 20, new HaircutTM(allHaircuts, true));
        frameDialog.addLabel(300, 30,250, 240, "Артикул бранируемой стрижки:");
        frameDialog.addTextField(300, 30,250, 270);
        frameDialog.addButton(300, 30,250, 320, "Бронь");
        frameDialog.addButton(300, 30,250, 370, "Поиск");
        frameDialog.addButton(300, 30,250, 420, "Сортировка");
        frameDialog.addButton(300, 30,250, 470, "Сброс");
        frameDialog.addButton(300, 30,250, 520, "Отмена");
    }

    @Override
    public void setEventsForButtons(){
        addEventBuy();
        setEventSearch();
        setEventSort();
        setEventReset();
        setEventExit();
    }

    private void addEventBuy(){
        ActionListener actionListener = new Service_UI_Book(frameDialog, allHaircuts);
        frameDialog.buttons.get(0).addActionListener(actionListener);
    }


    private void setEventSearch(){
        ActionListener actionListener = new CRUD_Concrete_Search(frameDialog, HaircutIEntity.class);
        frameDialog.buttons.get(1).addActionListener(actionListener);
    }

    private void setEventSort(){
        CRUD_Concrete_Ordering actionListener = new CRUD_Concrete_Ordering(frameDialog, HaircutIEntity.class);
        frameDialog.buttons.get(2).addActionListener(actionListener);
    }

    private void setEventReset(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        };
        frameDialog.buttons.get(3).addActionListener(actionListener);
    }

    private void updateData(){
        frameDialog.tables.get(0).setModel(new HaircutTM(allHaircuts, true));
    }

    protected ActionListener setEventExit(){
        ActionListener actionListener = super.setEventExit();
        frameDialog.buttons.get(4).addActionListener(actionListener);
        return actionListener;
    }

}
