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

package com.liferay.osb.hook.upgrade.v2_3_1;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.List;

/**
 * @author Joan Kim
 */
public class UpgradeAssetReceiptLicense extends UpgradeProcess {

	protected DynamicQuery buildDynamicQuery() {
		Junction junction = RestrictionsFactoryUtil.conjunction();

		Property typeProperty = PropertyFactoryUtil.forName(
			"productClassNameId");

		junction.add(
			typeProperty.eq(PortalUtil.getClassNameId(AssetLicense.class)));

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AssetReceiptLicense.class);

		return dynamicQuery.add(junction);
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAssetReceiptLicenses();
	}

	protected void upgradeAssetReceiptLicenses() throws Exception {
		DynamicQuery dynamicQuery = buildDynamicQuery();

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.dynamicQuery(dynamicQuery);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			AssetLicense assetLicense = assetReceiptLicense.getAssetLicense();

			assetReceiptLicense.setProductClassNameId(
				assetLicense.getClassNameId());
			assetReceiptLicense.setProductClassPK(assetLicense.getClassPK());

			AssetReceiptLicenseLocalServiceUtil.updateAssetReceiptLicense(
				assetReceiptLicense);
		}
	}

}