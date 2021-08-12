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

import com.liferay.osb.asah.common.entity.Suppression;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface SuppressionRepository
	extends OSBAsahRepository<Suppression, Long> {

	@Cacheable
	public long countByEmailAddressContainingIgnoreCase(String emailAddress);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByEmailAddress(String emailAddress);

	@Cacheable
	public boolean existsByEmailAddress(String emailAddress);

	@Cacheable
	public boolean existsByEmailAddressHashed(String emailAddressHashed);

	@Cacheable
	public List<Suppression> findAll(Pageable pageable);

	@Cacheable
	public Optional<Suppression> findByEmailAddress(String emailAddress);

	@Cacheable
	public List<Suppression> findByEmailAddressContainingIgnoreCase(
		String emailAddress, Pageable pageable);

}