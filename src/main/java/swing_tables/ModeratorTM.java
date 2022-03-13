package swing_tables;

import conventer.System_Datadown;
import entity.IEntity;
import entity.ModeratorIEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeratorTM extends AbstractTableModel {
    private ArrayList<ModeratorIEntity> userCategorys;

    public ArrayList<ModeratorIEntity> getUserCategorys() {
        return userCategorys;
    }

    public ModeratorTM(ArrayList<ModeratorIEntity> userCategorys) {
        super();
        this.userCategorys = userCategorys;
    }

    public ModeratorTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.userCategorys = System_Datadown.modelToUserCategory(IEntities);
    }

    @Override
    public int getRowCount() {
        return userCategorys.size();
    }

    @Override
    public int getColumnCount() {
        return ModeratorIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default: case 0: return userCategorys.get(r).getId();
            case 1: return userCategorys.get(r).getuser_id();
            case 2: return userCategorys.get(r).getuser_name();
            case 3: return userCategorys.get(r).getcategory_id();
            case 4: return userCategorys.get(r).getcategory_name();
        }
    }

    @Override
    public String getColumnName(int c) {
        return ModeratorIEntity.COLUMNS_BY_RUS[c];
    }

}
