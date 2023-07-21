/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.service.impl;

import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.service.base.LoopDivisionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Ethan Bustad
 */
public class LoopDivisionLocalServiceImpl
	extends LoopDivisionLocalServiceBaseImpl {

	public LoopDivision fetchRootLoopDivision(long companyId) {
		List<LoopDivision> loopDivisions = loopDivisionPersistence.findByCI_T(
			companyId, LoopConstants.TYPE_LOOP_DIVISION_ROOT);

		if (loopDivisions.isEmpty()) {
			return null;
		}

		return loopDivisions.get(0);
	}

	public String getRootLoopDivisionName(long companyId)
		throws PortalException {

		LoopDivision loopDivision = fetchRootLoopDivision(companyId);

		if (loopDivision == null) {
			return StringPool.BLANK;
		}

		Organization organization = organizationLocalService.getOrganization(
			loopDivision.getOrganizationId());

		return organization.getName();
	}

}