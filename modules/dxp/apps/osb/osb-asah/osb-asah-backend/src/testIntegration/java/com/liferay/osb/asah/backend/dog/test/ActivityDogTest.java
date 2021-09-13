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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.ActivityDog;
import com.liferay.osb.asah.backend.model.Activity;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class ActivityDogTest {

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetActivityResultBag() {
		ResultBag<Activity> activityResultBag =
			_activityDog.getActivityResultBag(337984659206412898L, 20, 0);

		Assert.assertEquals(2, activityResultBag.getTotal());
		Assert.assertEquals(
			SetUtil.of("pageLoaded", "pageViewed"),
			_getActivitiesEventIds(activityResultBag.getResults()));
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetEventContextKeys() {
		Assert.assertEquals(
			Arrays.asList("canonicalUrl", "country", "title", "url"),
			_activityDog.getEventContextKeys());
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetLast3DaysPageUnloadedActivityResultBag() {
		ResultBag<Activity> activityResultBag =
			_activityDog.getActivityResultBag(
				"Page", null, "pageUnloaded", 3, 20, 0);

		Assert.assertEquals(1, activityResultBag.getTotal());
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetLast7DaysPageUnloadedActivityResultBag() {
		ResultBag<Activity> activityResultBag =
			_activityDog.getActivityResultBag(
				"Page", null, "pageUnloaded", 7, 20, 0);

		Assert.assertEquals(2, activityResultBag.getTotal());
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageUnloadedActivityResultBagEqualsContextFilter() {
		ResultBag<Activity> activityResultBag =
			_activityDog.getActivityResultBag(
				"Page",
				Arrays.asList(
					new PropertyFilter("eventContext.country = Brazil", false)),
				"pageUnloaded", 7, 20, 0);

		Assert.assertEquals(1, activityResultBag.getTotal());
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetPageUnloadedActivityResultBagRegexContextFilter() {
		ResultBag<Activity> activityResultBag =
			_activityDog.getActivityResultBag(
				"Page",
				Arrays.asList(
					new PropertyFilter("eventContext.country ~ Br.*", false)),
				"pageUnloaded", 7, 20, 0);

		Assert.assertEquals(2, activityResultBag.getTotal());
		Assert.assertEquals(
			SetUtil.of("Brazil", "Brunei"),
			_getActivitiesEventContextProperty(
				activityResultBag.getResults(), "country"));
	}

	private Set<String> _getActivitiesEventContextProperty(
		List<Activity> activities, String property) {

		Stream<Activity> stream = activities.stream();

		return stream.map(
			Activity::getEventContext
		).map(
			eventContext -> MapUtil.getString(eventContext, property)
		).collect(
			Collectors.toSet()
		);
	}

	private Set<String> _getActivitiesEventIds(List<Activity> activities) {
		Stream<Activity> stream = activities.stream();

		return stream.map(
			Activity::getEventId
		).collect(
			Collectors.toSet()
		);
	}

	@Autowired
	private ActivityDog _activityDog;

}