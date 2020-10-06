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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.FilterUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.parser.FilterStringParser;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Sum;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoIndividualSegmentDog extends BaseFaroInfoDog {

	@CacheEvict(allEntries = true, value = "getReferencedAssetIds")
	public JSONObject addIndividualSegment(
			JSONObject individualSegmentJSONObject)
		throws Exception {

		_setReferencedFields(individualSegmentJSONObject);

		individualSegmentJSONObject = elasticsearchInvoker.add(
			"individual-segments", _setState(individualSegmentJSONObject));

		_addOSBAsahTask(individualSegmentJSONObject);

		return individualSegmentJSONObject;
	}

	public void assignChannel(String channelId, String individualSegmentId)
		throws Exception {

		elasticsearchInvoker.get("channels", channelId);

		JSONObject individualSegmentJSONObject = elasticsearchInvoker.get(
			"individual-segments", individualSegmentId);

		if (StringUtils.isNotBlank(
				individualSegmentJSONObject.optString("channelId"))) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Individual segment already assigned to property: " +
					individualSegmentJSONObject.optString("channelId"));
		}

		individualSegmentJSONObject = elasticsearchInvoker.update(
			"individual-segments", individualSegmentJSONObject.getString("id"),
			JSONUtil.put("channelId", channelId));

		_updateMemberships(channelId, individualSegmentJSONObject);
	}

	@CacheEvict(allEntries = true, value = "getReferencedAssetIds")
	public void deleteIndividualSegment(String individualSegmentId) {
		elasticsearchInvoker.delete("individual-segments", individualSegmentId);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"DeleteIndividualSegmentTasksNanite",
			JSONUtil.put("individualSegmentId", individualSegmentId));
	}

	public void disableDynamicIndividualSegments(
			DXPEntityType dxpEntityType, String id)
		throws Exception {

		if (StringUtils.isBlank(id)) {
			return;
		}

		_disableDynamicIndividualSegments(
			QueryBuilders.termsQuery(
				dxpEntityType.getIndividualSegmentFieldName(), id));
	}

	public void disableDynamicIndividualSegments(
			String dataSourceId, List<String> fieldMappingIds)
		throws Exception {

		if ((dataSourceId == null) && fieldMappingIds.isEmpty()) {
			return;
		}

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (dataSourceId != null) {
			boolQueryBuilder.should(
				QueryBuilders.termQuery(
					"referencedAssetDataSourceIds", dataSourceId));
		}

		if (!fieldMappingIds.isEmpty()) {
			boolQueryBuilder.should(
				QueryBuilders.termsQuery(
					"referencedFieldMappingIds", fieldMappingIds));
		}

		_disableDynamicIndividualSegments(boolQueryBuilder);
	}

	@Cacheable("getReferencedAssetIds")
	public Set<String> getReferencedAssetIds() throws Exception {
		Set<String> referencedAssetIds = new HashSet<>();

		JSONArrayIterator.of(
			"individual-segments", elasticsearchInvoker,
			individualSegmentJSONObject -> {
				referencedAssetIds.addAll(
					JSONUtil.toStringSet(
						individualSegmentJSONObject.optJSONArray(
							"referencedAssetIds")));

				return null;
			}
		).iterate();

		return referencedAssetIds;
	}

	public JSONObject replaceIndividualSegment(
			JSONObject individualSegmentJSONObject)
		throws Exception {

		_replaceAccount(individualSegmentJSONObject);

		JSONObject existingIndividualSegmentJSONObject =
			elasticsearchInvoker.get(
				"individual-segments",
				individualSegmentJSONObject.getString("id"));

		if (Objects.equals(
				existingIndividualSegmentJSONObject.optString("filter", null),
				individualSegmentJSONObject.optString("filter", null))) {

			elasticsearchInvoker.replace(
				"individual-segments", individualSegmentJSONObject);
		}
		else {
			_setReferencedFields(individualSegmentJSONObject);

			elasticsearchInvoker.replace(
				"individual-segments", _setState(individualSegmentJSONObject));

			_addOSBAsahTask(individualSegmentJSONObject);

			if (_cacheManager != null) {
				Cache cache = _cacheManager.getCache("getReferencedAssetIds");

				cache.clear();
			}
		}

		return individualSegmentJSONObject;
	}

	public JSONObject updateIndividualSegment(
			String individualSegmentId,
			JSONObject partialIndividualSegmentJSONObject)
		throws Exception {

		JSONObject individualSegmentJSONObject = elasticsearchInvoker.get(
			"individual-segments", individualSegmentId);

		_updateAccount(
			individualSegmentJSONObject, partialIndividualSegmentJSONObject);

		if ((_getModifiedFieldValue(
				individualSegmentJSONObject, "filter",
				partialIndividualSegmentJSONObject) == null) &&
			(_getModifiedFieldValue(
				individualSegmentJSONObject, "includeAnonymousUsers",
				partialIndividualSegmentJSONObject) == null)) {

			individualSegmentJSONObject = elasticsearchInvoker.update(
				"individual-segments", individualSegmentId,
				partialIndividualSegmentJSONObject);
		}
		else {
			_setReferencedFields(partialIndividualSegmentJSONObject);

			individualSegmentJSONObject = elasticsearchInvoker.update(
				"individual-segments", individualSegmentId,
				_setState(partialIndividualSegmentJSONObject));

			_addOSBAsahTask(individualSegmentJSONObject);

			if (_cacheManager != null) {
				Cache cache = _cacheManager.getCache("getReferencedAssetIds");

				cache.clear();
			}
		}

		return individualSegmentJSONObject;
	}

	private static String _getAssetId(String[] terms) {
		if (terms.length != 3) {
			throw new IllegalArgumentException(
				"Invalid terms length: " + terms.length);
		}

		String fieldName = terms[0];
		String operator = terms[1];

		if ((!fieldName.startsWith("activities/") &&
			 !fieldName.equals("activityKey")) ||
			(!operator.equalsIgnoreCase("eq") &&
			 !operator.equalsIgnoreCase("ne"))) {

			return null;
		}

		String activityKey = StringUtil.unquote(terms[2]);

		return activityKey.substring(activityKey.lastIndexOf("#") + 1);
	}

	private void _addOSBAsahTask(JSONObject individualSegmentJSONObject) {
		if (Objects.equals(
				individualSegmentJSONObject.getString("segmentType"),
				"DYNAMIC")) {

			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					"dateModified",
					individualSegmentJSONObject.getString("dateModified")
				).put(
					"individualSegmentJSONObject", individualSegmentJSONObject
				));
		}
	}

	private Exception _addReferencedFieldMappingId(
		String fullFieldName, String ownerType,
		Set<String> referencedFieldMappingIds) {

		String[] fieldNameParts = fullFieldName.split("/");

		if (fieldNameParts.length < 2) {
			return null;
		}

		String context = fieldNameParts[0];

		if (ownerType == null) {
			if (context.equals("custom") || context.equals("demographics")) {
				ownerType = "individual";
			}
			else if (context.equals("organization")) {
				ownerType = "account";
			}
			else {
				return null;
			}
		}

		String fieldName = fieldNameParts[1];

		JSONObject fieldMappingJSONObject =
			_faroInfoFieldMappingDog.fetchFieldMappingJSONObject(
				context, fieldName, ownerType);

		if (fieldMappingJSONObject == null) {
			return new Exception(
				"Unable to get field mapping with field name " + fieldName);
		}

		referencedFieldMappingIds.add(fieldMappingJSONObject.getString("id"));

		return null;
	}

	private Exception _addReferencedId(
		String collectionName, String dataSourceIdFieldName,
		ElasticsearchInvoker elasticsearchInvoker, String id, String key,
		Map<String, Set<String>> referencedObjectSets) {

		Set<String> referencedIds = referencedObjectSets.get(key);

		referencedIds.add(id);

		Set<String> referencedAssetDataSourceIds = referencedObjectSets.get(
			"referencedAssetDataSourceIds");

		JSONObject referencedJSONObject = elasticsearchInvoker.get(
			collectionName, id);

		referencedAssetDataSourceIds.add(
			referencedJSONObject.getString(dataSourceIdFieldName));

		return null;
	}

	private void _disableDynamicIndividualSegments(QueryBuilder queryBuilder)
		throws Exception {

		JSONArrayIterator.of(
			"individual-segments", elasticsearchInvoker,
			individualSegmentJSONObject -> {
				try {
					updateIndividualSegment(
						individualSegmentJSONObject.getString("id"),
						JSONUtil.put("state", "DISABLED"));
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("segmentType", "DYNAMIC")
			).filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("state", "DISABLED"))
			).filter(
				queryBuilder
			)
		).iterate();
	}

	private String _getAccountId(JSONObject individualSegmentJSONObject) {
		if (!Objects.equals(
				individualSegmentJSONObject.getString("status"), "INACTIVE")) {

			return null;
		}

		String name = individualSegmentJSONObject.getString("name");

		if (!name.startsWith(_ACCOUNT_PREFIX)) {
			return null;
		}

		return name.substring(_ACCOUNT_PREFIX.length());
	}

	private JSONArray _getActivitiesCountsJSONArray(
		boolean includeAnonymousUsers, String individualSegmentId,
		JSONObject individualSegmentJSONObject) {

		if (!individualSegmentJSONObject.has("activitiesCount")) {
			return null;
		}

		JSONArray jsonArray = new JSONArray();

		SearchResponse searchResponse = elasticsearchInvoker.search(
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
					_getQueryBuilder(
						includeAnonymousUsers, individualSegmentId));
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

		return jsonArray;
	}

	private JSONArray _getIndividualCountsJSONArray(
		boolean includeAnonymousUsers, String individualSegmentId,
		JSONObject individualSegmentJSONObject) {

		if (!individualSegmentJSONObject.has("individualCount")) {
			return null;
		}

		JSONArray jsonArray = new JSONArray();

		SearchResponse searchResponse = elasticsearchInvoker.search(
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
					_getQueryBuilder(
						includeAnonymousUsers, individualSegmentId));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("channelIds");

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			jsonArray.put(
				JSONUtil.put(
					"channelId", termsBucket.getKeyAsString()
				).put(
					"individualCount", termsBucket.getDocCount()
				));
		}

		return jsonArray;
	}

	private Object _getModifiedFieldValue(
		JSONObject existingIndividualSegmentJSONObject, String fieldName,
		JSONObject partialIndividualSegmentJSONObject) {

		Object newValue = partialIndividualSegmentJSONObject.opt(fieldName);

		if (Objects.equals(
				existingIndividualSegmentJSONObject.opt(fieldName), newValue)) {

			return null;
		}

		return newValue;
	}

	private QueryBuilder _getQueryBuilder(
		boolean includeAnonymousUsers, String individualSegmentId) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"individualSegmentIds", individualSegmentId));

		if (!includeAnonymousUsers) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("demographics.email"));
		}

		return boolQueryBuilder;
	}

	private JSONObject _getReferencedObjectIdsJSONObject(
			String filterString, String outerFunctionName)
		throws Exception {

		Map<String, Set<String>> referencedObjectSets = new HashMap<>();

		for (String referencedObjectName : _REFERENCED_OBJECT_NAMES) {
			referencedObjectSets.put(referencedObjectName, new HashSet<>());
		}

		JSONObject referencedObjectIdsJSONObject = FilterStringParser.parse(
			filterString,
			() -> {
				JSONObject jsonObject = new JSONObject();

				for (Map.Entry<String, Set<String>> entrySet :
						referencedObjectSets.entrySet()) {

					jsonObject.put(
						entrySet.getKey(), new JSONArray(entrySet.getValue()));
				}

				return jsonObject;
			},
			innerFilterString -> {
				try {
					JSONObject innerReferencedObjectIdsJSONObject =
						_getReferencedObjectIdsJSONObject(
							innerFilterString, outerFunctionName);

					for (Map.Entry<String, Set<String>> entrySet :
							referencedObjectSets.entrySet()) {

						JSONUtil.addToStringCollection(
							entrySet.getValue(),
							innerReferencedObjectIdsJSONObject.optJSONArray(
								entrySet.getKey()));
					}
				}
				catch (Exception e) {
					return e;
				}

				return null;
			},
			logicalOperator -> null,
			terms -> _processLogicalOperator(
				outerFunctionName, referencedObjectSets, terms),
			functionData -> _processStringFunction(
				functionData, outerFunctionName, referencedObjectSets));

		if (referencedObjectIdsJSONObject == null) {
			JSONObject jsonObject = new JSONObject();

			for (String referencedObjectName : _REFERENCED_OBJECT_NAMES) {
				jsonObject.put(referencedObjectName, new JSONArray());
			}

			return jsonObject;
		}

		return referencedObjectIdsJSONObject;
	}

	private Exception _processLogicalOperator(
		String outerFunctionName, Map<String, Set<String>> referencedObjectSets,
		String[] terms) {

		if (StringUtils.equals(outerFunctionName, "organizations.filter")) {
			return _processOrganizationLogicalOperator(
				referencedObjectSets, terms);
		}

		try {
			String assetId = _getAssetId(terms);

			if (assetId != null) {
				return _addReferencedId(
					"assets", "dataSourceId", elasticsearchInvoker, assetId,
					"referencedAssetIds", referencedObjectSets);
			}

			DXPEntityType dxpEntityType = DXPEntityType.ofIndividualFieldName(
				terms[0]);

			if (dxpEntityType != null) {
				if (dxpEntityType.isOrganization()) {
					return _addReferencedId(
						dxpEntityType.getCollectionName(), "dataSourceId",
						elasticsearchInvoker, StringUtil.unquote(terms[2]),
						dxpEntityType.getIndividualSegmentFieldName(),
						referencedObjectSets);
				}

				return _addReferencedId(
					dxpEntityType.getCollectionName(), "osbAsahDataSourceId",
					_dxpRawElasticsearchInvoker, StringUtil.unquote(terms[2]),
					dxpEntityType.getIndividualSegmentFieldName(),
					referencedObjectSets);
			}

			return _addReferencedFieldMappingId(
				terms[0], null,
				referencedObjectSets.get("referencedFieldMappingIds"));
		}
		catch (Exception e) {
			return e;
		}
	}

	private Exception _processOrganizationLogicalOperator(
		Map<String, Set<String>> referencedObjectSets, String[] terms) {

		if (terms.length != 3) {
			return null;
		}

		String fieldName = terms[0];

		if (fieldName.equals("id") || fieldName.equals("parentId")) {
			return _addReferencedId(
				"organizations", "dataSourceId", elasticsearchInvoker,
				StringUtil.unquote(terms[2]), "referencedOrganizationIds",
				referencedObjectSets);
		}

		return _addReferencedFieldMappingId(
			fieldName, "organization",
			referencedObjectSets.get("referencedFieldMappingIds"));
	}

	private Exception _processStringFunction(
		Object[] functionData, String outerFunctionName,
		Map<String, Set<String>> referencedObjectSets) {

		List<String> arguments = (List<String>)functionData[0];

		String name = (String)functionData[2];

		if (Objects.equals(name, "accounts.filter") ||
			Objects.equals(name, "accounts.filterByCount") ||
			Objects.equals(name, "activities.filter") ||
			Objects.equals(name, "activities.filterByCount") ||
			Objects.equals(name, "organizations.filter")) {

			String[] argumentNames = null;

			if (name.contains("filterByCount")) {
				argumentNames = new String[] {"filter", "operator", "value"};
			}
			else {
				argumentNames = new String[] {"filter"};
			}

			String[] argumentValues = FilterUtil.getArgumentValues(
				arguments, argumentNames);

			try {
				if (Objects.equals(name, "organizations.filter")) {
					outerFunctionName = name;
				}

				JSONObject referencedObjectIdsJSONObject =
					_getReferencedObjectIdsJSONObject(
						StringUtil.unquoteAndDecodeInnerQuotes(
							argumentValues[0]),
						outerFunctionName);

				for (Map.Entry<String, Set<String>> entrySet :
						referencedObjectSets.entrySet()) {

					JSONUtil.addToStringCollection(
						entrySet.getValue(),
						referencedObjectIdsJSONObject.optJSONArray(
							entrySet.getKey()));
				}
			}
			catch (Exception e) {
				return e;
			}
		}
		else if (Objects.equals(name, "contains") ||
				 Objects.equals(name, "endsWith") ||
				 Objects.equals(name, "startsWith")) {

			if (arguments.size() != 2) {
				return new IllegalArgumentException(
					"Expected 2 arguments for " + name + " function, got " +
						arguments.size() + " instead: " + arguments);
			}

			String ownerType = null;

			if (StringUtils.equals(outerFunctionName, "organizations.filter")) {
				ownerType = "organization";
			}

			return _addReferencedFieldMappingId(
				arguments.get(0), ownerType,
				referencedObjectSets.get("referencedFieldMappingIds"));
		}

		return null;
	}

	private void _replaceAccount(JSONObject individualSegmentJSONObject) {
		String accountId = _getAccountId(individualSegmentJSONObject);

		if (accountId == null) {
			return;
		}

		JSONObject accountJSONObject = elasticsearchInvoker.get(
			"accounts", accountId);

		JSONArray activitiesCountsJSONArray = _getActivitiesCountsJSONArray(
			individualSegmentJSONObject.optBoolean("includeAnonymousUsers"),
			individualSegmentJSONObject.getString("id"),
			individualSegmentJSONObject);

		if (activitiesCountsJSONArray != null) {
			accountJSONObject.put(
				"activitiesCounts", activitiesCountsJSONArray);
		}

		JSONArray individualCountsJSONArray = _getIndividualCountsJSONArray(
			individualSegmentJSONObject.optBoolean("includeAnonymousUsers"),
			individualSegmentJSONObject.getString("id"),
			individualSegmentJSONObject);

		if (individualCountsJSONArray != null) {
			accountJSONObject.put(
				"individualCounts", individualCountsJSONArray);
		}

		for (String fieldName : _SHARED_ACCOUNT_FIELD_NAMES) {
			accountJSONObject.put(
				fieldName, individualSegmentJSONObject.opt(fieldName));
		}

		_faroInfoAccountDog.replaceAccount(accountJSONObject);
	}

	private void _setReferencedFields(JSONObject individualSegmentJSONObject)
		throws Exception {

		JSONObject referencedObjectIdsJSONObject =
			_getReferencedObjectIdsJSONObject(
				individualSegmentJSONObject.optString("filter", null), null);

		for (String referencedObjectType :
				referencedObjectIdsJSONObject.keySet()) {

			individualSegmentJSONObject.put(
				referencedObjectType,
				referencedObjectIdsJSONObject.getJSONArray(
					referencedObjectType));
		}
	}

	private JSONObject _setState(JSONObject individualSegmentJSONObject) {
		String segmentType = individualSegmentJSONObject.optString(
			"segmentType", null);

		if ((segmentType == null) || segmentType.equals("DYNAMIC")) {
			individualSegmentJSONObject.put("state", "IN_PROGRESS");
		}
		else {
			individualSegmentJSONObject.put("state", "READY");
		}

		return individualSegmentJSONObject;
	}

	private void _updateAccount(
		JSONObject existingIndividualSegmentJSONObject,
		JSONObject partialIndividualSegmentJSONObject) {

		String accountId = _getAccountId(existingIndividualSegmentJSONObject);

		if (accountId == null) {
			return;
		}

		JSONObject partialAccountJSONObject = new JSONObject();

		JSONArray activitiesCountsJSONArray = _getActivitiesCountsJSONArray(
			partialIndividualSegmentJSONObject.optBoolean(
				"includeAnonymousUsers",
				existingIndividualSegmentJSONObject.optBoolean(
					"includeAnonymousUsers")),
			existingIndividualSegmentJSONObject.getString("id"),
			partialIndividualSegmentJSONObject);

		if (activitiesCountsJSONArray != null) {
			partialAccountJSONObject.put(
				"activitiesCounts", activitiesCountsJSONArray);
		}

		JSONArray individualCountsJSONArray = _getIndividualCountsJSONArray(
			partialIndividualSegmentJSONObject.optBoolean(
				"includeAnonymousUsers",
				existingIndividualSegmentJSONObject.optBoolean(
					"includeAnonymousUsers")),
			existingIndividualSegmentJSONObject.getString("id"),
			partialIndividualSegmentJSONObject);

		if (individualCountsJSONArray != null) {
			partialAccountJSONObject.put(
				"individualCounts", individualCountsJSONArray);
		}

		for (String fieldName : _SHARED_ACCOUNT_FIELD_NAMES) {
			partialAccountJSONObject.put(
				fieldName,
				_getModifiedFieldValue(
					existingIndividualSegmentJSONObject, fieldName,
					partialIndividualSegmentJSONObject));
		}

		if (partialAccountJSONObject.length() > 0) {
			_faroInfoAccountDog.updateAccount(
				accountId, partialAccountJSONObject);
		}
	}

	private void _updateMemberships(
			String channelId, JSONObject individualSegmentJSONObject)
		throws Exception {

		if (Objects.equals(
				individualSegmentJSONObject.getString("segmentType"),
				"DYNAMIC")) {

			_addOSBAsahTask(individualSegmentJSONObject);

			return;
		}

		List<String> individualIds =
			_faroInfoMembershipDog.getIndividualSegmentIndividualIds(
				individualSegmentJSONObject);

		for (String individualId : individualIds) {
			JSONObject individualJSONObject = elasticsearchInvoker.fetch(
				"individuals", individualId);

			if (individualJSONObject == null) {
				continue;
			}

			JSONArray channelIdsJSONArray = individualJSONObject.optJSONArray(
				"channelIds");

			if ((channelIdsJSONArray != null) &&
				!JSONUtil.hasValue(channelIdsJSONArray, channelId)) {

				_faroInfoMembershipDog.deactivateMembership(
					DateUtil.newDateString(), individualId,
					individualSegmentJSONObject.getString("id"));
			}
		}
	}

	private static final String _ACCOUNT_PREFIX = "Account: ";

	private static final String[] _REFERENCED_OBJECT_NAMES = {
		"referencedAssetDataSourceIds", "referencedAssetIds",
		"referencedFieldMappingIds", "referencedGroupIds",
		"referencedOrganizationIds", "referencedRoleIds", "referencedTeamIds",
		"referencedUserGroupIds", "referencedUserIds"
	};

	private static final String[] _SHARED_ACCOUNT_FIELD_NAMES = {
		"activeIndividualCount", "activitiesCount", "engagementScore",
		"individualCount", "lastActivityDate"
	};

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FaroInfoMembershipDog _faroInfoMembershipDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

}