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

package com.liferay.osb.asah.dataflow.ingestion.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * @author Marcellus Tavares
 */
@DefaultSchema(JavaFieldSchema.class)
public class AnalyticsEvent {

	public AnalyticsEvent() {
	}

	public AnalyticsEvent(AnalyticsEvent analyticsEvent) {
		applicationId = analyticsEvent.applicationId;
		channelId = analyticsEvent.channelId;
		clientIP = analyticsEvent.clientIP;
		context = new HashMap(analyticsEvent.context);
		createDate = analyticsEvent.createDate;
		dataSourceId = analyticsEvent.dataSourceId;
		eventDate = analyticsEvent.eventDate;
		eventId = analyticsEvent.eventId;
		eventProperties = new HashMap(analyticsEvent.eventProperties);
		id = analyticsEvent.id;
		projectId = analyticsEvent.projectId;
		projectTimeZoneId = analyticsEvent.projectTimeZoneId;
		userId = analyticsEvent.userId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !(obj instanceof AnalyticsEvent)) {
			return false;
		}

		AnalyticsEvent analyticsEvent = (AnalyticsEvent)obj;

		if (Objects.equals(applicationId, analyticsEvent.applicationId) &&
			Objects.equals(channelId, analyticsEvent.channelId) &&
			Objects.equals(clientIP, analyticsEvent.clientIP) &&
			Objects.equals(context, analyticsEvent.context) &&
			Objects.equals(createDate, analyticsEvent.createDate) &&
			Objects.equals(dataSourceId, analyticsEvent.dataSourceId) &&
			Objects.equals(eventDate, analyticsEvent.eventDate) &&
			Objects.equals(eventId, analyticsEvent.eventId) &&
			Objects.equals(eventProperties, analyticsEvent.eventProperties) &&
			Objects.equals(id, analyticsEvent.id) &&
			Objects.equals(projectId, analyticsEvent.projectId) &&
			Objects.equals(
				projectTimeZoneId, analyticsEvent.projectTimeZoneId) &&
			Objects.equals(userId, analyticsEvent.userId)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			applicationId, channelId, clientIP, context, createDate,
			dataSourceId, eventDate, eventId, eventProperties, id, projectId,
			projectTimeZoneId, userId);
	}

	public String applicationId;
	public String channelId;

	@Nullable
	public String clientIP;

	public Map<String, String> context;
	public String createDate;
	public String dataSourceId;

	@Nullable
	public String emailAddressHashed;

	public String eventDate;
	public String eventId;
	public Map<String, String> eventProperties;
	public String id;
	public String projectId;
	public String projectTimeZoneId;
	public String userId;

}