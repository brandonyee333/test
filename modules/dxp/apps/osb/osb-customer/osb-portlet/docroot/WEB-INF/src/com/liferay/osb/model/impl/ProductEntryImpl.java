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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class ProductEntryImpl extends ProductEntryBaseImpl {

	public ProductEntryImpl() {
	}

	public List<ListType> getAllVersionsListTypes() {
		if (isCommerce()) {
			return ListTypeServiceUtil.getListTypes(
				ProductEntryConstants.LIST_TYPE_COMMERCE_ALL_VERSIONS);
		}

		if (isPortal()) {
			return ListTypeServiceUtil.getListTypes(
				ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS);
		}

		if (isSocialOffice()) {
			return ListTypeServiceUtil.getListTypes(
				ProductEntryConstants.LIST_TYPE_SOCIAL_OFFICE_ALL_VERSIONS);
		}

		String majorListType =
			ProductEntry.class.getName() + StringPool.PERIOD +
				getVersionsListType();

		String allVersionsListType = ProductEntryConstants.getAllListType(
			majorListType);

		if (Validator.isNotNull(allVersionsListType)) {
			return ListTypeServiceUtil.getListTypes(allVersionsListType);
		}

		return ListTypeServiceUtil.getListTypes(
			ProductEntryConstants.LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS);
	}

	public String getDisplayName() {
		return ProductEntryConstants.getDisplayName(getName());
	}

	public String[] getDossieraIdMappings() {
		long classNameId = PortalUtil.getClassNameId(
			ProductEntry.class.getName());

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, getProductEntryId(),
				ExternalIdMapperConstants.TYPE_DOSSIERA);

		String[] dossieraIdMappings = new String[externalIdMappers.size()];

		for (int i = 0; i < externalIdMappers.size(); i++) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(i);

			dossieraIdMappings[i] = externalIdMapper.getExternalId();
		}

		return dossieraIdMappings;
	}

	public String getEnvironmentLabel() {
		return ProductEntryConstants.getEnvironmentLabel(getEnvironment());
	}

	@JSON
	public List<LicenseEntry> getLicenseEntries() {
		return LicenseEntryLocalServiceUtil.getLicenseEntries(
			getProductEntryId());
	}

	public String getTypeLabel() {
		return ProductEntryConstants.getTypeLabel(getType());
	}

	public List<ListType> getVersionsListTypes() {
		String listType =
			ProductEntry.class.getName() + StringPool.PERIOD +
				getVersionsListType();

		return ListTypeServiceUtil.getListTypes(listType);
	}

	public String getZendeskTag() {
		long classNameId = PortalUtil.getClassNameId(
			ProductEntry.class.getName());

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, getProductEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (!externalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			return externalIdMapper.getExternalId();
		}

		return StringPool.BLANK;
	}

	public boolean isAnalyticsCloud() {
		String name = getName();

		if (name.contains("Analytics Cloud")) {
			return true;
		}

		return false;
	}

	public boolean isAnalyticsCloudBusiness() {
		String name = getName();

		if (name.equals("Liferay Analytics Cloud Business")) {
			return true;
		}

		return false;
	}

	public boolean isAnalyticsCloudEnterprise() {
		String name = getName();

		if (name.equals("Liferay Analytics Cloud Enterprise")) {
			return true;
		}

		return false;
	}

	public boolean isCommerce() {
		String name = getName();

		if (name.contains("Commerce")) {
			return true;
		}

		return false;
	}

	public boolean isDeveloperTools() {
		String name = getName();

		if (name.contains("Developer Tools")) {
			return true;
		}

		return false;
	}

	public boolean isDeviceDetection() {
		String name = getName();

		if (name.contains("Device Detection")) {
			return true;
		}

		return false;
	}

	public boolean isDigitalEnterprise() {
		String name = getName();

		if (name.contains(ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE)) {
			return true;
		}

		return false;
	}

	public boolean isDXPCloud() {
		String name = getName();

		if (name.contains("DXP Cloud")) {
			return true;
		}

		return false;
	}

	public boolean isEnterpriseSearch() {
		String name = getName();

		if (name.contains("Enterprise Search")) {
			return true;
		}

		return false;
	}

	public boolean isExtendedPremiumSupport() {
		String name = getName();

		if (name.contains("Extended Premium Support")) {
			return true;
		}

		return false;
	}

	public boolean isManagementTools() {
		String name = getName();

		if (name.contains("Management Tools")) {
			return true;
		}

		return false;
	}

	public boolean isMobileExperience() {
		String name = getName();

		if (name.contains("Mobile Experience")) {
			return true;
		}

		return false;
	}

	public boolean isPortal() {
		String name = getName();

		if (name.contains(ProductEntryConstants.ROOT_NAME_PORTAL)) {
			return true;
		}

		return false;
	}

	public boolean isProductivityTools() {
		String name = getName();

		if (name.contains("Productivity Tools")) {
			return true;
		}

		return false;
	}

	public boolean isSocialOffice() {
		String name = getName();

		if (name.contains("Social Office")) {
			return true;
		}

		return false;
	}

	public boolean isUnlimitedEnterpriseWide() {
		String name = getName();

		if (name.contains("Unlimited Enterprise-Wide")) {
			return true;
		}

		return false;
	}

}