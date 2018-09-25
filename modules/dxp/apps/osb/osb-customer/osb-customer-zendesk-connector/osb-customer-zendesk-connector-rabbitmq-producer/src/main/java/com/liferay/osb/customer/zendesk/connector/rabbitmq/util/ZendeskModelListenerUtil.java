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
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true)
public class ZendeskModelListenerUtil {

	public static String convertToTag(ProductEntry productEntry) {
		if (productEntry.isAnalyticsCloud()) {
			return ZendeskTagConstants.LIFERAY_ANALYTICS_CLOUD;
		}
		else if (productEntry.isCommerce()) {
			return ZendeskTagConstants.LIFERAY_COMMERCE;
		}
		else if (productEntry.isDeveloperTools()) {
			return ZendeskTagConstants.DEVELOPER_TOOLS;
		}
		else if (productEntry.isDeviceDetection()) {
			return ZendeskTagConstants.MOBILE_DEVICE_DETECTION;
		}
		else if (productEntry.isDigitalEnterprise()) {
			return ZendeskTagConstants.LIFERAY_DXP;
		}
		else if (productEntry.isEnterpriseSearch()) {
			return ZendeskTagConstants.ENTERPRISE_SEARCH;
		}
		else if (productEntry.isManagementTools()) {
			return ZendeskTagConstants.MANAGEMENT_TOOLS_LCS;
		}
		else if (productEntry.isMobileExperience()) {
			return ZendeskTagConstants.MOBILE_EXPERIENCE;
		}
		else if (productEntry.isPortal()) {
			return ZendeskTagConstants.LIFERAY_PORTAL;
		}
		else if (productEntry.isProductivityTools()) {
			return ZendeskTagConstants.PRODUCTIVITY_TOOLS_SYNC;
		}
		else if (productEntry.isSocialOffice()) {
			return ZendeskTagConstants.SOCIAL_OFFICE;
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String convertToZendeskLocale(String locale) {
		if (locale.equals("zh_CN")) {
			return ZendeskLocales.CHINA;
		}
		else if (locale.equals("en_US")) {
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

	public static JSONObject getTagsJSONObject(
		Set<String> tags, String resource, long id) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("id", id);
		jsonObject.put("resource", resource);

		JSONObject tagsJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

		for (String tag : tags) {
			tagsJSONArray.put(tag);
		}

		tagsJSONObject.put("tags", tagsJSONArray);

		jsonObject.put("tagsArray", tagsJSONObject);

		return jsonObject;
	}

	public static ZendeskUser getZendeskUser(
			AccountEntry accountEntry, Set<String> tags, User user)
		throws PortalException {

		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmail(user.getEmailAddress());
		zendeskUser.setExternalId(user.getUuid());

		String locale = convertToZendeskLocale(user.getLanguageId());

		zendeskUser.setLocale(locale);

		zendeskUser.setName(user.getFullName());

		if (accountEntry != null) {
			zendeskUser.setOrganizationName(accountEntry.getName());
		}

		if (tags != null) {
			zendeskUser.setTags(tags);
		}

		return zendeskUser;
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