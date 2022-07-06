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

import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQSalesforceAuditEventRepository;

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
public class BQSalesforceAuditEventDog {

	public BQSalesforceAuditEvent addBQSalesforceAuditEvent(
		BQSalesforceAuditEvent bqSalesforceAuditEvent) {

		return _bqSalesforceAuditEventRepository.save(bqSalesforceAuditEvent);
	}

	public List<BQSalesforceAuditEvent> addBQSalesforceAuditEvents(
		List<BQSalesforceAuditEvent> bqSalesforceAuditEvents) {

		return IterableUtils.toList(
			_bqSalesforceAuditEventRepository.saveAll(bqSalesforceAuditEvents));
	}

	public void deleteBQSalesforceAuditEvent(
		BQSalesforceAuditEvent bqSalesforceAuditEvent) {

		_bqSalesforceAuditEventRepository.delete(bqSalesforceAuditEvent);
	}

	public List<BQSalesforceAuditEvent> getBQSalesforceAuditEvents(
		Long dataSourceId, String entityTypeName, int page, int size,
		Sort sort) {

		return _bqSalesforceAuditEventRepository.
			findByDataSourceIdAndEntityTypeName(
				dataSourceId, entityTypeName, PageRequest.of(page, size, sort));
	}

	public long getBQSalesforceAuditEventsCount(
		Long dataSourceId, String... entityTypeName) {

		return _bqSalesforceAuditEventRepository.
			countByDataSourceIdAndEntityTypeNameIn(
				dataSourceId, Arrays.asList(entityTypeName));
	}

	@Autowired
	private BQSalesforceAuditEventRepository _bqSalesforceAuditEventRepository;

}