/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.util.comparator;

import com.liferay.asset.kernel.exception.NoSuchCategoryPropertyException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class AssetCategoryPropertyComparator extends OrderByComparator {

	public AssetCategoryPropertyComparator(String property) {
		this(property, true);
	}

	public AssetCategoryPropertyComparator(String property, boolean ascending) {
		_property = property;
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetCategory assetCategory1 = (AssetCategory)obj1;
		AssetCategory assetCategory2 = (AssetCategory)obj2;

		double propertyValue1 = getPropertyValue(
			assetCategory1.getCategoryId());
		double propertyValue2 = getPropertyValue(
			assetCategory2.getCategoryId());

		int value = 0;

		if (propertyValue1 > propertyValue2) {
			value = 1;
		}
		else if (propertyValue1 < propertyValue2) {
			value = -1;
		}

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	protected double getPropertyValue(long assetCategoryId) {
		try {
			AssetCategoryProperty assetCategoryProperty =
				AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(
					assetCategoryId, _property);

			return GetterUtil.getDouble(assetCategoryProperty.getValue());
		}
		catch (NoSuchCategoryPropertyException nscpe) {
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return 0;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetCategoryPropertyComparator.class);

	private final boolean _ascending;
	private final String _property;

}