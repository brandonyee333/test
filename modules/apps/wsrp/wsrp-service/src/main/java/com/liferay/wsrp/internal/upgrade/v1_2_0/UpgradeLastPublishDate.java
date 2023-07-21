/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.upgrade.v1_2_0;

import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.wsrp.constants.WSRPPortletKeys;

/**
 * @author Máté Thurzó
 */
public class UpgradeLastPublishDate
	extends com.liferay.portal.upgrade.v7_0_0.UpgradeLastPublishDate {

	protected void addLastPublishDateColumns() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			addLastPublishDateColumn("WSRP_WSRPConsumerPortlet");
			addLastPublishDateColumn("WSRP_WSRPConsumer");
			addLastPublishDateColumn("WSRP_WSRPProducer");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		addLastPublishDateColumns();

		updateLastPublishDates(
			WSRPPortletKeys.WSRP_ADMIN, "WSRP_WSRPConsumerPortlet");
		updateLastPublishDates(WSRPPortletKeys.WSRP_ADMIN, "WSRP_WSRPConsumer");
		updateLastPublishDates(WSRPPortletKeys.WSRP_ADMIN, "WSRP_WSRPProducer");
	}

}