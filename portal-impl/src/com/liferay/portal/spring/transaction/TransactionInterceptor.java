/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.transaction;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

/**
 * @author Shuyang Zhou
 */
public class TransactionInterceptor implements MethodInterceptor {

	public TransactionAttributeSource getTransactionAttributeSource() {
		return transactionAttributeSource;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Method method = methodInvocation.getMethod();

		Class<?> targetClass = null;

		Object targetBean = methodInvocation.getThis();

		if (targetBean != null) {
			targetClass = targetBean.getClass();
		}

		TransactionAttribute transactionAttribute =
			transactionAttributeSource.getTransactionAttribute(
				method, targetClass);

		if (transactionAttribute == null) {
			return methodInvocation.proceed();
		}

		TransactionAttributeAdapter transactionAttributeAdapter =
			new TransactionAttributeAdapter(transactionAttribute);

		return transactionExecutor.execute(
			platformTransactionManager, transactionAttributeAdapter,
			methodInvocation);
	}

	public void setPlatformTransactionManager(
		PlatformTransactionManager platformTransactionManager) {

		this.platformTransactionManager = platformTransactionManager;
	}

	public void setTransactionAttributeSource(
		TransactionAttributeSource transactionAttributeSource) {

		this.transactionAttributeSource = transactionAttributeSource;
	}

	public void setTransactionExecutor(
		TransactionExecutor transactionExecutor) {

		this.transactionExecutor = transactionExecutor;
	}

	protected PlatformTransactionManager platformTransactionManager;
	protected TransactionAttributeSource transactionAttributeSource;
	protected TransactionExecutor transactionExecutor;

}