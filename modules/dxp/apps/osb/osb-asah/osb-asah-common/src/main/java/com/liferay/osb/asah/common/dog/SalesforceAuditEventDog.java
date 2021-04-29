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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.SalesforceAuditEventRepository;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SalesforceAuditEventDog {

	public SalesforceAuditEvent addSalesforceAuditEvent(
		SalesforceAuditEvent salesforceAuditEvent) {

		return _salesforceAuditEventRepository.save(salesforceAuditEvent);
	}

	public List<SalesforceAuditEvent> addSalesforceAuditEvents(
		List<SalesforceAuditEvent> salesforceAuditEvents) {

		return IterableUtils.toList(
			_salesforceAuditEventRepository.saveAll(salesforceAuditEvents));
	}

	public void deleteSalesforceAuditEvent(
		SalesforceAuditEvent salesforceAuditEvent) {

		_salesforceAuditEventRepository.delete(salesforceAuditEvent);
	}

	public List<SalesforceAuditEvent> getSalesforceAuditEvents(
		Long dataSourceId, String entityTypeName, int page, int size,
		Sort sort) {

		return _salesforceAuditEventRepository.
			findByDataSourceIdAndEntityTypeName(
				dataSourceId, entityTypeName, PageRequest.of(page, size, sort));
	}

	public long getSalesforceAuditEventsCount(
		Long dataSourceId, String... entityTypeName) {

		return _salesforceAuditEventRepository.
			countByDataSourceIdAndEntityTypeNameIn(
				dataSourceId, Arrays.asList(entityTypeName));
	}

	@Autowired
	private SalesforceAuditEventRepository _salesforceAuditEventRepository;

}