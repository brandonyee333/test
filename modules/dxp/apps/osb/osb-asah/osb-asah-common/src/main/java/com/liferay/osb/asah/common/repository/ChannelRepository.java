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

import com.liferay.osb.asah.common.entity.Channel;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Inácio Nery
 */
@Primary
public interface ChannelRepository extends CrudRepository<Channel, Long> {

	public long countByNameContainingIgnoreCase(String name);

	@Modifying
	public void deleteByIdIn(@Param("ids") Set<Long> ids);

	public boolean existsByIdNotAndName(Long id, String name);

	public boolean existsByName(String name);

	public List<Channel> findAll(Pageable pageable);

	public List<Channel> findByDataSourceId(
		@Param("dataSourceId") Long dataSourceId);

	public List<Channel> findByDataSourceIdAndDefaultChannel(
		@Param("dataSourceId") Long dataSourceId,
		@Param("defaultChannel") Boolean defaultChannel);

	public List<Channel> findByDataSourceIdAndGroupIds(
		@Param("dataSourceId") Long dataSourceId,
		@Param("groupsIds") Set<Long> groupsIds);

	public List<Channel> findByNameContainingIgnoreCase(
		String name, Pageable pageable);

}