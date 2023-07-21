/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.service.persistence.impl;

import com.liferay.osb.customer.release.tool.model.ArtifactVersion;
import com.liferay.osb.customer.release.tool.service.persistence.ArtifactVersionPersistence;
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
public class ArtifactVersionFinderBaseImpl
	extends BasePersistenceImpl<ArtifactVersion> {

	public ArtifactVersionFinderBaseImpl() {
		setModelClass(ArtifactVersion.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("group", "group_");

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
		return getArtifactVersionPersistence().getBadColumnNames();
	}

	/**
	 * Returns the artifact version persistence.
	 *
	 * @return the artifact version persistence
	 */
	public ArtifactVersionPersistence getArtifactVersionPersistence() {
		return artifactVersionPersistence;
	}

	/**
	 * Sets the artifact version persistence.
	 *
	 * @param artifactVersionPersistence the artifact version persistence
	 */
	public void setArtifactVersionPersistence(
		ArtifactVersionPersistence artifactVersionPersistence) {

		this.artifactVersionPersistence = artifactVersionPersistence;
	}

	@BeanReference(type = ArtifactVersionPersistence.class)
	protected ArtifactVersionPersistence artifactVersionPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		ArtifactVersionFinderBaseImpl.class);

}