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

package com.liferay.osb.community.meetup.client.util;

import com.liferay.osb.community.meetup.client.configuration.MeetupServiceConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Jamie Sammons
 */
@Component(
	configurationPid = "com.liferay.osb.community.meetup.client.configuration.MeetupServiceConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true
)
public class MeetupServiceConfigurationUtil {

	public static String getAPIKey() {
		return _meetupServiceConfiguration.apiKey();
	}

	public static MeetupServiceConfiguration getMeetupServiceConfiguration() {
		return _meetupServiceConfiguration;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_meetupServiceConfiguration = ConfigurableUtil.createConfigurable(
			MeetupServiceConfiguration.class, properties);
	}

	private static volatile MeetupServiceConfiguration
		_meetupServiceConfiguration;

}