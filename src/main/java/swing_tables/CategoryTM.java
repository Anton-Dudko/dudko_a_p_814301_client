package swing_tables;

import conventer.System_Datadown;
import entity.CategoryIEntity;
import entity.IEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CategoryTM extends AbstractTableModel {
    private ArrayList<CategoryIEntity> categorys;

    public ArrayList<CategoryIEntity> getCategorys() {
        return categorys;
    }

    public CategoryTM(ArrayList<CategoryIEntity> categorys) {
        super();
        this.categorys = categorys;
    }

    public CategoryTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.categorys = System_Datadown.modelToCategory(IEntities);
    }

    @Override
    public int getRowCount() {
        return categorys.size();
    }

    @Override
    public int getColumnCount() {
        return CategoryIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return categorys.get(r).getId();
            case 1: return categorys.get(r).getName();
            case 2: return categorys.get(r).getParent_category_id();
        }
    }

    @Override
    public String getColumnName(int c) {
        return CategoryIEntity.COLUMNS_BY_RUS[c];
    }

}
