/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.service.persistence.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetPersistence;
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
public class AssetEntrySetFinderBaseImpl
	extends BasePersistenceImpl<AssetEntrySet> {

	public AssetEntrySetFinderBaseImpl() {
		setModelClass(AssetEntrySet.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

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
		return getAssetEntrySetPersistence().getBadColumnNames();
	}

	/**
	 * Returns the asset entry set persistence.
	 *
	 * @return the asset entry set persistence
	 */
	public AssetEntrySetPersistence getAssetEntrySetPersistence() {
		return assetEntrySetPersistence;
	}

	/**
	 * Sets the asset entry set persistence.
	 *
	 * @param assetEntrySetPersistence the asset entry set persistence
	 */
	public void setAssetEntrySetPersistence(
		AssetEntrySetPersistence assetEntrySetPersistence) {

		this.assetEntrySetPersistence = assetEntrySetPersistence;
	}

	@BeanReference(type = AssetEntrySetPersistence.class)
	protected AssetEntrySetPersistence assetEntrySetPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntrySetFinderBaseImpl.class);

}