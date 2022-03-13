package sockets;


import com.google.gson.reflect.TypeToken;
import entity.*;
import conventer.Service_Serialize;

import java.util.ArrayList;


public class Data_Prerunner {
    private static Client_Socket connector;

    public static String startConnection(String host, int port){
        connector = new Client_Socket(host, port);
        connector.run();
        return connector.isHaveError ? connector.errorMessage : "";
    }

    public static UserIEntity checkAuthorization(UserIEntity userModel) {
        String userJSON = Service_Serialize.ObjectToJSON(userModel), command = "authorization", answer;
        answer = connector.writeCommand(command, userJSON);
        return  !("".equals(answer)) ? (UserIEntity) Service_Serialize.JSONToObject(answer, UserIEntity.class) : null;
    }

    public static ArrayList<IEntity> getCategorys(String orderBy, String where){
        String answer = getModelsFromServer("getCategorys", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<CategoryIEntity>>(){}.getType()) : null;
    }

    public static ArrayList<IEntity> getHaircuts(String orderBy, String where){
        String answer = getModelsFromServer("getHaircuts", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<HaircutIEntity>>(){}.getType()) : null;
    }

    public static ArrayList<IEntity> getPurchases(String orderBy, String where){
        String answer = getModelsFromServer("getPurchases", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<PurchaseIEntity>>(){}.getType()) : null;
    }

    public static ArrayList<IEntity> getMasters(String orderBy, String where){
        String answer = getModelsFromServer("getMasters", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<MasterIEntity>>(){}.getType()) : null;
    }

    public static ArrayList<IEntity> getUsers(String orderBy, String where){
        String answer = getModelsFromServer("getUsers", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<UserIEntity>>(){}.getType()) : null;
    }

    public static ArrayList<IEntity> getSalons(String orderBy, String where){
        String answer = getModelsFromServer("getSalons", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<SalonIEntity>>(){}.getType()) : null;
    }

    public static ArrayList<IEntity> getMastersHaircuts(String orderBy, String where){
        String answer = getModelsFromServer("getMastersHaircuts", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<TransferIEntity>>(){}.getType()) : null;
    }

    public static ArrayList<IEntity> getUserCategorys(String orderBy, String where){
        String answer = getModelsFromServer("getUserCategorys", orderBy, where);
        return !("".equals(answer)) ? Service_Serialize.JSONToObjects(answer, new TypeToken<ArrayList<ModeratorIEntity>>(){}.getType()) : null;
    }

    private static String getModelsFromServer(String command, String orderBy, String where) {
        String data = "";
        if(!orderBy.trim().equals("") || !where.trim().equals("")) {
            data = orderBy + " @@@ " + where;
        }
        return connector.writeCommand(command, data);
    }


    public static String insert(IEntity IEntity, String table){
        String command = "insert_", data;
        data = Service_Serialize.ObjectToJSON(IEntity);
        return connector.writeCommand(command + table, data);
    }

    public static String update(IEntity IEntity, String table, int id){
        String command = "update_", data;
        data = Service_Serialize.ObjectToJSON(IEntity) + "@@@" + id;
        return connector.writeCommand(command + table, data);
    }

    public static String delete(int id, String table){
        String command = "delete", data;
        data = table + "@@@" + id;
        return connector.writeCommand(command, data);
    }

    public static String exit(String message){
        return connector.closeSocket(message);
    }


}
