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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.model.CorpProjectMessageClp;
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

import java.util.List;

/**
 * @author Joan Kim
 */
public class CorpProjectImpl extends CorpProjectBaseImpl {

	public CorpProjectImpl() {
	}

	@JsonDeserialize(contentAs = CorpProjectMessageClp.class)
	public List<CorpProjectMessage> getCorpProjectMessages()
		throws SystemException {

		return CorpProjectMessageLocalServiceUtil.getCorpProjectMessages(
			getCorpProjectId());
	}

	public Group getGroup() throws PortalException, SystemException {
		return GroupLocalServiceUtil.getOrganizationGroup(
			OSBConstants.COMPANY_ID, getOrganizationId());
	}

	public long getGroupId() throws PortalException, SystemException {
		Group group = getGroup();

		return group.getGroupId();
	}

	public Organization getOrganization()
		throws PortalException, SystemException {

		return OrganizationLocalServiceUtil.getOrganization(
			getOrganizationId());
	}

}