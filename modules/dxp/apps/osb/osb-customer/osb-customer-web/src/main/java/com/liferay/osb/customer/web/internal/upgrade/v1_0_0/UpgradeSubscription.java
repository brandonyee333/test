/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.web.internal.upgrade.v1_0_0;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.osb.customer.web.internal.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Jenny Chen
 */
public class UpgradeSubscription extends UpgradeProcess {

	public UpgradeSubscription(Portal portal) {
		_portal = portal;
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
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeSubscription.class);

	private final Portal _portal;

}