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

import com.liferay.osb.asah.backend.dog.IndividualDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.BlogMetricType;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class IndividualDogTest {

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field-mappings-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetIndividualResultBag1() {
		ResultBag<Individual> individualResultBag =
			_individualDog.getIndividualResultBag(null, "", 10, 0);

		Assert.assertEquals(5, individualResultBag.getTotal());
		Assert.assertEquals(
			Collections.emptySet(),
			_getIndividualsDemographicsFieldValues(
				individualResultBag.getResults(), "email"));
		Assert.assertEquals(
			SetUtil.of("Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Mew"),
			_getIndividualsDemographicsFieldValues(
				individualResultBag.getResults(), "favoritePokemon"));
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field-mappings-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetIndividualResultBag2() {
		ResultBag<Individual> individualResultBag =
			_individualDog.getIndividualResultBag(null, "mander", 10, 0);

		Assert.assertEquals(1, individualResultBag.getTotal());
		Assert.assertEquals(
			SetUtil.of("Charmander"),
			_getIndividualsDemographicsFieldValues(
				individualResultBag.getResults(), "favoritePokemon"));
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "segment-individuals-blogs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "segment-individuals-info-1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSegmentIndividuals() {
		SearchQueryContext searchQueryContext = new SearchQueryContext(
			"1", AssetType.BLOG);

		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		ResultBag<Individual> individualResultBag =
			_individualDog.getIndividualResultBag(
				null, BlogMetricType.VIEWS, searchQueryContext, 2, 1);

		Assert.assertEquals(
			individualResultBag.toString(), 3, individualResultBag.getTotal());

		List<Individual> individuals = individualResultBag.getResults();

		Individual individual = individuals.get(0);

		Assert.assertEquals("Test1 Test1", individual.getName());
		Assert.assertEquals("test1@liferay.com", individual.getEmailAddress());

		individual = individuals.get(1);

		Assert.assertEquals("Test2 Test2", individual.getName());
		Assert.assertEquals("test2@liferay.com", individual.getEmailAddress());
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "segment-individuals-blogs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "segment-individuals-info-1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSegmentIndividualsSearch() {
		SearchQueryContext searchQueryContext = new SearchQueryContext(
			"1", AssetType.BLOG);

		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		ResultBag<Individual> individualResultBag =
			_individualDog.getIndividualResultBag(
				"john", BlogMetricType.VIEWS, searchQueryContext, 10, 0);

		Assert.assertEquals(
			individualResultBag.toString(), 1, individualResultBag.getTotal());

		List<Individual> individuals = individualResultBag.getResults();

		Individual individual = individuals.get(0);

		Assert.assertEquals("John Doe", individual.getName());
		Assert.assertEquals("john@acme.com", individual.getEmailAddress());
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "segment-individuals-blogs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "segment-individuals-info-2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSegmentUnknownIndividuals() {
		SearchQueryContext searchQueryContext = new SearchQueryContext(
			"1", AssetType.BLOG);

		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		ResultBag<Individual> individualResultBag =
			_individualDog.getIndividualResultBag(
				null, BlogMetricType.VIEWS, searchQueryContext, 2, 0);

		Assert.assertEquals(
			individualResultBag.toString(), 1, individualResultBag.getTotal());

		List<Individual> individuals = individualResultBag.getResults();

		Individual individual = individuals.get(0);

		Assert.assertEquals("1", individual.getId());
		Assert.assertEquals("john@acme.com", individual.getEmailAddress());
	}

	private Set<String> _getIndividualsDemographicsFieldValues(
		List<Individual> individuals, String fieldName) {

		Stream<Individual> stream = individuals.stream();

		return stream.map(
			Individual::getDemographics
		).map(
			demographicsMap -> demographicsMap.get(fieldName)
		).filter(
			fieldValue -> !Objects.isNull(fieldValue)
		).collect(
			Collectors.toSet()
		);
	}

	@Autowired
	private IndividualDog _individualDog;

}