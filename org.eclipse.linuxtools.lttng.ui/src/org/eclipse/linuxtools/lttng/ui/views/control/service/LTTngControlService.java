/**********************************************************************
 * Copyright (c) 2012 Ericsson
 * 
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Bernd Hufmann - Initial API and implementation
 **********************************************************************/
package org.eclipse.linuxtools.lttng.ui.views.control.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.linuxtools.lttng.ui.views.control.Messages;
import org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.IChannelInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.IDomainInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.IEventInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.ISessionInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.IUstProviderInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.TraceLogLevel;
import org.eclipse.linuxtools.lttng.ui.views.control.model.impl.BaseEventInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.impl.ChannelInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.impl.DomainInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.impl.EventInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.impl.SessionInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.impl.UstProviderInfo;

/**
 * <b><u>LTTngControlService</u></b>
 * <p>
 * Service for sending LTTng trace control commands to remote host.
 * </p>
 */
public class LTTngControlService implements ILttngControlService {
    // ------------------------------------------------------------------------
    // Constants
    // ------------------------------------------------------------------------
    // Command constants
    /**
     * The lttng tools command.
     */
    private final static String CONTROL_COMMAND = "lttng"; //$NON-NLS-1$
    /**
     * Command: lttng list.
     */
    private final static String COMMAND_LIST = CONTROL_COMMAND + " list "; //$NON-NLS-1$
    /**
     * Command to list kernel tracer information.
     */
    private final static String COMMAND_LIST_KERNEL = COMMAND_LIST + "-k"; //$NON-NLS-1$
    /**
     * Command to list user space trace information.
     */
    private final static String COMMAND_LIST_UST = COMMAND_LIST + "-u";  //$NON-NLS-1$
    /**
     * Command to create a session. 
     */
    private final static String COMMAND_CREATE_SESSION = CONTROL_COMMAND + " create "; //$NON-NLS-1$
    /**
     * Command to destroy a session. 
     */
    private final static String COMMAND_DESTROY_SESSION = CONTROL_COMMAND + " destroy "; //$NON-NLS-1$
    /**
     * Command to destroy a session. 
     */
    private final static String COMMAND_START_SESSION = CONTROL_COMMAND + " start "; //$NON-NLS-1$
    /**
     * Command to destroy a session. 
     */
    private final static String COMMAND_STOP_SESSION = CONTROL_COMMAND + " stop "; //$NON-NLS-1$
    /**
     * Command to enable a channel. 
     */
    private final static String COMMAND_ENABLE_CHANNEL = CONTROL_COMMAND + " enable-channel "; //$NON-NLS-1$
    /**
     * Command to destroy a session. 
     */
    private final static String COMMAND_DISABLE_CHANNEL = CONTROL_COMMAND + " disable-channel "; //$NON-NLS-1$

