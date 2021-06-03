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

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class Organization implements Persistable<Long> {

	public Organization() {
	}

	public Organization(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Organization)) {
			return false;
		}

		Organization organization = (Organization)obj;

		if (Objects.equals(_createDate, organization._createDate) &&
			Objects.equals(_dataSourceId, organization._dataSourceId) &&
			Objects.equals(_id, organization._id) &&
			Objects.equals(_modifiedDate, organization._modifiedDate) &&
			Objects.equals(_name, organization._name) &&
			Objects.equals(_nameTreePath, organization._nameTreePath) &&
			Objects.equals(_organizationPK, organization._organizationPK) &&
			Objects.equals(_parentName, organization._parentName) &&
			Objects.equals(
				_parentOrganizationPK, organization._parentOrganizationPK) &&
			Objects.equals(_treePath, organization._treePath) &&
			Objects.equals(_type, organization._type)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("createDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@JsonIgnore
	@MappedCollection(idColumn = "ownerid")
	public Set<Field> getCustomFields() {
		return _customFields;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("modifiedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateModified")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getNameTreePath() {
		return _nameTreePath;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getOrganizationPK() {
		return _organizationPK;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getParentName() {
		return _parentName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getParentOrganizationPK() {
		return _parentOrganizationPK;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTreePath() {
		return _treePath;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_createDate, _dataSourceId, _id, _modifiedDate, _name,
			_nameTreePath, _organizationPK, _parentName, _parentOrganizationPK,
			_type);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setCustomFields(Set<Field> fields) {
		_customFields = fields;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNameTreePath(String nameTreePath) {
		_nameTreePath = nameTreePath;
	}

	public void setOrganizationPK(Long organizationPK) {
		_organizationPK = organizationPK;
	}

	public void setParentName(String parentName) {
		_parentName = parentName;
	}

	public void setParentOrganizationPK(Long parentOrganizationPK) {
		_parentOrganizationPK = parentOrganizationPK;
	}

	public void setTreePath(String treePath) {
		_treePath = treePath;
	}

	public void setType(String type) {
		_type = type;
	}

	@Transient
	private Date _createDate = new Date();

	@Transient
	private Set<Field> _customFields = new HashSet<>();

	@Transient
	private Long _dataSourceId;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _modifiedDate = new Date();

	@Transient
	private String _name;

	@Transient
	private String _nameTreePath;

	@Transient
	private Long _organizationPK;

	@Transient
	private String _parentName;

	@Transient
	private Long _parentOrganizationPK;

	@Transient
	private String _treePath;

	@Transient
	private String _type;

}