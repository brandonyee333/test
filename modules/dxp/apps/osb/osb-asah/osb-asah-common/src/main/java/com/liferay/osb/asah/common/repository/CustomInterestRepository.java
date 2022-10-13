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

import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomInterestRepository {

	public long countByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, @Nullable Double score);

	public long countInterestDistributions(
		String keyword, List<String> ownerIds, String ownerType,
		Date recordedDate, Double score);

	public List<Interest> findByFilterStringAndScoreGreaterThanEqual(
		@Nullable FilterHelper filterHelper, @Nullable Double score,
		Pageable pageable);

	public List<Interest> findByNameAndOwnerIdAndRecordedDate(
		@Nullable String name, @Nullable String ownerId, Date recordedDate);

	public List<Interest> findByOwnerTypeAndRecordedDate(
		@Nullable Long interestId, @Nullable String ownerType,
		@Nullable Date recordedDate, int size);

	public List<String> findOwnerIdsByFilterStringAndOwnerId(
		FilterHelper filterHelper, @Nullable String ownerId);

	public List<Distribution> getInterestDistributions(
		@Nullable String keyword, @Nullable List<String> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score, Pageable pageable);

	public List<String> getTopNamesByOwnerIdAndOwnerType(
		String ownerId, String ownerType, int size);

	public List<Map<String, Object>> getTransformations(
		Date fromDate, @Nullable FilterHelper filterHelper, String period,
		Date toDate);

}