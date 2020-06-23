/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.release.notes.jira.service.persistence.impl;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAComponentPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAComponentFinderBaseImpl
	extends BasePersistenceImpl<JIRAComponent> {

	public JIRAComponentFinderBaseImpl() {
		setModelClass(JIRAComponent.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("jiraComponentId", "id");
		dbColumnNames.put("jiraProjectId", "project");
		dbColumnNames.put("name", "cname");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

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

	private static final Log _log = LogFactoryUtil.getLog(
		JIRAComponentFinderBaseImpl.class);

}