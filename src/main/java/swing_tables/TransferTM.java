package swing_tables;

import conventer.System_Datadown;
import entity.IEntity;
import entity.TransferIEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TransferTM extends AbstractTableModel {
    private ArrayList<TransferIEntity> masterHaircuts;

    public ArrayList<TransferIEntity> getMasterHaircuts() {
        return masterHaircuts;
    }

    public TransferTM(ArrayList<TransferIEntity> masterHaircuts) {
        super();
        this.masterHaircuts = masterHaircuts;
    }

    public TransferTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.masterHaircuts = System_Datadown.modelToMasterHaircut(IEntities);
    }

    @Override
    public int getRowCount() {
        return masterHaircuts.size();
    }

    @Override
    public int getColumnCount() {
        return TransferIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default: case 0: return masterHaircuts.get(r).getId();
            case 1: return masterHaircuts.get(r).getMaster_id();
            case 2: return masterHaircuts.get(r).getMaster_name();
            case 3: return masterHaircuts.get(r).getHaircut_id();
            case 4: return masterHaircuts.get(r).getHaircut_name();
        }
    }

    @Override
    public String getColumnName(int c) {
        return TransferIEntity.COLUMNS_BY_RUS[c];
    }

}
