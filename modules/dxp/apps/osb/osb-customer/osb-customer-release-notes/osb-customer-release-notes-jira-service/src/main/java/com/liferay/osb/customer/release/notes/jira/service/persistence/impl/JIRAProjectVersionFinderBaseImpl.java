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

package com.liferay.osb.customer.release.notes.jira.service.persistence.impl;

import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAProjectVersionPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAProjectVersionFinderBaseImpl extends BasePersistenceImpl<JIRAProjectVersion> {
	@Override
	public Set<String> getBadColumnNames() {
		return getJIRAProjectVersionPersistence().getBadColumnNames();
	}

	/**
	 * Returns the jira project version persistence.
	 *
	 * @return the jira project version persistence
	 */
	public JIRAProjectVersionPersistence getJIRAProjectVersionPersistence() {
		return jiraProjectVersionPersistence;
	}

	/**
	 * Sets the jira project version persistence.
	 *
	 * @param jiraProjectVersionPersistence the jira project version persistence
	 */
	public void setJIRAProjectVersionPersistence(
		JIRAProjectVersionPersistence jiraProjectVersionPersistence) {
		this.jiraProjectVersionPersistence = jiraProjectVersionPersistence;
	}

	@BeanReference(type = JIRAProjectVersionPersistence.class)
	protected JIRAProjectVersionPersistence jiraProjectVersionPersistence;
}