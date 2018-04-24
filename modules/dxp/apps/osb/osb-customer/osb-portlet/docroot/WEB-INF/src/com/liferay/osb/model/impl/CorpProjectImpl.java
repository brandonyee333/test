/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CorpProjectImpl extends CorpProjectBaseImpl {

	public CorpProjectImpl() {
	}

	public Group getGroup() throws PortalException {
		return GroupLocalServiceUtil.getOrganizationGroup(
			OSBConstants.COMPANY_ID, getOrganizationId());
	}

	public String getOrganizationUuid() throws PortalException {
		if (Validator.isNotNull(_organizationUuid)) {
			return _organizationUuid;
		}

		if (getOrganizationId() > 0) {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					getOrganizationId());

			return organization.getUuid();
		}

		return StringPool.BLANK;
	}

	public void setOrganizationUuid(String organizationUuid) {
		_organizationUuid = organizationUuid;
	}

	private String _organizationUuid;

}