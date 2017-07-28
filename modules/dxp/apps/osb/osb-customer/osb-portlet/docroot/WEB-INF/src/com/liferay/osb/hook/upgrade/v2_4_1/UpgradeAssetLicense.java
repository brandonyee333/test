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

package com.liferay.osb.hook.upgrade.v2_4_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

*/

/**
 * @author Ryan Park
 */
public class UpgradeAssetLicense extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	protected void doUpgrade() throws Exception {
		upgradeAssetLicense();
	}

	protected void doUpgradeAssetLicense(AssetLicense assetLicense)
		throws Exception {

		assetLicense.setLicenseId(getLicenseId(assetLicense));

		AssetLicenseLocalServiceUtil.updateAssetLicense(assetLicense);
	}

	protected List<AppEntry> getAppEntries() throws Exception {
		Junction junction = RestrictionsFactoryUtil.conjunction();

		Property licenseTypeProperty = PropertyFactoryUtil.forName(
			"licenseType");

		junction.add(
			licenseTypeProperty.eq(
				AssetLicenseConstants.LICENSE_TYPE_PRODUCTION));

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AppEntry.class);

		dynamicQuery.add(junction);

		return AppEntryLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	protected String getLicenseId(AssetLicense assetLicense) {
		StringBundler sb = new StringBundler(4);

		if (assetLicense.getUsageType() ==
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER) {

			sb.append("Developer License ");
		}
		else if (assetLicense.getUsageType() ==
					AssetLicenseConstants.USAGE_TYPE_STANDARD) {

			sb.append("Standard License ");
		}
		else if (assetLicense.getUsageType() ==
					AssetLicenseConstants.USAGE_TYPE_TRIAL) {

			sb.append("Trial License ");
		}

		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(assetLicense.getAssetLicenseId());
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected void upgradeAppEntry(AppEntry appEntry) throws Exception {
		List<AppVersion> appVersions =
			AppVersionLocalServiceUtil.getAppVersions(
				appEntry.getAppEntryId(), WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (AppVersion appVersion : appVersions) {
			List<AssetLicense> assetLicenses =
				AssetLicenseLocalServiceUtil.getAssetLicenses(
					AppVersion.class.getName(), appVersion.getAppVersionId(),
					WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			for (AssetLicense assetLicense : assetLicenses) {
				doUpgradeAssetLicense(assetLicense);
			}
		}
	}

	protected void upgradeAssetLicense() throws Exception {
		List<AppEntry> appEntries = getAppEntries();

		for (AppEntry appEntry : appEntries) {
			if (appEntry.isDeveloperEntryLiferayInc()) {
				continue;
			}

			upgradeAppEntry(appEntry);
		}
	}

}

*/

}