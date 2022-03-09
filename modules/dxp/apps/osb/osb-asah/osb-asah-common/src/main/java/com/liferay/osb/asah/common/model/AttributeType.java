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

	ACCOUNT(null, null, "ownerId", "Field"),
	EVENT("name", "value", "BQEventProperty.id", "BQEventProperty"),
	INDIVIDUAL(null, null, "ownerId", "Field"),
	USER_SESSION(null, null, "individualId", "UserSession");

	public String getAttributeIdFieldName() {
		return _attributeIdFieldName;
	}

	public String getAttributeValueFieldName() {
		return _attributeValueFieldName;
	}

	public String getJoinFieldName() {
		return _joinFieldName;
	}

	public String getQualifiedAttributeIdFieldName(String tableName) {
		if (tableName == null) {
			tableName = _tableName;
		}

		return tableName.concat(
			"."
		).concat(
			_attributeIdFieldName
		);
	}

	public String getQualifiedAttributeValueFieldName(String tableName) {
		if (tableName == null) {
			tableName = _tableName;
		}

		return tableName.concat(
			"."
		).concat(
			_attributeValueFieldName
		);
	}

	public String getQualifiedJoinFieldName(String tableName) {
		if (tableName == null) {
			tableName = _tableName;
		}

		return tableName.concat(
			"."
		).concat(
			_joinFieldName
		);
	}

	public String getTableName() {
		return _tableName;
	}

	private AttributeType(
		String attributeIdFieldName, String attributeValueFieldName,
		String joinFieldName, String tableName) {

		_attributeIdFieldName = attributeIdFieldName;
		_attributeValueFieldName = attributeValueFieldName;
		_joinFieldName = joinFieldName;
		_tableName = tableName;
	}

	private final String _attributeIdFieldName;
	private final String _attributeValueFieldName;
	private final String _joinFieldName;
	private final String _tableName;

}