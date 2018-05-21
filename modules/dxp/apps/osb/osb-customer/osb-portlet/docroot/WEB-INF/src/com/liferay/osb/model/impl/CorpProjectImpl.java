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