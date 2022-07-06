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

import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.repository.BQSalesforceEntityRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class BQSalesforceEntityDog {

	public void deleteBQSalesforceEntities(Long dataSourceId) {
		_bqSalesforceEntityRepository.deleteByDataSourceId(dataSourceId);
	}

	public void deleteBQSalesforceEntities(
		String fieldKey, String fieldValue, BQSalesforceEntity.Type type) {

		_bqSalesforceEntityRepository.deleteByFieldKeyAndFieldValueAndType(
			fieldKey, fieldValue, type);
	}

	public void deleteBQSalesforceEntity(
		BQSalesforceEntity bqSalesforceEntity) {

		_bqSalesforceEntityRepository.delete(bqSalesforceEntity);
	}

	public BQSalesforceEntity fetchBQSalesforceEntity(
		Long dataSourceId, String id, BQSalesforceEntity.Type type) {

		Optional<BQSalesforceEntity> bqSalesforceEntityOptional =
			_bqSalesforceEntityRepository.findByDataSourceIdAndIdAndType(
				dataSourceId, id, type);

		return bqSalesforceEntityOptional.orElse(null);
	}

	public List<BQSalesforceEntity> getBQSalesforceEntities(
		Long dataSourceId, int page, int size, BQSalesforceEntity.Type type) {

		return _bqSalesforceEntityRepository.findByDataSourceIdAndType(
			dataSourceId, type, PageRequest.of(page, size));
	}

	public List<BQSalesforceEntity> getBQSalesforceEntities(
		Long dataSourceId, String fieldKey, String fieldValue,
		BQSalesforceEntity.Type type) {

		return _bqSalesforceEntityRepository.
			findByDataSourceIdAndFieldKeyEqualsAndType(
				dataSourceId, fieldKey, fieldValue, type);
	}

	public List<BQSalesforceEntity> getBQSalesforceEntities(
		String after, String fieldKey, String fieldValue, int size,
		BQSalesforceEntity.Type type) {

		return _bqSalesforceEntityRepository.
			findByAfterAndFieldKeyAndFieldValueAndType(
				after, fieldKey, fieldValue, size, type);
	}

	public long getBQSalesforceEntitiesCount(
		Long dataSourceId, BQSalesforceEntity.Type type) {

		return _bqSalesforceEntityRepository.countByDataSourceIdAndType(
			dataSourceId, type);
	}

	public BQSalesforceEntity getBQSalesforceEntity(
		Long dataSourceId, String id, BQSalesforceEntity.Type type) {

		Optional<BQSalesforceEntity> bqSalesforceEntityOptional =
			_bqSalesforceEntityRepository.findByDataSourceIdAndIdAndType(
				dataSourceId, id, type);

		return bqSalesforceEntityOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				String.format(
					"There is no Salesforce entity with data source ID, ID " +
						"and type {%s, %s, %s}",
					dataSourceId, id, type)));
	}

	public List<String> getBQSalesforceEntityFieldValuesGroupByField(
		Long dataSourceId, String fieldKey, String fieldValue,
		String groupByFieldKey, BQSalesforceEntity.Type type) {

		return _bqSalesforceEntityRepository.
			findByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey(
				dataSourceId, fieldKey, fieldValue, type, groupByFieldKey);
	}

	public Page<BQSalesforceEntity> getBQSalesforceEntityPage(
		Long dataSourceId, int page, int size, BQSalesforceEntity.Type type) {

		PageRequest pageRequest = PageRequest.of(page, size);

		return PageableExecutionUtils.getPage(
			_bqSalesforceEntityRepository.findByDataSourceIdAndType(
				dataSourceId, type, pageRequest),
			pageRequest,
			() -> getBQSalesforceEntitiesCount(dataSourceId, type));
	}

	public BQSalesforceEntity saveBQSalesforceEntity(
		BQSalesforceEntity bqSalesforceEntity) {

		if (!_bqSalesforceEntityRepository.existsByDataSourceIdAndIdAndType(
				bqSalesforceEntity.getDataSourceId(),
				bqSalesforceEntity.getId(), bqSalesforceEntity.getType())) {

			bqSalesforceEntity.setIsNew(Boolean.TRUE);

			return _bqSalesforceEntityRepository.save(bqSalesforceEntity);
		}

		_bqSalesforceEntityRepository.updateBQSalesforceEntityFields(
			bqSalesforceEntity.getDataSourceId(),
			bqSalesforceEntity.getFieldsJSONObject(),
			bqSalesforceEntity.getId(), bqSalesforceEntity.getType());

		return getBQSalesforceEntity(
			bqSalesforceEntity.getDataSourceId(), bqSalesforceEntity.getId(),
			bqSalesforceEntity.getType());
	}

	public List<BQSalesforceEntity> saveSalesforceEntities(
		List<BQSalesforceEntity> bqSalesforceEntities) {

		Stream<BQSalesforceEntity> stream = bqSalesforceEntities.stream();

		return stream.map(
			this::saveBQSalesforceEntity
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private BQSalesforceEntityRepository _bqSalesforceEntityRepository;

}