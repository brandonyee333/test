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

import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
public interface SalesforceAuditEventRepository
	extends CrudRepository<SalesforceAuditEvent, Long> {

	public long countByDataSourceIdAndEntityTypeNameIn(
		Long dataSourceId, List<String> entityTypeNames);

	public List<SalesforceAuditEvent> findByDataSourceIdAndEntityTypeName(
		Long dataSourceId, String entityTypeName, Pageable pageable);

}