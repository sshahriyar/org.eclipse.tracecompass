/*********************************************************************************************
 * Copyright (c) 2014-2015  Software Behaviour Analysis Lab, Concordia University, Montreal, Canada
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of Eclipse Public License v1.0 License which
 * accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Syed Shariyar Murtaza -- Initial design and implementation
 **********************************************************************************************/

package org.eclipse.tracecompass.totalads.ui.diagnosis;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.tracecompass.totalads.ui.diagnosis.DiagnosisPartListener;
import org.eclipse.tracecompass.totalads.ui.diagnosis.DiagnosisView;
import org.eclipse.tracecompass.totalads.ui.diagnosis.IDiagnosisObserver;
import org.eclipse.tracecompass.totalads.ui.diagnosis.Messages;
import org.eclipse.tracecompass.totalads.ui.results.ResultsAndFeedback;
import org.eclipse.tracecompass.totalads.ui.results.ResultsView;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Implements an Eclipse Part Listener for the diagnosis view
 *
 * @author <p>
 *         Syed Shariyar Murtaza justsshary@hotmail.com
 *         </p>
 *
 */
public class DiagnosisPartListener implements IPartListener {
    private List<IDiagnosisObserver> fObservers;

    /**
     * Constructor to create the part listener
     */
    public DiagnosisPartListener() {
        fObservers = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void partActivated(IWorkbenchPart part) {
        try {
            if (part instanceof DiagnosisView) {
                // Launching Results View if it is not opened
                IViewPart viewRes = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ResultsView.VIEW_ID);
                ResultsView resultsView = (ResultsView) viewRes;
                notifyObservers(resultsView.getResultsAndFeddbackInstance());

            }
        } catch (PartInitException e) {

            String msgTitle="TotalADS"; //$NON-NLS-1$
            if (e.getMessage() != null) {
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        msgTitle,e.getMessage());
            }
            else {
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        msgTitle,Messages.DiagnosisPartListener_UnableToLaunch);
            }

            Logger.getLogger(DiagnosisPartListener.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart
     * )
     */
    @Override
    public void partBroughtToTop(IWorkbenchPart part) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void partClosed(IWorkbenchPart part) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart
     * )
     */
    @Override
    public void partDeactivated(IWorkbenchPart part) {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void partOpened(IWorkbenchPart part) {

    }

    /**
     * Adds an observer
     *
     * @param observer
     *            An observer
     */
    public void addObserver(IDiagnosisObserver observer) {
        fObservers.add(observer);

    }

    /**
     * Removes an observer
     *
     * @param observer
     *            An observer
     */
    public void removeObserver(IDiagnosisObserver observer) {
        fObservers.remove(observer);

    }

    /**
     * Notifies the fObservers about the {@link ResultsAndFeedback} object
     *
     * @param results
     *            An object to display results
     */
    private void notifyObservers(ResultsAndFeedback results) {
        for (IDiagnosisObserver ob : fObservers) {
            ob.update(results);
        }
    }

}
