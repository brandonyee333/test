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

package com.liferay.osb.customer.zendesk.documentation.web.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeZendeskArticle extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSBCustomer_ZendeskArticle add column " +
				"previousArticleDocumentationKey VARCHAR(150)");
		runSQL(
			"alter table OSBCustomer_ZendeskArticle add column " +
				"nextArticleDocumentationKey VARCHAR(150)");
		runSQL(
			"alter table OSBCustomer_ZendeskArticle add column remoteTitle " +
				"longtext");

		runSQL(
			"alter table OSBCustomer_ZendeskSection add column remoteName " +
				"longtext");
	}

}