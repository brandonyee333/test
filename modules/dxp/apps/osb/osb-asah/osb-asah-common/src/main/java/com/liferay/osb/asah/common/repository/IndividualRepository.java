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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface IndividualRepository extends CrudRepository<Individual, Long> {

	public boolean existsByChannelIdAndFilterAndId(
		Long channelId, String filter, Long individualId);

	public boolean existsByFilterAndId(String filter, Long individualId);

	public List<Individual> findByAnySegmentIds(
		@Param("segmentId") Long segmentId);

	public Optional<Individual>
		findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
			Long associatedId, Long dataSourceId, String fieldName,
			String individualPK);

	public List<Individual> findByDataSourceId(
		Long dataSourceId, Pageable pageable);

	public Optional<Individual> findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK);

	public Optional<Individual> findByEmailAddress(String emailAddress);

	public Optional<Individual> findByEmailAddressHashed(
		String emailAddressHashed);

	public Optional<Individual> findByEmailAddressOrEmailAddressHashed(
		@Nullable String emailAddress, String emailAddressHashed);

	public List<Individual.ActivitiesCount> getActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId);

	public Map<Long, Long> getIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId);

	public List<Individual> searchIndividuals(
		String filterString, Pageable pageable);

	@Modifying
	public void updateAssociatedIds(
		@Param("fieldName") String fieldName, @Param("ids") Set<Long> ids,
		@Param("individualId") Long individualId);

}