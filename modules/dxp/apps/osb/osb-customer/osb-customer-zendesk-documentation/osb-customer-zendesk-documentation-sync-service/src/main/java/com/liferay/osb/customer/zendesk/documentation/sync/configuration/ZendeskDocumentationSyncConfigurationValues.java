/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Wesley Gong
 */
public class ZendeskDocumentationSyncConfigurationValues {

	public static final long ZENDESK_ARTICLE_PERMISSION_GROUP_ID =
		GetterUtil.getLong(
			ZendeskDocumentationSyncConfigurationUtil.get(
				"zendesk.article.permission.group.id"));

	public static final String ZENDESK_ARTICLE_USER_SEGMENT_ID =
		GetterUtil.getString(
			ZendeskDocumentationSyncConfigurationUtil.get(
				"zendesk.article.user.segment.id"));

	public static final String ZENDESK_DOCUMENTATION_TRANSLATOR_CATEGORY_IDS =
		GetterUtil.getString(
			ZendeskDocumentationSyncConfigurationUtil.get(
				"zendesk.documentation.translator.category.ids"));

}