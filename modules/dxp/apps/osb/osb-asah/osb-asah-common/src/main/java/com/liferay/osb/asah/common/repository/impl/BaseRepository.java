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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.jooq.Field;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Sort;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseRepository {

	protected Collection<SortField<?>> getSortFields(
		Map<String, String> fieldNames, Sort sort, Table<?> table) {

		Collection<SortField<?>> sortFields = new ArrayList<>();

		if (sort == null) {
			Field<Object> field = DSL.field("id");

			sortFields.add(field.asc());

			return sortFields;
		}

		if (fieldNames == null) {
			fieldNames = Collections.emptyMap();
		}

		for (Sort.Order order : sort.toList()) {
			String fieldName = order.getProperty();

			if (fieldNames.containsKey(fieldName)) {
				fieldName = fieldNames.get(fieldName);
			}

			if (fieldName.equalsIgnoreCase("activeIndividualCount")) {
				fieldName = "activeIndividualsCount";
			}
			else if (fieldName.equalsIgnoreCase("anonymousIndividualCount")) {
				fieldName = "anonymousIndividualsCount";
			}
			else if (fieldName.equalsIgnoreCase("dateCreated")) {
				fieldName = "createDate";
			}
			else if (fieldName.equalsIgnoreCase("individualCount")) {
				fieldName = "individualsCount";
			}
			else if (fieldName.equalsIgnoreCase("knownIndividualCount")) {
				fieldName = "knownIndividualsCount";
			}
			else if (fieldName.equalsIgnoreCase("dateModified")) {
				fieldName = "modifiedDate";
			}

			if (table != null) {
				fieldName = table.getName() + "." + fieldName;
			}

			Field<?> field = DSL.field(fieldName);

			if (order.getDirection() == Sort.Direction.ASC) {
				sortFields.add(field.asc());
			}
			else {
				sortFields.add(field.desc());
			}
		}

		return sortFields;
	}

	protected Collection<SortField<?>> getSortFields(
		Sort sort, Table<?> table) {

		return getSortFields(null, sort, table);
	}

}