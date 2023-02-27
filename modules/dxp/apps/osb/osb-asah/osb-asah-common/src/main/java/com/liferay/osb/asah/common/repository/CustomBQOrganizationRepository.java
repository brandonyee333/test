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

import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomBQOrganizationRepository {

	public long count();

	public long countByName(@Nullable String name);

	public void deleteById(String id);

	public List<BQOrganization> findAll();

	public List<BQOrganization> findByDataSourceIdAndOrganizationId(
		Long dataSourceId, Long organizationId);

	public List<BQOrganization> findByDataSourceIdAndOrganizationIdIn(
		Long dataSourceId, Collection<Long> organizationIds);

	public Optional<BQOrganization> findById(String bqOrganizationId);

	@Cacheable
	public List<BQOrganization> findByName(
		@Nullable String name, Pageable pageable);

	public BQOrganization insert(BQOrganization bqOrganization);

	@Cacheable
	public List<BQOrganization> searchBQOrganizations(
		FilterHelper filterHelper, Pageable pageable);

}