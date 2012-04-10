package org.eclipse.linuxtools.internal.lttng2.ui.views.control.dialogs;

import org.eclipse.ui.PlatformUI;


final public class TraceControlDialogFactory {

    // ------------------------------------------------------------------------
    // Members
    // ------------------------------------------------------------------------

    /**
     * The factory instance.
     */
    private static TraceControlDialogFactory fInstance;

    /**
     * The new connection dialog reference.
     */
    private INewConnectionDialog fNewConnectionDialog;
    
    /**
     * The create channel dialog (on domain level)
     */
    private ICreateChannelDialog fCreateChannelDialog;
    
    /**
     * The create session dialog.
     */
    private ICreateSessionDialog fCreateSessionDialog;
    
    /**
     * The enable events dialog.
     */
    private IEnableEventsDialog fEnableEventsDialog;
    
    /**
     * The get event info dialog.
     */
    private IGetEventInfoDialog fGetEventInfoDialog;
    
    /**
     * The confirmation dialog implementation.
     */
    private IConfirmDialog fConfirmDialog;
    
    /**
     * The add context dialog implementation.
     */
    private IAddContextDialog fAddContextDialog;
    
    /**
     * The import dialog implementation.
     */
    private IImportDialog fImportDialog;
    
    /**
     * The import confirmation dialog.
     */
    private IImportConfirmationDialog fImportConfirmationDialog;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructor for R4EUIDialogFactory.
     */
    private TraceControlDialogFactory() {
    }

    // ------------------------------------------------------------------------
    // Operations
    // ------------------------------------------------------------------------

    /**
     * @return TraceControlDialogFactory instance
     */
    public synchronized static TraceControlDialogFactory getInstance() {
        if (fInstance == null) {
            fInstance = new TraceControlDialogFactory();
        }
        return fInstance;
    }

    /**
     * @return new connection dialog
     */
    public INewConnectionDialog getNewConnectionDialog() {
        if (fNewConnectionDialog == null) {
            fNewConnectionDialog = new NewConnectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fNewConnectionDialog;
    }

    /**
     * Sets a new connection dialog implementation.
     * @param newConnectionDialog - new connection dialog implementation
     */
    public void setNewConnectionDialog(INewConnectionDialog newConnectionDialog) {
        fNewConnectionDialog = newConnectionDialog;
    }
    
    /**
     * @return create channel dialog (on domain level)
     */
    public ICreateChannelDialog getCreateChannelDialog() {
        if (fCreateChannelDialog == null) {
            fCreateChannelDialog = new CreateChannelDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fCreateChannelDialog;
    }

    /**
     * Sets a create channel dialog implementation (on domain level).
     * @param createChannelDialog - a create channel dialog implementation
     */
    public void setCreateChannelDialog(ICreateChannelDialog createChannelDialog) {
        fCreateChannelDialog = createChannelDialog;
    }
    
    /**
     * @return create session dialog implementation
     */
    public ICreateSessionDialog getCreateSessionDialog() {
        if (fCreateSessionDialog == null) {
            fCreateSessionDialog = new CreateSessionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fCreateSessionDialog;
    }

    /**
     * Sets a create session dialog implementation.
     * @param createSessionDialog - a create session implementation.
     */
    public void setCreateSessionDialog(ICreateSessionDialog createSessionDialog) {
        fCreateSessionDialog = createSessionDialog;
    }

    /**
     * @return enable events dialog implementation.
     */
    public IEnableEventsDialog getEnableEventsDialog() {
        if (fEnableEventsDialog == null) {
            fEnableEventsDialog = new EnableEventsDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fEnableEventsDialog;
    }

    /**
     * Sets a enable events dialog implementation.
     * @param enableEventsDialog - a enable events dialog implementation.
     */
    public void setEnableEventsDialog(IEnableEventsDialog enableEventsDialog) {
        fEnableEventsDialog = enableEventsDialog;
    }

    /**
     * @return get events info dialog implementation.
     */
    public IGetEventInfoDialog getGetEventInfoDialog() {
        if (fGetEventInfoDialog == null) {
            fGetEventInfoDialog = new GetEventInfoDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fGetEventInfoDialog;
    }

    /**
     * Sets a get events info dialog implementation.
     * @param getEventInfoDialog - a get events info dialog implementation
     */
    public void setGetEventInfoDialog(IGetEventInfoDialog getEventInfoDialog) {
        fGetEventInfoDialog = getEventInfoDialog;
    }
    
    /**
     * @return the confirmation dialog implementation
     */
    public IConfirmDialog getConfirmDialog() {
        if (fConfirmDialog == null) {
            fConfirmDialog = new ConfirmDialog();
        }
        return fConfirmDialog;
    }
    
    /**
     * Sets the confirmation dialog implementation
     * @param confirmDialog - a confirmation dialog implementation
     */
    public void setConfirmDialog(IConfirmDialog confirmDialog) {
        fConfirmDialog = confirmDialog;
    }
    
    /**
     * @return the add context dialog implementation
     */
    public IAddContextDialog getAddContextDialog() {
        if (fAddContextDialog == null) {
            fAddContextDialog = new AddContextDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fAddContextDialog;
    }
    
    /**
     * Sets the add context dialog information
     * @param addContextDialog - a add context dialog implementation
     */
    public void setAddContextDialog(IAddContextDialog addContextDialog) {
        fAddContextDialog = addContextDialog;
    }
    
    /**
     * @return the import dialog implementation
     */
    public IImportDialog getImportDialog() {
        if (fImportDialog == null) {
            fImportDialog = new ImportDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fImportDialog;
    }
    
    /**
     * Sets the import dialog implementation.
     * @param importDialog - a import dialog implementation
     */
    public void setImportDialog(IImportDialog importDialog) {
        fImportDialog = importDialog;
    }
    
    /**
     * @return the import confirmation dialog implementation.
     */
    public IImportConfirmationDialog getImportConfirmationDialog() {
        if (fImportConfirmationDialog == null) {
            fImportConfirmationDialog = new ImportConfirmationDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        }
        return fImportConfirmationDialog;
    }
    
    /**
     * Sets the import confirmation dialog implementation.
     * @param confirmDialog - a import confirmation dialog implementation. 
     */
    public void setImportConfirmationDialog(IImportConfirmationDialog confirmDialog) {
        fImportConfirmationDialog = confirmDialog;
    }
}
