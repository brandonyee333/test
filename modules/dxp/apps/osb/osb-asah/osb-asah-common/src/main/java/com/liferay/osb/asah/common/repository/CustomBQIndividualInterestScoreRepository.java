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

package com.liferay.osb.asah.common.repository;

import com.liferay.osb.asah.common.entity.BQIndividualInterestScore;
import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.IndividualInterestScore;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomBQIndividualInterestScoreRepository {

	public long countByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, @Nullable Double score);

	public long countByIndividualId(String individualId);

	public long countInterestDistributions(
		List<String> individualIds, String keyword, Date recordedDate,
		Double score);

	@Modifying
	public void deleteBySegmentIdIn(List<Long> segmentIds);

	public List<IndividualInterestScore>
		findByFilterStringAndScoreGreaterThanEqual(
			@Nullable FilterHelper filterHelper, @Nullable Double score,
			Pageable pageable);

	public List<BQIndividualInterestScore> findByIndividualId(
		String individualId, Pageable pageable);

	public List<BQIndividualInterestScore>
		findByIndividualIdAndKeywordAndRecordedDateBetween(
			String individualId, String keyword, Date recordedDate1,
			Date recordedDate2);

	public List<BQIndividualInterestScore> findByRecordedDate(
		@Nullable Long interestId, @Nullable Date recordedDate, int size);

	public List<String> findIndividualIdsByFilterStringAndIndividualId(
		FilterHelper filterHelper, @Nullable String individualId);

	public Optional<IndividualInterestScore> findIndividualInterestScoreById(
		Long id);

	public BQIndividualInterestScore getByIndividualIdAndKeywordAndRecordedDate(
		String individualId, String keyword, Date recordedDate);

	public List<Distribution> getInterestDistributions(
		@Nullable List<String> individualIds, @Nullable String keyword,
		@Nullable Date recordedDate, @Nullable Double score, Pageable pageable);

	public List<String> getTopKeywordsByIndividualId(
		String individualId, int size);

	public List<Map<String, Object>> getTransformations(
		Date fromDate, @Nullable FilterHelper filterHelper, String period,
		Date toDate);

}