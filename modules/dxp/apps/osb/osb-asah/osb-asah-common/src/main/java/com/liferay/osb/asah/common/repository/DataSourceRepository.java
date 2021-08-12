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

import com.liferay.osb.asah.common.entity.DataSource;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * @author Inácio Nery
 */
@Primary
public interface DataSourceRepository
	extends OSBAsahRepository<DataSource, Long> {

	@Cacheable
	public long countDataSources(String filterString);

	@Cacheable
	public boolean existsByFaroBackendSecuritySignature(
		String faroBackendSecuritySignature);

	@Cacheable
	public boolean existsByIdNotAndName(Long id, String name);

	@Cacheable
	public boolean existsByName(String name);

	@Cacheable
	public boolean existsByProviderType(
		@Param("providerType") String providerType);

	@Cacheable
	public List<DataSource> findAll(Pageable pageable);

	@Cacheable
	public List<DataSource> findByCredentialType(
		String credentialType, Pageable pageable);

	@Cacheable
	public List<DataSource> findByCredentialTypeAndProviderType(
		String credentialType, String providerType, Pageable pageable);

	@Cacheable
	public List<DataSource> findByProviderType(String providerType);

	@Cacheable
	public List<DataSource> findByProviderType(
		String providerType, Pageable pageable);

	@Cacheable
	public List<DataSource> findByProviderTypeAndStatus(
		String providerType, String status);

	@Cacheable
	public List<DataSource> searchDataSources(
		String filterString, Pageable pageable);

}