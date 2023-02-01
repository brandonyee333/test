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

import com.liferay.osb.asah.common.model.MetricType;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.List;

import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomBQIdentityRepository {

	public long countIndividuals(boolean includeAnonymousUsers);

	public List<String> getIdentityIds(String individualId);

	public long getIndividualsCount(
		@Nullable Boolean active, @Nullable Long channelId, LocalDate localDate,
		MetricType metricType, ZoneId zoneId);

	public List<Long> getIndividualsCounts(
		@Nullable Boolean active, @Nullable Long channelId,
		List<LocalDate> localDates, List<MetricType> metricTypes,
		ZoneId zoneId);

	public List<Long> searchSegmentBQIdentityIds(String filterString);

}