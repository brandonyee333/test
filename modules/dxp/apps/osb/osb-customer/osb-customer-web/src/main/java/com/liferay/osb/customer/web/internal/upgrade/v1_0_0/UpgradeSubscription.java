/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.web.internal.upgrade.v1_0_0;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.service.persistence.SubscriptionPersistence;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Jenny Chen
 */
public class UpgradeSubscription extends UpgradeProcess {

	public UpgradeSubscription(
		Portal portal, SubscriptionPersistence subscriptionPersistence) {

		_portal = portal;
		_subscriptionPersistence = subscriptionPersistence;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(6);

		sb.append("update Subscription set classNameId = ");
		sb.append(_portal.getClassNameId(JournalArticle.class.getName()));
		sb.append(", groupId = ");
		sb.append(OSBCustomerConstants.GROUP_KNOWLEDGE_ID);
		sb.append(" where classNameId = ");
		sb.append(_portal.getClassNameId(JournalFolder.class.getName()));

		runSQL(sb.toString());

		_subscriptionPersistence.clearCache();
	}

	private final Portal _portal;
	private final SubscriptionPersistence _subscriptionPersistence;

}