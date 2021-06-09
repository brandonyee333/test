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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Marcos Martins
 * @author Alejo Ceballos
 */
public class DXPEntityRepositoryImpl extends BaseRepository {

	public DXPEntityRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long count() {
		throw new UnsupportedOperationException();
	}

	public long countByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			DSL.table(DXPEntity.class.getSimpleName())
		).where(
			_getConditions(dataSourceIds, keywords, type)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
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

	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	public void deleteAll(Iterable<? extends DXPEntity> dxpEntities) {
		dxpEntities.forEach(this::delete);
	}

	public void deleteByFieldNameAndFieldValueAndType(
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
				type.toString()
			)
		).execute();
	}

	public boolean existsById(Long id) {
		throw new UnsupportedOperationException();
	}

	public Iterable<DXPEntity> findAll() {
		throw new UnsupportedOperationException();
	}

	public Iterable<DXPEntity> findAllById(Iterable<Long> ids) {
		throw new UnsupportedOperationException();
	}

	public List<DXPEntity> findByAfterAndFieldsAndType(
		@Nullable Long after, Map<String, Object> fields, int size,
		DXPEntity.Type type) {

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

		for (Map.Entry<String, Object> field : fields.entrySet()) {
			selectConditionStep.and(
				_createCondition(field.getKey(), field.getValue()));
		}

		if (after != null) {
			selectConditionStep.and(
				DSL.field(
					"id"
				).greaterThan(
					after
				));
		}

		if (size > 0) {
			selectConditionStep.limit(size);
		}

		selectConditionStep.orderBy(DSL.field("id"));

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
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			DSL.table(DXPEntity.class.getSimpleName())
		).where(
			_getConditions(dataSourceIds, keywords, type)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new DXPEntity(record.intoMap())
		);
	}

	private Condition _createCondition(String fieldKey, Object fieldValue) {
		Field<Object> field = DSL.field(_createFieldPath(fieldKey));

		if (StringUtils.startsWith(fieldKey, "fields.memberships.")) {
			return field.contains(fieldValue);
		}

		if (fieldValue instanceof Collection) {
			return field.in((Collection)fieldValue);
		}

		return field.eq(fieldValue);
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

	private List<Condition> _getConditions(
		List<Long> dataSourceIds, String keywords, DXPEntity.Type type) {

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"type"
			).eq(
				type.toString()
			));

		if (dataSourceIds == null) {
			if (type.isUser()) {
				conditions.add(
					DSL.or(
						DSL.field(
							"fields->firstName", String.class
						).contains(
							keywords
						),
						DSL.field(
							"fields->lastName", String.class
						).contains(
							keywords
						)));

				return conditions;
			}

			conditions.add(
				DSL.field(
					"fields->name", String.class
				).contains(
					keywords
				));

			return conditions;
		}

		if ((type.isGroup() || type.isTeam()) &&
			StringUtils.isNotBlank(keywords)) {

			conditions.add(
				DSL.field(
					"fields->name", String.class
				).contains(
					keywords
				));
		}
		else if (type.isUser() && StringUtils.isNotBlank(keywords)) {
			conditions.add(
				DSL.or(
					DSL.field(
						"fields->firstName", String.class
					).contains(
						keywords
					),
					DSL.field(
						"fields->lastName", String.class
					).contains(
						keywords
					)));
		}

		if (dataSourceIds.isEmpty()) {
			return conditions;
		}

		conditions.add(
			DSL.field(
				"dataSourceId"
			).in(
				dataSourceIds
			));

		return conditions;
	}

	private final DSLContext _dslContext;

}