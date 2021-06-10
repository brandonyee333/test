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

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface RunLogRepository extends CrudRepository<RunLog, Long> {

	@Modifying
	public void deleteByDataSourceId(@Param("dataSourceId") Long dataSourceId);

	public Optional<RunLog>
		findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
			@Nullable Long dataSourceId, String naniteClassName,
			@Nullable String status);

}