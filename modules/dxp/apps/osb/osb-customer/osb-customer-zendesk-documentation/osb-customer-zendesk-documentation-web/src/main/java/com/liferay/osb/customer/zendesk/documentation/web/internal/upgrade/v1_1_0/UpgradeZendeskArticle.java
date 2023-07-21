/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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