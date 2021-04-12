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

import com.liferay.osb.asah.common.model.MembershipChange;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface MembershipChangeRepository
	extends CrudRepository<MembershipChange, Long> {

	@Modifying
	public void deleteByIndividualSegmentId(
		@Param("individualSegmentId") Long individualSegmentId);

	public Optional<MembershipChange> findByIndividualId(Long individualId);

	@Modifying
	public void updateIndividualDeletedByIndividualId(
		@Param("individualDeleted") Boolean individualDeleted,
		@Param("individualId") Long individualId);

	@Modifying
	public void updateIndividualNameByIndividualId(
		@Param("individualId") Long individualId,
		@Param("individualName") String individualName);

}