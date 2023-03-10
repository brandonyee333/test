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

import com.liferay.osb.asah.common.entity.BQSessionInterestScore;
import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.TimeRange;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Robson Pastor
 */
public interface CustomBQSessionInterestScoreRepository {

	public void deleteByRecordedDate(Date date);

	public CompositionResultBag getInterestCompositionResultBag(
		@Nullable Long channelId, Pageable pageable, TimeRange timeRange);

	public void insertAll(List<BQSessionInterestScore> bqSessionInterestScores);

}