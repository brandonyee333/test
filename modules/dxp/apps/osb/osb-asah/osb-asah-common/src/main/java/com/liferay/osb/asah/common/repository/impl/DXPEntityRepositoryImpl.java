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

import com.liferay.osb.asah.common.entity.DXPEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;

/**
 * @author Marcos Martins
 * @author Alejo Ceballos
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class DXPEntityRepositoryImpl extends BaseRepository {

	public DXPEntityRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long count() {
		throw new UnsupportedOperationException();
	}

	public void delete(DXPEntity dxpEntity) {
		if (dxpEntity.getId() == null) {
			return;
		}

		_dslContext.delete(
			DSL.table("DXPEntity")
		).where(
			DSL.field(
				"id"
			).eq(
				dxpEntity.getId()
			)
		).execute();
	}

	public void deleteAll(Iterable<? extends DXPEntity> dxpEntities) {
		dxpEntities.forEach(this::delete);
	}

	public void deleteByFieldNameEqualsAndType(
		String fieldName, String fieldValue, DXPEntity.Type type) {

		_dslContext.delete(
			DSL.table("DXPEntity")
		).where(
			DSL.field(
				"type"
			).eq(
				type.toString()
			)
		).and(
			_createCondition(fieldName, fieldValue)
		).execute();
	}

	public void deleteById(Long id) {
		throw new UnsupportedOperationException();
	}

	public void deleteByType(DXPEntity.Type type) {
		_dslContext.delete(
			DSL.table("DXPEntity")
		).where(
			DSL.field(
				"type"
			).eq(
				type.getCollectionName()
			)
		).execute();
	}

	public DXPEntity fetchByDataSourceIdAndEntityTypeIdFieldValue(
		String dataSourceId, DXPEntity.Type dxpEntityType,
		String dxpEntityTypeIdValue) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		List<DXPEntity> dxpEntities = selectSelectStep.from(
			DSL.table(DXPEntity.class.getSimpleName())
		).where(
			DSL.field(
				"dataSourceId"
			).eq(
				Long.valueOf(dataSourceId)
			)
		).and(
			DSL.field(
				"fields->>{0}", String.class, dxpEntityType.getIdFieldName()
			).eq(
				dxpEntityTypeIdValue
			)
		).fetch(
		).map(
			record -> new DXPEntity(record.intoMap())
		);

		if (dxpEntities.isEmpty()) {
			return null;
		}

		return dxpEntities.get(0);
	}

	public Iterable<DXPEntity> findAll() {
		throw new UnsupportedOperationException();
	}

	public Iterable<DXPEntity> findAllById(Iterable<Long> ids) {
		throw new UnsupportedOperationException();
	}

	public List<DXPEntity> findByFieldsAndType(
		Long after, Map<String, Object> fields, int size, DXPEntity.Type type) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectConditionStep<Record> selectConditionStep = selectSelectStep.from(
			DSL.table(DXPEntity.class.getSimpleName())
		).where(
			DSL.field(
				"type"
			).eq(
				type.toString()
			)
		);

		Set<Map.Entry<String, Object>> propertiesEntrySet = fields.entrySet();

		for (Map.Entry<String, Object> propertyEntrySet : propertiesEntrySet) {
			selectConditionStep.and(
				_createCondition(
					propertyEntrySet.getKey(), propertyEntrySet.getValue()));
		}

		if (after != null) {
			selectConditionStep.and(
				DSL.field(
					"id"
				).greaterThan(
					after
				)
			).orderBy(
				DSL.field("id")
			);
		}

		if (size > 0) {
			selectConditionStep.limit(size);
		}

		return selectConditionStep.fetch(
		).map(
			record -> new DXPEntity(record.intoMap())
		);
	}

	public Optional<DXPEntity> findById(Long id) {
		throw new UnsupportedOperationException();
	}

	public List<DXPEntity> findByMembershipClassNameAndMembershipId(
		String memebershipClassName, Long membershipId) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			DSL.table(DXPEntity.class.getSimpleName())
		).where(
			DSL.field(
				"type"
			).eq(
				DXPEntity.Type.USER.toString()
			)
		).and(
			DSL.field(
				"fields->'memberships'->>{0}", String.class,
				memebershipClassName
			).contains(
				String.valueOf(membershipId)
			)
		).fetch(
		).map(
			record -> new DXPEntity(record.intoMap())
		);
	}

	public List<DXPEntity> searchByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, String keywords, DXPEntity.Type type,
		Pageable pageable) {

		throw new UnsupportedOperationException();
	}

	private Condition _createCondition(String key, Object value) {
		Field<Object> field = DSL.field(_createFieldPath(key));

		if (StringUtils.startsWith(key, "fields.memberships.")) {
			return field.contains(value);
		}

		if (value instanceof Collection) {
			return field.in((Collection)value);
		}

		return field.eq(value);
	}

	private String _createFieldPath(String fieldName) {
		String actualFieldName = fieldName;
		String memberShipField = null;

		if (StringUtils.startsWith(fieldName, "fields.memberships.")) {
			actualFieldName = "fields.memberships.%s";
			memberShipField =
				StringUtils.splitByWholeSeparator(
					fieldName, "fields.memberships.")[0];
		}

		String[] keys = StringUtils.split(actualFieldName, '.');

		for (int idx = 1; idx < keys.length; idx++) {
			if (idx == (keys.length - 1)) {
				keys[idx] = "->>'" + keys[idx] + "'";
			}
			else {
				keys[idx] = "->'" + keys[idx] + "'";
			}
		}

		if (memberShipField != null) {
			return String.format(String.join("", keys), memberShipField);
		}

		return String.join("", keys);
	}

	private final DSLContext _dslContext;

}