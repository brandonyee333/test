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

package com.liferay.osb.service.messaging;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryServiceUtil;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetServiceUtil;
import com.liferay.osb.service.RemoteCorpEntryLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;
import com.liferay.osb.service.RemoteUserLocalServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			LCSSubscriptionEntryLocalServiceUtil.clearService();

			LCSSubscriptionEntryServiceUtil.clearService();
			LicenseEntryLocalServiceUtil.clearService();

			LicenseEntryServiceUtil.clearService();
			LicenseKeyLocalServiceUtil.clearService();

			LicenseKeyServiceUtil.clearService();
			LicenseKeySetLocalServiceUtil.clearService();

			LicenseKeySetServiceUtil.clearService();
			RemoteCorpEntryLocalServiceUtil.clearService();

			RemoteCorpProjectLocalServiceUtil.clearService();

			RemoteUserLocalServiceUtil.clearService();
		}
	}
}