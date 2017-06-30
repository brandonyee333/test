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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.DuplicateSupportResponseException;
import com.liferay.osb.RequiredSupportResponseException;
import com.liferay.osb.SupportResponseNameException;
import com.liferay.osb.SupportResponseSupportLevelException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.base.SupportResponseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class SupportResponseLocalServiceImpl
	extends SupportResponseLocalServiceBaseImpl {

	public SupportResponse addSupportResponse(
			long userId, String name, int supportLevel, int severity1Response,
			int severity1Resolution, int severity2Response,
			int severity2Resolution, int severity3Response,
			int severity3Resolution)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(0, name, supportLevel);

		long supportResponseId = counterLocalService.increment();

		SupportResponse supportResponse = supportResponsePersistence.create(
			supportResponseId);

		supportResponse.setUserId(user.getUserId());
		supportResponse.setUserName(user.getFullName());
		supportResponse.setCreateDate(now);
		supportResponse.setModifiedDate(now);
		supportResponse.setName(name);
		supportResponse.setSupportLevel(supportLevel);
		supportResponse.setSeverity1Response(severity1Response);
		supportResponse.setSeverity1Resolution(severity1Resolution);
		supportResponse.setSeverity2Response(severity2Response);
		supportResponse.setSeverity2Resolution(severity2Resolution);
		supportResponse.setSeverity3Response(severity3Response);
		supportResponse.setSeverity3Resolution(severity3Resolution);

		supportResponsePersistence.update(supportResponse, false);

		recalculateHighestSupportResponses(supportResponseId);

		return supportResponse;
	}

	@Override
	public SupportResponse deleteSupportResponse(long supportResponseId)
		throws PortalException, SystemException {

		if (offeringDefinitionPersistence.countBySupportResponseId(
				supportResponseId) > 0) {

			throw new RequiredSupportResponseException();
		}

		SupportResponse supportResponse = supportResponsePersistence.remove(
			supportResponseId);

		recalculateHighestSupportResponses(supportResponseId);

		return supportResponse;
	}

	public SupportResponse fetchSupportResponseByName(String name)
		throws SystemException {

		return supportResponsePersistence.fetchByName(name);
	}

	public SupportResponse getSupportResponseByName(String name)
		throws PortalException, SystemException {

		return supportResponsePersistence.findByName(name);
	}

	public SupportResponse updateSupportResponse(
			long supportResponseId, String name, int supportLevel,
			int severity1Response, int severity1Resolution,
			int severity2Response, int severity2Resolution,
			int severity3Response, int severity3Resolution)
		throws PortalException, SystemException {

		validate(supportResponseId, name, supportLevel);

		SupportResponse supportResponse =
			supportResponsePersistence.findByPrimaryKey(supportResponseId);

		supportResponse.setModifiedDate(new Date());
		supportResponse.setName(name);
		supportResponse.setSupportLevel(supportLevel);
		supportResponse.setSeverity1Response(severity1Response);
		supportResponse.setSeverity1Resolution(severity1Resolution);
		supportResponse.setSeverity2Response(severity2Response);
		supportResponse.setSeverity2Resolution(severity2Resolution);
		supportResponse.setSeverity3Response(severity3Response);
		supportResponse.setSeverity3Resolution(severity3Resolution);

		supportResponsePersistence.update(supportResponse, false);

		recalculateHighestSupportResponses(supportResponseId);

		return supportResponse;
	}

	protected void recalculateHighestSupportResponses(long supportResponseId)
		throws PortalException, SystemException {

		List<AccountEntry> accountEntries =
			accountEntryFinder.findBySupportResponse(supportResponseId);

		for (AccountEntry accountEntry : accountEntries) {
			accountEntryLocalService.recalculateHighestSupportResponse(
				accountEntry.getAccountEntryId());
		}
	}

	protected void validate(
			long supportResponseId, String name, int supportLevel)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			throw new SupportResponseNameException();
		}

		if (supportLevel <= 0) {
			throw new SupportResponseSupportLevelException();
		}

		SupportResponse supportResponse =
			supportResponsePersistence.fetchByName(name);

		if ((supportResponse != null) &&
			(supportResponse.getSupportResponseId() != supportResponseId)) {

			throw new DuplicateSupportResponseException();
		}
	}

}