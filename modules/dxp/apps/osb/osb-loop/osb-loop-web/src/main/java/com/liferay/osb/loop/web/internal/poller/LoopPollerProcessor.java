/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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