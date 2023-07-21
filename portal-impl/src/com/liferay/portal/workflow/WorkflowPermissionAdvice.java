/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowPermissionAdvice {

	public Object invoke(ProceedingJoinPoint proceedingJoinPoint)
		throws Throwable {

		Signature signature = proceedingJoinPoint.getSignature();

		String methodName = signature.getName();

		Object[] arguments = proceedingJoinPoint.getArgs();

		if (methodName.equals(_ASSIGN_WORKFLOW_TASK_TO_USER_METHOD_NAME)) {
			long userId = (Long)arguments[1];

			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (permissionChecker.getUserId() != userId) {
				throw new PrincipalException();
			}
		}

		return proceedingJoinPoint.proceed();
	}

	private static final String _ASSIGN_WORKFLOW_TASK_TO_USER_METHOD_NAME =
		"assignWorkflowTaskToUser";

}