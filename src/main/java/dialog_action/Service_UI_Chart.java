package dialog_action;

import dialog_construction.Just_Product_Type;
import dialogs.Dialog_Secondary;
import dialog_construction.Chart_Dialog;
import entity.IEntity;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Service_UI_Chart implements ActionListener {
    private Just_Product_Type parental;
    private Class<? extends IEntity> currentClass;
    private Dialog_Secondary infoDialog;

    public Service_UI_Chart(Class<? extends IEntity> currentClass, Just_Product_Type parental){
        this.currentClass = currentClass;
        this.parental = parental;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        prepareData();
        infoDialog.setVisible(true);
    }

    private void prepareData(){

        infoDialog = new Dialog_Secondary("Аналитические ДАННЫЕ", 800, 600, true);

        Chart_Dialog dataset = new Chart_Dialog(currentClass);

        JFreeChart chart = dataset.createChart(dataset.createDataset(parental.updateData()));
        chart.setPadding(new RectangleInsets(10, 10, 10, 10));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(100, 100));

        panel.setVisible(true);
        infoDialog.setContentPane(panel);
    }


}
