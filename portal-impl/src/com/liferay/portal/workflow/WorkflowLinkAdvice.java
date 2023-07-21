/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.workflow.RequiredWorkflowDefinitionException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowLinkAdvice {

	public Object invoke(ProceedingJoinPoint proceedingJoinPoint)
		throws Throwable {

		Signature signature = proceedingJoinPoint.getSignature();

		String methodName = signature.getName();

		Object[] arguments = proceedingJoinPoint.getArgs();

		if (methodName.equals(_UPDATE_ACTIVE)) {
			long companyId = (Long)arguments[0];
			String name = (String)arguments[2];
			int version = (Integer)arguments[3];
			boolean active = (Boolean)arguments[4];

			if (!active) {
				int workflowDefinitionLinksCount =
					WorkflowDefinitionLinkLocalServiceUtil.
						getWorkflowDefinitionLinksCount(
							companyId, name, version);

				if (workflowDefinitionLinksCount >= 1) {
					throw new RequiredWorkflowDefinitionException();
				}
			}
		}

		return proceedingJoinPoint.proceed();
	}

	private static final String _UPDATE_ACTIVE = "updateActive";

}