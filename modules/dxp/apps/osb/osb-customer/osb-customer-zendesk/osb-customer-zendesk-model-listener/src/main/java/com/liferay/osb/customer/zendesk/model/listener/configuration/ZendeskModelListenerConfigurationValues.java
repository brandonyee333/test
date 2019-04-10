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

package com.liferay.osb.customer.zendesk.model.listener.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Amos Fong
 */
public class ZendeskModelListenerConfigurationValues {

	public static final int ZENDESK_ACCOUNT_ENTRY_SYNC_BATCH =
		GetterUtil.getInteger(
			ZendeskModelListenerConfigurationUtil.get(
				"zendesk.account.entry.sync.batch"));

	public static final int ZENDESK_ACCOUNT_ENTRY_SYNC_INTERVAL =
		GetterUtil.getInteger(
			ZendeskModelListenerConfigurationUtil.get(
				"zendesk.account.entry.sync.interval"));

	public static final String ZENDESK_ADMIN_EMAIL_ADDRESS =
		GetterUtil.getString(
			ZendeskModelListenerConfigurationUtil.get(
				"zendesk.admin.email.address"));

	public static final String ZENDESK_USER_URL = GetterUtil.getString(
		ZendeskModelListenerConfigurationUtil.get("zendesk.user.url"));

}