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

import com.liferay.osb.asah.common.entity.BlockedKeyword;

import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

/**
 * @author André Miranda
 */
public interface BlockedKeywordRepository
	extends Repository<BlockedKeyword, Long> {

	@Cacheable
	public long countByKeywordContainingIgnoreCase(String keyword);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByIdIn(@Param("ids") Set<Long> ids);

	@Cacheable
	public List<BlockedKeyword> findByKeywordContainingIgnoreCase(
		String keyword, Pageable pageable);

	@Cacheable
	public List<BlockedKeyword> findByKeywordIn(Set<String> keywords);

}