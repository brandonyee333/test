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

import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

/**
 * @author Ryan Park
 * @author Rachael Koestartyo
 */
public class CorpGroupImpl extends CorpGroupBaseImpl {

	public CorpGroupImpl() {
	}

	public Group getGroup() throws PortalException, SystemException {
		if (getOrganizationId() > 0) {
			return GroupLocalServiceUtil.getOrganizationGroup(
				OSBConstants.COMPANY_ID, getOrganizationId());
		}

		return null;
	}

	public long getGroupId() throws PortalException, SystemException {
		Group group = getGroup();

		if (group != null) {
			return group.getGroupId();
		}
		else {
			return 0;
		}
	}

	public Organization getOrganization()
		throws PortalException, SystemException {

		if (getOrganizationId() > 0) {
			return OrganizationLocalServiceUtil.getOrganization(
				getOrganizationId());
		}

		return null;
	}

}