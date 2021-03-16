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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.backend.rest.response.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.embedded.IndividualsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.backend.rest.response.embedded.IndividualsIndividualSegmentsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping(produces = "application/json", value = "/api/1.0/individuals")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualsRestController"
)
public class IndividualsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public String getIndividual(
			@PathVariable String id,
			@RequestParam(required = false) String channelId,
			@RequestParam(required = false) String expand)
		throws Exception {

		String responseJSON = null;

		if (StringUtils.isEmpty(expand)) {
			responseJSON = toItemGetResponse("individuals", id);
		}
		else {
			responseJSON = toItemGetResponse(
				"individuals",
				new IndividualsEmbeddedJSONObjectCreator(
					_dataSourceDog, faroInfoElasticsearchInvoker, expand,
					_membershipDog, _objectMapper),
				id);
		}

		if (channelId == null) {
			return responseJSON;
		}

		JSONObject individualJSONObject = _doFilterByChannelId(
			channelId, new JSONObject(responseJSON));

		return individualJSONObject.toString();
	}

	@GetMapping(params = "!apply")
	public String getIndividuals(
			@RequestParam(required = false) String channelId,
			@RequestParam(required = false) String expand,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "false", required = false)
				boolean includeAnonymousUsers,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		if (channelId == null) {
			if (StringUtils.isEmpty(expand)) {
				return toCollectionGetResponse(
					"individuals", null, page,
					_faroInfoIndividualDog.buildIndividualsQueryBuilder(
						null, filterString, includeAnonymousUsers),
					size, sorts);
			}

			return toCollectionGetResponse(
				"individuals",
				new IndividualsEmbeddedJSONObjectCreator(
					_dataSourceDog, faroInfoElasticsearchInvoker, expand,
					_membershipDog, _objectMapper),
				page,
				_faroInfoIndividualDog.buildIndividualsQueryBuilder(
					null, filterString, includeAnonymousUsers),
				size, sorts);
		}

		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();
		List<String> newSorts = new ArrayList<>();

		if (sorts != null) {
			List<Pair<String, SortOrder>> sortOrderPairs =
				SortBuilderUtil.getSortOrderPairs(sorts);

			for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
				if (StringUtils.equalsIgnoreCase(
						sortOrderPair.getKey(), "activitiesCount")) {

					fieldSortBuilders.add(
						SortBuilderUtil.buildSort(
							"activitiesCounts.activitiesCount",
							"activitiesCounts",
							QueryBuilders.termQuery(
								"activitiesCounts.channelId", channelId),
							sortOrderPair.getValue()));
				}
				else if (StringUtils.equalsIgnoreCase(
							sortOrderPair.getKey(), "lastActivityDate")) {

					fieldSortBuilders.add(
						SortBuilderUtil.buildSort(
							"lastActivityDates.lastActivityDate",
							"lastActivityDates",
							QueryBuilders.termQuery(
								"lastActivityDates.channelId", channelId),
							sortOrderPair.getValue()));
				}
				else {
					SortOrder sortOrder = sortOrderPair.getValue();

					newSorts.add(sortOrderPair.getKey());
					newSorts.add(sortOrder.toString());
				}
			}
		}

		String responseJSON = null;

		if (StringUtils.isEmpty(expand)) {
			responseJSON = toCollectionGetResponse(
				"individuals", null, fieldSortBuilders, page,
				_faroInfoIndividualDog.buildIndividualsQueryBuilder(
					channelId, filterString, includeAnonymousUsers),
				size, newSorts.toArray(new String[0]));
		}
		else {
			responseJSON = toCollectionGetResponse(
				"individuals",
				new IndividualsEmbeddedJSONObjectCreator(
					_dataSourceDog, faroInfoElasticsearchInvoker, expand,
					_membershipDog, _objectMapper),
				fieldSortBuilders, page,
				_faroInfoIndividualDog.buildIndividualsQueryBuilder(
					channelId, filterString, includeAnonymousUsers),
				size, newSorts.toArray(new String[0]));
		}

		JSONObject responseJSONObject = _filterByChannelId(
			channelId, new JSONObject(responseJSON));

		return responseJSONObject.toString();
	}

	@GetMapping("/{id}/individual-segments")
	public String getIndividualSegments(
			@PathVariable String id,
			@RequestParam(required = false) String expand,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		List<Long> individualSegmentIds =
			_membershipDog.getActiveIndividualSegmentIds(Long.valueOf(id));

		QueryBuilder individualSegmentIdsQueryBuilder =
			QueryBuilders.termsQuery(
				"id", ListUtil.map(individualSegmentIds, String::valueOf));

		if (queryBuilder == null) {
			queryBuilder = individualSegmentIdsQueryBuilder;
		}
		else {
			queryBuilder = BoolQueryBuilderUtil.filter(
				individualSegmentIdsQueryBuilder
			).filter(
				queryBuilder
			);
		}

		return toCollectionGetResponse(
			"individual-segments",
			new IndividualsIndividualSegmentsEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker, expand, id, _membershipDog,
				_objectMapper),
			page, queryBuilder, size, sorts);
	}

	@GetMapping(params = "apply")
	public String getIndividualTransformations(
			@RequestParam String apply,
			@RequestParam(required = false) String channelId,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "false", required = false)
				boolean includeAnonymousUsers,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"individuals", page,
			_faroInfoIndividualDog.buildIndividualsQueryBuilder(
				channelId, filterString, includeAnonymousUsers),
			size, null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"individual-transformations");
	}

	private JSONObject _doFilterByChannelId(
		String channelId, JSONObject individualJSONObject) {

		JSONArray activitiesCountsJSONArray = individualJSONObject.optJSONArray(
			"activitiesCounts");

		if (activitiesCountsJSONArray != null) {
			Map<String, JSONObject> activitiesCounts = JSONUtil.toJSONObjectMap(
				individualJSONObject.getJSONArray("activitiesCounts"),
				"channelId");

			JSONObject activitiesCountJSONObject =
				activitiesCounts.getOrDefault(
					channelId, JSONUtil.put("activitiesCount", 0));

			individualJSONObject.put(
				"activitiesCount",
				activitiesCountJSONObject.getInt("activitiesCount"));

			individualJSONObject.remove("activitiesCounts");
		}

		JSONArray lastActivityDatesJSONArray =
			individualJSONObject.optJSONArray("lastActivityDates");

		if (lastActivityDatesJSONArray != null) {
			Map<String, JSONObject> individualCounts = JSONUtil.toJSONObjectMap(
				individualJSONObject.getJSONArray("lastActivityDates"),
				"channelId");

			JSONObject individualCountJSONObject =
				individualCounts.getOrDefault(
					channelId, JSONUtil.put("lastActivityDate", ""));

			individualJSONObject.put(
				"lastActivityDate",
				individualCountJSONObject.getString("lastActivityDate"));

			individualJSONObject.remove("lastActivityDates");
		}

		return individualJSONObject;
	}

	private JSONObject _filterByChannelId(
		String channelId, JSONObject responseJSONObject) {

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray individualsJSONArray = embeddedJSONObject.getJSONArray(
			"individuals");

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			_doFilterByChannelId(
				channelId, individualsJSONArray.getJSONObject(i));
		}

		return responseJSONObject;
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

}