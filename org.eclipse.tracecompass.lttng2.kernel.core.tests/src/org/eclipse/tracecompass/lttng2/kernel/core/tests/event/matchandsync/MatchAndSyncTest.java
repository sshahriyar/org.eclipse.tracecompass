/*******************************************************************************
 * Copyright (c) 2013 École Polytechnique de Montréal
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Geneviève Bastien - Initial implementation
 *******************************************************************************/

package org.eclipse.tracecompass.lttng2.kernel.core.tests.event.matchandsync;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.tracecompass.lttng2.kernel.core.event.matching.TcpEventMatching;
import org.eclipse.tracecompass.lttng2.kernel.core.event.matching.TcpLttngEventMatching;
import org.eclipse.tracecompass.tmf.core.event.matching.IMatchProcessingUnit;
import org.eclipse.tracecompass.tmf.core.event.matching.TmfEventMatching;
import org.eclipse.tracecompass.tmf.core.event.matching.TmfNetworkEventMatching;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.ctf.core.tests.shared.CtfTmfTestTrace;
import org.eclipse.tracecompass.tmf.ctf.core.trace.CtfTmfTrace;
import org.junit.Test;

/**
 * Tests for {@link TcpEventMatching}
 *
 * @author Geneviève Bastien
 */
@SuppressWarnings("nls")
public class MatchAndSyncTest {

    /**
     * Testing the packet matching
     */
    @Test
    public void testMatching() {
        assumeTrue(CtfTmfTestTrace.SYNC_SRC.exists());
        assumeTrue(CtfTmfTestTrace.SYNC_DEST.exists());
        try (CtfTmfTrace trace1 = CtfTmfTestTrace.SYNC_SRC.getTrace();
                CtfTmfTrace trace2 = CtfTmfTestTrace.SYNC_DEST.getTrace();) {

            List<ITmfTrace> tracearr = new LinkedList<>();
            tracearr.add(trace1);
            tracearr.add(trace2);

            TmfEventMatching.registerMatchObject(new TcpEventMatching());
            TmfEventMatching.registerMatchObject(new TcpLttngEventMatching());

            TmfNetworkEventMatching twoTraceMatch = new TmfNetworkEventMatching(tracearr);
            assertTrue(twoTraceMatch.matchEvents());

            /* Set method and fields accessible to make sure the counts are ok */
            try {
                /* Verify number of matches */
                Method method = TmfEventMatching.class.getDeclaredMethod("getProcessingUnit");
                method.setAccessible(true);
                IMatchProcessingUnit procUnit = (IMatchProcessingUnit) method.invoke(twoTraceMatch);
                assertEquals(46, procUnit.countMatches());

                /* Verify unmatched incoming */
                Field fieldIn = twoTraceMatch.getClass().getDeclaredField("fUnmatchedIn");
                fieldIn.setAccessible(true);
                Map<?, ?> unmatched = (Map<?, ?> ) fieldIn.get(twoTraceMatch);
                Map<?, ?> unmatchedTrace = (Map<?, ?>) unmatched.get(trace1);
                assertEquals(3, unmatchedTrace.size());
                unmatchedTrace = (Map<?, ?>) unmatched.get(trace2);
                assertEquals(2, unmatchedTrace.size());

                /* Verify unmatched outgoing */
                Field fieldOut = twoTraceMatch.getClass().getDeclaredField("fUnmatchedOut");
                fieldOut.setAccessible(true);
                unmatched = (Map<?, ?> ) fieldOut.get(twoTraceMatch);
                unmatchedTrace = (Map<?, ?>) unmatched.get(trace1);
                assertEquals(2, unmatchedTrace.size());
                unmatchedTrace = (Map<?, ?>) unmatched.get(trace2);
                assertEquals(1, unmatchedTrace.size());

            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e) {
                fail(e.getMessage());
            }

        }
    }

}
