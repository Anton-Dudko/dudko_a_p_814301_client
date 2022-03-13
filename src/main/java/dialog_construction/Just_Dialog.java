package dialog_construction;

import sockets.Data_Prerunner;
import dialogs.Dialog_Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

abstract class Just_Dialog {
    protected Dialog_Main frameDialog;
    private Dialog_Main authorizationjDialog;


    public Dialog_Main getFrameDialog() {
        frameDialog.setVisible(true);
        return frameDialog;
    }

    public void createDialog(Dialog_Main authorizationjDialog){
        frameDialog = new Dialog_Main("Клиент Барбершопа Style", 800, 610);
        setEventsForCloseDialog();
        prepareFrameDialog();
        setEventsForButtons();
        this.authorizationjDialog = authorizationjDialog;
    }

    public void setEventsForCloseDialog() {
        WindowListener windowListener = new WindowListener() {
            public void windowActivated(WindowEvent event) {}
            public void windowClosed(WindowEvent event) {}
            public void windowDeactivated(WindowEvent event) {}
            public void windowDeiconified(WindowEvent event) {}
            public void windowIconified(WindowEvent event) {}
            public void windowOpened(WindowEvent event) {}

            public void windowClosing(WindowEvent event) {
                String answer = Data_Prerunner.exit("witchout");
                if(!answer.equals("")) {
                    JOptionPane.showMessageDialog(frameDialog, answer, "Упс!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
        frameDialog.addWindowListener(windowListener);
    }

    abstract void prepareFrameDialog();
    abstract void setEventsForButtons();

    protected ActionListener setEventExit(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameDialog.dispose();
                authorizationjDialog.setVisible(true);
            }
        };
        return actionListener;
    }

}
