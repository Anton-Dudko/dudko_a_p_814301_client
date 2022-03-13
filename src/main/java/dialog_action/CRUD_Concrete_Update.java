package dialog_action;

import sockets.Data_Prerunner;
import dialogs.Dialog_Main;
import dialogs.Dialog_Secondary;
import dialog_construction.Just_Product_Type;
import entity.*;
import conventer.Valid_Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CRUD_Concrete_Update extends CRUD_Store {
    private Dialog_Secondary dialogAdd;

    public CRUD_Concrete_Update(Dialog_Main frameDialog, Class<? extends IEntity> currentClass, ArrayList<IEntity> IEntities, Just_Product_Type parental){
        super(frameDialog, currentClass, IEntities, parental);
    }

    public void actionPerformed(ActionEvent e) {
        boolean isRed;
        isRed = (e.getActionCommand() == "Добавить") ? false : true;
        if(isRed && !isIdCorrect()) return;
        prepareAddData(isRed);
        addEventAdd(isRed);
        dialogAdd.setVisible(true);
    }

    protected void prepareAddData(final boolean isRed){
        dialogAdd = new Dialog_Secondary("Добавление", 300, 420, true);
        int i;
        String columns[] = getColumns();

        if(currentClass.getName().equals("entity.TransferIEntity") || currentClass.getName().equals("entity.ModeratorIEntity")){
            dialogAdd.addLabel(200, 30, 50, 10, columns[2]);
            dialogAdd.addTextField(200, 30, 50, 40);
            dialogAdd.addLabel(200, 30, 50, 90, columns[4]);
            dialogAdd.addTextField(200, 30, 50, 120);
            i = 3;
        } else {
            for (i = 1; i < columns.length; i++) {
                dialogAdd.addLabel(200, 30, 50, 10 + (i - 1) * 80, columns[i]);
                dialogAdd.addTextField(200, 30, 50, 40 + (i - 1) * 80);
            }
        }

        dialogAdd.addButton(200, 30,50, 90+(i-2)*80, "Сохранить");
        dialogAdd.setSize(300, 180+(i-2)*80);
        if (isRed) addDataLast();
    }

    private String[] getColumns(){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return CategoryIEntity.COLUMNS_BY_RUS;
            case "entity.HaircutIEntity":      return HaircutIEntity.COLUMNS_BY_RUS;
            case "entity.PurchaseIEntity":     return PurchaseIEntity.COLUMNS_BY_RUS;
            case "entity.MasterIEntity":     return MasterIEntity.COLUMNS_BY_RUS;
            case "entity.UserIEntity":         return UserIEntity.COLUMNS_BY_RUS;
            case "entity.SalonIEntity":    return SalonIEntity.COLUMNS_BY_RUS;
            case "entity.TransferIEntity":  return TransferIEntity.COLUMNS_BY_RUS;
            case "entity.ModeratorIEntity":     return ModeratorIEntity.COLUMNS_BY_RUS;
        }
    }

    protected void addDataLast(){
        String where = " id = ? @@@" + frameDialog.textFields.get(0).getText() + "@@@" + " ";

        ArrayList<IEntity> result = getData(where);
        if(result == null){
            JOptionPane.showMessageDialog(dialogAdd, "Ошибка поиска!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
            dialogAdd.dispose();
        }
        IEntity IEntity = result.get(0);
        addOneLastData(IEntity);
    }

    private ArrayList<IEntity> getData(String where){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return Data_Prerunner.getCategorys("", where);
            case "entity.HaircutIEntity":      return Data_Prerunner.getHaircuts("", where);
            case "entity.PurchaseIEntity":     return Data_Prerunner.getPurchases("", where);
            case "entity.MasterIEntity":     return Data_Prerunner.getMasters("", where);
            case "entity.UserIEntity":         return Data_Prerunner.getUsers("", where);
            case "entity.SalonIEntity":    return Data_Prerunner.getSalons("", where);
            case "entity.TransferIEntity":      return Data_Prerunner.getMastersHaircuts("", where);
            case "entity.ModeratorIEntity":         return Data_Prerunner.getUserCategorys("", where);
        }
    }

    private void addOneLastData(IEntity IEntity){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     addOneLastDataCategory((CategoryIEntity) IEntity); break;
            case "entity.HaircutIEntity":      addOneLastDataHaircut((HaircutIEntity) IEntity); break;
            case "entity.PurchaseIEntity":     addOneLastDataPurchase((PurchaseIEntity) IEntity); break;
            case "entity.MasterIEntity":     addOneLastDataMaster((MasterIEntity) IEntity); break;
            case "entity.UserIEntity":         addOneLastDataUser((UserIEntity) IEntity); break;
            case "entity.SalonIEntity":    addOneLastDataSalon((SalonIEntity) IEntity); break;
            case "entity.TransferIEntity":      addOneLastDataMasterHaircut((TransferIEntity) IEntity); break;
            case "entity.ModeratorIEntity":         addOneLastDataUserCategory((ModeratorIEntity) IEntity); break;
        }
    }

    private void addOneLastDataCategory(CategoryIEntity model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        dialogAdd.textFields.get(1).setText(String.valueOf(model.getParent_category_id()));
    }

    private void addOneLastDataHaircut(HaircutIEntity model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        dialogAdd.textFields.get(1).setText(model.getDescription());
        dialogAdd.textFields.get(2).setText(String.valueOf(model.getPrice()));
        dialogAdd.textFields.get(3).setText(String.valueOf(model.getCount()));
        dialogAdd.textFields.get(4).setText(model.getStyle_name());
        dialogAdd.textFields.get(5).setText(String.valueOf(model.getCategory_id()));
        dialogAdd.textFields.get(6).setText(String.valueOf(model.getSalon_id()));
    }

    private void addOneLastDataPurchase(PurchaseIEntity model) {
        dialogAdd.textFields.get(0).setText(model.getDate());
        dialogAdd.textFields.get(1).setText(model.getAddress());
        dialogAdd.textFields.get(2).setText(model.getMail());
        dialogAdd.textFields.get(3).setText(String.valueOf(model.getHaircut_id()));
        dialogAdd.textFields.get(4).setText(String.valueOf(model.getCount()));
    }

    private void addOneLastDataMaster(MasterIEntity model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        dialogAdd.textFields.get(1).setText(model.getCountry());
        dialogAdd.textFields.get(2).setText(model.getDate_of_cooperation());
    }

    private void addOneLastDataUser(UserIEntity model) {
        dialogAdd.textFields.get(0).setText(model.getLogin());
        dialogAdd.textFields.get(1).setText(model.getPassword());
        dialogAdd.textFields.get(2).setText(String.valueOf(model.getRole()));
    }

    private void addOneLastDataSalon(SalonIEntity model) {
        dialogAdd.textFields.get(0).setText(model.getName());
        dialogAdd.textFields.get(1).setText(model.getAddress());

    }

    private void addOneLastDataMasterHaircut(TransferIEntity model) {
        dialogAdd.textFields.get(0).setText(String.valueOf(model.getMaster_id()));
        dialogAdd.textFields.get(1).setText(String.valueOf(model.getHaircut_id()));
    }

    private void addOneLastDataUserCategory(ModeratorIEntity model) {
        dialogAdd.textFields.get(0).setText(String.valueOf(model.getuser_id()));
        dialogAdd.textFields.get(1).setText(String.valueOf(model.getcategory_id()));
    }

    protected void addEventAdd(final boolean isRed){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(changeAndCheckOk()){
                    addData(isRed, generateNewModel());
                    parental.updateData();
                    dialogAdd.dispose();
                }
            }
        };
        dialogAdd.buttons.get(0).addActionListener(actionListener);
    }

    private void addData(boolean isRed, IEntity IEntity){
        String error;
        if(isRed){
            if (!"".equals(error = getQueryUpdate(IEntity))) {
                JOptionPane.showMessageDialog(dialogAdd, "Ошибка редактирования: " + error, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            if (!"".equals(error = getQueryInsert(IEntity))) {
                JOptionPane.showMessageDialog(dialogAdd, "Ошибка добавления: " + error, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }


    private IEntity generateNewModel(){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return generateNewCategory();
            case "entity.HaircutIEntity":      return generateNewHaircut();
            case "entity.PurchaseIEntity":     return generateNewPurchase();
            case "entity.MasterIEntity":     return generateNewMaster();
            case "entity.UserIEntity":         return generateNewUser();
            case "entity.SalonIEntity":    return generateNewSalon();
            case "entity.TransferIEntity":      return generateNewMasterHaircut();
            case "entity.ModeratorIEntity":         return generateNewUserCategory();
        }
    }

    private IEntity generateNewCategory() {
        CategoryIEntity model;
        if("".equals(dialogAdd.textFields.get(1).getText()))
            model = new CategoryIEntity(
                    0,
                    dialogAdd.textFields.get(0).getText()
            );
        else
            model = new CategoryIEntity(
                    0,
                    dialogAdd.textFields.get(0).getText(),
                    Integer.valueOf(dialogAdd.textFields.get(1).getText())
            );
        return (IEntity) model;
    }

    private IEntity generateNewHaircut() {
        HaircutIEntity model = new HaircutIEntity(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                Double.valueOf(dialogAdd.textFields.get(2).getText()),
                Integer.valueOf(dialogAdd.textFields.get(3).getText()),
                dialogAdd.textFields.get(4).getText(),
                Integer.valueOf(dialogAdd.textFields.get(5).getText()),
                Integer.valueOf(dialogAdd.textFields.get(6).getText())
        );
        return (IEntity) model;
    }

    private IEntity generateNewPurchase() {
        PurchaseIEntity model = new PurchaseIEntity(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                dialogAdd.textFields.get(2).getText(),
                Integer.valueOf(dialogAdd.textFields.get(3).getText()),
                Integer.valueOf(dialogAdd.textFields.get(4).getText())
        );
        return (IEntity) model;
    }

    private IEntity generateNewMaster() {
        MasterIEntity model = new MasterIEntity(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                dialogAdd.textFields.get(2).getText()
        );
        return (IEntity) model;
    }

    private IEntity generateNewUser() {
        UserIEntity model = new UserIEntity(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText(),
                Integer.valueOf(dialogAdd.textFields.get(2).getText())
        );
        return (IEntity) model;
    }

    private IEntity generateNewSalon() {
        SalonIEntity model = new SalonIEntity(
                0,
                dialogAdd.textFields.get(0).getText(),
                dialogAdd.textFields.get(1).getText()
        );
        return (IEntity) model;
    }

    private IEntity generateNewMasterHaircut() {
        TransferIEntity model = new TransferIEntity(
                0,
                Integer.valueOf(dialogAdd.textFields.get(0).getText()),
                "",
                Integer.valueOf(dialogAdd.textFields.get(1).getText()),
                ""
        );
        return (IEntity) model;
    }

    private IEntity generateNewUserCategory() {
        ModeratorIEntity model = new ModeratorIEntity(
                0,
                Integer.valueOf(dialogAdd.textFields.get(0).getText()),
                "",
                Integer.valueOf(dialogAdd.textFields.get(1).getText()),
                ""
        );
        return (IEntity) model;
    }

    private boolean changeAndCheckOk(){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return checkAddOk(CategoryIEntity.COLUMNS_BY_RUS, CategoryIEntity.IS_STR);
            case "entity.HaircutIEntity":      return checkAddOk(HaircutIEntity.COLUMNS_BY_RUS, HaircutIEntity.IS_STR);
            case "entity.PurchaseIEntity":     return checkAddOk(PurchaseIEntity.COLUMNS_BY_RUS, PurchaseIEntity.IS_STR);
            case "entity.MasterIEntity":     return checkAddOk(MasterIEntity.COLUMNS_BY_RUS, MasterIEntity.IS_STR);
            case "entity.UserIEntity":         return checkAddOk(UserIEntity.COLUMNS_BY_RUS, UserIEntity.IS_STR);
            case "entity.SalonIEntity":    return checkAddOk(SalonIEntity.COLUMNS_BY_RUS, SalonIEntity.IS_STR);
            case "entity.TransferIEntity":      return checkAddOkJoin(TransferIEntity.COLUMNS_BY_RUS);
            case "entity.ModeratorIEntity":         return checkAddOkJoin(ModeratorIEntity.COLUMNS_BY_RUS);
        }
    }

    private boolean checkAddOkJoin(String columns[]){
        String message = "";

        if (!Valid_Session.isInt(dialogAdd.textFields.get(0).getText())) {
            message += "  " + columns[1] + " не целое число;\r\n";
        }

        if (!Valid_Session.isInt(dialogAdd.textFields.get(1).getText())) {
            message += "  " + columns[3] + " не целое число;\r\n";
        }

        if(!message.equals("")) JOptionPane.showMessageDialog(dialogAdd, "Ошибки:\r\n" + message, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        return message.equals("") ? true : false;
    }

    private boolean checkAddOk(String columns[], boolean isStrColumns[]){
        String message = "";
        for (int i = 0; i < isStrColumns.length; i++) {
            if(!isSpecialColumn(i)) {
                if (isStrColumns[i]) {
                    if ("".equals(dialogAdd.textFields.get(i).getText().trim())) {
                        message += "  " + columns[i + 1] + " пусто;\r\n";
                    }
                } else {
                    if (!Valid_Session.isInt(dialogAdd.textFields.get(i).getText())) {
                        message += "  " + columns[i + 1] + " не целое число;\r\n";
                    }
                }
            }
        }
        message += checkAddOkSpecial();
        if(!message.equals("")) JOptionPane.showMessageDialog(dialogAdd, "Ошибки:\r\n" + message, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        return message.equals("") ? true : false;
    }

    private boolean isSpecialColumn(int i){
        boolean answer = false;
        if(currentClass.getName().equals("entity.PurchaseIEntity"))
            if (i == 0 || i == 2)
                answer = true;
        if(currentClass.getName().equals("entity.MasterIEntity"))
            if(i == 2)
                answer = true;
        if(currentClass.getName().equals("entity.HaircutIEntity"))
            if(i == 2)
                answer = true;
        if(currentClass.getName().equals("entity.CategoryIEntity"))
            if(i == 1)
                answer = true;
        if(currentClass.getName().equals("entity.UserIEntity"))
            if(i == 2)
                answer = true;

        return answer;
    }

    private String checkAddOkSpecial(){
        String message = "";
        if(currentClass.getName().equals("entity.PurchaseIEntity")) {
            if (!Valid_Session.isDate(dialogAdd.textFields.get(0).getText()))
                message += "  Некторректная дата;\r\n";
            if (!Valid_Session.isEMail(dialogAdd.textFields.get(2).getText()))
                message += "  Некторректный email;\r\n";
        }
        if(currentClass.getName().equals("entity.MasterIEntity"))
            if(!Valid_Session.isDate(dialogAdd.textFields.get(2).getText()))
                message += "  Некторректная дата;\r\n";
        if(currentClass.getName().equals("entity.HaircutIEntity"))
            if(!Valid_Session.isDouble(dialogAdd.textFields.get(2).getText()))
                message += "  Цена не число;\r\n";
        if(currentClass.getName().equals("entity.CategoryIEntity"))
            if(!Valid_Session.isInt(dialogAdd.textFields.get(1).getText()) && !(dialogAdd.textFields.get(1).getText().equals("")))
                message += "  База не число;\r\n";
        if(currentClass.getName().equals("entity.UserIEntity"))
            if(!Valid_Session.isInt(dialogAdd.textFields.get(2).getText()))
                message += "  Роль - не число;\r\n";
            else if(Integer.valueOf(dialogAdd.textFields.get(2).getText()) < 0 || Integer.valueOf(dialogAdd.textFields.get(2).getText()) > 2)
                message += "  Роль - не роль;\r\n";
        return message;
    }

}
