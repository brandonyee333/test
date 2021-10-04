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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ElasticsearchIndividualRepositoryTest
	extends BaseIndividualRepositoryTestCase {

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testActivitiesDataInconsistenciesAreNotRetrieved() {
		Optional<Individual> individualOptional = individualRepository.findById(
			1L);

		Individual individual = individualOptional.orElse(null);

		Assert.assertNotNull(individual);

		Set<Individual.LastActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		Assert.assertEquals(
			lastActivityDates.toString(), 1, lastActivityDates.size());

		Iterator<Individual.LastActivityDate> lastActivityDateIterator =
			lastActivityDates.iterator();

		Individual.LastActivityDate lastActivityDate =
			lastActivityDateIterator.next();

		Assert.assertNotNull(lastActivityDate.getChannelId());
		Assert.assertNotNull(lastActivityDate.getLastActivityDate());

		Set<Individual.LastActivityDate> previousActivityDates =
			individual.getPreviousActivityDates();

		Assert.assertEquals(
			previousActivityDates.toString(), 1, previousActivityDates.size());

		Iterator<Individual.LastActivityDate> previousActivityDateIterator =
			lastActivityDates.iterator();

		Individual.LastActivityDate previousActivityDate =
			previousActivityDateIterator.next();

		Assert.assertNotNull(previousActivityDate.getChannelId());
		Assert.assertNotNull(previousActivityDate.getLastActivityDate());

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		Assert.assertEquals(
			activitiesCounts.toString(), 1, activitiesCounts.size());

		Iterator<Individual.ActivitiesCount> activitiesCountIterator =
			activitiesCounts.iterator();

		Individual.ActivitiesCount activitiesCount =
			activitiesCountIterator.next();

		Assert.assertNotNull(activitiesCount.getChannelId());
		Assert.assertNotNull(activitiesCount.getActivitiesCount());
	}

	@Test
	public void testActivitiesDataInconsistenciesAreNotSaved() {
		Set<Individual.ActivitiesCount> activitiesCounts = new HashSet<>(
			Arrays.asList(
				new Individual.ActivitiesCount(1L, 100L),
				new Individual.ActivitiesCount(null, 100L),
				new Individual.ActivitiesCount(1L, null),
				new Individual.ActivitiesCount(), null));

		Set<Individual.LastActivityDate> lastActivityDates = new HashSet<>(
			Arrays.asList(
				new Individual.LastActivityDate(100L, DateUtil.newDate()),
				new Individual.LastActivityDate(null, DateUtil.newDate()),
				new Individual.LastActivityDate(), null));

		Set<Individual.LastActivityDate> previousActivityDates = new HashSet<>(
			Arrays.asList(
				new Individual.LastActivityDate(100L, DateUtil.newDate()),
				new Individual.LastActivityDate(null, DateUtil.newDate()),
				new Individual.LastActivityDate(), null));

		Individual individual = new Individual();

		individual.setActivitiesCounts(activitiesCounts);
		individual.setLastActivityDates(lastActivityDates);
		individual.setPreviousActivityDates(previousActivityDates);

		individual = getRepository().save(individual);

		String individualId = String.valueOf(individual.getId());

		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals", individualId);

		JSONArray activitiesCountsJSONArray = jsonObject.optJSONArray(
			"activitiesCounts");

		Assert.assertNotNull(jsonObject.toString(), activitiesCountsJSONArray);
		Assert.assertEquals(1, activitiesCountsJSONArray.length());

		JSONArray lastActivityDatesJSONArray = jsonObject.optJSONArray(
			"lastActivityDates");

		Assert.assertNotNull(jsonObject.toString(), lastActivityDatesJSONArray);
		Assert.assertEquals(1, lastActivityDatesJSONArray.length());

		JSONArray previousActivityDatesJSONArray = jsonObject.optJSONArray(
			"previousActivityDates");

		Assert.assertNotNull(
			jsonObject.toString(), previousActivityDatesJSONArray);
		Assert.assertEquals(1, previousActivityDatesJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Override
	@Test
	public void testGetIndividualDistributions() throws Exception {
		super.testGetIndividualDistributions();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}