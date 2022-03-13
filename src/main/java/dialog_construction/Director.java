package dialog_construction;

import dialogs.Dialog_Main;

public class Director {
    private Dialog_Main authorizationDialog;
    private Just_Dialog dialogCreator;

    public Director(Dialog_Main authorizationjDialog){
        this.authorizationDialog = authorizationjDialog;
    }

    public void setDialogCreator(Just_Dialog dialogCreator) {
        this.dialogCreator = dialogCreator;
    }

    public Dialog_Main getDialog() {
        return dialogCreator.getFrameDialog();
    }

    public void createDialog() {
        dialogCreator.createDialog(authorizationDialog);
    }
}
