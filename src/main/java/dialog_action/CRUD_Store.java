package dialog_action;

import entity.IEntity;
import sockets.Data_Prerunner;
import dialog_construction.Just_Product_Type;
import dialogs.Dialog_Main;
import conventer.Valid_Session;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

abstract class CRUD_Store implements ActionListener {
    protected Dialog_Main frameDialog;
    protected Class<? extends IEntity> currentClass;
    protected ArrayList<IEntity> IEntities;
    protected Just_Product_Type parental;

    public CRUD_Store(Dialog_Main frameDialog, Class<? extends IEntity> currentClass, ArrayList<IEntity> IEntities, Just_Product_Type parental){
        this.frameDialog = frameDialog;
        this.currentClass = currentClass;
        this.IEntities = IEntities;
        this.parental = parental;
    }

    protected boolean isIdCorrect(){
        IEntities = parental.updateData();
        if(Valid_Session.isInt(frameDialog.textFields.get(0).getText())){
            int myId = Integer.valueOf(frameDialog.textFields.get(0).getText());
            for (int i = 0; i < IEntities.size(); i++){
                if(IEntities.get(i).getId() == myId){
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(frameDialog, "Несуществующий индекс!", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        return false;
    }


    protected String getCurrentTable(){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return "categorys";
            case "entity.HaircutIEntity":      return "haircuts";
            case "entity.PurchaseIEntity":     return "purchases";
            case "entity.MasterIEntity":     return "masters";
            case "entity.UserIEntity":         return "users";
            case "entity.SalonIEntity":    return "salons";
            case "entity.TransferIEntity":  return "transfers";
            case "entity.ModeratorIEntity":     return "moderators";
        }
    }

    protected String getQueryUpdate(IEntity IEntity){
        String answer = Data_Prerunner.update(IEntity, getCurrentTable(), Integer.valueOf(frameDialog.textFields.get(0).getText()));
        
        if(answer.equals("@ok@")) return "";
        else return answer;
    }

    protected String getQueryInsert(IEntity IEntity){
        String answer = Data_Prerunner.insert(IEntity, getCurrentTable());
        System.out.println(answer+" ");
        if(answer.equals("@ok@")) return "";
        else return answer;
    }


    protected String getQueryDelete(int id){
        String answer = Data_Prerunner.delete(id, getCurrentTable());
        if(answer.equals("@ok@")) return "";
        else return answer;
    }

}
