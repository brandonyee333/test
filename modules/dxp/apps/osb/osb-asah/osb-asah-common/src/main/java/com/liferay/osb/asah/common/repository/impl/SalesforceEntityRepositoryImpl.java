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

package com.liferay.osb.asah.common.repository.impl;

import com.liferay.osb.asah.common.entity.SalesforceEntity;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

/**
 * @author Marcellus Tavares
 */
public class SalesforceEntityRepositoryImpl extends BaseRepository {

	public SalesforceEntityRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public List<SalesforceEntity> findByDataSourceIdAndFieldKeyEqualsAndType(
		Long dataSourceId, String fieldKey, String fieldValue,
		SalesforceEntity.Type type) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"SalesforceEntity"
		).where(
			_getConditions(dataSourceId, fieldKey, fieldValue, type)
		).fetch(
		).map(
			record -> new SalesforceEntity(record.intoMap())
		);
	}

	public List<String>
		findByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey(
			Long dataSourceId, String fieldKey, String fieldValue,
			SalesforceEntity.Type type, String groupByFieldKey) {

		Field<Object> groupByField = DSL.field(
			String.format("fields ->> '%s'", groupByFieldKey));

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			groupByField.as(groupByFieldKey));

		return selectSelectStep.from(
			"SalesforceEntity"
		).where(
			_getConditions(dataSourceId, fieldKey, fieldValue, type)
		).groupBy(
			groupByField
		).fetch(
		).map(
			record -> record.get(groupByFieldKey, String.class)
		);
	}

	private List<Condition> _getConditions(
		Long dataSourceId, String fieldKey, String fieldValue,
		SalesforceEntity.Type type) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"dataSourceId"
			).eq(
				dataSourceId
			));

		conditions.add(DSL.condition("fields ->> ? = ?", fieldKey, fieldValue));

		conditions.add(
			DSL.field(
				"type"
			).eq(
				String.valueOf(type)
			));

		return conditions;
	}

	private final DSLContext _dslContext;

}