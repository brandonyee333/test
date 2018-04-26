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
		if (isSocialOffice()) {
			return ListTypeServiceUtil.getListTypes(
				ProductEntryConstants.LIST_TYPE_SOCIAL_OFFICE_ALL_VERSIONS);
		}

		if (isDeveloperTools() || isEnterpriseSearchPremium() ||
			isEnterpriseSearchStandard() || isManagementTools() ||
			isMobileExperience() || isProductivityTools()) {

			return ListTypeServiceUtil.getListTypes(
				ProductEntryConstants.
					LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS);
		}

		String listType =
			ProductEntry.class.getName() + StringPool.PERIOD +
				getVersionsListType();

		String allVersionsListType = ProductEntryConstants.getAllListType(
			listType);

		if (Validator.isNotNull(allVersionsListType)) {
			return ListTypeServiceUtil.getListTypes(allVersionsListType);
		}

		return ListTypeServiceUtil.getListTypes(
			ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS);
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

	public boolean isDeveloperTools() {
		String name = getName();

		if (name.contains("Developer Tools")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDeviceDetection() {
		String name = getName();

		if (name.contains("Device Detection")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDigitalEnterprise() {
		String name = getName();

		if (name.contains("Digital Enterprise")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isEnterpriseSearchPremium() {
		String name = getName();

		if (name.contains("Enterprise Search") && name.contains("Premium")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isEnterpriseSearchStandard() {
		String name = getName();

		if (name.contains("Enterprise Search") && name.contains("Standard")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isManagementTools() {
		String name = getName();

		if (name.contains("Management Tools")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isMobileExperience() {
		String name = getName();

		if (name.contains("Mobile Experience")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPortal() {
		String name = getName();

		if (name.contains("Portal")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isProductivityTools() {
		String name = getName();

		if (name.contains("Productivity Tools")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isSocialOffice() {
		String name = getName();

		if (name.contains("Social Office")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isTicketComponentRequired() {
		if (isDeviceDetection() || isEnterpriseSearchPremium() ||
			isEnterpriseSearchStandard() || isSocialOffice()) {

			return false;
		}
		else {
			return true;
		}
	}

	public boolean isUnlimitedEnterpriseWide() {
		String name = getName();

		if (name.contains("Unlimited Enterprise-Wide")) {
			return true;
		}
		else {
			return false;
		}
	}

}