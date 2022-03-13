package swing_tables;

import conventer.System_Datadown;
import entity.IEntity;
import entity.HaircutIEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HaircutTM extends AbstractTableModel {
    private ArrayList<HaircutIEntity> haircuts;

    public ArrayList<HaircutIEntity> getHaircuts(){
        return haircuts;
    }

    public HaircutTM(ArrayList<HaircutIEntity> haircuts) {
        super();
        this.haircuts = haircuts;
    }

    public HaircutTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.haircuts = System_Datadown.modelToHaircut(IEntities);
    }

    @Override
    public int getRowCount() {
        return haircuts.size();
    }

    @Override
    public int getColumnCount() {
        return HaircutIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return haircuts.get(r).getId();
            case 1: return haircuts.get(r).getName();
            case 2: return haircuts.get(r).getDescription();
            case 3: return haircuts.get(r).getPrice();
            case 4: return haircuts.get(r).getCount();
            case 5: return haircuts.get(r).getStyle_name();
            case 6: return haircuts.get(r).getCategory_id();
            case 7: return haircuts.get(r).getSalon_id();
        }
    }

    @Override
    public String getColumnName(int c) {
        return HaircutIEntity.COLUMNS_BY_RUS[c];
    }
}
