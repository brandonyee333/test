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

import com.liferay.osb.asah.common.entity.BQIdentityInterestScore;
import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.IdentityInterestScore;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomBQIdentityInterestScoreRepository {

	public long countByFilterString(FilterHelper filterHelper);

	public long countByIndividualId(String individualId);

	public List<IdentityInterestScore> findByFilterString(
		@Nullable FilterHelper filterHelper, Pageable pageable);

	public List<BQIdentityInterestScore> findByIndividualId(
		String individualId, Pageable pageable);

	public List<BQIdentityInterestScore>
		findByIndividualIdAndKeywordAndRecordedDateBetween(
			String individualId, String keyword, Date recordedDate1,
			Date recordedDate2);

	public List<BQIdentityInterestScore> findByRecordedDate(
		@Nullable Long interestId, @Nullable Date recordedDate, int size);

	public Optional<IdentityInterestScore> findIdentityInterestScoreById(
		Long id);

	public List<String> findIndividualIdsByFilterStringAndIndividualId(
		FilterHelper filterHelper, @Nullable String individualId);

	public BQIdentityInterestScore getByIndividualIdAndKeywordAndRecordedDate(
		String individualId, String keyword, Date recordedDate);

	public CompositionResultBag getInterestCompositionResultBag(
		boolean active, @Nullable Long channelId, @Nullable String keywords,
		@Nullable Long segmentId, Pageable pageable);

	public List<String> getKeywords(
		@Nullable String keywords, Pageable pageable);

	public List<String> getTopKeywordsByIndividualId(
		String individualId, int size);

	public List<Map<String, Object>> getTransformations(
		Date fromDate, @Nullable FilterHelper filterHelper, String period,
		Date toDate);

}