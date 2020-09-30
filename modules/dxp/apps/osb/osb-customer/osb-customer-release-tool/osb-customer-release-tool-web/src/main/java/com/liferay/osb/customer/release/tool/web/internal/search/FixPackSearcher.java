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
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.release.tool.web.internal.constants.DDMStructureConstants;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackField;
import com.liferay.osb.customer.release.tool.web.internal.util.DDMFieldsUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelper;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = FixPackSearcher.class)
public class FixPackSearcher extends BaseSearcher {

	protected BooleanQuery buildFullQuery(
			ThemeDisplay themeDisplay, String product, double productVersion,
			double fromFixPackVersion, double toFixPackVersion,
			boolean commerce)
		throws PortalException {

		BooleanQuery fullQuery = new BooleanQueryImpl();

		fullQuery.addRequiredTerm(FixPackField.PRODUCT, product);

		BooleanQuery fixPackRangeQuery = new BooleanQueryImpl();

		fixPackRangeQuery.addRequiredTerm(
			FixPackField.PRODUCT_VERSION, productVersion);

		TermRangeQuery termRangeQuery = new TermRangeQueryImpl(
			FixPackField.FIX_PACK_VERSION_SORTABLE,
			String.valueOf(fromFixPackVersion),
			String.valueOf(toFixPackVersion), true, true);

		fixPackRangeQuery.add(termRangeQuery, BooleanClauseOccur.MUST);

		fullQuery.add(fixPackRangeQuery, BooleanClauseOccur.MUST);

		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		booleanFilter.addRequiredTerm(
			"ddmStructureKey", DDMStructureConstants.KEY_FIX_PACK);
		booleanFilter.addRequiredTerm("head", true);

		JournalFolder parentFolder = _journalFolderLocalService.fetchFolder(
			themeDisplay.getScopeGroupId(),
			product + StringPool.SPACE + String.valueOf(productVersion));

		if (commerce) {
			JournalFolder folder = _journalFolderLocalService.fetchFolder(
				themeDisplay.getScopeGroupId(), parentFolder.getFolderId(),
				"Commerce");

			booleanFilter.addRequiredTerm("folderId", folder.getFolderId());
		}
		else {
			booleanFilter.addRequiredTerm(
				"folderId", parentFolder.getFolderId());
		}

		fullQuery.setPreBooleanFilter(booleanFilter);

		return fullQuery;
	}

	protected SearchContext buildSearchContext(
			ThemeDisplay themeDisplay, String orderByType)
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

		searchContext.setSorts(getSorts(themeDisplay, orderByType));
		searchContext.setStart(QueryUtil.ALL_POS);

		return searchContext;
	}

	protected JSONObject doSearch(
			PortletRequest portletRequest, MimeResponse mimeResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String product = ParamUtil.getString(portletRequest, "product");
		double productVersion = ParamUtil.getDouble(
			portletRequest, "productVersion");
		double fromFixPackVersion = ParamUtil.getDouble(
			portletRequest, "fromFixPackVersion");
		double toFixPackVersion = ParamUtil.getDouble(
			portletRequest, "toFixPackVersion");
		boolean commerce = ParamUtil.getBoolean(portletRequest, "commerce");

		String orderByType = ParamUtil.getString(portletRequest, "orderByType");

		SearchContext searchContext = buildSearchContext(
			themeDisplay, orderByType);

		BooleanQuery fullQuery = buildFullQuery(
			themeDisplay, product, productVersion, fromFixPackVersion,
			toFixPackVersion, commerce);

		Hits hits = _indexSearcherHelper.search(searchContext, fullQuery);

		JSONObject jsonObject = jsonFactory.createJSONObject();

		jsonObject.put("total", hits.getLength());

		JSONArray jsonArray = jsonFactory.createJSONArray();

		for (Document document : hits.getDocs()) {
			long classPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			JournalArticle journalArticle =
				_journalArticleLocalService.fetchLatestArticle(
					classPK, WorkflowConstants.STATUS_APPROVED);

			if (journalArticle == null) {
				_log.error(
					"Journal article search index is stale and contains " +
						classPK);

				continue;
			}

			JSONObject journalArticleJSONObject = toJSONObject(
				portletRequest, mimeResponse, themeDisplay, journalArticle);

			jsonArray.put(journalArticleJSONObject);
		}

		jsonObject.put("results", jsonArray);

		return jsonObject;
	}

	protected Sort[] getSorts(ThemeDisplay themeDisplay, String orderByType)
		throws PortalException {

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

		JSONObject jsonObject = jsonFactory.createJSONObject();

		JournalArticleDisplay journalArticleDisplay =
			_journalArticleLocalService.getArticleDisplay(
				journalArticle, null, null, themeDisplay.getLanguageId(), 0,
				new PortletRequestModel(portletRequest, mimeResponse),
				themeDisplay);

		jsonObject.put("content", journalArticleDisplay.getContent());

		JSONArray fieldsUsedJSONArray = jsonFactory.createJSONArray();

		Fields ddmFields = _journalConverter.getDDMFields(
			journalArticle.getDDMStructure(), journalArticle.getContent());

		for (String ddmFieldName : ddmFields.getNames()) {
			String ddmFieldValue = DDMFieldsUtil.getString(
				ddmFields, ddmFieldName);

			if (Validator.isNotNull(ddmFieldValue)) {
				fieldsUsedJSONArray.put(ddmFieldName);
			}
		}

		jsonObject.put("fieldsUsed", fieldsUsedJSONArray);

		String releaseDate = DDMFieldsUtil.getString(ddmFields, "releaseDate");

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date date = dateFormat.parse(releaseDate);

			jsonObject.put("releaseDate", _releaseDateFormat.format(date));
		}
		catch (ParseException pe) {
			jsonObject.put("releaseDate", releaseDate);
		}

		jsonObject.put("resourcePrimKey", journalArticle.getResourcePrimKey());
		jsonObject.put(
			"title", journalArticle.getTitle(themeDisplay.getLocale()));

		return jsonObject;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FixPackSearcher.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMIndexer _ddmIndexer;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private IndexSearcherHelper _indexSearcherHelper;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalConverter _journalConverter;

	@Reference
	private JournalFolderLocalService _journalFolderLocalService;

	private final Format _releaseDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("MMM d, yyyy");

}