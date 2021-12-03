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

import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.UpdateSetFirstStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author Rachael Koestartyo
 */
public class FieldRepositoryImpl extends BaseRepository {

	public FieldRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countFields(FilterHelper filterHelper) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Field"
		).where(
			filterHelper.getCondition()
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Transformation> getFieldTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable) {

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					MatcherUtil.getGroupByPattern());
		}

		String containsField = matcher.group("containsField");
		String groupByField = matcher.group("groupByField");

		Condition condition = filterHelper.getCondition();

		condition = condition.and(
			_getIncludeCondition(containsField, groupByField));

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.field(
				groupByField
			).as(
				"terms"
			),
			DSL.count(
				DSL.field("id")
			).as(
				"totalelements"
			)
		).from(
			"Field"
		).where(
			condition
		).groupBy(
			DSL.field(groupByField)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField, record.get("terms"))),
				(Integer)record.get("totalelements"))
		);
	}

	public List<Field> searchFields(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Field"
		).where(
			filterHelper.getCondition()
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Field(record.intoMap())
		);
	}

	public void updateDataSourceNameByDataSourceId(
		Long dataSourceId, String dataSourceName) {

		UpdateSetFirstStep<Record> update = _dslContext.update(
			DSL.table("Field"));

		update.set(
			DSL.field("dataSourceName"), dataSourceName
		).where(
			DSL.field(
				"dataSourceId"
			).eq(
				dataSourceId
			)
		).execute();
	}

	private Condition _getIncludeCondition(
		String containsField, String fieldName) {

		if (containsField == null) {
			return DSL.noCondition();
		}

		return DSL.field(
			fieldName
		).containsIgnoreCase(
			containsField
		);
	}

	private final DSLContext _dslContext;

}