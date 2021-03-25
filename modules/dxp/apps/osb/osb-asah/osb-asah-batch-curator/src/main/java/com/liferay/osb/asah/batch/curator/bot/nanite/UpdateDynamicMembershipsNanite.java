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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.common.model.Segment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class UpdateDynamicMembershipsNanite extends BaseNanite {

	@Override
	public void logCompleted(
		String asahTaskId, JSONObject contextJSONObject, long duration) {
	}

	@Override
	public void logStart(JSONObject contextJSONObject) {
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		Date modifiedDate = DateUtil.toUTCDate(
			contextJSONObject.getString("dateModified"));

		JSONObject individualSegmentJSONObject =
			contextJSONObject.optJSONObject("individualSegmentJSONObject");

		if (individualSegmentJSONObject != null) {
			_updateDynamicMembershipsForIndividualSegment(
				individualSegmentJSONObject, modifiedDate);

			return;
		}

		JSONObject individualJSONObject = contextJSONObject.optJSONObject(
			"individualJSONObject");

		if (individualJSONObject != null) {
			_updateDynamicMembershipsForIndividual(
				contextJSONObject.optString("filter", null),
				individualJSONObject.getString("id"), modifiedDate);

			return;
		}

		QueryBuilder addQueryBuilder = null;

		String addQueryBuilderString = contextJSONObject.optString(
			"addQueryBuilder");

		if (!StringUtils.isEmpty(addQueryBuilderString)) {
			addQueryBuilder = new WrapperQueryBuilder(addQueryBuilderString);
		}

		QueryBuilder removeQueryBuilder = null;

		String removeQueryBuilderString = contextJSONObject.optString(
			"removeQueryBuilder");

		if (!StringUtils.isEmpty(removeQueryBuilderString)) {
			removeQueryBuilder = new WrapperQueryBuilder(
				removeQueryBuilderString);
		}

		if ((addQueryBuilder != null) || (removeQueryBuilder != null)) {
			_updateDynamicMembershipsForIndividualSegments(
				addQueryBuilder, modifiedDate, removeQueryBuilder);

			return;
		}

		String addFilter = contextJSONObject.optString("addFilter", null);
		String removeFilter = contextJSONObject.optString("removeFilter", null);

		if (Objects.equals(addFilter, removeFilter)) {
			_updateDynamicMembershipsForIndividualSegments(
				addFilter, modifiedDate);
		}
		else {
			_updateDynamicMembershipsForIndividualSegments(
				FilterStringToQueryBuilderConverter.convert(addFilter),
				modifiedDate,
				FilterStringToQueryBuilderConverter.convert(removeFilter));
		}
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(UpdateDynamicMembershipsNanite.class);
	}

	private void _updateDynamicMembershipsForIndividual(
			String filterString, String individualId, Date modifiedDate)
		throws Exception {

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals", individualId);

		if (individualJSONObject == null) {
			return;
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("segmentType", "DYNAMIC"));

		if (filterString != null) {
			boolQueryBuilder = boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(filterString));
		}

		JSONArray dataSourceAccountPKsJSONArray =
			individualJSONObject.optJSONArray("dataSourceAccountPKs");

		if (dataSourceAccountPKsJSONArray.length() == 0) {
			boolQueryBuilder.mustNot(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.prefixQuery(
						"filter", "((dataSourceAccountPKs/accountPKs eq '")
				).filter(
					QueryBuilders.prefixQuery("name", "Account: ")
				).filter(
					QueryBuilders.termQuery("status", "INACTIVE")
				));
		}
		else {
			List<String> filterStrings = new ArrayList<>();

			for (int i = 0; i < dataSourceAccountPKsJSONArray.length(); i++) {
				JSONObject accountPKJSONObject =
					dataSourceAccountPKsJSONArray.getJSONObject(i);

				JSONArray accountPKsJSONArray =
					accountPKJSONObject.getJSONArray("accountPKs");

				for (int j = 0; j < accountPKsJSONArray.length(); j++) {
					filterStrings.add(
						"((dataSourceAccountPKs/accountPKs eq '" +
							accountPKsJSONArray.getString(j) + "'))");
				}
			}

			boolQueryBuilder = BoolQueryBuilderUtil.should(
				BoolQueryBuilderUtil.filter(
					boolQueryBuilder
				).filter(
					QueryBuilders.termsQuery("status", "ACTIVE")
				)
			).should(
				BoolQueryBuilderUtil.filter(
					boolQueryBuilder
				).filter(
					QueryBuilders.termsQuery("filter", filterStrings)
				).filter(
					QueryBuilders.termsQuery("status", "INACTIVE")
				)
			);
		}

		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		if ((demographicsJSONObject == null) ||
			!demographicsJSONObject.has("email")) {

			boolQueryBuilder.mustNot(
				QueryBuilders.termQuery("includeAnonymousUsers", false));
		}

		JSONArray individualSegmentIdsJSONArray =
			individualJSONObject.optJSONArray("individualSegmentIds");

		if ((individualSegmentIdsJSONArray != null) &&
			(individualSegmentIdsJSONArray.length() > 0)) {

			boolQueryBuilder.should(
				QueryBuilders.termsQuery(
					"id",
					JSONUtil.toStringList(individualSegmentIdsJSONArray)));
		}

		JSONObject baseMembershipJSONObject = JSONUtil.put(
			"dateCreated", DateUtil.toUTCString(modifiedDate)
		).put(
			"dateModified", DateUtil.toUTCString(modifiedDate)
		).put(
			"individualId", individualId
		).put(
			"status", "ACTIVE"
		);

		JSONArrayIterator.of(
			"individual-segments", faroInfoElasticsearchInvoker,
			individualSegmentJSONObject -> {
				try {
					_updateMembershipForIndividual(
						baseMembershipJSONObject, individualJSONObject,
						individualSegmentJSONObject, modifiedDate);
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setQueryBuilder(
			boolQueryBuilder
		).iterate();
	}

	private void _updateDynamicMembershipsForIndividualSegment(
			JSONObject individualSegmentJSONObject, Date modifiedDate)
		throws Exception {

		Long segmentId = individualSegmentJSONObject.getLong("id");

		if (!_segmentDog.existsSegment(segmentId)) {
			return;
		}

		_faroInfoIndividualDog.updateDynamicMemberships(
			individualSegmentJSONObject, modifiedDate);

		Segment segment = new Segment();

		segment.setState("READY");

		_segmentDog.updateSegment(segment, segmentId);
	}

	private void _updateDynamicMembershipsForIndividualSegments(
			QueryBuilder addQueryBuilder, Date modifiedDate,
			QueryBuilder removeQueryBuilder)
		throws Exception {

		if (addQueryBuilder != null) {
			JSONArrayIterator.of(
				"individual-segments", faroInfoElasticsearchInvoker,
				individualSegmentJSONObject -> {
					try {
						_faroInfoIndividualDog.updateDynamicAddMemberships(
							true, individualSegmentJSONObject, modifiedDate);
					}
					catch (Exception e) {
						return e;
					}

					return null;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					addQueryBuilder
				).filter(
					QueryBuilders.termQuery("segmentType", "DYNAMIC")
				)
			).iterate();
		}

		if (removeQueryBuilder != null) {
			JSONArrayIterator.of(
				"individual-segments", faroInfoElasticsearchInvoker,
				individualSegmentJSONObject -> {
					try {
						_faroInfoIndividualDog.updateDynamicRemoveMemberships(
							individualSegmentJSONObject, modifiedDate);
					}
					catch (Exception e) {
						return e;
					}

					return null;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					removeQueryBuilder
				).filter(
					QueryBuilders.termQuery("segmentType", "DYNAMIC")
				)
			).iterate();
		}
	}

	private void _updateDynamicMembershipsForIndividualSegments(
			String filterString, Date modifiedDate)
		throws Exception {

		QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

		if (filterString != null) {
			queryBuilder = FilterStringToQueryBuilderConverter.convert(
				filterString);
		}

		JSONArrayIterator.of(
			"individual-segments", faroInfoElasticsearchInvoker,
			individualSegmentJSONObject -> {
				try {
					_updateDynamicMembershipsForIndividualSegment(
						individualSegmentJSONObject, modifiedDate);
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				queryBuilder
			).filter(
				QueryBuilders.termQuery("segmentType", "DYNAMIC")
			)
		).iterate();
	}

	private void _updateMembershipForIndividual(
			JSONObject baseMembershipJSONObject,
			JSONObject individualJSONObject,
			JSONObject individualSegmentJSONObject, Date modifiedDate)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			FilterStringToQueryBuilderConverter.convert(
				individualSegmentJSONObject.getString("filter"),
				_faroInfoIndividualsFilterStringConverterHelper)
		).filter(
			QueryBuilders.termQuery("id", individualJSONObject.getString("id"))
		);

		String channelId = individualSegmentJSONObject.optString("channelId");

		if (!StringUtils.isEmpty(channelId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("channelIds", channelId));
		}

		boolean newMember = faroInfoElasticsearchInvoker.exists(
			"individuals", boolQueryBuilder);

		boolean oldMember = JSONUtil.hasValue(
			individualJSONObject.getJSONArray("individualSegmentIds"),
			individualSegmentJSONObject.getString("id"));

		if (newMember && !oldMember) {
			_membershipDog.addMembership(
				_objectMapper.convertValue(
					baseMembershipJSONObject.put(
						"individualSegmentId",
						individualSegmentJSONObject.getString("id")),
					Membership.class));
		}
		else if (!newMember && oldMember) {
			_membershipDog.deactivateMembership(
				modifiedDate, individualJSONObject.getLong("id"),
				individualSegmentJSONObject.getLong("id"));
		}
	}

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}