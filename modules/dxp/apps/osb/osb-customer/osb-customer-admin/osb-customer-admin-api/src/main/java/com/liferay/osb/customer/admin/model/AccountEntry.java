/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AccountEntry service. Represents a row in the &quot;OSB_AccountEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.admin.model.impl.AccountEntryImpl"
)
@ProviderType
public interface AccountEntry extends AccountEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.admin.model.impl.AccountEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountEntry, Long> ACCOUNT_ENTRY_ID_ACCESSOR =
		new Accessor<AccountEntry, Long>() {

			@Override
			public Long get(AccountEntry accountEntry) {
				return accountEntry.getAccountEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AccountEntry> getTypeClass() {
				return AccountEntry.class;
			}

		};

	@com.liferay.portal.kernel.json.JSON
	public java.util.List<AccountAttachment> getAccountAttachments();

	public java.util.List<AccountAttachment> getAccountAttachments(
		long accountProjectId);

	public String getDescription() throws Exception;

	@com.liferay.portal.kernel.json.JSON
	public String[] getLanguageIds();

	@com.liferay.portal.kernel.json.JSON
	public java.util.List
		<com.liferay.osb.customer.admin.model.legacy.OfferingEntry>
			getOfferingEntries();

	public String getStatusLabel();

	public String getTier() throws Exception;

	public void setLanguageIds(String[] languageIds);

	public void setOfferingEntries(
		java.util.List
			<com.liferay.osb.customer.admin.model.legacy.OfferingEntry>
				offeringEntries);

}