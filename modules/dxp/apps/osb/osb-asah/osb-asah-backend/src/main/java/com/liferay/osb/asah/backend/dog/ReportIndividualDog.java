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

import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
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

		ResultBag<Individual> resultBag = new ResultBag<>();

		List<Individual> individuals = new ArrayList<>();

		for (com.liferay.osb.asah.common.entity.Individual individual :
				_individualDog.searchIndividuals(
					ListUtil.map(individualIds, Long::valueOf), keywords, size,
					start)) {

			individuals.add(
				new Individual(
					FaroInfoIndividualUtil.getIndividualEmail(individual),
					String.valueOf(individual.getId()),
					FaroInfoIndividualUtil.getIndividualName(individual)));
		}

		resultBag.setResults(individuals);

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
				metricType, searchQueryContext, _timeZoneDog.getTimeZoneId());

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

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}