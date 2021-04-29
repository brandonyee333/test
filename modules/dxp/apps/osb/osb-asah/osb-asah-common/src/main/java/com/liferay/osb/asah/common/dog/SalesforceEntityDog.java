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

import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.repository.SalesforceEntityRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SalesforceEntityDog {

	public void deleteSalesforceEntity(SalesforceEntity salesforceEntity) {
		_salesforceEntityRepository.delete(salesforceEntity);
	}

	public SalesforceEntity fetchSalesforceEntity(
		Long dataSourceId, String id, SalesforceEntity.Type type) {

		Optional<SalesforceEntity> salesforceEntityOptional =
			_salesforceEntityRepository.findByDataSourceIdAndIdAndType(
				dataSourceId, id, type);

		return salesforceEntityOptional.orElse(null);
	}

	public List<SalesforceEntity> getSalesforceEntities(
		Long dataSourceId, int page, int size, SalesforceEntity.Type type) {

		return _salesforceEntityRepository.findByDataSourceIdAndType(
			dataSourceId, type, PageRequest.of(page, size));
	}

	public List<SalesforceEntity> getSalesforceEntities(
		Long dataSourceId, String fieldKey, String fieldValue,
		SalesforceEntity.Type type) {

		return _salesforceEntityRepository.
			findByDataSourceIdAndFieldKeyEqualsAndType(
				dataSourceId, fieldKey, fieldValue, type);
	}

	public long getSalesforceEntitiesCount(
		Long dataSourceId, SalesforceEntity.Type type) {

		return _salesforceEntityRepository.countByDataSourceIdAndType(
			dataSourceId, type);
	}

	public SalesforceEntity getSalesforceEntity(
		Long dataSourceId, String id, SalesforceEntity.Type type) {

		Optional<SalesforceEntity> salesforceEntityOptional =
			_salesforceEntityRepository.findByDataSourceIdAndIdAndType(
				dataSourceId, id, type);

		return salesforceEntityOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				String.format(
					"There is no Salesforce entity with data source ID, ID " +
						"and type {%s, %s, %s}",
					dataSourceId, id, type)));
	}

	public List<String> getSalesforceEntityFieldValuesGroupByField(
		Long dataSourceId, String fieldKey, String fieldValue,
		String groupByFieldKey, SalesforceEntity.Type type) {

		return _salesforceEntityRepository.
			findByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey(
				dataSourceId, fieldKey, fieldValue, type, groupByFieldKey);
	}

	public Page<SalesforceEntity> getSalesforceEntityPage(
		Long dataSourceId, int page, int size, SalesforceEntity.Type type) {

		PageRequest pageRequest = PageRequest.of(page, size);

		return PageableExecutionUtils.getPage(
			_salesforceEntityRepository.findByDataSourceIdAndType(
				dataSourceId, type, pageRequest),
			pageRequest, () -> getSalesforceEntitiesCount(dataSourceId, type));
	}

	public List<SalesforceEntity> saveSalesforceEntities(
		List<SalesforceEntity> salesforceEntities) {

		for (SalesforceEntity salesforceEntity : salesforceEntities) {
			if (!_salesforceEntityRepository.existsByDataSourceIdAndIdAndType(
					salesforceEntity.getDataSourceId(),
					salesforceEntity.getId(), salesforceEntity.getType())) {

				salesforceEntity.setIsNew(Boolean.TRUE);
			}
		}

		return IterableUtils.toList(
			_salesforceEntityRepository.saveAll(salesforceEntities));
	}

	public SalesforceEntity saveSalesforceEntity(
		SalesforceEntity salesforceEntity) {

		List<SalesforceEntity> salesforceEntities = saveSalesforceEntities(
			Arrays.asList(salesforceEntity));

		return salesforceEntities.get(0);
	}

	@Autowired
	private SalesforceEntityRepository _salesforceEntityRepository;

}