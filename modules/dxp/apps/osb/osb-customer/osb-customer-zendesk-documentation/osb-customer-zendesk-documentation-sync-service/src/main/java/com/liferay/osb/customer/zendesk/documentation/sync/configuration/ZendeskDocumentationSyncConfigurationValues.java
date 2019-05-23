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

}