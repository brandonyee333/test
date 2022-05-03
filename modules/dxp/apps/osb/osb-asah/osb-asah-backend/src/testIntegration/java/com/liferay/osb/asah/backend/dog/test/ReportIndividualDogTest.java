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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.ReportIndividualDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.BlogMetricType;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
public class ReportIndividualDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_dataSourceRepository.save(dataSource);

		for (String fieldName : _FIELD_NAMES) {
			_fieldMappingRepository.save(
				FaroInfoTestUtil.buildIndividualFieldMapping(
					dataSource.getId(), fieldName, fieldName, "Text"));
		}
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "segment_individuals_blogs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "segment_fields_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "segment_individuals_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSegmentIndividuals() {
		SearchQueryContext searchQueryContext = new SearchQueryContext(
			"1", AssetType.BLOG);

		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		ResultBag<Individual> individualResultBag =
			_reportIndividualDog.getIndividualResultBag(
				null, BlogMetricType.VIEWS, searchQueryContext, 2, 1);

		Assertions.assertEquals(
			3, individualResultBag.getTotal(), individualResultBag.toString());

		List<Individual> individuals = individualResultBag.getResults();

		Individual individual = individuals.get(0);

		Assertions.assertEquals("Test1 Test1", individual.getName());
		Assertions.assertEquals(
			"test1@liferay.com", individual.getEmailAddress());

		individual = individuals.get(1);

		Assertions.assertEquals("Test2 Test2", individual.getName());
		Assertions.assertEquals(
			"test2@liferay.com", individual.getEmailAddress());
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "segment_individuals_blogs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "segment_fields_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "segment_individuals_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSegmentIndividualsSearch() {
		SearchQueryContext searchQueryContext = new SearchQueryContext(
			"1", AssetType.BLOG);

		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		ResultBag<Individual> individualResultBag =
			_reportIndividualDog.getIndividualResultBag(
				"john", BlogMetricType.VIEWS, searchQueryContext, 10, 0);

		Assertions.assertEquals(
			1, individualResultBag.getTotal(), individualResultBag.toString());

		List<Individual> individuals = individualResultBag.getResults();

		Individual individual = individuals.get(0);

		Assertions.assertEquals("John Doe", individual.getName());
		Assertions.assertEquals("john@acme.com", individual.getEmailAddress());
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "segment_individuals_blogs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "segment_fields_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "segment_individuals_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSegmentUnknownIndividuals() {
		SearchQueryContext searchQueryContext = new SearchQueryContext(
			"1", AssetType.BLOG);

		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		ResultBag<Individual> individualResultBag =
			_reportIndividualDog.getIndividualResultBag(
				null, BlogMetricType.VIEWS, searchQueryContext, 2, 0);

		Assertions.assertEquals(
			1, individualResultBag.getTotal(), individualResultBag.toString());

		List<Individual> individuals = individualResultBag.getResults();

		Individual individual = individuals.get(0);

		Assertions.assertEquals("1", individual.getId());
		Assertions.assertEquals("john@acme.com", individual.getEmailAddress());
	}

	private static final String[] _FIELD_NAMES = {
		"email", "familyName", "givenName"
	};

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private ReportIndividualDog _reportIndividualDog;

}