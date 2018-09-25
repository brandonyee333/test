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

import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountEntryLanguage;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AccountEntryImpl extends AccountEntryBaseImpl {

	public AccountEntryImpl() {
	}

	@Override
	public Object clone() {
		AccountEntry accountEntry = (AccountEntry)super.clone();

		try {
			accountEntry.setAddress(getAddress());
		}
		catch (Exception e) {
		}

		try {
			accountEntry.setLanguageIds(getLanguageIds());
		}
		catch (Exception e) {
		}

		try {
			accountEntry.setSupportRegionIds(getSupportRegionIds());
		}
		catch (Exception e) {
		}

		return accountEntry;
	}

	public List<AccountAttachment> getAccountAttachments(
		long accountProjectId) {

		return AccountAttachmentLocalServiceUtil.getAccountAttachments(
			getAccountEntryId(), accountProjectId);
	}

	public List<AccountCustomer> getAccountCustomers() {
		return AccountCustomerLocalServiceUtil.getAccountCustomers(
			getAccountEntryId());
	}

	public List<AccountWorker> getAccountWorkers() {
		return AccountWorkerLocalServiceUtil.getAccountWorkers(
			getAccountEntryId());
	}

	public Address getAddress() {
		if (_address != null) {
			return _address;
		}

		List<Address> addresses = AddressLocalServiceUtil.getAddresses(
			OSBConstants.COMPANY_ID, AccountEntry.class.getName(),
			getAccountEntryId());

		if (!addresses.isEmpty()) {
			return addresses.get(0);
		}

		return null;
	}

	public String getEWSADossieraProjectKey() {
		long classNameId = PortalUtil.getClassNameId(
			AccountEntry.class.getName());

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_EWSA_DOSSIERA_ACCOUNT_KEY);

		if (!externalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			return externalIdMapper.getExternalId();
		}
		else {
			return StringPool.BLANK;
		}
	}

	public String getIndustryLabel() {
		return AccountEntryConstants.getIndustryLabel(getIndustry());
	}

	public String[] getLanguageIds() {
		if (_languageIds != null) {
			return _languageIds;
		}

		List<AccountEntryLanguage> accountEntryLanguages =
			AccountEntryLanguageLocalServiceUtil.getAccountEntryLanguages(
				getAccountEntryId());

		String[] languageIds = new String[accountEntryLanguages.size()];

		for (int i = 0; i < accountEntryLanguages.size(); i++) {
			AccountEntryLanguage accountEntryLanguage =
				accountEntryLanguages.get(i);

			languageIds[i] = accountEntryLanguage.getLanguageId();
		}

		return languageIds;
	}

	@JSON
	public List<OfferingEntry> getOfferingEntries() {
		return OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntries(
			getAccountEntryId());
	}

	public List<OrderEntry> getOrderEntries() {
		return OrderEntryLocalServiceUtil.getAccountEntryOrderEntries(
			getAccountEntryId());
	}

	public PartnerEntry getPartnerEntry() throws PortalException {
		if (getPartnerEntryId() == 0) {
			return null;
		}

		return PartnerEntryLocalServiceUtil.getPartnerEntry(
			getPartnerEntryId());
	}

	public String getStatusLabel() {
		return WorkflowConstants.getStatusLabel(getStatus());
	}

	public long[] getSupportRegionIds() {
		if (_supportRegionIds != null) {
			return _supportRegionIds;
		}

		List<SupportRegion> supportRegions =
			SupportRegionLocalServiceUtil.getAccountEntrySupportRegions(
				getAccountEntryId());

		long[] supportRegionIds = new long[supportRegions.size()];

		for (int i = 0; i < supportRegions.size(); i++) {
			SupportRegion supportRegion = supportRegions.get(i);

			supportRegionIds[i] = supportRegion.getSupportRegionId();
		}

		return supportRegionIds;
	}

	public List<SupportRegion> getSupportRegions() {
		return SupportRegionLocalServiceUtil.getAccountEntrySupportRegions(
			getAccountEntryId());
	}

	public String getTypeLabel() {
		return AccountEntryConstants.getTypeLabel(getType());
	}

	public boolean hasActiveSupportOffering() {
		return OfferingEntryLocalServiceUtil.hasActiveSupportOfferingEntry(
			getAccountEntryId());
	}

	public boolean hasEnterpriseSearchOffering(int productEntryEnvironment)
		throws PortalException {

		List<OfferingEntry> offeringEntries = getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.isEnterpriseSearch() &&
				(productEntry.getEnvironment() == productEntryEnvironment)) {

				return true;
			}
		}

		return false;
	}

	public void setAddress(Address address) {
		_address = address;
	}

	public void setLanguageIds(String[] languageIds) {
		_languageIds = languageIds;
	}

	public void setSupportRegionIds(long[] supportRegionIds) {
		_supportRegionIds = supportRegionIds;
	}

	private Address _address;
	private String[] _languageIds;
	private long[] _supportRegionIds;

}