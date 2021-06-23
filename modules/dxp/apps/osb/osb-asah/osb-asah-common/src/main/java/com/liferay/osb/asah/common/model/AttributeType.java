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

/**
 * @author Leslie Wong
 */
public enum AttributeType {

	ACCOUNT("ownerId", "Field", "value"),
	EVENT("eventId", "EventAttribute", "attributeValue"),
	INDIVIDUAL("ownerId", "Field", "value"),
	USER_SESSION("ownerId", "UserSession", "value");

	public String getJoinFieldName() {
		return _joinFieldName;
	}

	public String getTableName() {
		return _tableName;
	}

	public String getValueFieldName() {
		return _valueFieldName;
	}

	private AttributeType(
		String joinFieldName, String tableName, String valueFieldName) {

		_joinFieldName = joinFieldName;
		_tableName = tableName;
		_valueFieldName = valueFieldName;
	}

	private final String _joinFieldName;
	private final String _tableName;
	private final String _valueFieldName;

}