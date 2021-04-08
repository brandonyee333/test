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

import com.liferay.osb.asah.common.model.Suppression;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface SuppressionRepository
	extends CrudRepository<Suppression, Long> {

	public long countByEmailAddressContainingIgnoreCase(String emailAddress);

	public void deleteByEmailAddress(String emailAddress);

	public boolean existsByEmailAddress(String emailAddress);

	public boolean existsByEmailAddressHashed(String emailAddressHashed);

	public List<Suppression> findAll(Pageable pageable);

	public Optional<Suppression> findByEmailAddress(String emailAddress);

	public List<Suppression> findByEmailAddressContainingIgnoreCase(
		String emailAddress, Pageable pageable);

}