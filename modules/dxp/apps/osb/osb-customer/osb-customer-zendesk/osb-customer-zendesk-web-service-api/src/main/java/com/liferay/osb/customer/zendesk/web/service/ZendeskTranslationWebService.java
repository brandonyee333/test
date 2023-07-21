/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskTranslationWebService {

	public ZendeskTranslation addZendeskTranslation(
			String sourceType, long sourceId, String zendeskLocale,
			String title, String body)
		throws Exception;

	public List<ZendeskTranslation> getZendeskTranslations(
			String sourceType, long sourceId)
		throws PortalException;

	public ZendeskTranslation updateZendeskTranslation(
			String sourceType, long sourceId, String zendeskLocale,
			String title, String body)
		throws Exception;

}