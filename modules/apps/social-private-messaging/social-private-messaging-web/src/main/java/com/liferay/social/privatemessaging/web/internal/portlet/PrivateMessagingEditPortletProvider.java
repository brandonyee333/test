/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.web.internal.portlet;

import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.EditPortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;
import com.liferay.social.privatemessaging.constants.PrivateMessagingPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.social.privatemessaging.model.UserThread",
	service = {EditPortletProvider.class, ViewPortletProvider.class}
)
public class PrivateMessagingEditPortletProvider
	extends BasePortletProvider
	implements EditPortletProvider, ViewPortletProvider {

	@Override
	public String getPortletName() {
		return PrivateMessagingPortletKeys.PRIVATE_MESSAGING;
	}

}