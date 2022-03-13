package dialog_construction;

import sockets.Data_Prerunner;
import dialogs.Dialog_Main;
import dialog_action.*;
import entity.*;
import swing_tables.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class Just_Product_Type {
    private Dialog_Main frameDialog;
    private Dialog_Main currentDialog;
    private String title;
    private Class<? extends IEntity> currentClass;
    private ArrayList<IEntity> IEntities = new ArrayList<>();

    public Just_Product_Type(Dialog_Main frameDialog, Class<? extends IEntity> currentClass, String title){
        this.frameDialog = frameDialog;
        this.currentClass = currentClass;
        this.title = "Сущность (Системы): " + title;
    }

    public void create(){
        int height = currentClass.getName().equals("entity.SalonIEntity") ? 710 : 760;
        height = currentClass.getName().equals("entity.TransferIEntity") ||
                currentClass.getName().equals("entity.ModeratorIEntity") ? 560 : height;

            currentDialog = new Dialog_Main(title, 800, height);
        addWindowListener();
        prepareDialog();
        setEventsForButtons();
        currentDialog.setVisible(true);
    }

    private void addWindowListener(){
        WindowListener windowListener = new WindowListener() {
            public void windowActivated(WindowEvent event) {}
            public void windowClosed(WindowEvent event) {}
            public void windowDeactivated(WindowEvent event) {}
            public void windowDeiconified(WindowEvent event) {}
            public void windowIconified(WindowEvent event) {}
            public void windowOpened(WindowEvent event) {
                frameDialog.setVisible(false);
            }
            public void windowClosing(WindowEvent event) {
                frameDialog.setVisible(true);
            }
        };
        currentDialog.addWindowListener(windowListener);
        currentDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void prepareDialog(){
        if((IEntities = Just_Product_Type.getData(currentClass)) == null) {
            JOptionPane.showMessageDialog(currentDialog, "Ошибка получения данных!", "Проверте корректность, ПОЖАЛУЙСТА",JOptionPane.PLAIN_MESSAGE);
            currentDialog.dispose();
            return;
        }
        currentDialog.addTable(750, 200, 25, 20, Just_Product_Type.getModel(IEntities, currentClass));

        currentDialog.addButton(300, 30,250, 240, "Добавить");
        currentDialog.addLabel(300, 30,250, 290, "Артикул стрижки:");
        currentDialog.addTextField(300, 30,250, 320);
        currentDialog.addButton(300, 30,250, 370, "Редактировать");
        currentDialog.addButton(300, 30,250, 420, "Удалить");
        currentDialog.addButton(300, 30,250, 470, "Документирование");
        if(!currentClass.getName().equals("entity.TransferIEntity") && !currentClass.getName().equals("entity.ModeratorIEntity")) {
            currentDialog.addButton(300, 30, 250, 520, "Поиск");
            currentDialog.addButton(300, 30, 250, 570, "Сортировка");
            currentDialog.addButton(300, 30, 250, 620, "Сброс");
        }
        if(!currentClass.getName().equals("entity.SalonIEntity") && !currentClass.getName().equals("entity.TransferIEntity")
                && !currentClass.getName().equals("entity.ModeratorIEntity"))
            currentDialog.addButton(300, 30, 250, 670, "Графичекая аналитика");
    }

    private static ArrayList<IEntity> getData(Class<? extends IEntity> currentClass){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return Data_Prerunner.getCategorys("", "");
            case "entity.HaircutIEntity":      return Data_Prerunner.getHaircuts("", "");
            case "entity.PurchaseIEntity":     return Data_Prerunner.getPurchases("", "");
            case "entity.MasterIEntity":     return Data_Prerunner.getMasters("", "");
            case "entity.UserIEntity":         return Data_Prerunner.getUsers("", "");
            case "entity.SalonIEntity":    return Data_Prerunner.getSalons("", "");
            case "entity.TransferIEntity":     return Data_Prerunner.getMastersHaircuts("", "");
            case "entity.ModeratorIEntity":         return Data_Prerunner.getUserCategorys("", "");
        }
    }

    private static AbstractTableModel getModel(ArrayList<IEntity> IEntity, Class<? extends IEntity> currentClass){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return new CategoryTM(IEntity, true);
            case "entity.HaircutIEntity":      return new HaircutTM(IEntity, true);
            case "entity.PurchaseIEntity":     return new PurchaseTM(IEntity, true);
            case "entity.MasterIEntity":     return new MasterTM(IEntity, true);
            case "entity.UserIEntity":         return new UserTM(IEntity, true);
            case "entity.SalonIEntity":    return new SalonTM(IEntity, true);
            case "entity.TransferIEntity":      return new TransferTM(IEntity, true);
            case "entity.ModeratorIEntity":         return new ModeratorTM(IEntity, true);
        }
    }

    protected void setEventsForButtons(){
        setEventAddRed();
        setEventDel();
        setEventSave();
        if(!currentClass.getName().equals("entity.TransferIEntity") && !currentClass.getName().equals("entity.ModeratorIEntity")) {
            setEventSearch();
            setEventSort();
            setEventReset();
        }
        if(!currentClass.getName().equals("entity.SalonIEntity") && !currentClass.getName().equals("entity.TransferIEntity")
            && !currentClass.getName().equals("entity.ModeratorIEntity"))
            setEventGetInfo();
    }

    private void setEventAddRed(){
        ActionListener actionListener = new CRUD_Concrete_Update(currentDialog, currentClass, IEntities, this);
        currentDialog.buttons.get(0).addActionListener(actionListener);
        currentDialog.buttons.get(1).addActionListener(actionListener);
    }

    private void setEventDel(){
        ActionListener actionListener = new CRUD_Concrete_Delete(currentDialog, currentClass, IEntities, this);
        currentDialog.buttons.get(2).addActionListener(actionListener);
    }


    private void setEventSave(){
        Service_UI_Report actionListener = new Service_UI_Report(IEntities, currentClass, frameDialog);
        currentDialog.buttons.get(3).addActionListener(actionListener);
    }

    private void setEventSearch(){
        ActionListener actionListener = new CRUD_Concrete_Search(currentDialog, currentClass);
        currentDialog.buttons.get(4).addActionListener(actionListener);
    }

    private void setEventSort(){
        CRUD_Concrete_Ordering actionListener = new CRUD_Concrete_Ordering(currentDialog, currentClass);
        currentDialog.buttons.get(5).addActionListener(actionListener);
    }

    private void setEventReset(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        };
        currentDialog.buttons.get(6).addActionListener(actionListener);
    }

    public ArrayList<IEntity> updateData(){
        if((IEntities = Just_Product_Type.getData(currentClass)) == null) {
            JOptionPane.showMessageDialog(currentDialog, "Ошибка получения данных!", "Проверте корректность, ПОЖАЛУЙСТА",JOptionPane.PLAIN_MESSAGE);
            currentDialog.dispose();
        }
        currentDialog.tables.get(0).setModel(getModel(IEntities, currentClass));
        return IEntities;
    }


    private void setEventGetInfo(){
        Service_UI_Chart actionListener = new Service_UI_Chart(currentClass, this);
        currentDialog.buttons.get(7).addActionListener(actionListener);
    }

}
