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

import com.liferay.osb.asah.common.entity.BQOrganization;

import java.util.Collection;
import java.util.List;

/**
 * @author Marcos Martins
 */
public interface BQOrganizationRepository
	extends BigQueryRepository<BQOrganization, String>,
			CustomBQOrganizationRepository {

	public List<BQOrganization> findByDataSourceIdAndOrganizationId(
		Long dataSourceId, Long organizationId);

	public List<BQOrganization> findByDataSourceIdAndOrganizationIdIn(
		Long dataSourceId, Collection<Long> organizationIds);

}