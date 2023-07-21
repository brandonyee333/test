/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.transaction;

import com.liferay.portal.kernel.transaction.TransactionLifecycleManager;

import org.aopalliance.intercept.MethodInvocation;

import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class DefaultTransactionExecutor
	implements TransactionExecutor, TransactionHandler {

	@Override
	public void commit(
		PlatformTransactionManager platformTransactionManager,
		TransactionAttributeAdapter transactionAttributeAdapter,
		TransactionStatusAdapter transactionStatusAdapter) {

		_commit(
			platformTransactionManager, transactionAttributeAdapter,
			transactionStatusAdapter, null);
	}

	@Override
	public Object execute(
			PlatformTransactionManager platformTransactionManager,
			TransactionAttributeAdapter transactionAttributeAdapter,
			MethodInvocation methodInvocation)
		throws Throwable {

		TransactionStatusAdapter transactionStatusAdapter = start(
			platformTransactionManager, transactionAttributeAdapter);

		Object returnValue = null;

		try {
			returnValue = methodInvocation.proceed();
		}
		catch (Throwable throwable) {
			rollback(
				platformTransactionManager, throwable,
				transactionAttributeAdapter, transactionStatusAdapter);
		}

		commit(
			platformTransactionManager, transactionAttributeAdapter,
			transactionStatusAdapter);

		return returnValue;
	}

	@Override
	public void rollback(
			PlatformTransactionManager platformTransactionManager,
			Throwable throwable,
			TransactionAttributeAdapter transactionAttributeAdapter,
			TransactionStatusAdapter transactionStatusAdapter)
		throws Throwable {

		if (transactionAttributeAdapter.rollbackOn(throwable)) {
			try {
				platformTransactionManager.rollback(
					transactionStatusAdapter.getTransactionStatus());
			}
			catch (Throwable t) {
				t.addSuppressed(throwable);

				throw t;
			}
			finally {
				TransactionLifecycleManager.fireTransactionRollbackedEvent(
					transactionAttributeAdapter, transactionStatusAdapter,
					throwable);
			}
		}
		else {
			_commit(
				platformTransactionManager, transactionAttributeAdapter,
				transactionStatusAdapter, throwable);
		}

		throw throwable;
	}

	@Override
	public TransactionStatusAdapter start(
		PlatformTransactionManager platformTransactionManager,
		TransactionAttributeAdapter transactionAttributeAdapter) {

		TransactionStatusAdapter transactionStatusAdapter =
			new TransactionStatusAdapter(
				platformTransactionManager,
				platformTransactionManager.getTransaction(
					transactionAttributeAdapter));

		TransactionLifecycleManager.fireTransactionCreatedEvent(
			transactionAttributeAdapter, transactionStatusAdapter);

		return transactionStatusAdapter;
	}

	private void _commit(
		PlatformTransactionManager platformTransactionManager,
		TransactionAttributeAdapter transactionAttributeAdapter,
		TransactionStatusAdapter transactionStatusAdapter,
		Throwable applicationThrowable) {

		Throwable throwable = null;

		try {
			platformTransactionManager.commit(
				transactionStatusAdapter.getTransactionStatus());
		}
		catch (Throwable t) {
			if (applicationThrowable != null) {
				t.addSuppressed(applicationThrowable);
			}

			throwable = t;

			throw t;
		}
		finally {
			if (throwable != null) {
				TransactionLifecycleManager.fireTransactionRollbackedEvent(
					transactionAttributeAdapter, transactionStatusAdapter,
					throwable);
			}
			else {
				TransactionLifecycleManager.fireTransactionCommittedEvent(
					transactionAttributeAdapter, transactionStatusAdapter);
			}
		}
	}

}