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
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.repository.BQSessionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leilany Ulisses
 * @author Regisson Cesar
 * @author Thiago Buarque
 */
@Component
public class SiteTechnologyDog {

	public List<Metric> getBrowserMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		Map<String, Integer> sessions =
			_bqSessionRepository.getSessionsGroupedByBrowserName(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		Set<Map.Entry<String, Integer>> entrySet = sessions.entrySet();

		Stream<Map.Entry<String, Integer>> stream = entrySet.stream();

		return stream.map(
			entry -> {
				Metric metric = new Metric(metricType);

				metric.setValue(Double.valueOf(entry.getValue()));
				metric.setValueKey(entry.getKey());

				return metric;
			}
		).collect(
			Collectors.toList()
		);
	}

	public List<Metric> getDeviceMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		Map<String, Integer> getSessions =
			_bqSessionRepository.getSessionsGroupedByBrowserName(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		return Collections.emptyList();
	}

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}