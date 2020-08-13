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
import com.liferay.osb.asah.backend.model.FieldMapping;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
@Component
public class IndividualDog {

	@Autowired
	public IndividualDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public Individual getIndividual(String id) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"individuals", _faroInfoElasticsearchInvoker,
			_buildSearchSourceBuilder(
				_getIndividualDemographicsFetchSourceExcludes(),
				QueryBuilders.termQuery("id", id), 1, 0));

		if (searchHits.getTotalHits() == 0) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "There is no individual with ID " + id);
		}

		SearchHit[] searchHitArray = searchHits.getHits();

		return _mapIndividual(searchHitArray[0]);
	}

	public ResultBag<Individual> getIndividualResultBag(
		Object[] searchAfter, int size, SortBuilder<?> sortBuilder) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"individuals", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				_getIndividualDemographicsFetchSourceExcludes(), null,
				searchAfter, size, sortBuilder));

		return DogUtil.createResultBag(this::_mapIndividual, searchHits);
	}

	public ResultBag<Individual> getIndividualResultBag(
		String keywords, MetricType metricType,
		SearchQueryContext searchQueryContext, int size, int start) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
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

		SearchHits searchHits = _dataDog.querySearchHits(
			"individuals", _faroInfoElasticsearchInvoker,
			_buildSearchSourceBuilder(individualIds, keywords, size, start));

		return DogUtil.createResultBag(
			searchHit -> {
				JSONObject individualJSONObject = new JSONObject(
					searchHit.getSourceAsMap());

				JSONObject demographicsJSONObject =
					individualJSONObject.getJSONObject("demographics");

				return new Individual(
					FaroInfoIndividualUtil.getIndividualEmail(
						demographicsJSONObject),
					individualJSONObject.getString("id"),
					FaroInfoIndividualUtil.getIndividualName(
						demographicsJSONObject));
			},
			searchHits);
	}

	public ResultBag<Individual> getIndividualResultBag(
		String individualSegmentId, String query, int size, int start) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (StringUtils.isNotEmpty(individualSegmentId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId));
		}

		String[] individualDemographicsSearchableFieldNames =
			_getIndividualDemographicsSearchableFieldNames();

		if (StringUtils.isNotEmpty(query) &&
			(individualDemographicsSearchableFieldNames != null)) {

			boolQueryBuilder.filter(
				QueryBuilders.multiMatchQuery(
					query, individualDemographicsSearchableFieldNames));
		}

		SearchHits searchHits = _dataDog.querySearchHits(
			"individuals", _faroInfoElasticsearchInvoker,
			_buildSearchSourceBuilder(
				_getIndividualDemographicsFetchSourceExcludes(),
				boolQueryBuilder, size, start));

		return DogUtil.createResultBag(this::_mapIndividual, searchHits);
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

	private SearchSourceBuilder _buildSearchSourceBuilder(
		List<String> individualIds, String keywords, int size, int start) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.from(start);

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("demographics.email")
		).filter(
			QueryBuilders.termsQuery("id", individualIds)
		);

		if (!StringUtils.isBlank(keywords)) {
			boolQueryBuilder.filter(
				_getKeywordsQueryBuilder(
					keywords, "demographics.email.value",
					"demographics.familyName.value",
					"demographics.givenName.value"));
		}

		searchSourceBuilder.query(boolQueryBuilder);

		searchSourceBuilder.size(size);
		searchSourceBuilder.sort(
			SortBuilderUtil.fieldSort(
				"demographics.givenName.value", SortOrder.ASC, "keyword"));

		return searchSourceBuilder;
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		String[] fetchSourceExcludes, QueryBuilder queryBuilder, int size,
		int start) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.fetchSource(null, fetchSourceExcludes);
		searchSourceBuilder.from(start);

		if (queryBuilder != null) {
			searchSourceBuilder.query(queryBuilder);
		}

		searchSourceBuilder.size(size);

		return searchSourceBuilder;
	}

	private String[] _getIndividualDemographicsFetchSourceExcludes() {
		ResultBag<FieldMapping> fieldMappingResultBag =
			_fieldMappingDog.getFieldMappingResultBag(
				"demographics", "individual", 20, 0);

		if (fieldMappingResultBag.getTotal() == 0) {
			return null;
		}

		List<String> fetchSourceExcludes = new ArrayList<>();

		for (FieldMapping fieldMapping : fieldMappingResultBag.getResults()) {
			if (fieldMapping.isRestricted()) {
				fetchSourceExcludes.add(
					"demographics." + fieldMapping.getFieldName());
			}
		}

		return fetchSourceExcludes.toArray(new String[0]);
	}

	private String[] _getIndividualDemographicsSearchableFieldNames() {
		ResultBag<FieldMapping> fieldMappingResultBag =
			_fieldMappingDog.getFieldMappingResultBag(
				"demographics", "individual", 20, 0);

		if (fieldMappingResultBag.getTotal() == 0) {
			return null;
		}

		List<String> searchableFieldNames = new ArrayList<>();

		for (FieldMapping fieldMapping : fieldMappingResultBag.getResults()) {
			if (!fieldMapping.isRestricted() &&
				Objects.equals("Text", fieldMapping.getFieldType())) {

				searchableFieldNames.add(
					String.format(
						"demographics.%s.value.searchable",
						fieldMapping.getFieldName()));
			}
		}

		return searchableFieldNames.toArray(new String[0]);
	}

	private List<String> _getIndividualIds(Terms terms) {
		List<? extends Terms.Bucket> buckets = terms.getBuckets();

		Stream<? extends Terms.Bucket> stream = buckets.stream();

		return stream.map(
			MultiBucketsAggregation.Bucket::getKeyAsString
		).collect(
			Collectors.toList()
		);
	}

	private QueryBuilder _getKeywordsQueryBuilder(
		String keywords, String... fieldNames) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		for (String fieldName : fieldNames) {
			boolQueryBuilder.should(
				QueryBuilders.queryStringQuery(
					String.format(
						"%s:*%s*", fieldName,
						QueryUtil.escapeKeywords(keywords)))
			).should(
				QueryBuilders.matchQuery(
					fieldName, keywords
				).fuzziness(
					Fuzziness.AUTO
				)
			);
		}

		return boolQueryBuilder;
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private Individual _mapIndividual(SearchHit searchHit) {
		Individual individual = new Individual();

		JSONObject individualJSONObject = new JSONObject(
			searchHit.getSourceAsMap());

		individual.setDemographics(
			FaroInfoIndividualUtil.getIndividualDemographics(
				individualJSONObject.optJSONObject("demographics")));
		individual.setId(individualJSONObject.getString("id"));
		individual.setIndividualSegmentIds(
			JSONUtil.toStringList(
				individualJSONObject.getJSONArray("individualSegmentIds")));

		return individual;
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}