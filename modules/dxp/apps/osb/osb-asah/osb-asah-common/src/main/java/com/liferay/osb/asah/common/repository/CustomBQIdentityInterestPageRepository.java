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

import com.liferay.osb.asah.common.entity.BQIdentityInterestPage;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.lang.Nullable;

/**
 * @author Leslie Wong
 */
public interface CustomBQIdentityInterestPageRepository {

	public long countActivePagesTransformations(
		@Nullable Long channelId, @Nullable String filterString, String ownerId,
		String ownerType);

	public long countInactivePagesTransformations(
		@Nullable Long channelId, @Nullable String filterString, String ownerId,
		String ownerType);

	@Modifying
	public void deleteAll();

	public List<Map<String, Object>> getActivePagesTransformations(
		@Nullable Long channelId, @Nullable String filterString, String ownerId,
		String ownerType, Pageable pageable);

	public List<BQIdentityInterestPage> getBQIdentityInterestPages(
		String keyword);

	public List<Map<String, Object>> getInactivePagesTransformations(
		@Nullable Long channelId, @Nullable String filterString, String ownerId,
		String ownerType, Pageable pageable);

	public void insertAll(List<BQIdentityInterestPage> bqIdentityInterestPages);

}