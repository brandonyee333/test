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

import com.liferay.osb.asah.common.entity.BQMembershipChange;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

/**
 * @author Marcellus Tavares
 */
public interface BQMembershipChangeRepository
	extends CustomBQMembershipChangeRepository,
			Repository<BQMembershipChange, Long> {

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByIndividualSegmentIdIn(
		@Param("individualSegmentIds") List<Long> individualSegmentIds);

	public BQMembershipChange findByIndividualSegmentId(
		long individualSegmentId);

}