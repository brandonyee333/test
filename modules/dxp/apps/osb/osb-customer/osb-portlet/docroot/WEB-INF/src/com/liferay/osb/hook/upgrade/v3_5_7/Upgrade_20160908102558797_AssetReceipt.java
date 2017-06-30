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

package com.liferay.osb.hook.upgrade.v3_5_7;

import com.liferay.osb.NoSuchAppEntryException;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;

import java.util.List;

/**
 * @author Haote Chou
 */
public class Upgrade_20160908102558797_AssetReceipt extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160908102558797L;
	}

	protected void deleteAssetReceipts(Long[] appEntryIds) throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AssetReceipt.class);

		Property productClassPKProperty = PropertyFactoryUtil.forName(
			"productClassPK");

		dynamicQuery.add(productClassPKProperty.in(appEntryIds));

		List<AssetReceipt> assetReceipts =
			(List<AssetReceipt>)AssetReceiptLocalServiceUtil.dynamicQuery(
				dynamicQuery);

		for (AssetReceipt assetReceipt : assetReceipts) {
			AssetReceiptLocalServiceUtil.deleteAssetReceipt(assetReceipt);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		deleteAssetReceipts(getAppEntryIds());
	}

	protected Long[] getAppEntryIds() throws Exception {
		String[] titles = {
			"Liferay CE Collaboration", "Liferay CE Forms and Workflow",
			"Liferay CE Foundation", "Liferay CE Static",
			"Liferay CE Web Experience", "Liferay Collaboration",
			"Liferay Forms and Workflow", "Liferay Foundation",
			"Liferay Static", "Liferay Web Experience"
		};

		int size = titles.length;

		Long[] appEntryIds = new Long[size];

		for (int i = 0; i < size; i++) {
			try {
				AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
					titles[i]);

				appEntryIds[i] = appEntry.getAppEntryId();
			}
			catch (Exception e) {
				if (e instanceof NoSuchAppEntryException) {
					appEntryIds[i] = 0L;

					continue;
				}

				throw e;
			}
		}

		return appEntryIds;
	}

}