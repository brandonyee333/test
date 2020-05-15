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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoMembershipDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

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
	public void run(JSONObject contextJSONObject) throws Exception {
		String dateModified = contextJSONObject.getString("dateModified");

		JSONObject individualSegmentJSONObject =
			contextJSONObject.optJSONObject("individualSegmentJSONObject");

		if (individualSegmentJSONObject != null) {
			_updateDynamicMembershipsForIndividualSegment(
				dateModified, individualSegmentJSONObject);

			return;
		}

		JSONObject individualJSONObject = contextJSONObject.optJSONObject(
			"individualJSONObject");

		if (individualJSONObject != null) {
			_updateDynamicMembershipsForIndividual(
				dateModified, contextJSONObject.optString("filter", null),
				individualJSONObject.getString("id"));

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
				addQueryBuilder, dateModified, removeQueryBuilder);

			return;
		}

		String addFilter = contextJSONObject.optString("addFilter", null);
		String removeFilter = contextJSONObject.optString("removeFilter", null);

		if (Objects.equals(addFilter, removeFilter)) {
			_updateDynamicMembershipsForIndividualSegments(
				dateModified, addFilter);
		}
		else {
			_updateDynamicMembershipsForIndividualSegments(
				FilterStringToQueryBuilderConverter.convert(addFilter),
				dateModified,
				FilterStringToQueryBuilderConverter.convert(removeFilter));
		}
	}

	private void _updateDynamicMembershipsForIndividual(
			String dateModified, String filterString, String individualId)
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
			"dateCreated", dateModified
		).put(
			"dateModified", dateModified
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
						baseMembershipJSONObject, dateModified,
						individualJSONObject, individualSegmentJSONObject);
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
			String dateModified, JSONObject individualSegmentJSONObject)
		throws Exception {

		String individualSegmentId = individualSegmentJSONObject.getString(
			"id");

		if (!faroInfoElasticsearchInvoker.exists(
				"individual-segments", individualSegmentId)) {

			return;
		}

		_faroInfoMembershipDog.updateDynamicMemberships(
			individualSegmentJSONObject, dateModified);

		_faroInfoIndividualSegmentDog.updateIndividualSegment(
			individualSegmentId, JSONUtil.put("state", "READY"));
	}

	private void _updateDynamicMembershipsForIndividualSegments(
			QueryBuilder addQueryBuilder, String dateModified,
			QueryBuilder removeQueryBuilder)
		throws Exception {

		if (addQueryBuilder != null) {
			JSONArrayIterator.of(
				"individual-segments", faroInfoElasticsearchInvoker,
				individualSegmentJSONObject -> {
					try {
						_faroInfoMembershipDog.updateDynamicAddMemberships(
							true, individualSegmentJSONObject, dateModified);
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
						_faroInfoMembershipDog.updateDynamicRemoveMemberships(
							individualSegmentJSONObject, dateModified);
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
			String dateModified, String filterString)
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
						dateModified, individualSegmentJSONObject);
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
			JSONObject baseMembershipJSONObject, String dateModified,
			JSONObject individualJSONObject,
			JSONObject individualSegmentJSONObject)
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
			_faroInfoMembershipDog.addMembership(
				baseMembershipJSONObject.put(
					"individualSegmentId",
					individualSegmentJSONObject.getString("id")));
		}
		else if (!newMember && oldMember) {
			_faroInfoMembershipDog.deactivateMembership(
				dateModified, individualJSONObject.getString("id"),
				individualSegmentJSONObject.getString("id"));
		}
	}

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private FaroInfoMembershipDog _faroInfoMembershipDog;

}