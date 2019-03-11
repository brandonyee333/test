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

package com.liferay.osb.customer.web.internal.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.journal.model.JournalArticle;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.osb.customer.asset.model.AssetCategoryDisplay;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.web.internal.constants.OSBJournalArticleConstants;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Jenny Chen
 */
public class ArticleSearcher extends BaseSearcher {

	public ArticleSearcher() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	protected Hits doSearch(SearchContext searchContext)
		throws SearchException {

		try {
			BooleanQuery fullQuery = _getFullQuery(searchContext);

			return IndexSearcherHelperUtil.search(searchContext, fullQuery);
		}
		catch (SearchException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	private void _addSearchLocalizedTerms(
			BooleanQuery booleanQuery, Locale locale, String value)
		throws ParseException {

		for (String field : _KEYWORD_FIELDS) {
			booleanQuery.addTerm(_getLocalizedName(locale, field), value);
		}
	}

	private BooleanFilter _createJournalArticleFilter(
			SearchContext searchContext)
		throws Exception {

		BooleanFilter booleanFilter = new BooleanFilter();

		String[] searchStructureIds = (String[])searchContext.getAttribute(
			"searchStructureIds");

		if ((searchStructureIds.length > 0) &&
			(!_articleTypeAssetCategories.isEmpty() ||
			 !_officialDocumentation)) {

			booleanFilter.addRequiredTerm(
				Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
			booleanFilter.addRequiredTerm(
				Field.STATUS, WorkflowConstants.STATUS_APPROVED);

			booleanFilter.addRequiredTerm("head", true);

			BooleanFilter ddmStructureKeyFilter = new BooleanFilter();

			for (String searchStructureId : searchStructureIds) {
				ddmStructureKeyFilter.addTerm(
					"ddmStructureKey", searchStructureId);
			}

			booleanFilter.add(ddmStructureKeyFilter, BooleanClauseOccur.MUST);

			BooleanFilter assetCategoriesFilter = new BooleanFilter();

			if (!_searchFilterAssetCategories.isEmpty()) {
				AssetCategoryDisplay assetCategoryDisplay =
					new AssetCategoryDisplay(
						null, _searchFilterAssetCategories);

				assetCategoriesFilter =
					assetCategoryDisplay.createBooleanFilter();
			}

			if (assetCategoriesFilter.hasClauses()) {
				booleanFilter.add(
					assetCategoriesFilter, BooleanClauseOccur.MUST);
			}

			if (!_articleTypeAssetCategories.isEmpty()) {
				AssetCategoryDisplay assetCategoryDisplay =
					new AssetCategoryDisplay(null, _articleTypeAssetCategories);

				BooleanFilter articleTypeFilter =
					assetCategoryDisplay.createBooleanFilter();

				booleanFilter.add(articleTypeFilter, BooleanClauseOccur.MUST);
			}

			BooleanFilter languageIdFilter = new BooleanFilter();

			String[] languageIds = (String[])searchContext.getAttribute(
				"languageIds");

			for (String languageId : languageIds) {
				languageIdFilter.addTerm(Field.DEFAULT_LANGUAGE_ID, languageId);
			}

			if (languageIdFilter.hasClauses()) {
				booleanFilter.add(languageIdFilter, BooleanClauseOccur.MUST);
			}

			BooleanFilter permissionTypeFilter = new BooleanFilter();

			long[] permissionTypes = (long[])searchContext.getAttribute(
				"permissionTypes");

			String fieldName = _getJournalArticleDDMFieldName("permissions");

			for (long permissionType : permissionTypes) {
				permissionTypeFilter.addTerm(fieldName, permissionType);
			}

			if (permissionTypeFilter.hasClauses()) {
				booleanFilter.add(
					permissionTypeFilter, BooleanClauseOccur.MUST);
			}
		}

		return booleanFilter;
	}

	private BooleanQuery _createJournalArticleQuery(SearchContext searchContext)
		throws Exception {

		BooleanQuery journalArticleQuery = new BooleanQueryImpl();

		String[] searchStructureIds = (String[])searchContext.getAttribute(
			"searchStructureIds");

		if ((searchStructureIds.length > 0) &&
			(!_articleTypeAssetCategories.isEmpty() ||
			 !_officialDocumentation)) {

			journalArticleQuery.addExactTerm(
				Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());

			String keywords = searchContext.getKeywords();

			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordsQuery = new BooleanQueryImpl();

				keywordsQuery.addExactTerm(Field.ASSET_TAG_NAMES, keywords);

				_addSearchLocalizedTerms(
					keywordsQuery, searchContext.getLocale(), keywords);

				journalArticleQuery.add(keywordsQuery, BooleanClauseOccur.MUST);
			}
		}

		return journalArticleQuery;
	}

	private BooleanFilter _createKBArticleFilter(SearchContext searchContext)
		throws Exception {

		BooleanFilter kbArticleFilter = new BooleanFilter();

		if (_articleTypeAssetCategories.isEmpty() || _officialDocumentation) {
			kbArticleFilter.addRequiredTerm(
				Field.ENTRY_CLASS_NAME, KBArticle.class.getName());

			BooleanFilter languageIdFilter = new BooleanFilter();

			String[] languageIds = (String[])searchContext.getAttribute(
				"languageIds");

			for (String languageId : languageIds) {
				languageIdFilter.addTerm(Field.LANGUAGE_ID, languageId);
			}

			if (languageIdFilter.hasClauses()) {
				kbArticleFilter.add(languageIdFilter, BooleanClauseOccur.MUST);
			}

			BooleanFilter permissionTypeFilter = new BooleanFilter();

			long[] permissionTypes = (long[])searchContext.getAttribute(
				"permissionTypes");

			if (!ArrayUtil.contains(
					permissionTypes,
					OSBJournalArticleConstants.PERMISSION_TYPE_EXTERNAL)) {

				for (long permissionType : permissionTypes) {
					permissionTypeFilter.addTerm(
						"permissionType", permissionType);
				}
			}

			if (permissionTypeFilter.hasClauses()) {
				kbArticleFilter.add(
					permissionTypeFilter, BooleanClauseOccur.MUST);
			}

			BooleanFilter assetCategoriesFilter = new BooleanFilter();

			if (!_searchFilterAssetCategories.isEmpty()) {
				AssetCategoryDisplay assetCategoryDisplay =
					new AssetCategoryDisplay(
						null, _searchFilterAssetCategories);

				assetCategoriesFilter =
					assetCategoryDisplay.createBooleanFilter();
			}

			if (assetCategoriesFilter.hasClauses()) {
				kbArticleFilter.add(
					assetCategoriesFilter, BooleanClauseOccur.MUST);
			}
		}

		return kbArticleFilter;
	}

	private BooleanQuery _createKBArticleQuery(SearchContext searchContext)
		throws Exception {

		BooleanQuery kbArticleQuery = new BooleanQueryImpl();

		if (_articleTypeAssetCategories.isEmpty() || _officialDocumentation) {
			kbArticleQuery.addExactTerm(
				Field.ENTRY_CLASS_NAME, KBArticle.class.getName());

			String keywords = searchContext.getKeywords();

			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordsQuery = new BooleanQueryImpl();

				keywordsQuery.addExactTerm(Field.ASSET_TAG_NAMES, keywords);

				keywordsQuery.addTerms(_KEYWORD_FIELDS, keywords);

				kbArticleQuery.add(keywordsQuery, BooleanClauseOccur.MUST);
			}
		}

		return kbArticleQuery;
	}

	private BooleanQuery _getFullQuery(SearchContext searchContext)
		throws Exception {

		_splitAssetCategories(searchContext);

		BooleanQuery fullQuery = new BooleanQueryImpl();

		BooleanFilter booleanFilter = new BooleanFilter();

		for (String entryClassName : searchContext.getEntryClassNames()) {
			if (entryClassName.equals(JournalArticle.class.getName())) {
				BooleanFilter journalArticleFilter =
					_createJournalArticleFilter(searchContext);

				if (journalArticleFilter.hasClauses()) {
					booleanFilter.add(
						journalArticleFilter, BooleanClauseOccur.SHOULD);
				}
			}
			else if (entryClassName.equals(KBArticle.class.getName())) {
				BooleanFilter kbArticleFilter = _createKBArticleFilter(
					searchContext);

				if (kbArticleFilter.hasClauses()) {
					booleanFilter.add(
						kbArticleFilter, BooleanClauseOccur.SHOULD);
				}
			}
		}

		for (String entryClassName : searchContext.getEntryClassNames()) {
			if (entryClassName.equals(JournalArticle.class.getName())) {
				BooleanQuery journalArticleQuery = _createJournalArticleQuery(
					searchContext);

				if (journalArticleQuery.hasClauses()) {
					BooleanQuery userLanguageIdQuery = new BooleanQueryImpl();

					String userLanguageId = (String)searchContext.getAttribute(
						"userLanguageId");

					if (Validator.isNotNull(userLanguageId)) {
						userLanguageIdQuery.addTerm(
							"availableLanguageIds", userLanguageId);
					}

					if (userLanguageIdQuery.hasClauses()) {
						journalArticleQuery.add(
							userLanguageIdQuery, BooleanClauseOccur.SHOULD);
					}

					fullQuery.add(
						journalArticleQuery, BooleanClauseOccur.SHOULD);
				}
			}
			else if (entryClassName.equals(KBArticle.class.getName())) {
				BooleanQuery kbArticleQuery = _createKBArticleQuery(
					searchContext);

				if (kbArticleQuery.hasClauses()) {
					fullQuery.add(kbArticleQuery, BooleanClauseOccur.SHOULD);
				}
			}
		}

		if (booleanFilter.hasClauses()) {
			fullQuery.setPreBooleanFilter(booleanFilter);
		}

		return fullQuery;
	}

	private String _getJournalArticleDDMFieldName(String fieldName)
		throws Exception {

		long classNameId = PortalUtil.getClassNameId(JournalArticle.class);

		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			OSBCustomerConstants.GROUP_KNOWLEDGE_ID, classNameId,
			OSBCustomerConstants.DDM_STRUCTURE_ARTICLE_DISPLAY_KEY);

		StringBundler sb = new StringBundler(8);

		sb.append(DDMIndexer.DDM_FIELD_PREFIX);
		sb.append(ddmStructure.getFieldProperty(fieldName, "indexType"));
		sb.append(DDMIndexer.DDM_FIELD_SEPARATOR);
		sb.append(ddmStructure.getStructureId());
		sb.append(DDMIndexer.DDM_FIELD_SEPARATOR);
		sb.append(fieldName);
		sb.append(StringPool.UNDERLINE);

		Locale locale = LocaleUtil.getDefault();

		sb.append(LocaleUtil.toLanguageId(locale));

		return sb.toString();
	}

	private String _getLocalizedName(Locale locale, String field) {
		return DocumentImpl.getLocalizedName(locale, field);
	}

	private void _splitAssetCategories(SearchContext searchContext)
		throws Exception {

		long[] assetCategoryIds = searchContext.getAssetCategoryIds();

		for (long assetCategoryId : assetCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.getCategory(assetCategoryId);

			if (assetCategory.getVocabularyId() ==
					OSBCustomerConstants.ASSET_VOCABULARY_ARTICLE_TYPE_ID) {

				String assetCategoryName = assetCategory.getName();

				if (assetCategoryName.equals("Official Documentation")) {
					_officialDocumentation = true;
				}
				else {
					_articleTypeAssetCategories.add(assetCategory);
				}
			}
			else {
				_searchFilterAssetCategories.add(assetCategory);
			}
		}
	}

	private static final String[] _KEYWORD_FIELDS = {
		Field.ASSET_CATEGORY_TITLES, Field.CONTENT, Field.TITLE
	};

	private final List<AssetCategory> _articleTypeAssetCategories =
		new ArrayList<>();
	private boolean _officialDocumentation;
	private final List<AssetCategory> _searchFilterAssetCategories =
		new ArrayList<>();

}