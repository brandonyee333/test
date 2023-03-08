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

import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.util.StringUtil;

/**
 * @author Marcos Martins
 */
@GraphQLType
public class DXPOrganizationDTO extends DXPEntityDTO {

	public DXPOrganizationDTO(BQOrganization bqOrganization) {
		super(bqOrganization);

		_organizationPK = StringUtil.get(
			bqOrganization.getOrganizationId(), null);
		_parentName = bqOrganization.getParentOrganizationName();
		_parentOrganizationPK = StringUtil.get(
			bqOrganization.getParentOrganizationId(), null);
		_treePath = bqOrganization.getTreePath();
		_type = StringUtil.get(bqOrganization.getType());
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

	private final String _organizationPK;
	private final String _parentName;
	private final String _parentOrganizationPK;
	private final String _treePath;
	private final String _type;

}