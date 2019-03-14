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
import com.liferay.osb.customer.jira.rest.connector.service.JIRAIssueLocalService;
import com.liferay.osb.customer.release.tool.configuration.ReleaseToolConfigurationValues;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackAssetCategoryConstants;
import com.liferay.osb.customer.release.tool.web.internal.constants.JiraIssueField;
import com.liferay.osb.customer.release.tool.web.internal.util.FixPacksAssetCategoryUtil;
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

import java.util.List;

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
			String product, double fromProductVersion,
			double fromFixPackVersion, double toProductVersion,
			double toFixPackVersion, String keywords, String[] components,
			String orderByType)
		throws PortalException {

		StringBundler sb = new StringBundler();

		sb.append("project in (\"");
		sb.append(
			StringUtil.merge(
				ReleaseToolConfigurationValues.FIX_PACK_JIRA_PROJECTS,
				"\",\""));
		sb.append("\")");

		List<AssetCategory> productAssetCategories =
			_fixPacksAssetCategoryUtil.getProductAssetCategories(
				product, fromProductVersion, toProductVersion);

		if (productAssetCategories.size() == 1) {
			AssetCategory assetCategory = productAssetCategories.get(0);

			String jiraProductVersion =
				_fixPacksAssetCategoryUtil.getPropertyValue(
					assetCategory.getCategoryId(),
					FixPackAssetCategoryConstants.
						PROPERTY_JIRA_PRODUCT_VERSION);

			sb.append(" AND ");
			sb.append(StringUtil.quote(JiraIssueField.PRODUCT_VERSION));
			sb.append("=");
			sb.append(StringUtil.quote(jiraProductVersion));
			sb.append(" AND ");
			sb.append(
				StringUtil.quote(JiraIssueField.PRODUCT_FIX_PACK_VERSION));
			sb.append(">=");
			sb.append(fromFixPackVersion);
			sb.append(" AND ");
			sb.append(
				StringUtil.quote(JiraIssueField.PRODUCT_FIX_PACK_VERSION));
			sb.append("<=");
			sb.append(toFixPackVersion);
		}
		else {
			sb.append(" AND (");

			for (int i = 0; i < productAssetCategories.size(); i++) {
				AssetCategory assetCategory = productAssetCategories.get(i);

				String jiraProductVersion =
					_fixPacksAssetCategoryUtil.getPropertyValue(
						assetCategory.getCategoryId(),
						FixPackAssetCategoryConstants.
							PROPERTY_JIRA_PRODUCT_VERSION);

				if (i == 0) {
					sb.append("(");
					sb.append(StringUtil.quote(JiraIssueField.PRODUCT_VERSION));
					sb.append("=");
					sb.append(StringUtil.quote(jiraProductVersion));
					sb.append(" AND ");
					sb.append(
						StringUtil.quote(
							JiraIssueField.PRODUCT_FIX_PACK_VERSION));
					sb.append(">=");
					sb.append(fromFixPackVersion);
					sb.append(")");
				}
				else if ((i + 1) == productAssetCategories.size()) {
					sb.append(" OR (");
					sb.append(StringUtil.quote(JiraIssueField.PRODUCT_VERSION));
					sb.append("=");
					sb.append(StringUtil.quote(jiraProductVersion));
					sb.append(" AND ");
					sb.append(
						StringUtil.quote(
							JiraIssueField.PRODUCT_FIX_PACK_VERSION));
					sb.append("<=");
					sb.append(toFixPackVersion);
					sb.append(")");
				}
				else {
					sb.append(" OR (");
					sb.append(StringUtil.quote(JiraIssueField.PRODUCT_VERSION));
					sb.append("=");
					sb.append(StringUtil.quote(jiraProductVersion));
					sb.append(")");
				}
			}

			sb.append(")");
		}

		if (Validator.isNotNull(keywords)) {
			sb.append(" AND (description ~ ");
			sb.append(StringUtil.quote(keywords));
			sb.append(" OR summary ~ ");
			sb.append(StringUtil.quote(keywords));
			sb.append(")");
		}

		if (!ArrayUtil.isEmpty(components)) {
			sb.append(" AND component in (\"");
			sb.append(StringUtil.merge(components, "\",\""));
			sb.append("\")");
		}

		sb.append(" AND level is empty");

		if (!orderByType.equals("desc")) {
			orderByType = "asc";
		}

		sb.append(" order by ");
		sb.append(StringUtil.quote(JiraIssueField.PRODUCT_VERSION));
		sb.append(" ");
		sb.append(orderByType);
		sb.append(", ");
		sb.append(StringUtil.quote(JiraIssueField.PRODUCT_FIX_PACK_VERSION));
		sb.append(" ");
		sb.append(orderByType);

		return sb.toString();
	}

	protected JSONObject doSearch(
			PortletRequest portletRequest, MimeResponse mimeResponse)
		throws Exception {

		String product = ParamUtil.getString(portletRequest, "product");
		double fromProductVersion = ParamUtil.getDouble(
			portletRequest, "fromProductVersion");
		double fromFixPackVersion = ParamUtil.getDouble(
			portletRequest, "fromFixPackVersion");
		double toProductVersion = ParamUtil.getDouble(
			portletRequest, "toProductVersion");
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

		String jql = buildJQL(
			product, fromProductVersion, fromFixPackVersion, toProductVersion,
			toFixPackVersion, keywords, components, orderByType);

		JSONObject jiraResultsJSONObject = _jiraIssueLocalService.getJIRAIssues(
			jql, "renderedFields", _ISSUE_FIELDS, startAt, maxResults);

		return processResults(jiraResultsJSONObject);
	}

	protected String getJiraIssueURL(String issueKey) {
		StringBundler sb = new StringBundler(4);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(JIRARESTConnectorConfigurationValues.JIRA_DOMAIN_NAME);
		sb.append("/browse/");
		sb.append(issueKey);

		return sb.toString();
	}

	protected JSONObject processJiraIssue(JSONObject jiraIssueJSONObject) {
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

		JSONObject productVersionJSONObject = fieldsJSONObject.getJSONObject(
			"customfield_13120");

		String productVersion = productVersionJSONObject.getString("value");

		jsonObject.put("product", productVersion);

		double fixPackVersion = GetterUtil.getDouble(
			fieldsJSONObject.getString("customfield_22520"));

		AssetCategory assetCategory =
			_fixPacksAssetCategoryUtil.getFixPackAssetCategory(
				productVersion, fixPackVersion);

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

	protected JSONObject processResults(JSONObject jiraResultsJSONObject) {
		JSONObject jsonObject = jsonFactory.createJSONObject();

		JSONArray jsonArray = jsonFactory.createJSONArray();

		JSONArray jiraIssuesJSONArray = jiraResultsJSONObject.getJSONArray(
			"issues");

		for (int i = 0; i < jiraIssuesJSONArray.length(); i++) {
			JSONObject jiraIssueJSONObject = jiraIssuesJSONArray.getJSONObject(
				i);

			jsonArray.put(processJiraIssue(jiraIssueJSONObject));
		}

		jsonObject.put("results", jsonArray);

		jsonObject.put("total", jiraResultsJSONObject.getInt("total"));

		return jsonObject;
	}

	private static final String _ISSUE_FIELDS =
		"components,customfield_13120,customfield_22520,description,key," +
			"summary";

	@Reference
	private FixPacksAssetCategoryUtil _fixPacksAssetCategoryUtil;

	@Reference
	private JIRAIssueLocalService _jiraIssueLocalService;

}