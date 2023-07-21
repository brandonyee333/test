/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.web.internal.util;

import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true)
public class WSRPConsumerManagerFactoryUtil {

	public static WSRPConsumerManager getWSRPConsumerManager(
			WSRPConsumer wsrpConsumer)
		throws Exception {

		return _wsrpConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);
	}

	public static boolean testWSRPConsumerManager(WSRPConsumer wsrpConsumer) {
		return _wsrpConsumerManagerFactory.testWSRPConsumerManager(
			wsrpConsumer);
	}

	@Reference(unbind = "-")
	protected void setWSRPConsumerManagerFactory(
		WSRPConsumerManagerFactory wsrpConsumerManagerFactory) {

		_wsrpConsumerManagerFactory = wsrpConsumerManagerFactory;
	}

	private static WSRPConsumerManagerFactory _wsrpConsumerManagerFactory;

}