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

package com.liferay.osb.asah.extractor.bot;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.extractor.processor.AnalyticsEventsMessageProcessor;
import com.liferay.osb.asah.extractor.processor.DXPEntitiesMessageProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Eddie Olson
 */
@Component
@EnableScheduling
@Profile("!test")
public class OSBAsahExtractorBot {

	@Scheduled(fixedDelay = DateUtil.SECOND, initialDelay = DateUtil.SECOND * 5)
	public void run() {
		try {
			_analyticsEventsMessageProcessor.processQueuedMessages();

			_dxpEntitiesMessageProcessor.processQueuedMessages();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahExtractorBot.class);

	@Autowired
	private AnalyticsEventsMessageProcessor _analyticsEventsMessageProcessor;

	@Autowired
	private DXPEntitiesMessageProcessor _dxpEntitiesMessageProcessor;

}