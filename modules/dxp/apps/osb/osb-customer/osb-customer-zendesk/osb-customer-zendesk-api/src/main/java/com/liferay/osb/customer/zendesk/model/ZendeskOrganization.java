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

package com.liferay.osb.customer.zendesk.model;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.Set;

/**
 * @author Kyle Bischof
 */
public class ZendeskOrganization {

	public ZendeskOrganization() {
	}

	public String getExternalId() {
		return _externalId;
	}

	public String getName() {
		return _name;
	}

	public String getPartnerFirstLineSupport() {
		return _partnerFirstLineSupport;
	}

	public String getPartnerOrganization() {
		return _partnerOrganization;
	}

	public String getSharedComments() {
		return _sharedComments;
	}

	public String getSharedTickets() {
		return _sharedTickets;
	}

	public String getSLA() {
		return _sla;
	}

	public String getStatus() {
		return _status;
	}

	public String getSupportLanguage() {
		return _supportLanguage;
	}

	public String getSupportRegion() {
		return _supportRegion;
	}

	public Set<String> getTags() {
		return _tags;
	}

	public String getTier() {
		return _tier;
	}

	public void setExternalId(String externalId) {
		_externalId = externalId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPartnerFirstLineSupport(String partnerFirstLineSupport) {
		_partnerFirstLineSupport = partnerFirstLineSupport;
	}

	public void setPartnerOrganization(String partnerOrganization) {
		_partnerOrganization = partnerOrganization;
	}

	public void setSharedComments(String sharedComments) {
		_sharedComments = sharedComments;
	}

	public void setSharedTickets(String sharedTickets) {
		_sharedTickets = sharedTickets;
	}

	public void setSLA(String sla) {
		_sla = sla;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setSupportLanguage(String supportLanguage) {
		_supportLanguage = supportLanguage;
	}

	public void setSupportRegion(String supportRegion) {
		_supportRegion = supportRegion;
	}

	public void setTags(Set<String> tags) {
		_tags = tags;
	}

	public void setTier(String tier) {
		_tier = tier;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject organizationJSONObject = JSONFactoryUtil.createJSONObject();

		organizationJSONObject.put("name", _name);

		if (Validator.isNotNull(_externalId)) {
			organizationJSONObject.put("external_id", _externalId);
		}

		if (Validator.isNotNull(_sharedTickets)) {
			organizationJSONObject.put("shared_tickets", _sharedTickets);
		}

		if (Validator.isNotNull(_sharedComments)) {
			organizationJSONObject.put("shared_comments", _sharedComments);
		}

		JSONObject organizationFieldsJSONObject =
			JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(_partnerFirstLineSupport)) {
			organizationFieldsJSONObject.put(
				"partner_first_line_support", _partnerFirstLineSupport);
		}

		if (Validator.isNotNull(_partnerOrganization)) {
			organizationFieldsJSONObject.put(
				"partner_organization", _partnerOrganization);
		}

		if (Validator.isNotNull(_sla)) {
			organizationFieldsJSONObject.put("sla", _sla);
		}

		if (Validator.isNotNull(_status)) {
			organizationFieldsJSONObject.put("status", _status);
		}

		if (Validator.isNotNull(_supportLanguage)) {
			organizationFieldsJSONObject.put(
				"support_language", _supportLanguage);
		}

		if (Validator.isNotNull(_supportRegion)) {
			organizationFieldsJSONObject.put("support_region", _supportRegion);
		}

		if (Validator.isNotNull(_tier)) {
			organizationFieldsJSONObject.put("tier", _tier);
		}

		organizationJSONObject.put(
			"organization_fields", organizationFieldsJSONObject);

		JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

		if (_tags != null) {
			for (String tag : _tags) {
				tagsJSONArray.put(tag);
			}

			organizationJSONObject.put("tags", tagsJSONArray);
		}

		jsonObject.put("organization", organizationJSONObject);

		return jsonObject;
	}

	private String _externalId;
	private String _name;
	private String _partnerFirstLineSupport;
	private String _partnerOrganization;
	private String _sharedComments;
	private String _sharedTickets;
	private String _sla;
	private String _status;
	private String _supportLanguage;
	private String _supportRegion;
	private Set<String> _tags;
	private String _tier;

}