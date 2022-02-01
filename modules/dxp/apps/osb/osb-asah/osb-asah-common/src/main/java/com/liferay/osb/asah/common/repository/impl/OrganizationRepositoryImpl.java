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

import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import org.eclipse.jetty.util.StringUtil;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class OrganizationRepositoryImpl extends BaseRepository {

	public OrganizationRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public List<Transformation> getOrganizationTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable) {

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					MatcherUtil.getGroupByPattern());
		}

		String containsField = matcher.group("containsField");

		String groupByField = matcher.group("groupByField");

		String fieldName = groupByField.replace("custom/", "");

		fieldName = fieldName.replace("/value", "");

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> modifiedDateField = DSL.field("modifiedDate");
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Object> valueField = DSL.field("value");

		Condition condition = filterHelper.getCondition();

		condition = condition.and(_getIncludeCondition(containsField));

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			modifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), nameField.as("name"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				condition.and(nameField.eq(fieldName))
			).groupBy(
				ownerIdField, nameField
			).asTable(
				"maxModifiedDateTable"
			);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			valueField.as("terms"),
			DSL.count(
				ownerIdField
			).as(
				"totalelements"
			)
		).from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				modifiedDateField.eq(maxModifiedDateTable.field("modifiedDate"))
			).and(
				nameField.eq(maxModifiedDateTable.field("name"))
			).and(
				ownerIdField.eq(maxModifiedDateTable.field("ownerId"))
			)
		).groupBy(
			valueField
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

	public List<Organization> searchOrganizations(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Organization"
		).where(
			filterHelper.getCondition()
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Organization(record.intoMap())
		);
	}

	public List<Organization> searchOrganizations(
		@Nullable String keywords, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Organization"
		).where(
			_getCondition(keywords)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Organization(record.intoMap())
		);
	}

	private Condition _getCondition(String keywords) {
		if (StringUtil.isBlank(keywords)) {
			return DSL.noCondition();
		}

		return DSL.field(
			"name"
		).contains(
			keywords
		);
	}

	private Condition _getIncludeCondition(String contains) {
		if (contains == null) {
			return DSL.noCondition();
		}

		return DSL.field(
			"value"
		).containsIgnoreCase(
			contains
		);
	}

	private final DSLContext _dslContext;

}