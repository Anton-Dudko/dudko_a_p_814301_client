package dialog_action;

import dialogs.Dialog_Main;
import dialog_construction.Just_Product_Type;
import entity.IEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CRUD_Concrete_Delete extends CRUD_Store {
    public CRUD_Concrete_Delete(Dialog_Main frameDialog, Class<? extends IEntity> currentClass, ArrayList<IEntity> IEntities, Just_Product_Type parental){
        super(frameDialog, currentClass, IEntities, parental);
    }

    public void actionPerformed(ActionEvent e) {
        String error;
        if(!isIdCorrect()) return;
        int id = Integer.valueOf(frameDialog.textFields.get(0).getText());

        if(!"".equals(error = getQueryDelete(id))) {
            JOptionPane.showMessageDialog(frameDialog, "Ошибка для: Delete", "Проверте корректность, ПОЖАЛУЙСТА", JOptionPane.PLAIN_MESSAGE);
        }
        parental.updateData();
    }

}
