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

import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface OrganizationRepository extends Repository<Organization, Long> {

	@Cacheable
	public long countByName(@Nullable String name);

	@Cacheable
	public Organization findByDataSourceIdAndOrganizationPK(
		Long dataSourceId, Long organizationPK);

	@Cacheable
	public List<Organization> findByDataSourceIdAndOrganizationPKIn(
		Long dataSourceId, Collection<Long> organizationPKs);

	@Cacheable
	public List<Organization> findByName(
		@Nullable String name, Pageable pageable);

	public List<Transformation> getOrganizationTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<Organization> searchOrganizations(
		FilterHelper filterHelper, Pageable pageable);

}