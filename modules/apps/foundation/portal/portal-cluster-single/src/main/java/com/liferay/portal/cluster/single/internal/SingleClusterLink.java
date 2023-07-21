/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cluster.single.internal;

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.ClusterLink;
import com.liferay.portal.kernel.cluster.Priority;
import com.liferay.portal.kernel.messaging.Message;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(enabled = false, immediate = true, service = ClusterLink.class)
public class SingleClusterLink implements ClusterLink {

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void sendMulticastMessage(Message message, Priority priority) {
	}

	@Override
	public void sendUnicastMessage(
		Address address, Message message, Priority priority) {
	}

}