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

import com.liferay.osb.asah.common.entity.BQFieldMapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

/**
 * @author Robson Pastor
 */
public interface CustomBQFieldMappingRepository {

	public long count();

	public long countByFilterString(String filterString);

	public Optional<BQFieldMapping> findByFieldName(String id);

	public List<BQFieldMapping> findByFieldNameIn(
		Collection<String> fieldNames);

	public List<BQFieldMapping> searchByFilterString(
		String filterString, Pageable pageable);

}