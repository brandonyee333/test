/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.synchronizer.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Amos Fong
 */
public class ZendeskSynchronizerConfigurationValues {

	public static final int ZENDESK_ACCOUNT_ENTRY_SYNC_BATCH =
		GetterUtil.getInteger(
			ZendeskSynchronizerConfigurationUtil.get(
				"zendesk.account.entry.sync.batch"));

	public static final int ZENDESK_ACCOUNT_ENTRY_SYNC_INTERVAL =
		GetterUtil.getInteger(
			ZendeskSynchronizerConfigurationUtil.get(
				"zendesk.account.entry.sync.interval"));

	public static final long ZENDESK_TICKET_LONG_TERM_RESOLUTION_FIELD_ID =
		GetterUtil.getLong(
			ZendeskSynchronizerConfigurationUtil.get(
				"zendesk.ticket.long.term.resolution.field.Id"));

}