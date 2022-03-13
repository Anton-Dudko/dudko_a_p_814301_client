package conventer;

import entity.*;

import java.util.ArrayList;

public class System_Datadown {
    public static ArrayList<CategoryIEntity> modelToCategory(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<CategoryIEntity> categorys = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            categorys.add((CategoryIEntity) IEntities.get(i));
        }

        return categorys;
    }

    public static ArrayList<HaircutIEntity> modelToHaircut(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<HaircutIEntity> haircuts = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            haircuts.add((HaircutIEntity) IEntities.get(i));
        }

        return haircuts;
    }

    public static ArrayList<PurchaseIEntity> modelToPurchase(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<PurchaseIEntity> purchases = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            purchases.add((PurchaseIEntity) IEntities.get(i));
        }

        return purchases;
    }

    public static ArrayList<MasterIEntity> modelToMaster(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<MasterIEntity> purchases = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            purchases.add((MasterIEntity) IEntities.get(i));
        }

        return purchases;
    }

    public static ArrayList<UserIEntity> modelToUser(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<UserIEntity> users = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            users.add((UserIEntity) IEntities.get(i));
        }

        return users;
    }

    public static ArrayList<SalonIEntity> modelToSalon(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<SalonIEntity> salons = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            salons.add((SalonIEntity) IEntities.get(i));
        }

        return salons;
    }

    public static ArrayList<TransferIEntity> modelToMasterHaircut(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<TransferIEntity> salons = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            salons.add((TransferIEntity) IEntities.get(i));
        }

        return salons;
    }

    public static ArrayList<ModeratorIEntity> modelToUserCategory(ArrayList<IEntity> IEntities){
        if(IEntities == null) return null;

        ArrayList<ModeratorIEntity> salons = new ArrayList<>();
        for (int i = 0; i < IEntities.size(); i++) {
            salons.add((ModeratorIEntity) IEntities.get(i));
        }

        return salons;
    }
}
