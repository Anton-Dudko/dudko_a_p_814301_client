package swing_tables;

import conventer.System_Datadown;
import entity.IEntity;
import entity.SalonIEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SalonTM extends AbstractTableModel {
    private ArrayList<SalonIEntity> salons;

    public ArrayList<SalonIEntity> getSalons() {
        return salons;
    }

    public SalonTM(ArrayList<SalonIEntity> salons) {
        super();
        this.salons = salons;
    }

    public SalonTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.salons = System_Datadown.modelToSalon(IEntities);
    }

    @Override
    public int getRowCount() {
        return salons.size();
    }

    @Override
    public int getColumnCount() {
        return SalonIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return salons.get(r).getId();
            case 1: return salons.get(r).getName();
            case 2: return salons.get(r).getAddress();
        }
    }

    @Override
    public String getColumnName(int c) {
        return SalonIEntity.COLUMNS_BY_RUS[c];
    }

}
