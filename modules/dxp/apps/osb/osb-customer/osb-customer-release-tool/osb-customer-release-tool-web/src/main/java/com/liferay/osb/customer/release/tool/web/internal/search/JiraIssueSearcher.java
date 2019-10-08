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

package com.liferay.osb.customer.release.tool.web.internal.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.osb.customer.jira.rest.connector.configuration.JIRARESTConnectorConfigurationValues;
import com.liferay.osb.customer.jira.rest.connector.service.JIRAIssueRESTService;
import com.liferay.osb.customer.release.tool.configuration.ReleaseToolConfigurationValues;
import com.liferay.osb.customer.release.tool.web.internal.constants.ReleaseAssetCategoryProperty;
import com.liferay.osb.customer.release.tool.web.internal.util.ReleasesAssetCategoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = JiraIssueSearcher.class)
public class JiraIssueSearcher extends BaseSearcher {

	protected String buildJQL(
			String jiraFixPackCustomField, double fromFixPackVersion,
			double toFixPackVersion, String keywords, String[] components,
			String orderByType)
		throws PortalException {

		int pos = jiraFixPackCustomField.indexOf(StringPool.UNDERLINE);

		String jiraFixPackJQLField =
			"cf[" + jiraFixPackCustomField.substring(pos + 1) + "]";

		StringBundler sb = new StringBundler(27);

		sb.append("project in (\"");
		sb.append(
			StringUtil.merge(
				ReleaseToolConfigurationValues.FIX_PACK_JIRA_PROJECTS,
				"\",\""));
		sb.append("\") AND ");
		sb.append(jiraFixPackJQLField);
		sb.append(">=");
		sb.append(fromFixPackVersion);
		sb.append(" AND ");
		sb.append(jiraFixPackJQLField);
		sb.append("<=");
		sb.append(toFixPackVersion);

		if (Validator.isNotNull(keywords)) {
			sb.append(" AND (description ~ ");
			sb.append(StringUtil.quote(keywords));
			sb.append(" OR summary ~ ");
			sb.append(StringUtil.quote(keywords));
			sb.append(")");
		}

		for (int i = 0; i < components.length; i++) {
			if (i == 0) {
				sb.append(" AND (");
			}
			else {
				sb.append(" OR ");
			}

			sb.append("component in subcomponents(LPS, \"");
			sb.append(components[i]);
			sb.append("\", \"true\")");
		}

		if (!ArrayUtil.isEmpty(components)) {
			sb.append(" OR component in componentMatch(\"");
			sb.append(StringUtil.merge(components, "|"));
			sb.append("\"))");
		}

		sb.append(" AND level is empty");

		if (!orderByType.equals("asc")) {
			orderByType = "desc";
		}

		sb.append(" order by ");
		sb.append(jiraFixPackJQLField);
		sb.append(" ");
		sb.append(orderByType);

		return sb.toString();
	}

	protected JSONObject doSearch(
			PortletRequest portletRequest, MimeResponse mimeResponse)
		throws Exception {

		String product = ParamUtil.getString(portletRequest, "product");
		double productVersion = ParamUtil.getDouble(
			portletRequest, "productVersion");
		double fromFixPackVersion = ParamUtil.getDouble(
			portletRequest, "fromFixPackVersion");
		double toFixPackVersion = ParamUtil.getDouble(
			portletRequest, "toFixPackVersion");
		String keywords = ParamUtil.getString(portletRequest, "keywords");
		String[] components = ParamUtil.getStringValues(
			portletRequest, "components");
		int startAt = ParamUtil.getInteger(portletRequest, "startAt");
		int maxResults = ParamUtil.getInteger(
			portletRequest, "maxResults",
			ReleaseToolConfigurationValues.FIX_PACK_JIRA_MAX_RESULTS);
		String orderByType = ParamUtil.getString(portletRequest, "orderByType");

		AssetCategory productAssetCategory =
			_releasesAssetCategoryUtil.getProductAssetCategory(
				product, productVersion);

		String jiraFixPackCustomField =
			_releasesAssetCategoryUtil.getPropertyValue(
				productAssetCategory.getCategoryId(),
				ReleaseAssetCategoryProperty.JIRA_FIX_PACK_VERSION);

		String jql = buildJQL(
			jiraFixPackCustomField, fromFixPackVersion, toFixPackVersion,
			keywords, components, orderByType);

		JSONObject jiraResultsJSONObject = _jiraIssueRESTService.getJIRAIssues(
			jql, "renderedFields", _ISSUE_FIELDS + "," + jiraFixPackCustomField,
			startAt, maxResults);

		return processResults(
			productAssetCategory, jiraFixPackCustomField,
			jiraResultsJSONObject);
	}

