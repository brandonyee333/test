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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.entity.BQIdentityInterestScore;
import com.liferay.osb.asah.common.model.IdentityInterestScore;
import com.liferay.osb.asah.common.postgresql.converter.helper.InterestFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQIdentityInterestScoreRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class BQIdentityInterestScoreDog {

	public Page<BQIdentityInterestScore> getBQIdentityInterestScorePage(
		String individualId, int size, int start) {

		return PageableExecutionUtils.getPage(
			_bqIdentityInterestScoreRepository.findByIndividualId(
				individualId, PageRequest.of(start / size, size)),
			PageRequest.of(start / size, size),
			() -> _bqIdentityInterestScoreRepository.countByIndividualId(
				individualId));
	}

	public List<BQIdentityInterestScore> getBQIdentityInterestScores(
		String individualId, String keyword, Date fromRecordedDate,
		Date toRecordedDate) {

		return _bqIdentityInterestScoreRepository.
			findByIndividualIdAndKeywordAndRecordedDateBetween(
				individualId, keyword, fromRecordedDate, toRecordedDate);
	}

	public IdentityInterestScore getIdentityInterestScore(Long id) {
		Optional<IdentityInterestScore> identityInterestScoreOptional =
			_bqIdentityInterestScoreRepository.findIdentityInterestScoreById(
				id);

		return identityInterestScoreOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no identity interest score with ID " + id));
	}

	public Page<IdentityInterestScore> getIdentityInterestScorePage(
		String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		FilterHelper filterHelper = new FilterHelper(
			null, filterString, new InterestFilterStringConverterHelper());

		return PageableExecutionUtils.getPage(
			_bqIdentityInterestScoreRepository.findByFilterString(
				filterHelper, pageRequest),
			pageRequest,
			() -> _bqIdentityInterestScoreRepository.countByFilterString(
				filterHelper));
	}

	public List<String> getIndividualIds(
		String filterString, String individualId) {

		return _bqIdentityInterestScoreRepository.
			findIndividualIdsByFilterStringAndIndividualId(
				new FilterHelper(
					null, filterString, _interestFilterStringConverterHelper),
				individualId);
	}

	public Page<String> getKeywordsPage(String keywords, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);

		return PageableExecutionUtils.getPage(
			_bqIdentityInterestScoreRepository.getKeywords(
				keywords, pageRequest),
			pageRequest,
			() -> _bqIdentityInterestScoreRepository.countKeywords(keywords));
	}

	public List<String> getTopKeywords(String individualId, int size) {
		return _bqIdentityInterestScoreRepository.getTopKeywordsByIndividualId(
			individualId, size);
	}

	public JSONArray getTransformations(
		String apply, String filterString, int page, int size) {

		String period = "day";

		if (apply != null) {
			Matcher matcher = _periodPattern.matcher(apply);

			if (!matcher.find()) {
				throw new IllegalArgumentException("Invalid apply: " + apply);
			}

			String fieldName = matcher.group("fieldName");

			if (!fieldName.equals("dateRecorded")) {
				throw new IllegalArgumentException(
					"Compute function not supported for " + fieldName);
			}

			period = matcher.group("period");
		}

		Date toDate = DateUtil.addDays(DateUtil.newDayDate(), -(page * size));

		Date fromDate = DateUtil.addDays(toDate, 1 - size);

		return new JSONArray(
			_bqIdentityInterestScoreRepository.getTransformations(
				fromDate,
				new FilterHelper(
					null, filterString,
					new InterestFilterStringConverterHelper()),
				period, toDate));
	}

	private static final InterestFilterStringConverterHelper
		_interestFilterStringConverterHelper =
			new InterestFilterStringConverterHelper();
	private static final Pattern _periodPattern = Pattern.compile(
		"compute\\((?<period>\\w+)\\((?<fieldName>\\w+)\\)\\)");

	@Autowired
	private BQIdentityInterestScoreRepository
		_bqIdentityInterestScoreRepository;

}