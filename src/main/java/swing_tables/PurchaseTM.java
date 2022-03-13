package swing_tables;

import conventer.System_Datadown;
import entity.IEntity;
import entity.PurchaseIEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PurchaseTM extends AbstractTableModel {
    private ArrayList<PurchaseIEntity> purchases;

    public ArrayList<PurchaseIEntity> getPurchases() {
        return purchases;
    }

    public PurchaseTM(ArrayList<PurchaseIEntity> purchases) {
        super();
        this.purchases = purchases;
    }

    public PurchaseTM(ArrayList<IEntity> IEntities, boolean isAbstract) {
        super();
        this.purchases = System_Datadown.modelToPurchase(IEntities);
    }

    @Override
    public int getRowCount() {
        return purchases.size();
    }

    @Override
    public int getColumnCount() {
        return PurchaseIEntity.COLUMNS_BY_RUS.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            default:case 0: return purchases.get(r).getId();
            case 1: return purchases.get(r).getDate();
            case 2: return purchases.get(r).getAddress();
            case 3: return purchases.get(r).getMail();
            case 4: return purchases.get(r).getHaircut_id();
            case 5: return purchases.get(r).getCount();
        }
    }

    @Override
    public String getColumnName(int c) {
        return PurchaseIEntity.COLUMNS_BY_RUS[c];
    }

}
