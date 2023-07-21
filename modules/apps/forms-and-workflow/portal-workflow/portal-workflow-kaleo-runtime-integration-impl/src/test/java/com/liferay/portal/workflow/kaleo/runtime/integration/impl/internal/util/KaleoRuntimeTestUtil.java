/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.integration.impl.internal.util;

import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.util.Arrays;

import org.junit.Assert;

import org.mockito.Mockito;

/**
 * @author Marcellus Tavares
 */
public class KaleoRuntimeTestUtil {

	public static void assertWorkflowTaskAssignee(
		String expectedAssigneeClassName, long expectedAssigneeClassPK,
		WorkflowTaskAssignee actualWorkflowTaskAssignee) {

		Assert.assertEquals(
			expectedAssigneeClassName,
			actualWorkflowTaskAssignee.getAssigneeClassName());

		Assert.assertEquals(
			expectedAssigneeClassPK,
			actualWorkflowTaskAssignee.getAssigneeClassPK());
	}

	public static KaleoTaskAssignmentInstance mockKaleoTaskAssignmentInstance(
		String returnAssigneeClassName, long returnAssigneeClassPK) {

		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = Mockito.mock(
			KaleoTaskAssignmentInstance.class);

		Mockito.when(
			kaleoTaskAssignmentInstance.getAssigneeClassName()
		).thenReturn(
			returnAssigneeClassName
		);

		Mockito.when(
			kaleoTaskAssignmentInstance.getAssigneeClassPK()
		).thenReturn(
			returnAssigneeClassPK
		);

		return kaleoTaskAssignmentInstance;
	}

	public static KaleoTaskInstanceToken mockKaleoTaskInstanceToken(
		KaleoTaskAssignmentInstance... returnKaleoTaskAssignmentInstances) {

		KaleoTaskInstanceToken kaleoTaskInstanceToken = Mockito.mock(
			KaleoTaskInstanceToken.class);

		Mockito.when(
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances()
		).thenReturn(
			Arrays.asList(returnKaleoTaskAssignmentInstances)
		);

		if ((returnKaleoTaskAssignmentInstances.length == 0) ||
			(returnKaleoTaskAssignmentInstances.length > 1)) {

			return kaleoTaskInstanceToken;
		}

		Mockito.when(
			kaleoTaskInstanceToken.getFirstKaleoTaskAssignmentInstance()
		).thenReturn(
			returnKaleoTaskAssignmentInstances[0]
		);

		return kaleoTaskInstanceToken;
	}

}