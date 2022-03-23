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

import java.util.Map;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * @author Marcellus Tavares
 */
@DefaultSchema(JavaFieldSchema.class)
public class AnalyticsEvent {

	public String applicationId;
	public String channelId;

	@Nullable
	public String clientIP;

	public Map<String, String> context;
	public String createDate;
	public String dataSourceId;
	public String eventDate;
	public String eventId;
	public Map<String, String> eventProperties;
	public String id;
	public String projectId;
	public String projectTimeZoneId;
	public String userId;

}