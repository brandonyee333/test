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

package com.liferay.osb.asah.common.model;

import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;

import java.util.Collections;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
@SuppressFBWarnings(
	{"EQ_DOESNT_OVERRIDE_EQUALS", "NM_SAME_SIMPLE_NAME_AS_SUPERCLASS"}
)
public class Sort extends org.springframework.data.domain.Sort {

	public static Sort asc(String column) {
		return new Sort(column, "asc");
	}

	public static Sort desc(String column) {
		return new Sort(column, "desc");
	}

	public static Sort of(Map<String, String> sort) {
		return new Sort(sort.get("column"), sort.get("type"));
	}

	public Sort(String column, String type) {
		super(
			Collections.singletonList(
				new Order(Direction.valueOf(type.toUpperCase()), column)));

		_column = column;
		_type = type.toUpperCase();
	}

	public String getColumn() {
		return _column;
	}

	public String getType() {
		return _type;
	}

	private final String _column;
	private final String _type;

}