    // Parsing constants
    /**
     * Pattern to match for error output
     */
    private final static Pattern ERROR_PATTERN = Pattern.compile("\\s*Error\\:.*"); //$NON-NLS-1$
    /**
     * Pattern to match for session information (lttng list)
     */
    private final static Pattern SESSION_PATTERN = Pattern.compile("\\s+(\\d+)\\)\\s+(.*)\\s+\\((.*)\\)\\s+\\[(active|inactive)\\].*"); //$NON-NLS-1$
    /**
     * Pattern to match for session information (lttng list <session>)
     */
    private final static Pattern TRACE_SESSION_PATTERN = Pattern.compile("\\s*Tracing\\s+session\\s+(.*)\\:\\s+\\[(active|inactive)\\].*"); //$NON-NLS-1$
    /**
     * Pattern to match for session path information (lttng list <session>)
     */
    private final static Pattern TRACE_SESSION_PATH_PATTERN = Pattern.compile("\\s*Trace\\s+path\\:\\s+(.*)"); //$NON-NLS-1$
    /**
     * Pattern to match for kernel domain information (lttng list <session>)
     */
    private final static Pattern DOMAIN_KERNEL_PATTERN = Pattern.compile("=== Domain: Kernel ==="); //$NON-NLS-1$
    /**
     * Pattern to match for ust domain information (lttng list <session>)
     */
    private final static Pattern DOMAIN_UST_GLOBAL_PATTERN = Pattern.compile("=== Domain: UST global ==="); //$NON-NLS-1$
    /**
     * Pattern to match for channels section (lttng list <session>)
     */
    private final static Pattern CHANNELS_SECTION_PATTERN = Pattern.compile("\\s*Channels\\:"); //$NON-NLS-1$
    /**
     * Pattern to match for channel information (lttng list <session>)
     */
    private final static Pattern CHANNEL_PATTERN = Pattern.compile("\\s*-\\s+(.*)\\:\\s+\\[(enabled|disabled)\\]"); //$NON-NLS-1$
    /**
     * Pattern to match for events section information (lttng list <session>)
     */
    private final static Pattern EVENT_SECTION_PATTERN = Pattern.compile("\\s*Events\\:"); //$NON-NLS-1$
    /**
     * Pattern to match for event information (no enabled events) (lttng list
     * <session>)
     */
    //    private final static String EVENT_NONE_PATTERN = "\\s+None"; //$NON-NLS-1$
    /**
     * Pattern to match for event information (lttng list <session>)
     */
    private final static Pattern EVENT_PATTERN = Pattern.compile("\\s+(.*)\\s+\\(loglevel:\\s+(.*)\\s+\\(\\d*\\)\\)\\s+\\(type:\\s+(.*)\\)\\s+\\[(enabled|disabled)\\].*"); //$NON-NLS-1$
    /**
     * Pattern to match a wildcarded event information (lttng list <session>)
     */
    private final static Pattern WILDCARD_EVENT_PATTERN = Pattern.compile("\\s+(.*)\\s+\\(type:\\s+(.*)\\)\\s+\\[(enabled|disabled)\\].*"); //$NON-NLS-1$
    /**
     * Pattern to match for channel (overwite mode) information (lttng list
     * <session>)
     */
    private final static Pattern OVERWRITE_MODE_ATTRIBUTE = Pattern.compile("\\s+overwrite\\s+mode\\:.*"); //$NON-NLS-1$
    /**
     * Pattern to match indicating false for overwrite mode
     */
    private final static String OVERWRITE_MODE_ATTRIBUTE_FALSE = "0"; //$NON-NLS-1$
    /**
     * Pattern to match for channel (sub-buffer size) information (lttng list
     * <session>)
     */
    private final static Pattern SUBBUFFER_SIZE_ATTRIBUTE = Pattern.compile("\\s+subbufers\\s+size\\:.*"); //$NON-NLS-1$
    /**
     * Pattern to match for channel (number of sub-buffers) information (lttng
     * list <session>)
     */
    private final static Pattern NUM_SUBBUFFERS_ATTRIBUTE = Pattern.compile("\\s+number\\s+of\\s+subbufers\\:.*"); //$NON-NLS-1$
    /**
     * Pattern to match for channel (switch timer) information (lttng list
     * <session>)
     */
    private final static Pattern SWITCH_TIMER_ATTRIBUTE = Pattern.compile("\\s+switch\\s+timer\\s+interval\\:.*"); //$NON-NLS-1$
    /**
     * Pattern to match for channel (read timer) information (lttng list
     * <session>)
     */
    private final static Pattern READ_TIMER_ATTRIBUTE = Pattern.compile("\\s+read\\s+timer\\s+interval\\:.*"); //$NON-NLS-1$
    /**
     * Pattern to match for channel (output type) information (lttng list
     * <session>)
     */
    private final static Pattern OUTPUT_ATTRIBUTE = Pattern.compile("\\s+output\\:.*"); //$NON-NLS-1$
    /**
     * Pattern to match for provider information (lttng list -k/-u)
     */
    private final static Pattern PROVIDER_EVENT_PATTERN = Pattern.compile("\\s*(.*)\\s+\\(loglevel:\\s+(.*)\\s+\\(\\d*\\)\\)\\s+\\(type:\\s+(.*)\\)"); //$NON-NLS-1$
    /**
     * Pattern to match for UST provider information (lttng list -u)
     */
    private final static Pattern UST_PROVIDER_PATTERN = Pattern.compile("\\s*PID\\:\\s+(\\d+)\\s+-\\s+Name\\:\\s+(.*)"); //$NON-NLS-1$
    /**
     * Pattern to match for session information (lttng create <session name>)
     */
    private final static Pattern CREATE_SESSION_NAME_PATTERN = Pattern.compile("\\s*Session\\s+(.*)\\s+created\\."); //$NON-NLS-1$
    /**
     * Pattern to match for session path information (lttng create <session name>)
     */
    private final static Pattern CREATE_SESSION_PATH_PATTERN = Pattern.compile("\\s*Traces\\s+will\\s+be\\s+written\\s+in\\s+(.*).*"); //$NON-NLS-1$
    /**
     * Pattern to match for session command output for "session name not found".
     */
    private final static Pattern SESSION_NOT_FOUND_ERROR_PATTERN = Pattern.compile("\\s*Error:\\s+Session\\s+name\\s+not\\s+found"); //$NON-NLS-1$
    
