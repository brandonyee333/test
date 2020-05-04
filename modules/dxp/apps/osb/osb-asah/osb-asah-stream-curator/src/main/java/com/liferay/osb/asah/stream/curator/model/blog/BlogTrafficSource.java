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

package com.liferay.osb.asah.stream.curator.model.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;

/**
 * @author Inácio Nery
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = BlogTrafficSource.class,
	include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "messageFormat",
	use = JsonTypeInfo.Id.NAME, visible = true
)
public class BlogTrafficSource extends BaseAssetModel {

	public void addTrafficSources(long trafficSources) {
		_trafficSources += trafficSources;
	}

	public long getTrafficSources() {
		return _trafficSources;
	}

	public String getTrafficSourcesDomain() {
		return _trafficSourcesDomain;
	}

	public void setTrafficSources(long trafficSources) {
		_trafficSources = trafficSources;
	}

	public void setTrafficSourcesDomain(String trafficSourcesDomain) {
		_trafficSourcesDomain = trafficSourcesDomain;
	}

	private long _trafficSources;
	private String _trafficSourcesDomain;

}