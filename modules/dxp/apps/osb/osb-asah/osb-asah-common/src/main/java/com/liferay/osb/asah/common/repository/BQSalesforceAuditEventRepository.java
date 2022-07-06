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

import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
 * @author Marcellus Tavares
 */
public interface BQSalesforceAuditEventRepository
	extends Repository<BQSalesforceAuditEvent, Long> {

	@Cacheable
	public long countByDataSourceIdAndEntityTypeNameIn(
		Long dataSourceId, List<String> entityTypeNames);

	@Cacheable
	public List<BQSalesforceAuditEvent> findByDataSourceIdAndEntityTypeName(
		Long dataSourceId, String entityTypeName, Pageable pageable);

}