    // ------------------------------------------------------------------------
    // Attributes
    // ------------------------------------------------------------------------
    /**
     * The command shell implementation
     */
    private ICommandShell fCommandShell = null;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Constructor
     * 
     * @param shell
     *            - the command shell implementation to use
     */
    public LTTngControlService(ICommandShell shell) {
        fCommandShell = shell;
    }

    // ------------------------------------------------------------------------
    // Operations
    // ------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService
     * #getSessionNames(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public String[] getSessionNames(IProgressMonitor monitor) throws ExecutionException {

        String command = COMMAND_LIST;
        ICommandResult result = fCommandShell.executeCommand(command, monitor);

        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // Output:
        // Available tracing sessions:
        // 1) mysession1 (/home/user/lttng-traces/mysession1-20120123-083928)
        // [inactive]
        // 2) mysession (/home/user/lttng-traces/mysession-20120123-083318)
        // [inactive]
        //
        // Use lttng list <session_name> for more details

        ArrayList<String> retArray = new ArrayList<String>();
        int index = 0;
        while (index < result.getOutput().length) {
            String line = result.getOutput()[index];
            Matcher matcher = SESSION_PATTERN.matcher(line);
            if (matcher.matches()) {
                retArray.add(matcher.group(2).trim());
            }
            index++;
        }
        return retArray.toArray(new String[retArray.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService
     * #getSession(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public ISessionInfo getSession(String sessionName, IProgressMonitor monitor) throws ExecutionException {
        String command = COMMAND_LIST + sessionName;
        ICommandResult result = fCommandShell.executeCommand(command, monitor);

        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }

        int index = 0;

        // Output:
        // Tracing session mysession2: [inactive]
        // Trace path: /home/eedbhu/lttng-traces/mysession2-20120123-110330
        ISessionInfo sessionInfo = new SessionInfo(sessionName);

        while (index < result.getOutput().length) {
            // Tracing session mysession2: [inactive]
            // Trace path: /home/eedbhu/lttng-traces/mysession2-20120123-110330
            //
            // === Domain: Kernel ===
            //
            String line = result.getOutput()[index];
            Matcher matcher = TRACE_SESSION_PATTERN.matcher(line);
            if (matcher.matches()) {
                sessionInfo.setSessionState(matcher.group(2));
                index++;
                continue;
            }

            matcher = TRACE_SESSION_PATH_PATTERN.matcher(line);
            if (matcher.matches()) {
                sessionInfo.setSessionPath(matcher.group(1).trim());
                index++;
                continue;
            }

            matcher = DOMAIN_KERNEL_PATTERN.matcher(line);
            if (matcher.matches()) {
                // Create Domain
                IDomainInfo domainInfo = new DomainInfo(Messages.TraceControl_KernelDomainDisplayName);
                sessionInfo.addDomain(domainInfo);

                // in domain kernel
                ArrayList<IChannelInfo> channels = new ArrayList<IChannelInfo>();
                index = parseDomain(result.getOutput(), index, channels);

                // set channels
                domainInfo.setChannels(channels);
                
                // set kernel flag
                domainInfo.setIsKernel(true);
                continue;
            }

            matcher = DOMAIN_UST_GLOBAL_PATTERN.matcher(line);
            if (matcher.matches()) {
                IDomainInfo domainInfo = new DomainInfo(Messages.TraceControl_UstGlobalDomainDisplayName);
                sessionInfo.addDomain(domainInfo);

                // in domain UST
                ArrayList<IChannelInfo> channels = new ArrayList<IChannelInfo>();
                index = parseDomain(result.getOutput(), index, channels);

                // set channels
                domainInfo.setChannels(channels);
                
                // set kernel flag
                domainInfo.setIsKernel(false);
                continue;
            }
            index++;
        }
        return sessionInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService
     * #getKernelProvider(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public List<IBaseEventInfo> getKernelProvider(IProgressMonitor monitor) throws ExecutionException {
        String command = COMMAND_LIST_KERNEL;
        ICommandResult result = fCommandShell.executeCommand(command, monitor);
        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // Kernel events:
        // -------------
        // sched_kthread_stop (type: tracepoint)
        List<IBaseEventInfo> events = new ArrayList<IBaseEventInfo>();
        getProviderEventInfo(result.getOutput(), 0, events);
        return events;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService
     * #getUstProvider()
     */
    @Override
    public List<IUstProviderInfo> getUstProvider() throws ExecutionException {
        return getUstProvider(new NullProgressMonitor());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService
     * #getUstProvider(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public List<IUstProviderInfo> getUstProvider(IProgressMonitor monitor) throws ExecutionException {
        String command = COMMAND_LIST_UST;
        ICommandResult result = fCommandShell.executeCommand(command, monitor);

        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // UST events:
        // -------------
        //
        // PID: 3635 - Name:
        // /home/user/git/lttng-ust/tests/hello.cxx/.libs/lt-hello
        // ust_tests_hello:tptest_sighandler (loglevel: TRACE_EMERG0) (type:
        // tracepoint)
        // ust_tests_hello:tptest (loglevel: TRACE_EMERG0) (type: tracepoint)
        //
        // PID: 6459 - Name:
        // /home/user/git/lttng-ust/tests/hello.cxx/.libs/lt-hello
        // ust_tests_hello:tptest_sighandler (loglevel: TRACE_EMERG0) (type:
        // tracepoint)
        // ust_tests_hello:tptest (loglevel: TRACE_EMERG0) (type: tracepoint)

        List<IUstProviderInfo> allProviders = new ArrayList<IUstProviderInfo>();
        IUstProviderInfo provider = null;

        int index = 0;
        while (index < result.getOutput().length) {
            String line = result.getOutput()[index];
            Matcher matcher = UST_PROVIDER_PATTERN.matcher(line);
            if (matcher.matches()) {

                provider = new UstProviderInfo(matcher.group(2).trim());
                provider.setPid(Integer.valueOf(matcher.group(1).trim()));
                List<IBaseEventInfo> events = new ArrayList<IBaseEventInfo>();
                index = getProviderEventInfo(result.getOutput(), ++index, events);
                provider.setEvents(events);
                allProviders.add(provider);

            } else {
                index++;
            }

        }
        return allProviders;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService#createSession(java.lang.String, java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public ISessionInfo createSession(String sessionName, String sessionPath, IProgressMonitor monitor) throws ExecutionException {

        String newName = formatParameter(sessionName);
        String newPath = formatParameter(sessionPath);

        String command = COMMAND_CREATE_SESSION + newName;
        if (newPath != null && !"".equals(newPath)) { //$NON-NLS-1$
            command += " -o " + newPath; //$NON-NLS-1$
        }

        ICommandResult result = fCommandShell.executeCommand(command, monitor);
        
        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }
        //Session myssession2 created.
        //Traces will be written in /home/user/lttng-traces/myssession2-20120209-095418
        String[] output = result.getOutput();
        
        // Get and verify session name
        Matcher matcher = CREATE_SESSION_NAME_PATTERN.matcher(output[0]);
        String name = null;

        if (matcher.matches()) {
            name = String.valueOf(matcher.group(1).trim());
        } else {
            // Output format not expected
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + //$NON-NLS-1$ //$NON-NLS-2$ 
                    Messages.TraceControl_UnexpectedCommnadOutputFormat + ":\n" + //$NON-NLS-1$ 
                    formatOutput(result.getOutput())); 
        }

