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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchMembershipChangeRepositoryImpl
	extends BaseElasticsearchRepository<MembershipChange, Long>
	implements MembershipChangeRepository {

	@Override
	public long countMembershipChanges(
		FilterHelper filterHelper, Boolean includeAnonymousUsers,
		Long segmentId) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getQueryBuilder(
				filterHelper, includeAnonymousUsers,
				Collections.singletonList(segmentId)));
	}

	@Override
	public void deleteByIndividualSegmentId(Long individualSegmentId) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
			QueryBuilders.termQuery(
				"individualSegmentId", String.valueOf(individualSegmentId)));
	}

	@Override
	public Optional<MembershipChange> findByIndividualId(Long individualId) {
		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			getCollectionName(),
			QueryBuilders.termQuery(
				"individualId", String.valueOf(individualId)));

		return Optional.ofNullable(
			jsonObject
		).map(
			this::toEntity
		);
	}

	@Override
	public List<MembershipChange>
		searchLastByModifiedDateAndIndividualSegmentId(
			@Nullable Date fromModifiedDate, boolean includeAnonymousUsers,
			List<Long> individualSegmentIds, Date toModifiedDate) {

		BoolQueryBuilder boolQueryBuilder = (BoolQueryBuilder)_getQueryBuilder(
			FilterHelper.EMPTY, includeAnonymousUsers, individualSegmentIds);

		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
			"dateChanged"
		).lte(
			DateUtil.toUTCString(toModifiedDate)
		);

		if (fromModifiedDate != null) {
			rangeQueryBuilder = rangeQueryBuilder.gte(
				DateUtil.toUTCString(fromModifiedDate));
		}

		return toList(
			_faroInfoElasticsearchInvoker.get(
				new CollapseBuilder("individualSegmentId"), getCollectionName(),
				Arrays.asList(
					SortBuilderUtil.fieldSort("dateChanged", SortOrder.DESC),
					SortBuilderUtil.fieldSort(
						"individualsCount", SortOrder.DESC)),
				boolQueryBuilder.filter(rangeQueryBuilder)));
	}

	@Override
	public List<MembershipChange> searchMembershipChanges(
		FilterHelper filterHelper, Boolean includeAnonymousUsers,
		Long segmentId, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				(int)pageable.getOffset(),
				_getQueryBuilder(
					filterHelper, includeAnonymousUsers,
					Collections.singletonList(segmentId)),
				pageable.getPageSize()));
	}

	@Override
	public void updateIndividualDeletedByIndividualId(
		Boolean individualDeleted, Long individualId) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery(
				"individualId", String.valueOf(individualId)),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualDeleted = params.individualDeleted",
				Collections.singletonMap(
					"individualDeleted", individualDeleted)),
			getCollectionName());
	}

	@Override
	public void updateIndividualDeletedByIndividualIdIn(
		Boolean individualDeleted, List<Long> individualIds) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termsQuery(
				"individualId", ListUtil.map(individualIds, String::valueOf)),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualDeleted = params.individualDeleted",
				Collections.singletonMap(
					"individualDeleted", individualDeleted)),
			getCollectionName());
	}

	@Override
	public void updateIndividualNameByIndividualId(
		Long individualId, String individualName) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery(
				"individualId", String.valueOf(individualId)),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualName = params.individualName",
				Collections.singletonMap("individualName", individualName)),
			getCollectionName());
	}

	@Override
	protected String getCollectionName() {
		return "membership-changes";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _getQueryBuilder(
		FilterHelper filterHelper, Boolean includeAnonymousUsers,
		List<Long> segmentIds) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery("individualSegmentId", segmentIds));

		if (!BooleanUtils.toBoolean(includeAnonymousUsers)) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("individualEmail"));
		}

		if (StringUtils.isEmpty(filterHelper.getFilterString())) {
			return boolQueryBuilder;
		}

		return filterHelper.getQueryBuilder();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}