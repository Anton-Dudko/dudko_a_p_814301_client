package dialog_action;

import sockets.Data_Prerunner;
import dialogs.Dialog_Main;
import dialogs.Dialog_Secondary;
import entity.IEntity;
import entity.HaircutIEntity;
import entity.PurchaseIEntity;
import conventer.Valid_Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Service_UI_Book implements ActionListener {
    private ArrayList<IEntity> allHaircuts;
    private Dialog_Secondary dialogBuy;
    private Dialog_Main frameDialog;

    public Service_UI_Book(Dialog_Main frameDialog, ArrayList<IEntity> allHaircuts){
        super();
        this.frameDialog = frameDialog;
        this.allHaircuts = allHaircuts;
    }

    public void actionPerformed(ActionEvent e) {
        HaircutIEntity selectedHaircut ;
        if((selectedHaircut = messageOk()) == null) return;

        prepareBuyData(selectedHaircut);
        setEventBuy(selectedHaircut.getCount(), selectedHaircut.getId());
        dialogBuy.setVisible(true);
    }

    private HaircutIEntity messageOk(){
        if(Valid_Session.isInt(frameDialog.textFields.get(0).getText())){
            int myId = Integer.valueOf(frameDialog.textFields.get(0).getText());

            for (int i = 0; i < allHaircuts.size(); i++){
                if(allHaircuts.get(i).getId() == myId){
                    return (HaircutIEntity)allHaircuts.get(i);
                }
            }
        }

        JOptionPane.showMessageDialog(dialogBuy, "Несуществующий индекс!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        return null;
    }

    private void prepareBuyData(HaircutIEntity selectedHaircut){
        dialogBuy = new Dialog_Secondary("Заказ", 300, 430, true);

        dialogBuy.addLabel(100, 30,50, 20, "Ваша стрижка:");
        dialogBuy.addTextField(200, 30,50, 50);
        addHaircut(selectedHaircut);
        dialogBuy.addLabel(100, 30,50, 100, "Ваш email:");
        dialogBuy.addTextField(200, 30,50, 130);
        dialogBuy.addLabel(100, 30,50, 180, "Метоположение::");
        dialogBuy.addTextField(200, 30,50, 210);
        dialogBuy.addLabel(100, 30,50, 260, "Количество человек:");
        dialogBuy.addTextField(200, 30,50, 290);

        dialogBuy.addButton(200, 30,50, 340, "Бронь");

    }

    private void addHaircut(HaircutIEntity haircut){
        String stringHaircut = haircut.getStyle_name() + " \"" + haircut.getName() + "\" Цена: " + haircut.getPrice();
        dialogBuy.textFields.get(0).setText(stringHaircut);
        dialogBuy.textFields.get(0).setEnabled(false);

    }

    private void setEventBuy(final int count,final int id){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(chectAddOk(count)){
                    String messageToUser;
                    if(addNewPurchase(count, id)) {
                        frameDialog.textFields.get(0).setText("");
                        messageToUser = "Ваше место забранировано, приходите, ждем!";
                    } else {
                        messageToUser = "Ошибка добавления в Базу Данных!";
                    }
                    JOptionPane.showMessageDialog(dialogBuy, messageToUser, "Операция завершена!", JOptionPane.PLAIN_MESSAGE);
                    dialogBuy.dispose();
                }
            }
        };
        dialogBuy.buttons.get(0).addActionListener(actionListener);
    }

    private boolean addNewPurchase(int count, int id){
        PurchaseIEntity purchase = new PurchaseIEntity(0,"", dialogBuy.textFields.get(2).getText().trim(),
                dialogBuy.textFields.get(1).getText().trim(), id, count);

        if("@ok@".equals(Data_Prerunner.insert(purchase, "purchases"))) return true;
        else return false;
    }

    private boolean chectAddOk(int count){
        String message = "";

        if(!Valid_Session.isEMail(dialogBuy.textFields.get(1).getText().trim()))
            message += "  E-mail не корректен;\r\n";
        if("".equals(dialogBuy.textFields.get(2).getText().trim()))
            message += "  Метоположение пуст пусто;\r\n";
        if(!Valid_Session.isInt(dialogBuy.textFields.get(3).getText())){
            message += "  Кол-во часов - не число!;\r\n";
        } else if(Integer.valueOf(dialogBuy.textFields.get(3).getText()) <= 0 || Integer.valueOf(dialogBuy.textFields.get(3).getText()) > count) {
            message += "  Кол-во часов - в недопустимых пределах!;\r\n";
        }

        if(!message.equals("")) JOptionPane.showMessageDialog(dialogBuy, "Ошибки: \r\n" + message, "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        return message.equals("") ? true : false;
    }


}
