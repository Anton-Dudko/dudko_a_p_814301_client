package dialog_action;

import dialogs.Dialog_Main;
import entity.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Service_UI_Report implements ActionListener {
    private ArrayList<IEntity> IEntities;
    private Class<? extends IEntity> currentClass;
    private Dialog_Main dialog;

    public Service_UI_Report(ArrayList<IEntity> IEntities, Class<? extends IEntity> currentClass, Dialog_Main dialog){
        this.IEntities = IEntities;
        this.currentClass = currentClass;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new MyTXTFilter());
        fileChooser.setDialogTitle("Доккументирование: '" + currentClass.getSimpleName() + "'");
        if (fileChooser.showSaveDialog(dialog) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            saveDataInFile(IEntities, getCorrectFileName(fileToSave.getAbsolutePath()));
        }
    }

    private String getCorrectFileName(String name){
        return name.substring(name.length()-4).equals(".txt") ? name : name + ".txt";
    }

    class MyTXTFilter extends FileFilter implements java.io.FileFilter {
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return true;
            }
            if (pathname.getName().toLowerCase().endsWith(".txt")) {
                return true;
            }
            return false;
        }

        public String getDescription() {
            return "Файлы расширения TXT (*.txt)";
        }
    }

    private void saveDataInFile(ArrayList<IEntity> IEntities, String file){
        try (
                FileWriter fout = new FileWriter(file, false);
        ){
            switch (currentClass.getName()) {
                default:
                case "entity.CategoryIEntity":      saveCategory(IEntities, fout); break;
                case "entity.HaircutIEntity":       saveHaircut(IEntities, fout); break;
                case "entity.PurchaseIEntity":      savePurchase(IEntities, fout); break;
                case "entity.MasterIEntity":      saveMaster(IEntities, fout); break;
                case "entity.UserIEntity":          saveUser(IEntities, fout); break;
                case "entity.SalonIEntity":     saveSalon(IEntities, fout); break;
                case "entity.TransferIEntity":      saveMasterHaircut(IEntities, fout); break;
                case "entity.ModeratorIEntity":         saveCategoryUser(IEntities, fout); break;
            }
            JOptionPane.showMessageDialog(dialog, "Отчётность построена!", "Отлично!",JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(dialog, "Ошибка создания Отчётности", "Проверте корректность, ПОЖАЛУЙСТА",JOptionPane.PLAIN_MESSAGE);
        }
    }

    private String getSpaces(String line, int length){
        int size = line.length();
        if(size > length){
            char[] simbols = new char[length];
            line.getChars(0, length-2, simbols, 0);
            simbols[length-2] = '.';
            simbols[length-1] = '.';
            line = String.valueOf(simbols);

        } else {
            for (int i = 0; i < length - size; i++) {
                line += " ";
            }
        }
        return line;
    }

    private String getLine(int length){
        String answer = "";
        for (int i = 0; i < length+2; i++) {
            answer += "=";
        }
        return answer + "\r\n";
    }

    private void saveCategory(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Название", 35) + "|"
                 + getSpaces("К.ИД", 5) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<КАТЕГОРИИ>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            CategoryIEntity category = (CategoryIEntity) IEntity.get(i);
            fout.write("|" + category.getId() + "\t|"
                    + getSpaces(category.getName(), 35) + "|"
                    + getSpaces(String.valueOf(category.getParent_category_id()), 5) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveHaircut(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Название", 25) + "|"
                + getSpaces("Описание", 35) + "|"  + getSpaces("Цена", 15) + "|"
                + getSpaces("Кл-во", 10) + "|" + getSpaces("Стиль", 25) + "|"
                + getSpaces("К.ИД", 5) + "|" +  getSpaces("С.ИД", 5) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<СТРИЖКИ>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            HaircutIEntity haircut = (HaircutIEntity) IEntity.get(i);
            fout.write("|" + haircut.getId() + "\t|" + getSpaces(haircut.getName(), 25) + "|"
                    + getSpaces(haircut.getDescription(), 35) + "|"  + getSpaces(String.valueOf(haircut.getPrice()), 15) + "|"
                    + getSpaces(String.valueOf(haircut.getCount()), 10) + "|" + getSpaces(haircut.getStyle_name(), 25) + "|"
                    + getSpaces(String.valueOf(haircut.getCategory_id()), 5) + "|"
                    + getSpaces(String.valueOf(haircut.getSalon_id()),5) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void savePurchase(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Дата", 15) + "|"
                + getSpaces("Метоположение", 25)  + "|" + getSpaces("Почта", 25) + "|"
                + getSpaces("П.ИД", 5) + "|" + getSpaces("Кл-во", 10) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<БРОНИ>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            PurchaseIEntity purchase = (PurchaseIEntity) IEntity.get(i);
            fout.write("|" + purchase.getId() + "\t|" + getSpaces(purchase.getDate(), 15) + "|"
                    + getSpaces(purchase.getAddress(), 25)  + "|" + getSpaces(purchase.getMail(), 25) + "|"
                    + getSpaces(String.valueOf(purchase.getHaircut_id()), 5) + "|"
                    + getSpaces(String.valueOf(purchase.getCount()), 10) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveMaster(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("ФИО", 25) + "|"
                + getSpaces("Страна",25) + "|" + getSpaces("Сотрудничество с", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<МАСТЕРА>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            MasterIEntity master = (MasterIEntity) IEntity.get(i);
            fout.write("|" + master.getId() + "\t|" + getSpaces(master.getName(), 25) + "|"
                    + getSpaces(master.getCountry(), 25) + "|" + getSpaces(master.getDate_of_cooperation(), 25)
                    + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveUser(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Логин", 25) + "|"
                + getSpaces("Паспорт", 25) + "|" + "Роль|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<МАСТЕРА>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            UserIEntity user = (UserIEntity) IEntity.get(i);
            fout.write("|" + user.getId() + "\t|" + getSpaces(user.getLogin(), 25) + "|"
                    + getSpaces(user.getPassword(), 25) + "|" + user.getRole() + "   |\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveSalon(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("Название", 25) + "|"
                + getSpaces("Метоположение", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<САЛОН-КРАСОТЫ>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            SalonIEntity salon = (SalonIEntity) IEntity.get(i);
            fout.write("|" + salon.getId() + "\t|" + getSpaces(salon.getName(), 25) + "|"
                    + getSpaces(salon.getAddress(), 25) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveMasterHaircut(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("ИД Мастера", 15) + "|"
                + getSpaces("Мастер", 25) + "|"+ getSpaces("ИД Стрижка", 15) + "|"
                + getSpaces("Стрижка", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<Мастер-Стрижка>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            TransferIEntity masterHaircut = (TransferIEntity) IEntity.get(i);
            fout.write("|" + masterHaircut.getId() + "\t|" + getSpaces(String.valueOf(masterHaircut.getMaster_id()), 15)
                    + "|" + getSpaces(masterHaircut.getMaster_name(), 25) + "|" + getSpaces(String.valueOf(masterHaircut.getHaircut_id()), 15)
                    + "|" + getSpaces(masterHaircut.getHaircut_name(), 25) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }

    private void saveCategoryUser(ArrayList<IEntity> IEntity, FileWriter fout) throws IOException {
        String columnHead = "|" + "ИД\t|" + getSpaces("ИД Категор", 15) + "|"
                + getSpaces("Категоря", 25) + "|"+ getSpaces("ИД Пользоват", 15) + "|"
                + getSpaces("Пользователь", 25) + "|\r\n";
        fout.write(getLine(columnHead.length()) + getSpaces("| Таблица: <<<Категор-Пользователь>>>", columnHead.length()) + " |\r\n");
        fout.write(getLine(columnHead.length()) + columnHead + getLine(columnHead.length()));
        for (int i = 0; i < IEntity.size(); i++) {
            ModeratorIEntity userCategory = (ModeratorIEntity) IEntity.get(i);
            fout.write("|" + userCategory.getId() + "\t|" + getSpaces(String.valueOf(userCategory.getcategory_id()), 15)
                    + "|" + getSpaces(userCategory.getcategory_name(), 25) + "|" + getSpaces(String.valueOf(userCategory.getuser_id()), 15)
                    + "|" + getSpaces(userCategory.getuser_name(), 25) + "|\r\n");
        }
        fout.write(getLine(columnHead.length()));
    }
}
