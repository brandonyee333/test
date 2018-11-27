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

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ZendeskOrganizationWebService.class
)
public class DefaultZendeskOrganizationWebService
	implements ZendeskOrganizationWebService {

	public ZendeskOrganization createOrUpdateZendeskOrganization(
			String accountCode, String details, String externalId, String name,
			String notes, String partnerFirstLineSupport,
			String partnerJiraProject, String partnerCode, String sla,
			String status, String supportLanguage, String supportRegion,
			String tier, Set<String> tags)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATIONS_CREATE_OR_UPDATE;

		JSONObject zendeskOrganizationJSONObject =
			getZendeskOrganizationJSONObject(
				accountCode, details, externalId, name, notes,
				partnerFirstLineSupport, partnerJiraProject, partnerCode, sla,
				status, supportLanguage, supportRegion, tier, tags);

		JSONObject responseJSONObject = _zendeskBaseWebService.post(
			endpoint, zendeskOrganizationJSONObject.toString());

		_messagePublisherUtil.sendEventNotification(
			"zendesk.organization.create", responseJSONObject);

		JSONObject organizationJSONObject = responseJSONObject.getJSONObject(
			"organization");

		return _zendeskConverter.toZendeskOrganization(organizationJSONObject);
	}

	public ZendeskOrganization getZendeskOrganization(String externalId)
		throws PortalException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("external_id", externalId);

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "organizations/search.json",
			parameters);

		JSONArray organizationsJSONArray = responseJSONObject.getJSONArray(
			"organizations");

		if (organizationsJSONArray.length() <= 0) {
			return null;
		}

		return _zendeskConverter.toZendeskOrganization(
			organizationsJSONArray.getJSONObject(0));
	}

	protected JSONObject getZendeskOrganizationJSONObject(
		String accountCode, String details, String externalId, String name,
		String notes, String partnerFirstLineSupport, String partnerJiraProject,
		String partnerOrganization, String sla, String status,
		String supportLanguage, String supportRegion, String tier,
		Set<String> tags) {

		JSONObject organizationJSONObject = JSONFactoryUtil.createJSONObject();

		organizationJSONObject.put("details", details);
		organizationJSONObject.put("external_id", externalId);
		organizationJSONObject.put("name", name);
		organizationJSONObject.put("notes", notes);

		JSONObject organizationFieldsJSONObject =
			JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(accountCode)) {
			organizationFieldsJSONObject.put("account_code", accountCode);
		}

		if (Validator.isNotNull(partnerFirstLineSupport)) {
			organizationFieldsJSONObject.put(
				"partner_first_line_support", partnerFirstLineSupport);
		}

		if (Validator.isNotNull(partnerJiraProject)) {
			organizationFieldsJSONObject.put(
				"partner_jira_project", partnerJiraProject);
		}

		if (Validator.isNotNull(partnerOrganization)) {
			organizationFieldsJSONObject.put(
				"partner_organization", partnerOrganization);
		}

		if (Validator.isNotNull(sla)) {
			organizationFieldsJSONObject.put("sla", sla);
		}

		if (Validator.isNotNull(status)) {
			organizationFieldsJSONObject.put("status", status);
		}

		if (Validator.isNotNull(supportLanguage)) {
			organizationFieldsJSONObject.put(
				"support_language", supportLanguage);
		}

		if (Validator.isNotNull(supportRegion)) {
			organizationFieldsJSONObject.put("support_region", supportRegion);
		}

		if (Validator.isNotNull(tier)) {
			organizationFieldsJSONObject.put("tier", tier);
		}

		organizationJSONObject.put(
			"organization_fields", organizationFieldsJSONObject);

		organizationJSONObject.put("shared_comments", Boolean.TRUE.toString());
		organizationJSONObject.put("shared_tickets", Boolean.TRUE.toString());

		JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

		if ((tags != null) && !tags.isEmpty()) {
			for (String tag : tags) {
				tagsJSONArray.put(tag);
			}

			organizationJSONObject.put("tags", tagsJSONArray);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("organization", organizationJSONObject);

		return jsonObject;
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

	@Reference
	private ZendeskConverter _zendeskConverter;

}