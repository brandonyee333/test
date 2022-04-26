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

import com.liferay.osb.asah.common.model.ExpandoField;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcos Martins
 */
@Table
public class BQOrganization implements Persistable<String> {
	public BQOrganization() {
	}

	public BQOrganization(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Override
	public Long getDataSourceId() {
		return _dataSourceId;
	}


	@AccessType(AccessType.Type.PROPERTY)
	public Long[] getExpandoColumnIds() {
		return Arrays.copyOf(_expandoColumnIds, _expandoColumnIds.length);
	}

	public List<ExpandoField> getExpandoFields() {
		return _expandoFields;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String[] getExpandoValueIds() {
		return Arrays.copyOf(_expandoValueIds, _expandoValueIds.length);
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
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
	public String getTreePath() {
		return _treePath;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("organizationtype")
	public String getType() {
		return _type;
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setExpandoColumnIds(Long[] expandoColumnIds) {
		_expandoColumnIds = Arrays.copyOf(
			expandoColumnIds, expandoColumnIds.length);
	}

	public void setExpandoFields(List<ExpandoField> expandoFields) {
		_expandoFields = expandoFields;
	}

	public void setExpandoValueIds(String[] expandoValueIds) {
		_expandoValueIds = Arrays.copyOf(
			expandoValueIds, expandoValueIds.length);
	}

	public void setId(String id) {
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

	public void setOrganizationId(Long organizationId) {
		_organizationId = organizationId;
	}

	public void setParentOrganizationId(Long parentOrganizationId) {
		_parentOrganizationId = parentOrganizationId;
	}

	public void setTreePath(String treePath) {
		_treePath = treePath;
	}

	public void setType(String type) {
		_type = type;
	}

	@Transient
	private Long _dataSourceId;

	@Transient
	private Long[] _expandoColumnIds = {};

	@Transient
	private List<ExpandoField> _expandoFields;

	@Transient
	private String[] _expandoValueIds = {};

	@Transient
	private String _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _name;

	@Transient
	private Long _organizationId;

	@Transient
	private Long _parentOrganizationId;

	@Transient
	private String _treePath;

	@Transient
	private String _type;

}