/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.util;

import com.liferay.wsrp.model.WSRPConsumer;

import javax.servlet.http.HttpSession;

/**
 * @author Matthew Tambara
 */
public interface WSRPConsumerManagerFactory {

	public void destroyWSRPConsumerManager(String url);

	public HttpSession getSession();

	public WSRPConsumerManager getWSRPConsumerManager(WSRPConsumer wsrpConsumer)
		throws Exception;

	public void setSession(HttpSession session);

	public boolean testWSRPConsumerManager(WSRPConsumer wsrpConsumer);

}