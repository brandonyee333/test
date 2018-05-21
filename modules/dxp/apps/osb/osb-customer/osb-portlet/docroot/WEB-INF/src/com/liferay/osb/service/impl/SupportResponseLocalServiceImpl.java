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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.DuplicateSupportResponseException;
import com.liferay.osb.exception.RequiredSupportResponseException;
import com.liferay.osb.exception.SupportResponseNameException;
import com.liferay.osb.exception.SupportResponseSupportLevelException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.base.SupportResponseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

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
		throws PortalException {

		User user = userLocalService.getUser(userId);
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

		supportResponsePersistence.update(supportResponse);

		recalculateHighestSupportResponses(supportResponseId);

		return supportResponse;
	}

	@Override
	public SupportResponse deleteSupportResponse(long supportResponseId)
		throws PortalException {

		if (offeringDefinitionPersistence.countBySupportResponseId(
				supportResponseId) > 0) {

			throw new RequiredSupportResponseException();
		}

		SupportResponse supportResponse = supportResponsePersistence.remove(
			supportResponseId);

		recalculateHighestSupportResponses(supportResponseId);

		return supportResponse;
	}

	public SupportResponse fetchSupportResponseByName(String name) {
		return supportResponsePersistence.fetchByName(name);
	}

	public SupportResponse updateSupportResponse(
			long supportResponseId, String name, int supportLevel,
			int severity1Response, int severity1Resolution,
			int severity2Response, int severity2Resolution,
			int severity3Response, int severity3Resolution)
		throws PortalException {

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

		supportResponsePersistence.update(supportResponse);

		recalculateHighestSupportResponses(supportResponseId);

		return supportResponse;
	}

	protected void recalculateHighestSupportResponses(long supportResponseId)
		throws PortalException {

		List<AccountEntry> accountEntries =
			accountEntryFinder.findBySupportResponse(supportResponseId);

		for (AccountEntry accountEntry : accountEntries) {
			accountEntryLocalService.recalculateHighestSupportResponse(
				accountEntry.getAccountEntryId());
		}
	}

	protected void validate(
			long supportResponseId, String name, int supportLevel)
		throws PortalException {

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