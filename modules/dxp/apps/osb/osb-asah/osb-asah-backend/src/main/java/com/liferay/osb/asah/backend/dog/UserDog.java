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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.common.model.MetricType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component("com.liferay.osb.asah.backend.dog.UserDog")
public class UserDog {

	@Autowired
	public UserDog() {
	}

	public Long getAnonymousUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		return 0L;
	}

	public Long getKnownUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		return 0L;
	}

	public Long getNonsegmentedKnownUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		return 0L;
	}

	public Long getSegmentedAnonymousUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		return 0L;
	}

	public Long getSegmentedKnownUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		return 0L;
	}

}