/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.wsrp.model.impl.WSRPConsumerImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl;
import com.liferay.wsrp.model.impl.WSRPProducerImpl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true)
public class XStreamAliasRegistrar {

	@Activate
	protected void activate() {
		XStreamAliasRegistryUtil.register(
			WSRPConsumerImpl.class, "WSRPConsumer");
		XStreamAliasRegistryUtil.register(
			WSRPConsumerPortletImpl.class, "WSRPConsumerPortlet");
		XStreamAliasRegistryUtil.register(
			WSRPProducerImpl.class, "WSRPProducer");
	}

}