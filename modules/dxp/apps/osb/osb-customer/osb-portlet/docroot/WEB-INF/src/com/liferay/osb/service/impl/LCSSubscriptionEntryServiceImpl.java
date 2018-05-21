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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.LCSSubscriptionEntry;
import com.liferay.osb.service.base.LCSSubscriptionEntryServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.auth.PrincipalException;

import java.util.List;

/**
 * @author Amos Fong
 */
public class LCSSubscriptionEntryServiceImpl
	extends LCSSubscriptionEntryServiceBaseImpl {

	@JSONWebService
	public List<LCSSubscriptionEntry> getLCSSubscriptionEntries(
			String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		AccountEntry accountEntry =
			accountEntryPersistence.findByCorpProjectUuid(corpProjectUuid);

		return lcsSubscriptionEntryLocalService.getLCSSubscriptionEntries(
			accountEntry.getAccountEntryId());
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

}