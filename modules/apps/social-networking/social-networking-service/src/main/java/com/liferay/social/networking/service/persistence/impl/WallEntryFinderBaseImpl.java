/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.social.networking.model.WallEntry;
import com.liferay.social.networking.service.persistence.WallEntryPersistence;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WallEntryFinderBaseImpl extends BasePersistenceImpl<WallEntry> {

	public WallEntryFinderBaseImpl() {
		setModelClass(WallEntry.class);
	}

	/**
	 * Returns the wall entry persistence.
	 *
	 * @return the wall entry persistence
	 */
	public WallEntryPersistence getWallEntryPersistence() {
		return wallEntryPersistence;
	}

	/**
	 * Sets the wall entry persistence.
	 *
	 * @param wallEntryPersistence the wall entry persistence
	 */
	public void setWallEntryPersistence(
		WallEntryPersistence wallEntryPersistence) {

		this.wallEntryPersistence = wallEntryPersistence;
	}

	@BeanReference(type = WallEntryPersistence.class)
	protected WallEntryPersistence wallEntryPersistence;

}