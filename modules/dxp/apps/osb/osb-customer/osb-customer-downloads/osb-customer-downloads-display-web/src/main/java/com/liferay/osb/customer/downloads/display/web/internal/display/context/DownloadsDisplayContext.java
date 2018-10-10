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

package com.liferay.osb.customer.downloads.display.web.internal.display.context;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DDMStructureConstants;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.ActionRequest;
import javax.portlet.MimeResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceURL;

/**
 * @author Amos Fong
 */
public class DownloadsDisplayContext {

	public DownloadsDisplayContext(
			RenderRequest renderRequest, MimeResponse mimeResponse)
		throws Exception {

		_renderRequest = renderRequest;
		_mimeResponse = mimeResponse;

		_ddmIndexer = (DDMIndexer)_renderRequest.getAttribute(
			DDMIndexer.class.getName());
		_themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences portletPreferences = _renderRequest.getPreferences();

		String ddmStructureKey = portletPreferences.getValue(
			"ddmStructureKey", null);

		if (Validator.isNotNull(ddmStructureKey)) {
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(
				JournalArticle.class);

			_ddmStructure = DDMStructureLocalServiceUtil.getStructure(
				_themeDisplay.getCompanyGroupId(), classNameId,
				ddmStructureKey);
		}
		else {
			_ddmStructure = null;
		}
	}

	public String getAcceptAgreementURL(String agreement, String version) {
		PortletURL acceptAgreementURL = _mimeResponse.createActionURL();

		acceptAgreementURL.setParameter(
			ActionRequest.ACTION_NAME, "acceptAgreement");
		acceptAgreementURL.setParameter("agreement", agreement);
		acceptAgreementURL.setParameter("version", version);

		return acceptAgreementURL.toString();
	}

	public String getDDMStructureKey() {
		return _ddmStructure.getStructureKey();
	}

	public JSONArray getProductsJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Set<String> products = getProducts();

		for (String product : products) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("fileTypes", getFileTypesJSONArray(product));
			jsonObject.put(
				"name", DDMStructureConstants.getProductLabel(product));
			jsonObject.put("value", product);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public SearchContainer getSearchContainer() throws PortalException {
		String fileType = ParamUtil.getString(_renderRequest, "fileType");
		String product = ParamUtil.getString(_renderRequest, "product");

		PortletURL iteratorURL = _mimeResponse.createRenderURL();

		iteratorURL.setParameter("product", product);
		iteratorURL.setParameter("fileType", fileType);

		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, iteratorURL, null, null);

		searchContainer.setSearch(true);

		Indexer indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		SearchContext searchContext = buildSearchContext(
			fileType, product, searchContainer.getStart(),
			searchContainer.getEnd());

		Hits hits = indexer.search(searchContext);

		searchContainer.setTotal(hits.getLength());

		List results = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			long classPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.fetchLatestArticle(
					classPK, WorkflowConstants.STATUS_APPROVED);

