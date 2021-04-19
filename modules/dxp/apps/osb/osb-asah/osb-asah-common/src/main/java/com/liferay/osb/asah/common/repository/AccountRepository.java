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

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	public long countAccounts(@Nullable String filterString);

	public List<Account> findAllByDataSourceId(Long dataSourceId);

	public Optional<Account> findByAccountPK(String accountPK);

	public Optional<Account> findByAccountPKAndDataSourceId(
		String accountPK, Long dataSourceId);

	public List<Distribution> getAccountDistributions(
		@Nullable Long channelId, String fieldName, String fieldType,
		@Nullable String filterString, @Nullable Long individualSegmentId,
		Pageable pageable);

	public List<Account> searchAccounts(
		@Nullable Long channelId, @Nullable String filterString,
		Pageable pageable, @Nullable Sort segmentSort);

}