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
import com.liferay.osb.customer.admin.model.legacy.OfferingEntry;
import com.liferay.osb.customer.admin.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.customer.admin.service.AccountEntryLanguageLocalServiceUtil;
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

		return accountEntry;
	}

	@JSON
	public List<AccountAttachment> getAccountAttachments() {
		return AccountAttachmentLocalServiceUtil.getAccountAttachments(
			getAccountEntryId());
	}

	public List<AccountAttachment> getAccountAttachments(
		long accountProjectId) {

		return AccountAttachmentLocalServiceUtil.getAccountAttachments(
			getAccountEntryId(), accountProjectId);
	}

	public String getDescription() throws Exception {
		return null;
	}

	@JSON
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
		return _offeringEntries;
	}

	public String getStatusLabel() {
		return WorkflowConstants.getStatusLabel(getStatus());
	}

	public String getTier() throws Exception {
		return null;
	}

	public void setLanguageIds(String[] languageIds) {
		_languageIds = languageIds;
	}

	public void setOfferingEntries(List<OfferingEntry> offeringEntries) {
		_offeringEntries = offeringEntries;
	}

	private String[] _languageIds;
	private List<OfferingEntry> _offeringEntries;

}