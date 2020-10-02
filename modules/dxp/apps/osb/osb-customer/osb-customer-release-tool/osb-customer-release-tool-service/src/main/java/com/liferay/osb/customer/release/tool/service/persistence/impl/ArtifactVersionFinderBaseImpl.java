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

package com.liferay.osb.customer.release.tool.service.persistence.impl;

import com.liferay.osb.customer.release.tool.model.ArtifactVersion;
import com.liferay.osb.customer.release.tool.service.persistence.ArtifactVersionPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ArtifactVersionFinderBaseImpl extends BasePersistenceImpl<ArtifactVersion> {
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
}