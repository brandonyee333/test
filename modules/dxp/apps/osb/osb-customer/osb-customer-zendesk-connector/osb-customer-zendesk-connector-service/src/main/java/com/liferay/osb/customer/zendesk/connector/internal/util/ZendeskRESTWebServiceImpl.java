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

package com.liferay.osb.customer.zendesk.connector.internal.util;

import com.liferay.osb.customer.zendesk.connector.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.connector.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskHttp;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskRESTWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true)
public class ZendeskRESTWebServiceImpl implements ZendeskRESTWebService {

	@Override
	public JSONObject addTranslation(ZendeskSection zendeskSection)
		throws PortalException {

		return _zendeskHttp.post(
			"help_center/sections/" + zendeskSection.getId() +
				"/translations.json",
			zendeskSection.toTranslationJSONObject());
	}

	@Override
	public JSONObject addZendeskArticle(ZendeskArticle zendeskArticle)
		throws PortalException {

		return _zendeskHttp.post(
			"help_center/sections/" + zendeskArticle.getSectionId() +
				"/articles.json",
			zendeskArticle.toJSONObject(true));
	}

	@Override
	public JSONObject addZendeskSection(ZendeskSection zendeskSection)
		throws PortalException {

		return _zendeskHttp.post(
			"help_center/categories/" + zendeskSection.getCategoryId() +
				"/sections.json",
			zendeskSection.toJSONObject(true));
	}

	@Override
	public JSONObject updateTranslation(ZendeskSection zendeskSection)
		throws PortalException {

		return _zendeskHttp.put(
			"help_center/sections/" + zendeskSection.getId() +
				"/translations/" + zendeskSection.getLocale() + ".json",
			zendeskSection.toTranslationJSONObject());
	}

	@Override
	public void updateZendeskArticle(ZendeskArticle zendeskArticle)
		throws PortalException {

		_zendeskHttp.put(
			"help_center/articles/" + zendeskArticle.getId() + ".json",
			zendeskArticle.toJSONObject(false));

		for (String locale : zendeskArticle.getLocales()) {
			_zendeskHttp.put(
				"help_center/articles/" + zendeskArticle.getId() +
					"/translations/" + locale + ".json",
				zendeskArticle.toTranslationJSONObject(locale));
		}
	}

	@Override
	public JSONObject updateZendeskSection(ZendeskSection zendeskSection)
		throws PortalException {

		return _zendeskHttp.put(
			"help_center/sections/" + zendeskSection.getId() + ".json",
			zendeskSection.toJSONObject(false));
	}

	@Reference
	private ZendeskHttp _zendeskHttp;

}