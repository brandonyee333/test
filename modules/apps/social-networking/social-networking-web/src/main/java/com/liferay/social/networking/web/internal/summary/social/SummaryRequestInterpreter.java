/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.summary.social;

import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.social.kernel.model.SocialRequestInterpreter;
import com.liferay.social.kernel.service.SocialActivityLocalService;
import com.liferay.social.kernel.service.SocialRelationLocalService;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.web.internal.social.BaseSocialNetworkingRequestInterpreter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = "javax.portlet.name=" + SocialNetworkingPortletKeys.SUMMARY,
	service = SocialRequestInterpreter.class
)
public class SummaryRequestInterpreter
	extends BaseSocialNetworkingRequestInterpreter {

	@Override
	protected SocialActivityLocalService getSocialActivityLocalService() {
		return _socialActivityLocalService;
	}

	@Override
	protected SocialRelationLocalService getSocialRelationLocalService() {
		return _socialRelationLocalService;
	}

	@Override
	protected UserLocalService getUserLocalService() {
		return _userLocalService;
	}

	@Reference(unbind = "-")
	protected void setSocialActivityLocalService(
		SocialActivityLocalService socialActivityLocalService) {

		_socialActivityLocalService = socialActivityLocalService;
	}

	@Reference(unbind = "-")
	protected void setSocialRelationLocalService(
		SocialRelationLocalService socialRelationLocalService) {

		_socialRelationLocalService = socialRelationLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private SocialActivityLocalService _socialActivityLocalService;
	private SocialRelationLocalService _socialRelationLocalService;
	private UserLocalService _userLocalService;

}