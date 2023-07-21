/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.poller;

import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.LoopPersonLocalService;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Evan Thibodeau
 * @author Timothy Bell
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + LoopPortletKeys.LOOP,
	service = PollerProcessor.class
)
public class LoopPollerProcessor extends BasePollerProcessor {

	@Override
	protected PollerResponse doReceive(PollerRequest pollerRequest)
		throws Exception {

		PollerResponse pollerResponse = pollerRequest.createPollerResponse();

		pollerResponse.setParameter(
			"timestamp", String.valueOf(System.currentTimeMillis()));

		LoopPerson loopPerson =
			_loopPersonLocalService.getLoopPersonByPersonUserId(
				pollerRequest.getUserId());

		pollerResponse.setParameter(
			"groupedUserNotificationEventsCount",
			String.valueOf(loopPerson.getGroupedUserNotificationEventsCount()));

		return pollerResponse;
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) {
	}

	@Reference
	private LoopPersonLocalService _loopPersonLocalService;

}