	protected String getJiraIssueURL(String issueKey) {
		StringBundler sb = new StringBundler(4);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(JIRARESTConnectorConfigurationValues.JIRA_DOMAIN_NAME);
		sb.append("/browse/");
		sb.append(issueKey);

		return sb.toString();
	}

	protected JSONObject processJiraIssue(
		AssetCategory productAssetCategory, String jiraFixPackCustomField,
		JSONObject jiraIssueJSONObject) {

		JSONObject jsonObject = jsonFactory.createJSONObject();

		JSONObject fieldsJSONObject = jiraIssueJSONObject.getJSONObject(
			"fields");

		JSONArray componentsJSONArray = processJiraIssueComponents(
			fieldsJSONObject.getJSONArray("components"));

		jsonObject.put("components", componentsJSONArray);

		JSONObject renderedFieldsJSONObject = jiraIssueJSONObject.getJSONObject(
			"renderedFields");

		jsonObject.put(
			"description", renderedFieldsJSONObject.getString("description"));

		jsonObject.put("key", jiraIssueJSONObject.getString("key"));

		double fixPackVersion = GetterUtil.getDouble(
			fieldsJSONObject.getString(jiraFixPackCustomField));

		AssetCategory assetCategory =
			_releasesAssetCategoryUtil.getFixPackAssetCategory(
				productAssetCategory.getCategoryId(), fixPackVersion);

		if (assetCategory != null) {
			jsonObject.put("release", assetCategory.getName());
		}
		else {
			jsonObject.put("release", StringPool.BLANK);
		}

		jsonObject.put("summary", fieldsJSONObject.getString("summary"));
		jsonObject.put(
			"url", getJiraIssueURL(jiraIssueJSONObject.getString("key")));

		return jsonObject;
	}

	protected JSONArray processJiraIssueComponents(JSONArray jsonArray) {
		JSONArray componentsJSONArray = jsonFactory.createJSONArray();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			componentsJSONArray.put(jsonObject.getString("name"));
		}

		return componentsJSONArray;
	}

	protected JSONObject processResults(
		AssetCategory productAssetCategory, String jiraFixPackCustomField,
		JSONObject jiraResultsJSONObject) {

		JSONObject jsonObject = jsonFactory.createJSONObject();

		JSONArray jsonArray = jsonFactory.createJSONArray();

		JSONArray jiraIssuesJSONArray = jiraResultsJSONObject.getJSONArray(
			"issues");

		for (int i = 0; i < jiraIssuesJSONArray.length(); i++) {
			JSONObject jiraIssueJSONObject = jiraIssuesJSONArray.getJSONObject(
				i);

			jsonArray.put(
				processJiraIssue(
					productAssetCategory, jiraFixPackCustomField,
					jiraIssueJSONObject));
		}

		jsonObject.put("results", jsonArray);

		jsonObject.put("total", jiraResultsJSONObject.getInt("total"));

		return jsonObject;
	}

	private static final String _ISSUE_FIELDS =
		"components,description,key,summary";

	@Reference
	private JIRAIssueRESTService _jiraIssueRESTService;

	@Reference
	private ReleasesAssetCategoryUtil _releasesAssetCategoryUtil;

}