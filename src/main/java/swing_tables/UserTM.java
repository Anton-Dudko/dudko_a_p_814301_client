package swing_tables;

import conventer.System_Datadown;
import entity.IEntity;
import entity.UserIEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UserTM extends AbstractTableModel {
    private ArrayList<UserIEntity> users;

    public ArrayList<UserIEntity> getUsers() {
        return users;
    }

    public UserTM(ArrayList<UserIEntity> users) {
        super();
        this.users = users;
    }

    public UserTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.users = System_Datadown.modelToUser(IEntities);
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return UserIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return users.get(r).getId();
            case 1: return users.get(r).getLogin();
            case 2: return users.get(r).getPassword();
            case 3: return users.get(r).getRole();
        }
    }

    @Override
    public String getColumnName(int c) {
        return UserIEntity.COLUMNS_BY_RUS[c];
    }

}
