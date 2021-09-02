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

import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface AccountRepository extends Repository<Account, Long> {

	@Cacheable
	public long countAccounts(
		@Nullable Set<String> accountPKs, @Nullable String filterString);

	@Cacheable
	public long countByIdAfter(Long accountId);

	@Cacheable
	public List<Account> findAllByDataSourceId(Long dataSourceId);

	@Cacheable
	public Optional<Account> findByAccountPK(String accountPK);

	@Cacheable
	public Optional<Account> findByAccountPKAndDataSourceId(
		String accountPK, Long dataSourceId);

	@Cacheable
	public List<Account> findByIdAfter(Long accountId, Pageable pageable);

	public List<Distribution> getAccountDistributions(
		@Nullable Long channelId, String fieldName, String fieldType,
		@Nullable String filterString, @Nullable Long individualSegmentId,
		Pageable pageable);

	@Cacheable
	public List<Transformation> getAccountTransformations(
		String apply, @Nullable Long channelId, @Nullable String filterString,
		Pageable pageable);

	@Cacheable
	public List<Account> searchAccounts(
		@Nullable Set<String> accountPKs, @Nullable Long channelId,
		@Nullable String filterString, Pageable pageable,
		@Nullable Sort segmentSort);

	@Cacheable
	public List<Account> searchAccounts(String filterString, Pageable pageable);

}