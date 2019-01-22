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

package com.liferay.osb.hook.upgrade.v5_0_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20190122121242433_ProductEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20190122121242433L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			ProductEntry.class);

		List<ProductEntry> productEntries =
			ProductEntryLocalServiceUtil.getProductEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ProductEntry productEntry : productEntries) {
			if (productEntry.isAnalyticsCloud()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK,
					"liferay_analytics_cloud");
			}
			else if (productEntry.isCommerce()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK, "liferay_commerce");
			}
			else if (productEntry.isDeveloperTools()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK, "developer_tools");
			}
			else if (productEntry.isDeviceDetection()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK,
					"mobile_device_detection");
			}
			else if (productEntry.isDigitalEnterprise()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK, "liferay_dxp");
			}
			else if (productEntry.isDXPCloud()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK,
					"liferay_dxp_cloud");
			}
			else if (productEntry.isEnterpriseSearch()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK,
					"enterprise_search");
			}
			else if (productEntry.isManagementTools()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK,
					"management_tools_lcs");
			}
			else if (productEntry.isMobileExperience()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK,
					"mobile_experience");
			}
			else if (productEntry.isPortal()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK, "liferay_portal");
			}
			else if (productEntry.isProductivityTools()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK,
					"productivity_tools_sync");
			}
			else if (productEntry.isSocialOffice()) {
				ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK, "social_office");
			}
		}
	}

}