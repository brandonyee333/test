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

import com.liferay.osb.asah.common.entity.RunLog;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Marcellus Tavares
 */
public interface RunLogRepository
	extends CustomRunLogRepository, PagingAndSortingRepository<RunLog, Long> {

	@Modifying
	public void deleteByDataSourceId(@Param("dataSourceId") Long dataSourceId);

	@Modifying
	@Query("UPDATE RunLog SET status = :status WHERE status = :previousStatus")
	public void updateStatus(
		@Param("previousStatus") String previousStatus,
		@Param("status") String status);

}