			results.add(journalArticle);
		}

		searchContainer.setResults(results);

		return searchContainer;
	}

	public String getVerifyAgreementURL(String agreement, String version) {
		ResourceURL verifyAgreementURL = _mimeResponse.createResourceURL();

		verifyAgreementURL.setParameter("agreement", agreement);
		verifyAgreementURL.setParameter("version", version);
		verifyAgreementURL.setResourceID("/verify_agreement");

		return verifyAgreementURL.toString();
	}

	protected SearchContext buildSearchContext(
		String fileType, String product, int start, int end) {

		SearchContext searchContext = new SearchContext();

		searchContext.setAndSearch(true);

		searchContext.setAttribute(
			Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
		searchContext.setAttribute(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setAttribute(
			"ddmStructureId", _ddmStructure.getStructureId());
		searchContext.setAttribute(
			"ddmStructureKey", _ddmStructure.getStructureKey());

		if (Validator.isNotNull(fileType)) {
			searchContext.setAttribute("fileType", fileType);
		}

		searchContext.setAttribute("head", Boolean.TRUE);
		searchContext.setAttribute("latest", Boolean.TRUE);

		if (Validator.isNotNull(product)) {
			searchContext.setAttribute("product", product);
		}

		searchContext.setCompanyId(_themeDisplay.getCompanyId());
		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {_themeDisplay.getScopeGroupId()});
		searchContext.setLocale(_themeDisplay.getLocale());

		QueryConfig queryConfig = new QueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		searchContext.setQueryConfig(queryConfig);

		searchContext.setSorts(getSorts());
		searchContext.setStart(start);

		return searchContext;
	}

	protected JSONArray getFileTypesJSONArray(String product) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String[] fileTypes = DDMStructureConstants.getFileTypes(product);

		for (String fileType : fileTypes) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"name", DDMStructureConstants.getFileTypeLabel(fileType));
			jsonObject.put("value", fileType);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected Set<String> getProducts() {
		User user = _themeDisplay.getUser();

		boolean liferayIncOrg =
			OrganizationLocalServiceUtil.hasUserOrganization(
				user.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID);

		long[] roleIds = user.getRoleIds();

		Set<String> products = new TreeSet<>();

		if (liferayIncOrg ||
			ArrayUtil.contains(
				roleIds, OSBCustomerConstants.ROLE_CUSTOMER_COMMERCE_ID)) {

			products.add(DDMStructureConstants.PRODUCT_COMMERCE);
		}

		if (liferayIncOrg ||
			ArrayUtil.contains(
				roleIds,
				OSBCustomerConstants.ROLE_CUSTOMER_COMMERCE_CONNECTORS_ID)) {

			products.add(DDMStructureConstants.PRODUCT_COMMERCE_CONNECTORS);
		}

		if (liferayIncOrg ||
			ArrayUtil.contains(
				roleIds, OSBCustomerConstants.ROLE_CUSTOMER_DXP_ID)) {

			products.add(DDMStructureConstants.PRODUCT_CONNECTED_SERVICES);
			products.add(DDMStructureConstants.PRODUCT_DEVELOPER_TOOLS);
			products.add(DDMStructureConstants.PRODUCT_DXP_70);
			products.add(DDMStructureConstants.PRODUCT_DXP_71);
			products.add(
				DDMStructureConstants.PRODUCT_MOBILE_EXPERIENCE_PLATFORM);
			products.add(DDMStructureConstants.PRODUCT_PATCHING_TOOL);
			products.add(DDMStructureConstants.PRODUCT_SYNC);
		}

		if (liferayIncOrg ||
			ArrayUtil.contains(
				roleIds,
				OSBCustomerConstants.
					ROLE_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID)) {

			products.add(
				DDMStructureConstants.PRODUCT_ENTERPRISE_SEARCH_PREMIUM);
		}

		if (liferayIncOrg ||
			ArrayUtil.contains(
				roleIds,
				OSBCustomerConstants.
					ROLE_CUSTOMER_ENTERPRISE_SEARCH_STANDARD_ID)) {

			products.add(
				DDMStructureConstants.PRODUCT_ENTERPRISE_SEARCH_STANDARD);
		}

		if (liferayIncOrg ||
			ArrayUtil.contains(
				roleIds, OSBCustomerConstants.ROLE_CUSTOMER_PORTAL_ID)) {

			products.add(DDMStructureConstants.PRODUCT_CONNECTED_SERVICES);
			products.add(DDMStructureConstants.PRODUCT_DEVELOPER_TOOLS);
			products.add(
				DDMStructureConstants.PRODUCT_MOBILE_EXPERIENCE_PLATFORM);
			products.add(DDMStructureConstants.PRODUCT_PATCHING_TOOL);
			products.add(DDMStructureConstants.PRODUCT_PORTAL_52);
			products.add(DDMStructureConstants.PRODUCT_PORTAL_60);
			products.add(DDMStructureConstants.PRODUCT_PORTAL_61);
			products.add(DDMStructureConstants.PRODUCT_PORTAL_62);
			products.add(DDMStructureConstants.PRODUCT_SYNC);
		}

		return products;
	}

	protected Sort[] getSorts() {
		Sort[] sorts = new Sort[2];

		String downloadNumberSortFieldName = _ddmIndexer.encodeName(
			_ddmStructure.getStructureId(), "downloadNumber",
			_themeDisplay.getLocale());

		downloadNumberSortFieldName += "_Number";

		sorts[0] = new Sort(downloadNumberSortFieldName, Sort.LONG_TYPE, true);

		String releaseDateSortFieldName = _ddmIndexer.encodeName(
			_ddmStructure.getStructureId(), "releaseDate",
			_themeDisplay.getLocale());

		releaseDateSortFieldName += "_String_sortable";

		sorts[1] = new Sort(releaseDateSortFieldName, Sort.STRING_TYPE, true);

		return sorts;
	}

	private final DDMIndexer _ddmIndexer;
	private final DDMStructure _ddmStructure;
	private final MimeResponse _mimeResponse;
	private final RenderRequest _renderRequest;
	private final ThemeDisplay _themeDisplay;

}