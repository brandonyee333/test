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

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.release.tool.web.internal.constants.DDMStructureConstants;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackField;
import com.liferay.osb.customer.release.tool.web.internal.util.DDMFieldsUtil;
import com.liferay.osb.customer.release.tool.web.internal.util.FixPacksAssetCategoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelper;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = FixPackSearcher.class)
public class FixPackSearcher {

	public JSONObject search(
			PortletRequest portletRequest, MimeResponse mimeResponse)
		throws SearchException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String product = ParamUtil.getString(portletRequest, "product");
		double fromProductVersion = ParamUtil.getDouble(
			portletRequest, "fromProductVersion");
		double fromFixPackVersion = ParamUtil.getDouble(
			portletRequest, "fromFixPackVersion");
		double toProductVersion = ParamUtil.getDouble(
			portletRequest, "toProductVersion");
		double toFixPackVersion = ParamUtil.getDouble(
			portletRequest, "toFixPackVersion");
		String orderByCol = ParamUtil.getString(portletRequest, "orderByCol");
		String orderByType = ParamUtil.getString(portletRequest, "orderByType");

		try {
			SearchContext searchContext = buildSearchContext(
				themeDisplay, orderByCol, orderByType);

			BooleanQuery fullQuery = buildFullQuery(
				product, fromProductVersion, fromFixPackVersion,
				toProductVersion, toFixPackVersion);

			Hits hits = _indexSearcherHelper.search(searchContext, fullQuery);

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
					portletRequest, mimeResponse, themeDisplay, journalArticle);

				jsonArray.put(journalArticleJSONObject);
			}

			jsonObject.put("results", jsonArray);

			return jsonObject;
		}
		catch (SearchException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	protected BooleanQuery buildFixPackRangeQuery(
			double fromProductVersion, double fromFixPackVersion,
			double toProductVersion, double toFixPackVersion)
		throws PortalException {

		BooleanQuery fixPackRangQuery = new BooleanQueryImpl();

		if (fromProductVersion == toProductVersion) {
			fixPackRangQuery.addRequiredTerm(
				FixPackField.PRODUCT_VERSION, fromProductVersion);

			TermRangeQuery termRangeQuery = new TermRangeQueryImpl(
				FixPackField.FIX_PACK_VERSION_SORTABLE,
				String.valueOf(fromFixPackVersion),
				String.valueOf(toFixPackVersion), true, true);

			fixPackRangQuery.add(termRangeQuery, BooleanClauseOccur.MUST);

			return fixPackRangQuery;
		}

		BooleanQuery fromQuery = new BooleanQueryImpl();

		fromQuery.addRequiredTerm(
			FixPackField.PRODUCT_VERSION, fromProductVersion);

		TermRangeQuery fromTermRangeQuery = new TermRangeQueryImpl(
			FixPackField.FIX_PACK_VERSION_SORTABLE,
			String.valueOf(fromFixPackVersion), "10000.0", true, true);

		fromQuery.add(fromTermRangeQuery, BooleanClauseOccur.MUST);

		fixPackRangQuery.add(fromQuery, BooleanClauseOccur.SHOULD);

		TermRangeQuery betweenTermRangeQuery = new TermRangeQueryImpl(
			FixPackField.PRODUCT_VERSION_SORTABLE,
			String.valueOf(fromProductVersion),
			String.valueOf(toProductVersion), false, false);

		fixPackRangQuery.add(betweenTermRangeQuery, BooleanClauseOccur.SHOULD);

		BooleanQuery toQuery = new BooleanQueryImpl();

		toQuery.addRequiredTerm(FixPackField.PRODUCT_VERSION, toProductVersion);

		TermRangeQuery toTermRangeQuery = new TermRangeQueryImpl(
			FixPackField.FIX_PACK_VERSION_SORTABLE, "0.0",
			String.valueOf(toFixPackVersion), true, true);

		toQuery.add(toTermRangeQuery, BooleanClauseOccur.MUST);

		fixPackRangQuery.add(toQuery, BooleanClauseOccur.SHOULD);

		return fixPackRangQuery;
	}

	protected BooleanQuery buildFullQuery(
			String product, double fromProductVersion,
			double fromFixPackVersion, double toProductVersion,
			double toFixPackVersion)
		throws PortalException {

		BooleanQuery fullQuery = new BooleanQueryImpl();

		fullQuery.addRequiredTerm(FixPackField.PRODUCT, product);

		BooleanQuery fixPackRangeQuery = buildFixPackRangeQuery(
			fromProductVersion, fromFixPackVersion, toProductVersion,
			toFixPackVersion);

		fullQuery.add(fixPackRangeQuery, BooleanClauseOccur.MUST);

		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		booleanFilter.addRequiredTerm(
			"ddmStructureKey", DDMStructureConstants.KEY_FIX_PACK);
		booleanFilter.addRequiredTerm("head", true);

		fullQuery.setPreBooleanFilter(booleanFilter);

		return fullQuery;
	}

	protected SearchContext buildSearchContext(
			ThemeDisplay themeDisplay, String orderByCol, String orderByType)
		throws PortalException {

		SearchContext searchContext = new SearchContext();

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
			PortletRequest portletRequest, MimeResponse mimeResponse,
			ThemeDisplay themeDisplay, JournalArticle journalArticle)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		JournalArticleDisplay journalArticleDisplay =
			_journalArticleLocalService.getArticleDisplay(
				journalArticle, null, null, themeDisplay.getLanguageId(), 0,
				new PortletRequestModel(portletRequest, mimeResponse),
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
	private IndexSearcherHelper _indexSearcherHelper;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalConverter _journalConverter;

	@Reference
	private JSONFactory _jsonFactory;

}