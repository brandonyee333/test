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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.backend.rest.response.EngagementsHistogramTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.embedded.EngagementsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Bowerman
 */
@RequestMapping("/engagements")
@RestController
public class EngagementsRestController extends BaseRestController {

	@GetMapping(params = {"accountId", "!apply"})
	public String getAccountEngagements(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(required = false) String accountId,
			@RequestParam(required = false) String expand,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("name", "Account: " + accountId)
				).filter(
					QueryBuilders.termQuery("status", "INACTIVE")
				));

		return getSegmentEngagements(
			expand, filterString, individualSegmentJSONObject.getString("id"),
			page, size, sorts);
	}

	@GetMapping("/{id}")
	public String getEngagement(
			@PathVariable String id,
			@RequestParam(required = false) String expand)
		throws Exception {

		if (StringUtils.isEmpty(expand)) {
			return toItemGetResponse("individuals", id);
		}

		return toItemGetResponse(
			"engagements",
			new EngagementsEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker, expand),
			id);
	}

	@Cacheable("getEngagements")
	@GetMapping(params = {"!accountId", "!apply", "!individualSegmentId"})
	public String getEngagements(
			@RequestParam(required = false) String expand,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"engagements",
			new EngagementsEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker, expand),
			page, FilterStringToQueryBuilderConverter.convert(filterString),
			size, sorts);
	}

	@Cacheable("getEngagementTransformations")
	@GetMapping(params = "apply")
	public String getEngagementTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(required = false) String individualSegmentId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		if (!StringUtils.isEmpty(individualSegmentId)) {
			QueryBuilder individualSegmentIdQueryBuilder =
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId);

			if (queryBuilder == null) {
				queryBuilder = individualSegmentIdQueryBuilder;
			}
			else {
				queryBuilder = BoolQueryBuilderUtil.filter(
					queryBuilder
				).filter(
					individualSegmentIdQueryBuilder
				);
			}
		}

		return toTransformationGetResponse(
			apply, "engagements", page, queryBuilder, size, "dateRecorded",
			new EngagementsHistogramTransformationJSONArrayFunction(
				true, individualSegmentId),
			"engagement-transformations");
	}

	@GetMapping(params = {"!apply", "individualSegmentId"})
	public String getSegmentEngagements(
			@RequestParam(required = false) String expand,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(required = false) String individualSegmentId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		QueryBuilder queryBuilder = QueryBuilders.termQuery(
			"individualSegmentIds", individualSegmentId);

		if (!StringUtils.isEmpty(filterString)) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				queryBuilder
			).filter(
				FilterStringToQueryBuilderConverter.convert(filterString)
			);
		}

		JSONObject responseJSONObject = new JSONObject(
			toCollectionGetResponse(
				"engagements",
				new EngagementsEmbeddedJSONObjectCreator(
					faroInfoElasticsearchInvoker, expand),
				page, queryBuilder, size, sorts));

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray engagementsJSONArray = embeddedJSONObject.getJSONArray(
			"engagements");

		_setCurrentMember(engagementsJSONArray, individualSegmentId);

		return responseJSONObject.toString();
	}

	private void _setCurrentMember(
		JSONArray engagementsJSONArray, String individualSegmentId) {

		if (engagementsJSONArray.length() == 0) {
			return;
		}

		Set<String> individualIds = JSONUtil.toStringSet(
			faroInfoElasticsearchInvoker.get(
				"memberships",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termsQuery(
						"individualId",
						new ArrayList<>(
							JSONUtil.toStringSet(
								engagementsJSONArray, "ownerId")))
				).filter(
					QueryBuilders.termQuery(
						"individualSegmentId", individualSegmentId)
				).filter(
					QueryBuilders.termQuery("status", "ACTIVE")
				)),
			"individualId");

		for (int i = 0; i < engagementsJSONArray.length(); i++) {
			JSONObject engagementJSONObject =
				engagementsJSONArray.getJSONObject(i);

			String individualId = engagementJSONObject.getString("ownerId");

			if (individualIds.contains(individualId)) {
				engagementJSONObject.put("currentMember", true);
			}
			else {
				engagementJSONObject.put("currentMember", false);
			}

			engagementJSONObject.put(
				"accountNames",
				FaroInfoIndividualUtil.getIndividualAccountNamesJSONArray(
					faroInfoElasticsearchInvoker, individualId));
		}
	}

}