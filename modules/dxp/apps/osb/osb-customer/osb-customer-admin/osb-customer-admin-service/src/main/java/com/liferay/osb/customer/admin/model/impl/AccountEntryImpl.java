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

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.customer.admin.model.AccountAttachment;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.AccountEntryLanguage;
import com.liferay.osb.customer.admin.model.SupportRegion;
import com.liferay.osb.customer.admin.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.customer.admin.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.customer.admin.service.SupportRegionLocalServiceUtil;
import com.liferay.portal.kernel.json.JSON;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@JSON(strict = true)
public class AccountEntryImpl extends AccountEntryBaseImpl {

	public AccountEntryImpl() {
	}

	@Override
	public Object clone() {
		AccountEntry accountEntry = (AccountEntry)super.clone();

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

	public long getCorpProjectId() {

		// TODO

		return 0;
	}

	public String getDescription() throws Exception {
		return null;
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

	@JSON
	public List<SupportRegion> getSupportRegions() {
		return SupportRegionLocalServiceUtil.getAccountEntrySupportRegions(
			getAccountEntryId());
	}

	public String getTier() throws Exception {
		return null;
	}

	public void setLanguageIds(String[] languageIds) {
		_languageIds = languageIds;
	}

	public void setSupportRegionIds(long[] supportRegionIds) {
		_supportRegionIds = supportRegionIds;
	}

	private String[] _languageIds;
	private long[] _supportRegionIds;

}