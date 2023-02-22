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

import java.util.Date;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

/**
 * @author Robson Pastor
 */
public interface BQIdentityInterestScoreRepository
	extends BigQueryRepository<BQIdentityInterestScore, Long>,
			CustomBQIdentityInterestScoreRepository {

	@Modifying
	public void deleteByKeywordAndRecordedDateGreaterThanEqual(
		@Param("keyword") String keyword,
		@Param("recordedDate") Date recordedDate);

	@Modifying
	public void deleteByRecordedDate(@Param("recordedDate") Date recordedDate);

	@Modifying
	public void deleteByRecordedDateLessThanEqual(
		@Param("recordedDate") Date recordedDate);

}