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

import com.liferay.osb.asah.common.entity.Job;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface JobRepository extends Repository<Job, Long> {

	@Cacheable
	public long countByNameContainingIgnoreCase(String name);

	@Cacheable
	public List<Job> findByNameContainingIgnoreCase(
		String name, Pageable pageable);

	@Cacheable
	public Optional<Job> findFirstByName(String name);

}