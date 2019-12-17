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
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryLanguage;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.remote.koroneiki.KoroneikiRESTWebServiceUtil;
import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
		JSONObject jsonObject = _getKoroneikiAccount();

		if (jsonObject != null) {
			return jsonObject.getString("description");
		}

		return null;
	}

	public String getDossieraAccountURL() {
		String dossieraAccountKey = getDossieraAccountKey();

		if (Validator.isNotNull(dossieraAccountKey)) {
			return PortletPropsValues.DOSSIERA_ACCOUNT_LINK +
				dossieraAccountKey;
		}

		return StringPool.BLANK;
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
		JSONObject jsonObject = _getKoroneikiAccount();

		if (jsonObject != null) {
			return jsonObject.getString("tier");
		}

		return null;
	}

	public void setLanguageIds(String[] languageIds) {
		_languageIds = languageIds;
	}

	public void setSupportRegionIds(long[] supportRegionIds) {
		_supportRegionIds = supportRegionIds;
	}

	private JSONObject _getKoroneikiAccount() throws Exception {
		if ((_jsonObject == null) &&
			Validator.isNotNull(getKoroneikiAccountKey())) {

			_jsonObject = KoroneikiRESTWebServiceUtil.getAccounts(
				getKoroneikiAccountKey());
		}

		return _jsonObject;
	}

	private JSONObject _jsonObject;
	private String[] _languageIds;
	private long[] _supportRegionIds;

}