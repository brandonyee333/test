/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.portal;

import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.portal.LCSSubscriptionEntry;
import com.liferay.lcs.client.platform.portal.LCSSubscriptionEntryClient;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSSubscriptionEntryClient.class)
public class LCSSubscriptionEntryClientImpl
	implements LCSSubscriptionEntryClient {

	@Override
	public LCSSubscriptionEntry fetchLCSSubscriptionEntry(String key)
		throws LCSException {

		return _lcsPortalClient.doGetToObject(
			LCSSubscriptionEntry.class,
			_URL_LCS_SUBSCRIPTION_ENTRY + "/find/" + key);
	}

	private static final String _URL_LCS_SUBSCRIPTION_ENTRY =
		"/o/osb-lcs-rest/LCSSubscriptionEntry";

	@Reference
	private LCSPortalClient _lcsPortalClient;

}