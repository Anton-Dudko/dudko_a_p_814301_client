package dialog_construction;

import entity.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
import java.util.ArrayList;

public class Chart_Dialog {
    private Class<? extends IEntity> currentClass;
    private String chartName;

    public Chart_Dialog(Class<? extends IEntity> currentClass){
        this.currentClass = currentClass;
        this.chartName = getChartName();
    }

    private String getChartName(){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return "Процент Разделов";
            case "entity.HaircutIEntity":      return "Общая стоимость стрижек";
            case "entity.PurchaseIEntity":     return "Процент броней по годам";
            case "entity.MasterIEntity":     return "Статистика гражданств мастеров";
            case "entity.UserIEntity":         return "Статистика ролей пользователей";
        }
    }

    public PieDataset createDataset(ArrayList<IEntity> IEntities) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < IEntities.size(); i++) {
            switch (currentClass.getName()) {
                default:
                case "entity.CategoryIEntity":     addOneCategory(dataset, IEntities.get(i)); break;
                case "entity.HaircutIEntity":      addOneHaircut(dataset, IEntities.get(i), calculateTotalCost(IEntities)); break;
                case "entity.PurchaseIEntity":     addOnePurchase(dataset, IEntities.get(i)); break;
                case "entity.MasterIEntity":     addOneMaster(dataset, IEntities.get(i)); break;
                case "entity.UserIEntity":         addOneUser(dataset, IEntities.get(i)); break;
            }
        }
        return dataset;
    }

    private double calculateTotalCost (ArrayList<IEntity> IEntities){
        double total_cost = 0;
        for (int i = 0; i < IEntities.size(); i++) {
            total_cost += ((HaircutIEntity) IEntities.get(i)).getCount()*((HaircutIEntity) IEntities.get(i)).getPrice();
        }
        return total_cost;
    }

    private void addOneCategory(DefaultPieDataset dataset, IEntity IEntity){
        CategoryIEntity category = (CategoryIEntity) IEntity;
        try {
            if(category.getParent_category_id() == 0)
                dataset.setValue("Раздел", dataset.getValue("Раздел").intValue() + 1);
            else
                dataset.setValue("Пункт", dataset.getValue("Пункт").intValue() + 1);
        } catch (Exception e){
            if(category.getParent_category_id() == 0)
                dataset.setValue("Раздел", 1);
            else
                dataset.setValue("Пункт", 1);
        }
    }

    private void addOneHaircut(DefaultPieDataset dataset, IEntity IEntity, double total_cost){
        HaircutIEntity haircut = (HaircutIEntity) IEntity;
        double cost = haircut.getPrice()*haircut.getCount();
        String poursent =  String.format("%.2f", (cost/total_cost*100));
        String title = "\"" + haircut.getStyle_name() + " " + haircut.getName() + "\" - " + cost + "(" + poursent + "%)";
        dataset.setValue(title, haircut.getCount()*haircut.getPrice());
    }

    private void addOnePurchase(DefaultPieDataset dataset, IEntity IEntity){
        char[] yearChar = new char[4];
        ((PurchaseIEntity) IEntity).getDate().getChars(0, 4, yearChar, 0);
        String year = String.valueOf(yearChar);
        try {
            dataset.getValue(year);
            dataset.setValue(year, dataset.getValue(year).intValue() + 1);
        } catch (Exception e){
            dataset.setValue(year, 1);
        }
    }

    private void addOneMaster(DefaultPieDataset dataset, IEntity IEntity){
        MasterIEntity master = (MasterIEntity) IEntity;
        try {
            dataset.getValue(master.getCountry());
            dataset.setValue(master.getCountry(), dataset.getValue(master.getCountry()).intValue() + 1);
        } catch (Exception e){
            dataset.setValue(master.getCountry(), 1);
        }
    }

    private void addOneUser(DefaultPieDataset dataset, IEntity IEntity){
        UserIEntity user = (UserIEntity) IEntity;
        String role = user.getRole() == 2 ? "Пользователь" : (user.getRole() == 1 ? "Админ": "Абсолютный Админ");
        try {
            dataset.getValue(role);
            dataset.setValue(role, dataset.getValue(role).intValue() + 1);
        } catch (Exception e){
            dataset.setValue(role, 1);
        }
    }

    public JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(chartName, dataset,false,true, false);

        chart.setBackgroundPaint(new GradientPaint(
                new Point(0, 0),
                new Color(20, 20, 20),
                new Point(400, 200),
                Color.DARK_GRAY)
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.10);
        plot.setOutlineVisible(false);

        plot.setBaseSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setBaseSectionOutlineStroke(new BasicStroke(3.0f));

        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);

        return chart;
    }

}