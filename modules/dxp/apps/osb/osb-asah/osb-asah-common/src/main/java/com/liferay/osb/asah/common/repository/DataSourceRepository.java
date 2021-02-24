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

import com.liferay.osb.asah.common.model.DataSource;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface DataSourceRepository extends CrudRepository<DataSource, Long> {

	public boolean existsByFaroBackendSecuritySignature(
		String faroBackendSecuritySignature);

	public boolean existsByIdNotAndName(Long id, String name);

	public boolean existsByName(String name);

	public boolean existsByProviderType(
		@Param("providerType") String providerType);

	public List<DataSource> findAll(Pageable pageable);

	public List<DataSource> findByCredentialType(
		String credentialType, Pageable pageable);

	public List<DataSource> findByCredentialTypeAndProviderType(
		String credentialType, String providerType, Pageable pageable);

	public List<DataSource> findByProviderType(String providerType);

	public List<DataSource> findByProviderType(
		String providerType, Pageable pageable);

	public List<DataSource> findByProviderTypeAndStatus(
		String providerType, String status);

}