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
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.Pageable;
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
		String filterString, Boolean includeAnonymousUsers, Long segmentId) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getQueryBuilder(
				filterString, includeAnonymousUsers,
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

	public List<MembershipChange>
		searchLastByDateChangedPeriodAndIndividualSegmentId(
			Date dateChangedEnd, Date dateChangedStart,
			boolean includeAnonymousUsers, List<Long> individualSegmentIds) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						BoolQueryBuilder boolQueryBuilder =
							(BoolQueryBuilder)_getQueryBuilder(
								null, includeAnonymousUsers,
								individualSegmentIds);

						searchSourceBuilder.query(
							boolQueryBuilder.filter(
								QueryBuilders.rangeQuery(
									"dateChanged"
								).gte(
									DateUtil.toUTCString(dateChangedStart)
								).lte(
									DateUtil.toUTCString(dateChangedEnd)
								))
						).collapse(
							new CollapseBuilder("individualSegmentId")
						).sort(
							"dateChanged", SortOrder.DESC
						).sort(
							"individualsCount", SortOrder.DESC
						);
					})));
	}

	@Override
	public List<MembershipChange> searchMembershipChanges(
		String filterString, Boolean includeAnonymousUsers, Long segmentId,
		Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_getQueryBuilder(
								filterString, includeAnonymousUsers,
								Collections.singletonList(segmentId)));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public void updateIndividualDeletedByIndividualId(
		Boolean individualDeleted, Long individualId) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("individualId", individualId), true,
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
			QueryBuilders.termQuery("individualId", individualId), true,
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
		String filterString, Boolean includeAnonymousUsers,
		List<Long> segmentIds) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery("individualSegmentId", segmentIds));

		if (!BooleanUtils.toBoolean(includeAnonymousUsers)) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("individualEmail"));
		}

		if (StringUtils.isEmpty(filterString)) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(
			FilterStringToQueryBuilderConverter.convert(filterString));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}