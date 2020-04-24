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