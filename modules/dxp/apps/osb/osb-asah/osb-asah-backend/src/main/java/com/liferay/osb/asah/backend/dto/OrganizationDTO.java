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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("organizations")
public class OrganizationDTO {

	public OrganizationDTO() {
	}

	public OrganizationDTO(List<Organization> organizations) {
		_organizationDTOs = SetUtil.map(organizations, OrganizationDTO::new);
	}

	public OrganizationDTO(Organization organization) {
		_createDate = organization.getCreateDate();
		_dataSourceId = StringUtil.get(organization.getDataSourceId(), null);
		_id = StringUtil.get(organization.getId(), null);
		_modifiedDate = organization.getModifiedDate();
		_name = organization.getName();

		_organizationCustomFieldDTO = new OrganizationCustomFieldDTO(
			organization.getCustomFields());

		_organizationPK = StringUtil.get(
			organization.getOrganizationPK(), null);
		_parentName = organization.getParentName();
		_parentOrganizationPK = StringUtil.get(
			organization.getParentOrganizationPK(), null);
		_treePath = organization.getTreePath();
		_type = organization.getType();
	}

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

	@JsonProperty("dataSourceId")
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

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

	public String getName() {
		return _name;
	}

	public String getNameTreePath() {
		return _nameTreePath;
	}

	@JsonProperty("custom")
	public OrganizationCustomFieldDTO getOrganizationCustomFieldDTO() {
		return _organizationCustomFieldDTO;
	}

	@JsonProperty("organizations")
	public Set<OrganizationDTO> getOrganizationDTO() {
		return _organizationDTOs;
	}

	public String getOrganizationPK() {
		return _organizationPK;
	}

	public String getParentName() {
		return _parentName;
	}

	public String getParentOrganizationPK() {
		return _parentOrganizationPK;
	}

	public String getTreePath() {
		return _treePath;
	}

	public String getType() {
		return _type;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class OrganizationCustomFieldDTO {

		public OrganizationCustomFieldDTO(Set<Field> fields) {
			for (Field field : fields) {
				_fieldMap.put(
					field.getName(),
					Collections.singletonList(new FieldDTO(field)));
			}
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof OrganizationCustomFieldDTO)) {
				return false;
			}

			OrganizationCustomFieldDTO organizationCustomFieldDTO =
				(OrganizationCustomFieldDTO)obj;

			if (Objects.equals(
					_fieldMap, organizationCustomFieldDTO._fieldMap)) {

				return true;
			}

			return false;
		}

		@JsonAnyGetter
		public Map<String, Object> getField() {
			return _fieldMap;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_fieldMap);
		}

		public void setFieldMap(Map<String, Object> fieldMap) {
			_fieldMap = fieldMap;
		}

		private Map<String, Object> _fieldMap = new HashMap<>();

	}

	private Date _createDate;
	private String _dataSourceId;
	private String _id;
	private Date _modifiedDate;
	private String _name;
	private String _nameTreePath;
	private OrganizationCustomFieldDTO _organizationCustomFieldDTO;
	private Set<OrganizationDTO> _organizationDTOs;
	private String _organizationPK;
	private String _parentName;
	private String _parentOrganizationPK;
	private String _treePath;
	private String _type;

}