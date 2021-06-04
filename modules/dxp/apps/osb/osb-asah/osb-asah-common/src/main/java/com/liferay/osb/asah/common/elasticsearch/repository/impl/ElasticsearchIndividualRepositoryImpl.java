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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Sum;

import org.jooq.tools.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchIndividualRepositoryImpl
	extends BaseElasticsearchRepository<Individual, Long>
	implements IndividualRepository {

	@Override
	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, @Nullable String filterString,
		@Nullable Long individualId) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelIds", String.valueOf(channelId)));
		}

		if (!StringUtils.isEmpty(filterString)) {
			boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(
					filterString,
					_faroInfoIndividualsFilterStringConverterHelper));
		}

		if (individualId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("id", String.valueOf(individualId)));
		}

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(), boolQueryBuilder);
	}

	@Override
	public boolean existsByFilterStringAndId(
		@Nullable String filterString, @Nullable Long individualId) {

		return existsByChannelIdAndFilterStringAndId(
			null, filterString, individualId);
	}

	@Override
	public List<Individual.ActivitiesCount> findActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		JSONArray jsonArray = new JSONArray();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"individuals",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.nested(
						"activitiesCounts", "activitiesCounts"
					).subAggregation(
						AggregationBuilders.terms(
							"channelId"
						).field(
							"activitiesCounts.channelId"
						).size(
							Integer.MAX_VALUE
						).subAggregation(
							AggregationBuilders.sum(
								"activitiesCount"
							).field(
								"activitiesCounts.activitiesCount"
							)
						)
					));
				searchSourceBuilder.query(
					_getQueryBuilder(includeAnonymousUsers, segmentId));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		InternalNested internalNested = aggregations.get("activitiesCounts");

		Aggregations internalAggregations = internalNested.getAggregations();

		Terms terms = internalAggregations.get("channelId");

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			Aggregations bucketAggregations = termsBucket.getAggregations();

			Sum sum = bucketAggregations.get("activitiesCount");

			int activitiesCount = 0;

			if (sum != null) {
				activitiesCount = (int)sum.getValue();
			}

			jsonArray.put(
				JSONUtil.put(
					"activitiesCount", activitiesCount
				).put(
					"channelId", termsBucket.getKeyAsString()
				));
		}

		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> {
				JSONObject jsonObject = (JSONObject)object;

				return new Individual.ActivitiesCount(
					new IndividualChannel(
						jsonObject.getLong("activitiesCount"),
						jsonObject.getLong("channelId"), null, null));
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<Individual> findByAnySegmentIds(Long segmentId) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))));
	}

	@Override
	public Optional<Individual>
		findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
			Long associatedId, Long dataSourceId, String fieldName,
			String individualPK) {

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					BoolQueryBuilderUtil.filter(
						QueryBuilders.nestedQuery(
							"dataSourceIndividualPKs",
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"dataSourceIndividualPKs.dataSourceId",
									String.valueOf(dataSourceId))
							).filter(
								QueryBuilders.termQuery(
									"dataSourceIndividualPKs.individualPKs",
									individualPK)
							),
							ScoreMode.None)
					).mustNot(
						QueryBuilders.termQuery(fieldName, associatedId)
					))));
	}

	public List<Individual> findByDataSourceId(
		Long dataSourceId, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.nestedQuery(
								"dataSourceIndividualPKs",
								QueryBuilders.termQuery(
									"dataSourceIndividualPKs.dataSourceId",
									String.valueOf(dataSourceId)),
								ScoreMode.None));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public Optional<Individual> findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK) {

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					QueryBuilders.nestedQuery(
						"dataSourceIndividualPKs",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"dataSourceIndividualPKs.dataSourceId",
								String.valueOf(dataSourceId))
						).filter(
							QueryBuilders.termsQuery(
								"dataSourceIndividualPKs.individualPKs",
								individualPK)
						),
						ScoreMode.None))));
	}

	@Override
	public Optional<Individual> findByEmailAddress(String emailAddress) {
		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					QueryBuilders.termQuery(
						"demographics.email.value", emailAddress))));
	}

	@Override
	public Optional<Individual> findByEmailAddressHashed(
		String emailAddressHashed) {

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					QueryBuilders.termQuery(
						"emailAddressHashed", emailAddressHashed))));
	}

	@Override
	public Optional<Individual> findByEmailAddressOrEmailAddressHashed(
		@Nullable String emailAddress, @Nullable String emailAddressHashed) {

		if (StringUtils.isBlank(emailAddress)) {
			return findByEmailAddressHashed(emailAddressHashed);
		}

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					BoolQueryBuilderUtil.should(
						QueryBuilders.termQuery(
							"demographics.email.value", emailAddress)
					).should(
						QueryBuilders.termQuery(
							"emailAddressHashed",
							DigestUtils.sha256Hex(emailAddress))
					))));
	}

	@Override
	public Map<Long, Long> findIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		Map<Long, Long> individualCounts = new HashMap<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"individuals",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"channelIds"
					).field(
						"channelIds"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					_getQueryBuilder(includeAnonymousUsers, segmentId));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("channelIds");

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			individualCounts.put(
				Long.valueOf(termsBucket.getKeyAsString()),
				termsBucket.getDocCount());
		}

		return individualCounts;
	}

	@Override
	public <S extends Individual> S save(S individual) {
		JSONObject jsonObject = toJSONObject(individual);

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		if (CollectionUtils.isNotEmpty(activitiesCounts)) {
			JSONArray activitiesCountsJSONArray = new JSONArray();

			for (Individual.ActivitiesCount activityCount : activitiesCounts) {
				activitiesCountsJSONArray.put(
					JSONUtil.put(
						"activitiesCount", activityCount.getActivitiesCount()
					).put(
						"channelId",
						String.valueOf(activityCount.getChannelId())
					));
			}

			jsonObject.put("activitiesCounts", activitiesCountsJSONArray);
		}

		Set<Long> channelIds = individual.getChannelIds();

		if (CollectionUtils.isNotEmpty(channelIds)) {
			try {
				jsonObject.put(
					"channelIds",
					JSONUtil.toJSONArray(
						new ArrayList<>(channelIds), String::valueOf));
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs =
			individual.getDataSourceAccountPKs();

		if (CollectionUtils.isNotEmpty(dataSourceAccountPKs)) {
			JSONArray dataSourceAccountPKsJSONArray = new JSONArray();

			for (Individual.DataSourceAccountPK dataSourceAccountPK :
					dataSourceAccountPKs) {

				if (CollectionUtils.isNotEmpty(
						dataSourceAccountPK.getAccountPKs())) {

					try {
						dataSourceAccountPKsJSONArray.put(
							JSONUtil.put(
								"accountPKs",
								new JSONArray(
									objectMapper.writeValueAsString(
										dataSourceAccountPK.getAccountPKs()))
							).put(
								"dataSourceId",
								String.valueOf(
									dataSourceAccountPK.getDataSourceId())
							));
					}
					catch (JsonProcessingException jsonProcessingException) {
						throw new RuntimeException(jsonProcessingException);
					}
				}
			}

			jsonObject.put(
				"dataSourceIndividualPKs", dataSourceAccountPKsJSONArray);
		}

		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs =
			individual.getDataSourceIndividualPKs();

		if (CollectionUtils.isNotEmpty(dataSourceIndividualPKs)) {
			JSONArray dataSourceIndividualPKsJSONArray = new JSONArray();

			for (Individual.DataSourceIndividualPK dataSourceIndividualPK :
					dataSourceIndividualPKs) {

				if (CollectionUtils.isNotEmpty(
						dataSourceIndividualPK.getIndividualPKs())) {

					try {
						dataSourceIndividualPKsJSONArray.put(
							JSONUtil.put(
								"dataSourceId",
								String.valueOf(
									dataSourceIndividualPK.getDataSourceId())
							).put(
								"individualPKs",
								new JSONArray(
									objectMapper.writeValueAsString(
										dataSourceIndividualPK.
											getIndividualPKs()))
							));
					}
					catch (JsonProcessingException jsonProcessingException) {
						throw new RuntimeException(jsonProcessingException);
					}
				}
			}

			jsonObject.put(
				"dataSourceIndividualPKs", dataSourceIndividualPKsJSONArray);
		}

		Set<Individual.LastActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		if (CollectionUtils.isNotEmpty(lastActivityDates)) {
			JSONArray lastActivityDatesJSONArray = new JSONArray();

			for (Individual.LastActivityDate lastActivityDate :
					lastActivityDates) {

				lastActivityDatesJSONArray.put(
					JSONUtil.put(
						"channelId",
						String.valueOf(lastActivityDate.getChannelId())
					).put(
						"lastActivityDate",
						DateUtil.toUTCString(
							lastActivityDate.getLastActivityDate())
					));
			}

			jsonObject.put("lastActivityDates", lastActivityDatesJSONArray);
		}

		if ((individual.getId() != null) &&
			_faroInfoElasticsearchInvoker.exists(
				getCollectionName(), String.valueOf(individual.getId()))) {

			jsonObject = _faroInfoElasticsearchInvoker.update(
				getCollectionName(), jsonObject);
		}
		else {
			jsonObject = _faroInfoElasticsearchInvoker.add(
				getCollectionName(), jsonObject);
		}

		Set<Field> customFields = individual.getCustomFields();

		if (CollectionUtils.isNotEmpty(customFields)) {
			JSONObject customJSONObject = new JSONObject();

			for (Field customField : customFields) {
				JSONObject customFieldJSONObject = objectMapper.convertValue(
					customField, JSONObject.class);

				customFieldJSONObject.remove("id");

				customFieldJSONObject.put(
					"ownerId", jsonObject.getString("id"));

				customJSONObject.put(
					customField.getName(), JSONUtil.put(customFieldJSONObject));
			}

			jsonObject.put("custom", customJSONObject);
		}

		Set<Field> fields = individual.getFields();

		if (CollectionUtils.isNotEmpty(fields)) {
			JSONObject demographicsJSONObject = new JSONObject();

			for (Field field : fields) {
				JSONObject fieldJSONObject = objectMapper.convertValue(
					field, JSONObject.class);

				fieldJSONObject.remove("id");

				fieldJSONObject.put("ownerId", jsonObject.getString("id"));

				demographicsJSONObject.put(
					field.getName(), JSONUtil.put(fieldJSONObject));
			}

			jsonObject.put("demographics", demographicsJSONObject);
		}

		if (CollectionUtils.isNotEmpty(customFields) ||
			CollectionUtils.isNotEmpty(fields)) {

			jsonObject = _faroInfoElasticsearchInvoker.update(
				getCollectionName(), jsonObject);
		}

		return (S)toEntity(jsonObject);
	}

	@Override
	public List<Individual> searchIndividuals(
		String filterString, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							FilterStringToQueryBuilderConverter.convert(
								filterString,
								_faroInfoIndividualsFilterStringConverterHelper));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public void updateAssociatedIds(
		String fieldName, Long[] ids, Long individualId) {

		_faroInfoElasticsearchInvoker.update(
			getCollectionName(), String.valueOf(individualId),
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				_getScriptSource(fieldName),
				Collections.singletonMap(fieldName, ids)));
	}

	@Override
	protected String getCollectionName() {
		return "individuals";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _getQueryBuilder(
		boolean includeAnonymousUsers, Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"individualSegmentIds", String.valueOf(segmentId)));

		if (!includeAnonymousUsers) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("demographics.email"));
		}

		return boolQueryBuilder;
	}

	private String _getScriptSource(String fieldName) {
		StringBuilder sb = new StringBuilder();

		sb.append("ctx._source.");
		sb.append(fieldName);
		sb.append(" = params.");
		sb.append(fieldName);
		sb.append(";");

		return sb.toString();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

}