package swing_tables;

import conventer.System_Datadown;
import entity.IEntity;
import entity.MasterIEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MasterTM extends AbstractTableModel {
    private ArrayList<MasterIEntity> masters;

    public ArrayList<MasterIEntity> getMasters() {
        return masters;
    }

    public MasterTM(ArrayList<MasterIEntity> masters) {
        super();
        this.masters = masters;
    }

    public MasterTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.masters = System_Datadown.modelToMaster(IEntities);
    }

    @Override
    public int getRowCount() {
        return masters.size();
    }

    @Override
    public int getColumnCount() {
        return MasterIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return masters.get(r).getId();
            case 1: return masters.get(r).getName();
            case 2: return masters.get(r).getCountry();
            case 3: return masters.get(r).getDate_of_cooperation();
        }
    }

    @Override
    public String getColumnName(int c) {
        return MasterIEntity.COLUMNS_BY_RUS[c];
    }

}
