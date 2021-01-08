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

package com.liferay.osb.asah.stream.curator.bot.nanite;

import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.model.Model;

import java.util.List;

import org.apache.commons.logging.Log;

import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseStreamNanite<T extends Model> extends BaseNanite<T> {

	@Override
	protected void doRun() throws Exception {
		while (true) {
			long start = System.currentTimeMillis();

			List<AnalyticsEvent> analyticsEvents = pullAnalyticsEvents();

			if (analyticsEvents.isEmpty()) {
				break;
			}

			saveModels(getModels(analyticsEvents));

			Log log = getLog();

			if (log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				log.info(
					String.format(
						"%s processed %d events in %d ms",
						clazz.getSimpleName(), analyticsEvents.size(),
						System.currentTimeMillis() - start));
			}
		}
	}

	protected abstract MessageSubscriber getMessageSubscriber();

	@Override
	protected QueryBuilder getQueryBuilder() {
		return null;
	}

	protected List<AnalyticsEvent> pullAnalyticsEvents() throws Exception {
		MessageSubscriber messageSubscriber = getMessageSubscriber();

		return messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);
	}

}