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

import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAProjectVersionPersistence;
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
public class JIRAProjectVersionFinderBaseImpl
	extends BasePersistenceImpl<JIRAProjectVersion> {

	public JIRAProjectVersionFinderBaseImpl() {
		setModelClass(JIRAProjectVersion.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("jiraProjectVersionId", "id");
		dbColumnNames.put("jiraProjectId", "project");
		dbColumnNames.put("name", "vname");

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

	private static final Log _log = LogFactoryUtil.getLog(
		JIRAProjectVersionFinderBaseImpl.class);

}