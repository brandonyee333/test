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
		else {
			return -value;
		}
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