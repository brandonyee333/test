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
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Rachael Koestartyo
 */
public class ElasticsearchIndividualRepositoryTest
	extends BaseIndividualRepositoryTestCase {

	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_2.json"
	)
	@Test
	public void testActivitiesDataInconsistenciesAreNotRetrieved() {
		Optional<Individual> individualOptional = individualRepository.findById(
			1L);

		Individual individual = individualOptional.orElse(null);

		Assertions.assertNotNull(individual);

		Set<Individual.ActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		Assertions.assertEquals(
			1, lastActivityDates.size(), lastActivityDates.toString());

		Iterator<Individual.ActivityDate> lastActivityDateIterator =
			lastActivityDates.iterator();

		Individual.ActivityDate lastActivityDate =
			lastActivityDateIterator.next();

		Assertions.assertNotNull(lastActivityDate.getActivityDate());
		Assertions.assertNotNull(lastActivityDate.getChannelId());

		Set<Individual.ActivityDate> previousActivityDates =
			individual.getPreviousActivityDates();

		Assertions.assertEquals(
			1, previousActivityDates.size(), previousActivityDates.toString());

		Iterator<Individual.ActivityDate> previousActivityDateIterator =
			lastActivityDates.iterator();

		Individual.ActivityDate previousActivityDate =
			previousActivityDateIterator.next();

		Assertions.assertNotNull(previousActivityDate.getActivityDate());
		Assertions.assertNotNull(previousActivityDate.getChannelId());

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		Assertions.assertEquals(
			1, activitiesCounts.size(), activitiesCounts.toString());

		Iterator<Individual.ActivitiesCount> activitiesCountIterator =
			activitiesCounts.iterator();

		Individual.ActivitiesCount activitiesCount =
			activitiesCountIterator.next();

		Assertions.assertNotNull(activitiesCount.getActivitiesCount());
		Assertions.assertNotNull(activitiesCount.getChannelId());
	}

	@Test
	public void testActivitiesDataInconsistenciesAreNotSaved() {
		Set<Individual.ActivitiesCount> activitiesCounts = new HashSet<>(
			Arrays.asList(
				new Individual.ActivitiesCount(1L, 100L),
				new Individual.ActivitiesCount(null, 100L),
				new Individual.ActivitiesCount(1L, null),
				new Individual.ActivitiesCount(), null));

		Set<Individual.ActivityDate> lastActivityDates = new HashSet<>(
			Arrays.asList(
				new Individual.ActivityDate(DateUtil.newDate(), 100L),
				new Individual.ActivityDate(DateUtil.newDate(), null),
				new Individual.ActivityDate(), null));

		Set<Individual.ActivityDate> previousActivityDates = new HashSet<>(
			Arrays.asList(
				new Individual.ActivityDate(DateUtil.newDate(), 100L),
				new Individual.ActivityDate(DateUtil.newDate(), null),
				new Individual.ActivityDate(), null));

		Individual individual = new Individual();

		individual.setActivitiesCounts(activitiesCounts);
		individual.setLastActivityDates(lastActivityDates);
		individual.setPreviousActivityDates(previousActivityDates);

		individual = individualRepository.save(individual);

		String individualId = String.valueOf(individual.getId());

		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals", individualId);

		JSONArray activitiesCountsJSONArray = jsonObject.optJSONArray(
			"activitiesCounts");

		Assertions.assertNotNull(
			activitiesCountsJSONArray, jsonObject.toString());
		Assertions.assertEquals(1, activitiesCountsJSONArray.length());

		JSONArray lastActivityDatesJSONArray = jsonObject.optJSONArray(
			"lastActivityDates");

		Assertions.assertNotNull(
			lastActivityDatesJSONArray, jsonObject.toString());
		Assertions.assertEquals(1, lastActivityDatesJSONArray.length());

		JSONArray previousActivityDatesJSONArray = jsonObject.optJSONArray(
			"previousActivityDates");

		Assertions.assertNotNull(
			previousActivityDatesJSONArray, jsonObject.toString());
		Assertions.assertEquals(1, previousActivityDatesJSONArray.length());
	}

	@Disabled
	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Override
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
	)
	@Test
	public void testGetIndividualDistributions() throws Exception {
		super.testGetIndividualDistributions();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}