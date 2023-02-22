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

import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.BQDXPEntity;
import com.liferay.osb.asah.common.model.ExpandoField;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.JSONObject;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcos Martins
 */
@Table
public class BQOrganization implements BQDXPEntity {

	public BQOrganization() {
	}

	public BQOrganization(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQOrganization)) {
			return false;
		}

		BQOrganization bqOrganization = (BQOrganization)obj;

		if (Objects.equals(_createDate, bqOrganization._createDate) &&
			Objects.equals(_dataSourceId, bqOrganization._dataSourceId) &&
			Objects.equals(_dataSourceName, bqOrganization._dataSourceName) &&
			Objects.equals(_id, bqOrganization._id) &&
			Objects.equals(_modifiedDate, bqOrganization._modifiedDate) &&
			Objects.equals(_name, bqOrganization._name) &&
			Objects.equals(_organizationId, bqOrganization._organizationId) &&
			Objects.equals(
				_parentOrganizationId, bqOrganization._parentOrganizationId) &&
			Objects.equals(
				_parentOrganizationName,
				bqOrganization._parentOrganizationName) &&
			Objects.equals(_treePath, bqOrganization._treePath) &&
			Objects.equals(_type, bqOrganization._type)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public String getDataSourceName() {
		return _dataSourceName;
	}

	public String getDXPEntityType() {
		return DXPEntity.Type.ORGANIZATION.name();
	}

	public List<ExpandoField> getExpandoFields() {
		return _expandoFields;
	}

	@JsonProperty("fields")
	public JSONObject getFieldsJSONObject() {
		return JSONUtil.put(
			"name", _name
		).put(
			"organizationId", _organizationId
		).put(
			"parentOrganizationId", _parentOrganizationId
		).put(
			"parentOrganizationName", _parentOrganizationName
		).put(
			"treePath", _treePath
		).put(
			"type", _type
		);
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	public String getIdFieldName() {
		return "organizationId";
	}

	public Long getIdFieldValue() {
		return _organizationId;
	}

	@AccessType(AccessType.Type.PROPERTY)
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
	public Long getOrganizationId() {
		return _organizationId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getParentOrganizationId() {
		return _parentOrganizationId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getParentOrganizationName() {
		return _parentOrganizationName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTreePath() {
		return _treePath;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("organizationtype")
	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_createDate, _dataSourceId, _dataSourceName, _id, _modifiedDate,
			_name, _organizationId, _parentOrganizationId,
			_parentOrganizationName, _treePath, _type);
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	public void setExpandoFields(List<ExpandoField> expandoFields) {
		if (expandoFields != null) {
			_expandoFields = new ArrayList<>(expandoFields);
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrganizationId(Long organizationId) {
		_organizationId = organizationId;
	}

	public void setParentOrganizationId(Long parentOrganizationId) {
		_parentOrganizationId = parentOrganizationId;
	}

	public void setParentOrganizationName(String parentOrganizationName) {
		_parentOrganizationName = parentOrganizationName;
	}

	public void setTreePath(String treePath) {
		_treePath = treePath;
	}

	public void setType(String type) {
		_type = type;
	}

	@Transient
	private Date _createDate;

	@Transient
	private Long _dataSourceId;

	@Transient
	private String _dataSourceName;

	@Transient
	private List<ExpandoField> _expandoFields;

	@Transient
	private String _id;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _name;

	@Transient
	private Long _organizationId;

	@Transient
	private Long _parentOrganizationId;

	@Transient
	private String _parentOrganizationName;

	@Transient
	private String _treePath;

	@Transient
	private String _type;

}