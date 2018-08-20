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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.util;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskLocales;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true)
public class ZendeskModelListenerUtil {

	public static String convertToTag(String s) {
		s = StringUtil.toLowerCase(s);

		s = StringUtil.replace(s, CharPool.SPACE, CharPool.UNDERLINE);
		s = StringUtil.replace(s, CharPool.OPEN_PARENTHESIS, StringPool.BLANK);
		s = StringUtil.replace(s, CharPool.CLOSE_PARENTHESIS, StringPool.BLANK);

		return s;
	}

	public static String convertToZendeskLocale(String locale) {
		if (locale.equals("en_US")) {
			return ZendeskLocales.US;
		}
		else if (locale.equals("es_ES")) {
			return ZendeskLocales.SPAIN;
		}
		else if (locale.equals("ja_JP")) {
			return ZendeskLocales.JAPAN;
		}
		else if (locale.equals("pt_BR")) {
			return ZendeskLocales.PORTUGAL;
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String getExternalId(Class<?> clazz, long classPK) {
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(clazz);

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, classPK, ExternalIdMapperConstants.TYPE_ZENDESK);

		if (!externalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			return externalIdMapper.getExternalId();
		}
		else {
			return null;
		}
	}

	public static JSONObject getTagsJSONObject(
		JSONArray tagsJSONArray, String resource, long id) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("id", id);
		jsonObject.put("resource", resource);

		JSONObject tagsJSONObject = JSONFactoryUtil.createJSONObject();

		tagsJSONObject.put("tags", tagsJSONArray);

		jsonObject.put("tagsArray", tagsJSONObject);

		return jsonObject;
	}

	public static boolean hasActiveSupportOffering(
		AccountCustomer accountCustomer) {

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getUserAccountEntries(
				accountCustomer.getUserId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			if (accountEntry.hasActiveSupportOffering()) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasActiveSupportOffering(AccountEntry accountEntry) {
		if ((accountEntry.getStatus() ==
				WorkflowConstants.STATUS_APPROVED) &&
			accountEntry.hasActiveSupportOffering()) {

			return true;
		}

		return false;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}