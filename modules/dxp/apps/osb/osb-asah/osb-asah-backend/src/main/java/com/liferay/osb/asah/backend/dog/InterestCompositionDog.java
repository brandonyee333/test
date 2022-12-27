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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.BQIndividualDog;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.model.Composition;
import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQIndividualInterestScoreRepository;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class InterestCompositionDog {

	public CompositionResultBag getIndividualCompositionResultBag(
		Long channelId, String keywords, int size, Sort sort, int start) {

		return _bqIndividualInterestScoreRepository.
			getInterestCompositionResultBag(
				channelId, keywords, PageRequest.of(start / size, size, sort));
	}

	public CompositionResultBag getIndividualSegmentCompositionResultBag(
		boolean active, String channelId, String keywords, Long segmentId,
		int size, Sort sort, int start) {

		List<String> individualIds = _getIndividualIds(
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
		List<String> individualIds, String keyword, Date recordedDate,
		Double score, int size, Sort sort, int start) {

		List<Composition> compositions = new ArrayList<>();

		List<Distribution> distributions =
			_bqIndividualInterestScoreRepository.getInterestDistributions(
				individualIds, keyword, recordedDate, score,
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

		long count =
			_bqIndividualInterestScoreRepository.countInterestDistributions(
				individualIds, keyword, recordedDate, score);

		if (_calculateMaxCount(sort, start)) {
			List<Distribution> distributionsList =
				_bqIndividualInterestScoreRepository.getInterestDistributions(
					individualIds, keyword, recordedDate, score,
					PageRequest.of(0, 1, Sort.desc("count")));

			Distribution distribution = distributionsList.get(0);

			return new CompositionResultBag(
				distribution.getCount(), compositions, count,
				individualIds.size());
		}

		return new CompositionResultBag(
			compositions, count, individualIds.size());
	}

	private List<String> _getIndividualIds(
		boolean active, String channelId, Long segmentId) {

		Date date = null;

		if (active) {
			LocalDateTime newDayLocalDateTime = DateUtil.newDayLocalDateTime(
				TimeZoneDogUtil.getZoneId());

			date = DateUtil.toUTCDate(newDayLocalDateTime.minusDays(30));
		}

		return _bqIndividualDog.getBQIndividualIds(
			NumberUtils.createLong(channelId), date, segmentId);
	}

	private Date _getLastSuccessfulDate() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"IndividualInterestScoresNanite");

		if (asahMarker != null) {
			JSONObject asahMarkerContextJSONObject =
				asahMarker.getContextJSONObject();

			if (asahMarkerContextJSONObject.has("lastSuccessfulDay")) {
				return DateUtil.toUTCDate(
					asahMarkerContextJSONObject.getString("lastSuccessfulDay"));
			}
		}

		return null;
	}

	private Double _getMinimumScore() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"InterestThresholdScoreNanite");

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

	@Autowired
	private BQIndividualDog _bqIndividualDog;

	@Autowired
	private BQIndividualInterestScoreRepository
		_bqIndividualInterestScoreRepository;

}