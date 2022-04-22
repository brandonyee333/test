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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface AccountRepository
	extends CustomAccountRepository, Repository<Account, Long> {

	@Cacheable
	public long countByCreateDateBetweenAndIdAfter(
		Date fromCreateDate, Date toCreateDate, Long id);

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
	public List<Account> findByCreateDateBetweenAndIdAfter(
		Date fromCreateDate, Date toCreateDate, Long id, Pageable pageable);

	@Cacheable
	public List<Account> findByIdAfter(Long accountId, Pageable pageable);

}