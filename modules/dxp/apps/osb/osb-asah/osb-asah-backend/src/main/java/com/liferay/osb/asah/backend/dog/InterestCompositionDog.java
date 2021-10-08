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

import com.liferay.osb.asah.backend.model.Composition;
import com.liferay.osb.asah.backend.model.CompositionResultBag;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class InterestCompositionDog {

	public CompositionResultBag getAccountCompositionResultBag(
		String accountId, boolean active, String channelId, String keywords,
		int size, Sort sort, int start) {

		Segment segment = _segmentDog.getSegment(
			"Account: " + accountId, "INACTIVE");

		return getIndividualSegmentCompositionResultBag(
			active, channelId, keywords, segment.getId(), size, sort, start);
	}

	public CompositionResultBag getIndividualCompositionResultBag(
		String channelId, String keywords, int size, Sort sort, int start) {

		return _getCompositionResultBag(
			_getIndividualIds(false, channelId, null), keywords,
			_getLastSuccessfulDate(), _getMinimumScore(), size, sort, start);
	}

	public CompositionResultBag getIndividualSegmentCompositionResultBag(
		boolean active, String channelId, String keywords, Long segmentId,
		int size, Sort sort, int start) {

		List<Long> individualIds = _getIndividualIds(
			active, channelId, segmentId);

		if (individualIds.isEmpty()) {
			return new CompositionResultBag(Collections.emptyList(), 0, 0);
		}

		return _getCompositionResultBag(
			individualIds, keywords, _getLastSuccessfulDate(),
			_getMinimumScore(), size, sort, start);
	}

	private boolean _calculateMaxCount(Sort sort, int start) {
		if (!StringUtils.equals(sort.getColumn(), "count") ||
			StringUtils.equals(sort.getType(), "ASC") || (start != 0)) {

			return true;
		}

		return false;
	}

	private CompositionResultBag _getCompositionResultBag(
		List<Long> individualIds, String keyword, Date recordedDate,
		Double score, int size, Sort sort, int start) {

		List<Composition> compositions = new ArrayList<>();

		List<Distribution> distributions =
			_interestRepository.getInterestDistributions(
				keyword, individualIds, "individual", recordedDate, score,
				PageRequest.of(start / size, size, sort));

		if (distributions.isEmpty()) {
			return new CompositionResultBag(compositions, 0, 0);
		}

		for (Distribution distribution : distributions) {
			List<Object> names = distribution.getValues();

			if (!names.isEmpty()) {
				compositions.add(
					new Composition(
						distribution.getCount(), String.valueOf(names.get(0))));
			}
		}

		if (_calculateMaxCount(sort, start)) {
			List<Distribution> maxList =
				_interestRepository.getInterestDistributions(
					keyword, individualIds, "individual", recordedDate, score,
					PageRequest.of(0, 1, Sort.desc("count")));

			Distribution maxDistribution = maxList.get(0);

			return new CompositionResultBag(
				maxDistribution.getCount(), compositions, compositions.size(),
				individualIds.size());
		}

		return new CompositionResultBag(
			compositions, compositions.size(), individualIds.size());
	}

	private List<Long> _getIndividualIds(
		boolean active, String channelId, Long segmentId) {

		List<Long> individualIds = new ArrayList<>();

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelIds", channelId);

		if (active) {
			LocalDateTime nowLocalDateTime = LocalDateTime.now();

			boolQueryBuilder.filter(
				QueryBuilders.nestedQuery(
					"lastActivityDates",
					QueryBuilders.rangeQuery(
						"lastActivityDates.lastActivityDate"
					).gt(
						DateUtil.toUTCString(nowLocalDateTime.minusDays(30))
					),
					ScoreMode.None));
		}

		if (!Objects.isNull(segmentId)) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "individualSegmentIds",
				String.valueOf(segmentId));
		}

		try {
			JSONArrayIterator.of(
				"individuals", _faroInfoElasticsearchInvoker,
				individualJSONObject -> {
					individualIds.add(individualJSONObject.getLong("id"));

					return null;
				}
			).setQueryBuilder(
				boolQueryBuilder
			).iterate();
		}
		catch (Exception exception) {
			throw new RuntimeException("Unable to get individuals", exception);
		}

		return individualIds;
	}

	private Date _getLastSuccessfulDate() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"IndividualInterestScoresNanite",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (asahMarker != null) {
			JSONObject asahMarkerContextJSONObject =
				asahMarker.getContextJSONObject();

			return DateUtil.toUTCDate(
				asahMarkerContextJSONObject.optString(
					"lastSuccessfulDay", null));
		}

		return null;
	}

	private Double _getMinimumScore() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"InterestThresholdScoreNanite",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (asahMarker == null) {
			return null;
		}

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		if (!asahMarkerContextJSONObject.has("score")) {
			return null;
		}

		return asahMarkerContextJSONObject.getDouble("score");
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private InterestRepository _interestRepository;

	@Autowired
	private SegmentDog _segmentDog;

}