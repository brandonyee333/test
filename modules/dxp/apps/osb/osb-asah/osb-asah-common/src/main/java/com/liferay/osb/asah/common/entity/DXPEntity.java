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

package com.liferay.osb.asah.common.entity;

import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;

/**
 * @author Matthew Kong
 */
public class DXPEntity {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof DXPEntity)) {
			return false;
		}

		DXPEntity dxpEntity = (DXPEntity)obj;

		if (Objects.equals(_dataSourceName, dxpEntity._dataSourceName) &&
			Objects.equals(_id, dxpEntity._id) &&
			Objects.equals(_name, dxpEntity._name)) {

			return true;
		}

		return false;
	}

	public String getDataSourceName() {
		return _dataSourceName;
	}

	public String getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public DXPEntity.Type getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _name);
	}

	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setType(DXPEntity.Type type) {
		_type = type;
	}

	public enum Type {

		EXPANDO_COLUMN(Type.CLASS_NAME_EXPANDO_COLUMN, null, null, null, null),
		GROUP(
			Type.CLASS_NAME_GROUP, "groups", "groupId", "groupIds",
			"referencedGroupIds"),
		ORGANIZATION(
			Type.CLASS_NAME_ORGANIZATION, "organizations", "organizationId",
			"organizationIds", "referencedOrganizationIds"),
		ROLE(
			Type.CLASS_NAME_ROLE, "roles", "roleId", "roleIds",
			"referencedRoleIds"),
		TEAM(
			Type.CLASS_NAME_TEAM, "teams", "teamId", "teamIds",
			"referencedTeamIds"),
		USER(
			Type.CLASS_NAME_USER, "users", "userId", "userId",
			"referencedUserIds"),
		USER_FIELD(Type.CLASS_NAME_USER_FIELD, null, null, null, null),
		USER_GROUP(
			Type.CLASS_NAME_USER_GROUP, "user-groups", "userGroupId",
			"userGroupIds", "referencedUserGroupIds");

		public static final String CLASS_NAME_CONTACT =
			"com.liferay.portal.kernel.model.Contact";

		public static final String CLASS_NAME_EXPANDO_COLUMN =
			"com.liferay.expando.kernel.model.ExpandoColumn";

		public static final String CLASS_NAME_GROUP =
			"com.liferay.portal.kernel.model.Group";

		public static final String CLASS_NAME_ORGANIZATION =
			"com.liferay.portal.kernel.model.Organization";

		public static final String CLASS_NAME_ROLE =
			"com.liferay.portal.kernel.model.Role";

		public static final String CLASS_NAME_TEAM =
			"com.liferay.portal.kernel.model.Team";

		public static final String CLASS_NAME_USER =
			"com.liferay.portal.kernel.model.User";

		public static final String CLASS_NAME_USER_FIELD =
			"com.liferay.portal.kernel.model.User.field";

		public static final String CLASS_NAME_USER_GROUP =
			"com.liferay.portal.kernel.model.UserGroup";

		public static Type of(String className) {
			if (className.equals(CLASS_NAME_EXPANDO_COLUMN)) {
				return Type.EXPANDO_COLUMN;
			}

			if (className.equals(CLASS_NAME_GROUP)) {
				return Type.GROUP;
			}

			if (className.equals(CLASS_NAME_ORGANIZATION)) {
				return Type.ORGANIZATION;
			}

			if (className.equals(CLASS_NAME_ROLE)) {
				return Type.ROLE;
			}

			if (className.equals(CLASS_NAME_TEAM)) {
				return Type.TEAM;
			}

			if (className.equals(CLASS_NAME_USER)) {
				return Type.USER;
			}

			if (className.equals(CLASS_NAME_USER_FIELD)) {
				return Type.USER_FIELD;
			}
			else if (className.equals(CLASS_NAME_USER_GROUP)) {
				return Type.USER_GROUP;
			}

			return null;
		}

		public static Type ofCollectionName(String collectionName) {
			if (collectionName.equals("groups")) {
				return of(CLASS_NAME_GROUP);
			}

			if (collectionName.equals("organizations")) {
				return of(CLASS_NAME_ORGANIZATION);
			}

			if (collectionName.equals("roles")) {
				return of(CLASS_NAME_ROLE);
			}

			if (collectionName.equals("teams")) {
				return of(CLASS_NAME_TEAM);
			}

			if (collectionName.equals("user-groups")) {
				return of(CLASS_NAME_USER_GROUP);
			}

			if (collectionName.equals("users")) {
				return of(CLASS_NAME_USER);
			}

			return null;
		}

		public static Type ofIndividualFieldName(String individualFieldName) {
			if (individualFieldName.equals("groupIds")) {
				return of(CLASS_NAME_GROUP);
			}

			if (individualFieldName.equals("organizationIds")) {
				return of(CLASS_NAME_ORGANIZATION);
			}

			if (individualFieldName.equals("roleIds")) {
				return of(CLASS_NAME_ROLE);
			}

			if (individualFieldName.equals("teamIds")) {
				return of(CLASS_NAME_TEAM);
			}

			if (individualFieldName.equals("userGroupIds")) {
				return of(CLASS_NAME_USER_GROUP);
			}

			if (individualFieldName.equals("userId")) {
				return of(CLASS_NAME_USER);
			}

			return null;
		}

		public String getClassName() {
			return _className;
		}

		public String getCollectionName() {
			return _collectionName;
		}

		public String getIdFieldName() {
			return _idFieldName;
		}

		public String getIndividualFieldName() {
			return _individualFieldName;
		}

		public String getIndividualSegmentFieldName() {
			return _individualSegmentFieldName;
		}

		public boolean isExpandoColumn() {
			if (equals(Type.EXPANDO_COLUMN)) {
				return true;
			}

			return false;
		}

		public boolean isGroup() {
			if (equals(Type.GROUP)) {
				return true;
			}

			return false;
		}

		public boolean isOrganization() {
			if (equals(Type.ORGANIZATION)) {
				return true;
			}

			return false;
		}

		public boolean isRole() {
			if (equals(Type.ROLE)) {
				return true;
			}

			return false;
		}

		public boolean isTeam() {
			if (equals(Type.TEAM)) {
				return true;
			}

			return false;
		}

		public boolean isUser() {
			if (equals(Type.USER)) {
				return true;
			}

			return false;
		}

		public boolean isUserField() {
			if (equals(Type.USER_FIELD)) {
				return true;
			}

			return false;
		}

		public boolean isUserGroup() {
			if (equals(Type.USER_GROUP)) {
				return true;
			}

			return false;
		}

		private Type(
			String className, String collectionName, String idFieldName,
			String individualFieldName, String individualSegmentFieldName) {

			_className = className;
			_collectionName = collectionName;
			_idFieldName = idFieldName;
			_individualFieldName = individualFieldName;
			_individualSegmentFieldName = individualSegmentFieldName;
		}

		private final String _className;
		private final String _collectionName;
		private final String _idFieldName;
		private final String _individualFieldName;
		private final String _individualSegmentFieldName;

	}

	private String _dataSourceName;
	private String _id;
	private String _name;

	@Transient
	private Type _type;

}