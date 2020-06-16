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

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAComponentPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAComponentFinderBaseImpl extends BasePersistenceImpl<JIRAComponent> {
	@Override
	public Set<String> getBadColumnNames() {
		return getJIRAComponentPersistence().getBadColumnNames();
	}

	/**
	 * Returns the jira component persistence.
	 *
	 * @return the jira component persistence
	 */
	public JIRAComponentPersistence getJIRAComponentPersistence() {
		return jiraComponentPersistence;
	}

	/**
	 * Sets the jira component persistence.
	 *
	 * @param jiraComponentPersistence the jira component persistence
	 */
	public void setJIRAComponentPersistence(
		JIRAComponentPersistence jiraComponentPersistence) {
		this.jiraComponentPersistence = jiraComponentPersistence;
	}

	@BeanReference(type = JIRAComponentPersistence.class)
	protected JIRAComponentPersistence jiraComponentPersistence;
}