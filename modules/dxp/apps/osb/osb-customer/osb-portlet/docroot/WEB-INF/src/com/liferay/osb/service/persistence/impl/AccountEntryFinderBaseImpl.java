/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.persistence.impl;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.persistence.AccountEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountEntryFinderBaseImpl extends BasePersistenceImpl<AccountEntry> {
	@Override
	public Set<String> getBadColumnNames() {
		return getAccountEntryPersistence().getBadColumnNames();
	}

	/**
	 * Returns the account entry persistence.
	 *
	 * @return the account entry persistence
	 */
	public AccountEntryPersistence getAccountEntryPersistence() {
		return accountEntryPersistence;
	}

	/**
	 * Sets the account entry persistence.
	 *
	 * @param accountEntryPersistence the account entry persistence
	 */
	public void setAccountEntryPersistence(
		AccountEntryPersistence accountEntryPersistence) {
		this.accountEntryPersistence = accountEntryPersistence;
	}

	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
}