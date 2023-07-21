/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.jmx;

import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

/**
 * @author Michael C. Han
 */
public class WSRPConsumerPortletManager
	extends StandardMBean
	implements DynamicMBean, WSRPConsumerPortletManagerMBean {

	public WSRPConsumerPortletManager() throws NotCompliantMBeanException {
		super(WSRPConsumerPortletManagerMBean.class);
	}

	@Override
	public void initFailedWSRPConsumerPortlets() {
		WSRPConsumerPortletLocalServiceUtil.initFailedWSRPConsumerPortlets();
	}

}