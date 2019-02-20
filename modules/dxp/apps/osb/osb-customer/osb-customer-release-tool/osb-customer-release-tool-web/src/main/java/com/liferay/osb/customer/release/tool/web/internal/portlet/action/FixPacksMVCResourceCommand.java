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

package com.liferay.osb.customer.release.tool.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.release.tool.web.internal.constants.DDMStructureConstants;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackAssetCategoryConstants;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackField;
import com.liferay.osb.customer.release.tool.web.internal.constants.ReleaseToolPortletKeys;
import com.liferay.osb.customer.release.tool.web.internal.util.DDMFieldsUtil;
import com.liferay.osb.customer.release.tool.web.internal.util.FixPacksAssetCategoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.RangeFacet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ReleaseToolPortletKeys.RELEASE_TOOL,
		"mvc.command.name=/fix_packs"
	},
	service = MVCResourceCommand.class
)
public class FixPacksMVCResourceCommand extends BaseMVCResourceCommand {

	protected SearchContext buildSearchContext(
			ThemeDisplay themeDisplay, long productAssetCategoryId,
			long fromFixPackAssetCategoryId, long toFixPackAssetCategoryId,
			String orderByCol, String orderByType)
		throws PortalException {

		SearchContext searchContext = new SearchContext();

		Facet facet = new RangeFacet(searchContext);

		facet.setFieldName(FixPackField.FIX_PACK_VERSION);
		facet.setStatic(true);

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		facetConfiguration.setDataJSONObject(
			createDataJSONObject(
				fromFixPackAssetCategoryId, toFixPackAssetCategoryId));

		searchContext.addFacet(facet);

		searchContext.setAndSearch(true);

		searchContext.setAttribute(
			Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
		searchContext.setAttribute(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setAttribute(
			FixPackField.PRODUCT_ASSET_CATEGORY_ID, productAssetCategoryId);

		searchContext.setAttribute(
			"ddmStructureKey", DDMStructureConstants.KEY_FIX_PACK);
		searchContext.setAttribute("head", Boolean.TRUE);
		searchContext.setAttribute("latest", Boolean.TRUE);

		searchContext.setCompanyId(themeDisplay.getCompanyId());
		searchContext.setEnd(QueryUtil.ALL_POS);
		searchContext.setGroupIds(new long[] {themeDisplay.getScopeGroupId()});
		searchContext.setLocale(themeDisplay.getLocale());

		QueryConfig queryConfig = new QueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		searchContext.setQueryConfig(queryConfig);

		searchContext.setSorts(getSorts(themeDisplay, orderByCol, orderByType));
		searchContext.setStart(QueryUtil.ALL_POS);

		return searchContext;
	}

	protected JSONObject createDataJSONObject(
			long fromFixPackAssetCategoryId, long toFixPackAssetCategoryId)
		throws PortalException {

		JSONObject dataJSONObject = _jsonFactory.createJSONObject();

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		StringBundler sb = new StringBundler(5);

		sb.append(StringPool.OPEN_BRACKET);

		String fromVersion = _fixPacksAssetCategoryUtil.getPropertyValue(
			fromFixPackAssetCategoryId,
			FixPackAssetCategoryConstants.PROPERTY_VERSION);

		sb.append(GetterUtil.getDouble(fromVersion));

		sb.append(" TO ");

		String toVersion = _fixPacksAssetCategoryUtil.getPropertyValue(
			toFixPackAssetCategoryId,
			FixPackAssetCategoryConstants.PROPERTY_VERSION);

		sb.append(GetterUtil.getDouble(toVersion));

		sb.append(StringPool.CLOSE_BRACKET);

		jsonObject.put("range", sb.toString());

		jsonArray.put(jsonObject);

		dataJSONObject.put("ranges", jsonArray);

		return dataJSONObject;
	}

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long productAssetCategoryId = ParamUtil.getLong(
			resourceRequest, "productAssetCategoryId");
		long fromFixPackAssetCategoryId = ParamUtil.getLong(
			resourceRequest, "fromFixPackAssetCategoryId");
		long toFixPackAssetCategoryId = ParamUtil.getLong(
			resourceRequest, "toFixPackAssetCategoryId");
		String orderByCol = ParamUtil.getString(resourceRequest, "orderByCol");
		String orderByType = ParamUtil.getString(
			resourceRequest, "orderByType");

		SearchContext searchContext = buildSearchContext(
			themeDisplay, productAssetCategoryId, fromFixPackAssetCategoryId,
			toFixPackAssetCategoryId, orderByCol, orderByType);

		Indexer indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		Hits hits = indexer.search(searchContext);

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put("total", hits.getLength());

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (Document document : hits.getDocs()) {
			long classPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			JournalArticle journalArticle =
				_journalArticleLocalService.fetchLatestArticle(
					classPK, WorkflowConstants.STATUS_APPROVED);

			JSONObject journalArticleJSONObject = toJSONObject(
				resourceRequest, resourceResponse, themeDisplay,
				journalArticle);

			jsonArray.put(journalArticleJSONObject);
		}

		jsonObject.put("results", jsonArray);

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, jsonObject);
	}

	protected Sort[] getSorts(
			ThemeDisplay themeDisplay, String orderByCol, String orderByType)
		throws PortalException {

		if (Validator.isNull(orderByCol) || !orderByCol.equals("releaseDate")) {
			return new Sort[0];
		}

		long classNameId = _classNameLocalService.getClassNameId(
			JournalArticle.class);

		DDMStructure ddmStructure = _ddmStructureLocalService.getStructure(
			themeDisplay.getCompanyGroupId(), classNameId,
			DDMStructureConstants.KEY_FIX_PACK);

		String releaseDateSortFieldName = _ddmIndexer.encodeName(
			ddmStructure.getStructureId(), "releaseDate",
			themeDisplay.getLocale());

		releaseDateSortFieldName += "_String_sortable";

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		Sort sort = new Sort(
			releaseDateSortFieldName, Sort.STRING_TYPE, reverse);

		return new Sort[] {sort};
	}

	protected JSONObject toJSONObject(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			ThemeDisplay themeDisplay, JournalArticle journalArticle)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		JournalArticleDisplay journalArticleDisplay =
			_journalArticleLocalService.getArticleDisplay(
				journalArticle, null, null, themeDisplay.getLanguageId(), 0,
				new PortletRequestModel(resourceRequest, resourceResponse),
				themeDisplay);

		jsonObject.put("content", journalArticleDisplay.getContent());

		Fields ddmFields = _journalConverter.getDDMFields(
			journalArticle.getDDMStructure(), journalArticle.getContent());

		String releaseDate = DDMFieldsUtil.getString(ddmFields, "releaseDate");

		jsonObject.put("releaseDate", releaseDate);

		jsonObject.put("resourcePrimKey", journalArticle.getResourcePrimKey());
		jsonObject.put(
			"title", journalArticle.getTitle(themeDisplay.getLocale()));

		return jsonObject;
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMIndexer _ddmIndexer;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private FixPacksAssetCategoryUtil _fixPacksAssetCategoryUtil;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalConverter _journalConverter;

	@Reference
	private JSONFactory _jsonFactory;

}