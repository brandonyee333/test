/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.util.List;

/**
 * @author Peter Fellwock
 */
public class UpgradeWSRP extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateWSRPConsumer();

		updateWSRPConsumerPortlet();

		updateWSRPProducer();
	}

	protected void updateWSRPConsumer() throws Exception {
		List<WSRPConsumer> wsrpConsumers =
			WSRPConsumerLocalServiceUtil.getWSRPConsumers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			WSRPConsumerLocalServiceUtil.updateWSRPConsumer(wsrpConsumer);
		}
	}

	protected void updateWSRPConsumerPortlet() throws Exception {
		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(
				wsrpConsumerPortlet);
		}
	}

	protected void updateWSRPProducer() throws Exception {
		runSQL(
			"update WSRP_WSRPProducer set version = '2.0' where version is " +
				"null or version = ''");

		List<WSRPProducer> wsrpProducers =
			WSRPProducerLocalServiceUtil.getWSRPProducers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPProducer wsrpProducer : wsrpProducers) {
			WSRPProducerLocalServiceUtil.updateWSRPProducer(wsrpProducer);
		}
	}

}