        if ((name == null) || (!name.equals(sessionName))) {
            // Unexpected name returned
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + //$NON-NLS-1$ //$NON-NLS-2$ 
                    Messages.TraceControl_UnexpectedNameError + ": " + name); //$NON-NLS-1$ 
        }
        
        // Get and verify session path
        matcher = CREATE_SESSION_PATH_PATTERN.matcher(output[1]);
        String path = null;
        
        if (matcher.matches()) {
            path = String.valueOf(matcher.group(1).trim());
        } else {
            // Output format not expected
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + //$NON-NLS-1$ //$NON-NLS-2$ 
                    Messages.TraceControl_UnexpectedCommnadOutputFormat + ":\n" + //$NON-NLS-1$ 
                    formatOutput(result.getOutput())); 
        }

        if ((path == null) || ((sessionPath != null) && (!path.contains(sessionPath)))) {
            // Unexpected path
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + //$NON-NLS-1$ //$NON-NLS-2$ 
                    Messages.TraceControl_UnexpectedPathError + ": " + name); //$NON-NLS-1$
        }
        
        SessionInfo sessionInfo = new SessionInfo(name);
        sessionInfo.setSessionPath(path);

        return sessionInfo;
    }
    
    @Override
    public void destroySession(String sessionName, IProgressMonitor monitor) throws ExecutionException {
        String newName = formatParameter(sessionName);
        String command = COMMAND_DESTROY_SESSION + newName;

        ICommandResult result = fCommandShell.executeCommand(command, monitor);
        String[] output = result.getOutput();
        
        if (isError(result)) {
            // In case "session not found" treat it as success 
            if ((output == null) || (!SESSION_NOT_FOUND_ERROR_PATTERN.matcher(output[0]).matches())) {
                throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        //Session <sessionName> destroyed
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService#startSession(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void startSession(String sessionName, IProgressMonitor monitor) throws ExecutionException {

        String newSessionName = formatParameter(sessionName);

        String command = COMMAND_START_SESSION + newSessionName;

        ICommandResult result = fCommandShell.executeCommand(command, monitor);

        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }
        //Session <sessionName> started
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService#stopSession(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void stopSession(String sessionName, IProgressMonitor monitor) throws ExecutionException {
        String newSessionName = formatParameter(sessionName);
        String command = COMMAND_STOP_SESSION + newSessionName;

        ICommandResult result = fCommandShell.executeCommand(command, monitor);

        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }
        //Session <sessionName> stopped
        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService#enableChannel(java.lang.String, java.util.List, boolean, org.eclipse.linuxtools.lttng.ui.views.control.model.IChannelInfo, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void enableChannel(String sessionName, List<String> channelNames, boolean isKernel, IChannelInfo info, IProgressMonitor monitor) throws ExecutionException {

        // no channels to enable
        if (channelNames.size() == 0) {
            return;
        }

        String newSessionName = formatParameter(sessionName);
        
        StringBuffer command = new StringBuffer(COMMAND_ENABLE_CHANNEL);

        for (Iterator<String> iterator = channelNames.iterator(); iterator.hasNext();) {
            String channel = (String) iterator.next();
            command.append(channel);
            if (iterator.hasNext()) {
                command.append(","); //$NON-NLS-1$
            }
        }

        if (isKernel) {
            command.append(" -k ");
        } else {
            command.append(" -u ");
        }

        command.append(" -s "); //$NON-NLS-1$
        command.append(newSessionName);

        if (info != null) {
//            --discard            Discard event when buffers are full (default)
            // TODO discard

//            --overwrite          Flight recorder mode
            if (info.isOverwriteMode()) {
                command.append(" --overwrite ");
            }
//            --subbuf-size SIZE   Subbuffer size in bytes
//                                     (default: 4096, kernel default: 262144)
            command.append(" --subbuf-size ");
            command.append(String.valueOf(info.getSubBufferSize()));

//            --num-subbuf NUM     Number of subbufers
//                                     (default: 8, kernel default: 4)
            command.append(" --num-subbuf ");
            command.append(String.valueOf(info.getNumberOfSubBuffers()));
            
//            --switch-timer USEC  Switch timer interval in usec (default: 0)
            command.append(" --switch-timer ");
            command.append(String.valueOf(info.getSwitchTimer()));

//            --read-timer USEC    Read timer interval in usec (default: 200)
            command.append(" --read-timer ");
            command.append(String.valueOf(info.getReadTimer()));
        } 

        ICommandResult result = fCommandShell.executeCommand(command.toString(), monitor);
        
        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.service.ILttngControlService#disableChannel(java.lang.String, java.util.List, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void disableChannel(String sessionName, List<String> channelNames, boolean isKernel, IProgressMonitor monitor) throws ExecutionException{
        
        // no channels to enable
        if (channelNames.size() == 0) {
            return;
        }

        String newSessionName = formatParameter(sessionName);
        
        StringBuffer command = new StringBuffer(COMMAND_DISABLE_CHANNEL);

        for (Iterator<String> iterator = channelNames.iterator(); iterator.hasNext();) {
            String channel = (String) iterator.next();
            command.append(channel);
            if (iterator.hasNext()) {
                command.append(","); //$NON-NLS-1$
            }
        }

        if (isKernel) {
            command.append(" -k ");
        } else {
            command.append(" -u ");
        }

        command.append(" -s "); //$NON-NLS-1$
        command.append(newSessionName);

        ICommandResult result = fCommandShell.executeCommand(command.toString(), monitor);
        
        if (isError(result)) {
            throw new ExecutionException(Messages.TraceControl_CommandError + " " + command + "\n" + formatOutput(result.getOutput())); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    // ------------------------------------------------------------------------
    // Helper methods
    // ------------------------------------------------------------------------
    /**
     * Checks if command result is an error result.
     * 
     * @param result
     *            - the command result to check
     * @return true if error else false
     */
    private boolean isError(ICommandResult result) {
        if ((result.getResult()) != 0 || (result.getOutput().length < 1 || ERROR_PATTERN.matcher(result.getOutput()[0]).matches())) {
            return true;
        }
        return false;
    }

    /**
     * Formats the output string as single string.
     * 
     * @param output
     *            - output array
     * @return - the formatted output
     */
    private String formatOutput(String[] output) {
        if (output == null || output.length == 0) {
            return ""; //$NON-NLS-1$
        }

        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < output.length; i++) {
            ret.append(output[i] + "\n"); //$NON-NLS-1$
        }
        return ret.toString();
    }

    /**
     * Parses the domain information.
     * 
     * @param output
     *            - a command output array
     * @param currentIndex
     *            - current index in command output array
     * @param channels
     *            - list for returning channel information
     * @return the new current index in command output array
     */
    private int parseDomain(String[] output, int currentIndex, List<IChannelInfo> channels) {
        int index = currentIndex;

        // Channels:
        // -------------
        // - channnel1: [enabled]
        //
        // Attributes:
        // overwrite mode: 0
        // subbufers size: 262144
        // number of subbufers: 4
        // switch timer interval: 0
        // read timer interval: 200
        // output: splice()

        while (index < output.length) {
            String line = output[index];

            Matcher outerMatcher = CHANNELS_SECTION_PATTERN.matcher(line);
            if (outerMatcher.matches()) {
                IChannelInfo channelInfo = null;
                while (index < output.length) {
                    String subLine = output[index];

                    Matcher innerMatcher = CHANNEL_PATTERN.matcher(subLine);
                    if (innerMatcher.matches()) {
                        channelInfo = new ChannelInfo(""); //$NON-NLS-1$
                        // get channel name
                        channelInfo.setName(innerMatcher.group(1));

                        // get channel enablement
                        channelInfo.setState(innerMatcher.group(2));

                        // add channel
                        channels.add(channelInfo);

                    } else if (OVERWRITE_MODE_ATTRIBUTE.matcher(subLine).matches()) {
                        String value = getAttributeValue(subLine);
                        channelInfo.setOverwriteMode(!OVERWRITE_MODE_ATTRIBUTE_FALSE.equals(value));
                    } else if (SUBBUFFER_SIZE_ATTRIBUTE.matcher(subLine).matches()) {
                        channelInfo.setSubBufferSize(Long.valueOf(getAttributeValue(subLine)));

                    } else if (NUM_SUBBUFFERS_ATTRIBUTE.matcher(subLine).matches()) {
                        channelInfo.setNumberOfSubBuffers(Integer.valueOf(getAttributeValue(subLine)));

                    } else if (SWITCH_TIMER_ATTRIBUTE.matcher(subLine).matches()) {
                        channelInfo.setSwitchTimer(Long.valueOf(getAttributeValue(subLine)));

                    } else if (READ_TIMER_ATTRIBUTE.matcher(subLine).matches()) {
                        channelInfo.setReadTimer(Long.valueOf(getAttributeValue(subLine)));

                    } else if (OUTPUT_ATTRIBUTE.matcher(subLine).matches()) {
                        channelInfo.setOutputType(getAttributeValue(subLine));

                    } else if (EVENT_SECTION_PATTERN.matcher(subLine).matches()) {
                        List<IEventInfo> events = new ArrayList<IEventInfo>();
                        index = parseEvents(output, index, events);
                        channelInfo.setEvents(events);
                        // we want to stay at the current index to be able to
                        // exit the domain
                        continue;
                    } else if (DOMAIN_KERNEL_PATTERN.matcher(subLine).matches()) {
                        return index;

                    } else if (DOMAIN_UST_GLOBAL_PATTERN.matcher(subLine).matches()) {
                        return index;
                    }
                    index++;
                }
            }
            index++;
        }
        return index;
    }

    /**
     * Parses the event information within a domain.
     * 
     * @param output
     *            - a command output array
     * @param currentIndex
     *            - current index in command output array
     * @param events
     *            - list for returning event information
     * @return the new current index in command output array
     */
    private int parseEvents(String[] output, int currentIndex, List<IEventInfo> events) {
        int index = currentIndex;

        while (index < output.length) {
            String line = output[index];
            if (CHANNEL_PATTERN.matcher(line).matches()) {
                // end of channel
                return index;
            } else if (DOMAIN_KERNEL_PATTERN.matcher(line).matches()) {
                // end of domain
                return index;
            } else if (DOMAIN_UST_GLOBAL_PATTERN.matcher(line).matches()) {
                // end of domain
                return index;
            } 

            Matcher matcher = EVENT_PATTERN.matcher(line);
            Matcher matcher2 = WILDCARD_EVENT_PATTERN.matcher(line);

            if (matcher.matches()) {
                IEventInfo eventInfo = new EventInfo(matcher.group(1).trim());
                eventInfo.setLogLevel(matcher.group(2).trim());
                eventInfo.setEventType(matcher.group(3).trim());
                eventInfo.setState(matcher.group(4));
                events.add(eventInfo);
            } else if (matcher2.matches()) {
                IEventInfo eventInfo = new EventInfo(matcher2.group(1).trim());
                eventInfo.setLogLevel(TraceLogLevel.LEVEL_UNKNOWN);
                eventInfo.setEventType(matcher2.group(2).trim());
                eventInfo.setState(matcher2.group(3));
                events.add(eventInfo);
            }
//            else if (line.matches(EVENT_NONE_PATTERN)) {
                // do nothing
//            } else 
            index++;
        }

        return index;
    }

    /**
     * Parses a line with attributes: <attribute Name>: <attribute value>
     * 
     * @param line
     *            - attribute line to parse
     * @return the attribute value as string
     */
    private String getAttributeValue(String line) {
        String[] temp = line.split("\\: "); //$NON-NLS-1$
        return temp[1];
    }

    /**
     * Parses the event information within a provider.
     * 
     * @param output
     *            - a command output array
     * @param currentIndex
     *            - current index in command output array
     * @param events
     *            - list for returning event information
     * @return the new current index in command output array
     */
    private int getProviderEventInfo(String[] output, int currentIndex, List<IBaseEventInfo> events) {
        int index = currentIndex;
        while (index < output.length) {
            String line = output[index];
            Matcher matcher = PROVIDER_EVENT_PATTERN.matcher(line);
            if (matcher.matches()) {
                // sched_kthread_stop (loglevel: TRACE_EMERG0) (type:
                // tracepoint)
                IBaseEventInfo eventInfo = new BaseEventInfo(matcher.group(1).trim());
                eventInfo.setLogLevel(matcher.group(2).trim());
                eventInfo.setEventType(matcher.group(3).trim());
                events.add(eventInfo);
            } else if (UST_PROVIDER_PATTERN.matcher(line).matches()) {
                return index;
            }
            index++;
        }
        return index;
    }

    /**
     * Formats a command parameter for the command execution i.e. adds quotes 
     * at the beginning and end if necessary.
     * @param parameter - parameter to format
     * @return formated parameter
     */
    private String formatParameter(String parameter) {
        if (parameter != null) {
            String newString = String.valueOf(parameter);

            if (parameter.contains(" ")) { //$NON-NLS-1$
                newString = "\"" + newString + "\""; //$NON-NLS-1$ //$NON-NLS-2$
            }
            return newString;
        }
        return null;
    }
    
}