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