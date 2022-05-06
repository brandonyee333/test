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

import com.liferay.osb.asah.common.entity.BQUser;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcos Martins
 */
@Repository
public interface CustomBQUserRepository {

	public long countByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords);

	public List<BQUser> findByFields(
		Map<String, Object> fields, Pageable pageable);

	public List<BQUser> searchByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords, Pageable pageable);

}