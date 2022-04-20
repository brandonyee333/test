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

import com.liferay.osb.asah.common.entity.Individual;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Rachael Koestartyo
 */
@Primary
public interface IndividualRepository
	extends CustomIndividualRepository,
			PagingAndSortingRepository<Individual, Long> {

	public long countByCreateDateBetweenAndIdAfter(
		Date fromCreateDate, Date toCreateDate, Long id);

	public long countByIdAfter(Long id);

	public long countKnownIndividualsByAnySegmentIds(
		@Param("segmentId") Long segmentId);

	public long countKnownIndividualsByIdIn(@Param("ids") List<Long> ids);

	@Modifying
	public void deleteByIdIn(@Param("ids") List<Long> ids);

	public List<Individual> findByCreateDateBetweenAndIdAfter(
		Date fromCreateDate, Date toCreateDate, Long id, Pageable pageable);

	public Individual findByEmailAddressHashed(String emailAddressHashed);

	public List<Individual> findByIdAfter(Long id, Pageable pageable);

	public List<Long> findIdsByAnySegmentIds(
		@Param("segmentId") Long segmentId);

	@Modifying
	public void removeSegmentId(@Param("segmentId") Long segmentId);

	@Modifying
	public void removeSegmentIdByIdIn(
		@Param("ids") Set<Long> ids, @Param("segmentId") Long segmentId);

	@Modifying
	public void removeSegmentIds(@Param("segmentIds") List<Long> segmentIds);

}