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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
@Component
public class ReportIndividualDog {

	@Autowired
	public ReportIndividualDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public Individual getIndividual(Long id) {
		return _mapIndividual(_individualDog.getIndividual(id));
	}

	public Page<Individual> getIndividualPage(
		Long individualId, int size, Sort sort) {

		List<com.liferay.osb.asah.common.entity.Individual> individuals =
			_individualRepository.findByIdAfter(
				individualId, PageRequest.of(0, size, sort));

		Stream<com.liferay.osb.asah.common.entity.Individual> stream =
			individuals.stream();

		List<Individual> reportIndividuals = new LinkedList<>();

		stream.forEachOrdered(
			individual -> reportIndividuals.add(_mapIndividual(individual)));

		return PageableExecutionUtils.getPage(
			reportIndividuals, PageRequest.of(0, size, sort),
			() -> _individualRepository.countByIdAfter(individualId));
	}

	public ResultBag<Individual> getIndividualResultBag(
		String query, Long segmentId, int size, int start) {

		List<com.liferay.osb.asah.common.entity.Individual> individuals =
			_individualDog.getIndividuals(query, segmentId, start / size, size);

		ResultBag<Individual> resultBag = new ResultBag<>();

		List<Individual> models = new ArrayList<>();

		for (com.liferay.osb.asah.common.entity.Individual individual :
				individuals) {

			models.add(_mapIndividual(individual));
		}

		resultBag.setResults(models);
		resultBag.setTotal(_individualDog.countIndividuals(query, segmentId));

		return resultBag;
	}

	public ResultBag<Individual> getIndividualResultBag(
		String keywords, MetricType metricType,
		SearchQueryContext searchQueryContext, int size, int start) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildSearchSourceBuilder(
				dogConfiguration, metricType, searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return new ResultBag<>();
		}

		List<String> individualIds = _getIndividualIds(
			aggregations.get("individuals"));

		if (individualIds.isEmpty()) {
			return new ResultBag<>();
		}

		List<com.liferay.osb.asah.common.entity.Individual> individuals =
			_individualDog.searchIndividuals(
				ListUtil.map(individualIds, Long::valueOf), keywords, size,
				start);

		ResultBag<Individual> resultBag = new ResultBag<>();

		List<Individual> models = new ArrayList<>();

		for (com.liferay.osb.asah.common.entity.Individual individual :
				individuals) {

			models.add(
				new Individual(
					FaroInfoIndividualUtil.getIndividualEmail(individual),
					String.valueOf(individual.getId()),
					FaroInfoIndividualUtil.getIndividualName(individual)));
		}

		resultBag.setResults(models);
		resultBag.setTotal(
			_individualDog.countIndividuals(
				ListUtil.map(individualIds, Long::valueOf), keywords));

		return resultBag;
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		DogConfiguration dogConfiguration, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			AggregationBuilders.terms(
				"individuals"
			).field(
				"individualId"
			).size(
				Integer.MAX_VALUE
			));

		BoolQueryBuilder filterBoolQueryBuilder =
			_searchQueryHelper.createFilterBoolQueryBuilder(
				DogUtil.getAssetIdOptional(
					searchQueryContext.getAssetId(), dogConfiguration),
				metricType, searchQueryContext);

		searchSourceBuilder.query(
			filterBoolQueryBuilder.filter(
				QueryBuilders.termQuery("knownIndividual", true)));

		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private List<String> _getIndividualIds(Terms terms) {
		return ListUtil.map(
			terms.getBuckets(), MultiBucketsAggregation.Bucket::getKeyAsString);
	}

	private Map<String, String> _getIndividualProperties(
		JSONObject demographicsJSONObject) {

		if (demographicsJSONObject == null) {
			return Collections.emptyMap();
		}

		Map<String, String> properties = new HashMap<>();

		for (String propertyName : demographicsJSONObject.keySet()) {
			String propertyValue = _getPropertyValue(
				demographicsJSONObject, propertyName);

			if (propertyValue == null) {
				continue;
			}

			properties.put(propertyName, propertyValue);
		}

		return properties;
	}

	private String _getPropertyValue(
		JSONObject organizationJSONObject, String fieldName) {

		JSONArray propertyJSONArray = organizationJSONObject.optJSONArray(
			fieldName);

		if (propertyJSONArray == null) {
			return null;
		}

		JSONObject propertyJSONObject = propertyJSONArray.optJSONObject(0);

		if (propertyJSONObject == null) {
			return null;
		}

		return String.valueOf(propertyJSONObject.get("value"));
	}

	private Individual _mapIndividual(
		com.liferay.osb.asah.common.entity.Individual individual) {

		Individual newIndividual = new Individual();

		newIndividual.setCustom(
			_getIndividualProperties(
				_objectMapper.convertValue(
					individual.getCustomDemographics(), JSONObject.class)));
		newIndividual.setDemographics(
			_getIndividualProperties(
				_objectMapper.convertValue(
					individual.getDemographics(), JSONObject.class)));
		newIndividual.setId(String.valueOf(individual.getId()));
		newIndividual.setIndividualSegmentIds(
			ListUtil.map(individual.getSegmentIds(), String::valueOf));

		return newIndividual;
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}