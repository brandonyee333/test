/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.web.internal.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.osb.customer.web.internal.asset.model.AssetCategoryDisplay;
import com.liferay.osb.customer.web.internal.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.FacetedSearcher;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Jenny Chen
 */
public class ArticleFacetedSearcher extends FacetedSearcher {

	public static Indexer getInstance() {
		return new ArticleFacetedSearcher();
	}

	@Override
	protected BooleanQuery createFullQuery(
			BooleanFilter fullQueryBooleanFilter, SearchContext searchContext)
		throws Exception {

		_splitAssetCategories(searchContext);

		BooleanQuery fullQuery = BooleanQueryFactoryUtil.create(searchContext);

		BooleanQuery userLanguageIdQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		String userLanguageId = (String)searchContext.getAttribute(
			"userLanguageId");

		if (Validator.isNotNull(userLanguageId)) {
			userLanguageIdQuery.addTerm(Field.LANGUAGE_ID, userLanguageId);
		}

		for (String entryClassName : searchContext.getEntryClassNames()) {
			if (entryClassName.equals(JournalArticle.class.getName())) {
				BooleanQuery journalArticleQuery = _createJournalArticleQuery(
					searchContext);

				if (journalArticleQuery.hasClauses()) {
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
					if (userLanguageIdQuery.hasClauses()) {
						kbArticleQuery.add(
							userLanguageIdQuery, BooleanClauseOccur.SHOULD);
					}

					fullQuery.add(kbArticleQuery, BooleanClauseOccur.SHOULD);
				}
			}
		}

		return fullQuery;
	}

	private void _addSearchLocalizedTerms(
			BooleanQuery booleanQuery, Locale locale, String value)
		throws Exception {

		for (String field : _keywordFields) {
			booleanQuery.addTerm(_getLocalizedName(locale, field), value);
		}
	}

	private BooleanQuery _createJournalArticleQuery(SearchContext searchContext)
		throws Exception {

		BooleanQuery journalArticleQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		String[] searchStructureIds = (String[])searchContext.getAttribute(
			"searchStructureIds");

		if ((searchStructureIds.length > 0) &&
			(!_articleTypeAssetCategories.isEmpty() ||
			 !_officialDocumentation)) {

			journalArticleQuery.addRequiredTerm(
				Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
			journalArticleQuery.addRequiredTerm(
				Field.STATUS, WorkflowConstants.STATUS_APPROVED);

			journalArticleQuery.addRequiredTerm("head", true);

			BooleanQuery ddmStructureKeyQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			for (String searchStructureId : searchStructureIds) {
				ddmStructureKeyQuery.addTerm(
					"ddmStructureKey", searchStructureId);
			}

			journalArticleQuery.add(
				ddmStructureKeyQuery, BooleanClauseOccur.MUST);

			BooleanQuery searchFilterQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			if (!_searchFilterAssetCategories.isEmpty()) {
				AssetCategoryDisplay assetCategoryDisplay =
					new AssetCategoryDisplay(
						null, _searchFilterAssetCategories);

				searchFilterQuery =
					assetCategoryDisplay.createSearchFilterQuery(searchContext);
			}

			if (searchFilterQuery.hasClauses()) {
				journalArticleQuery.add(
					searchFilterQuery, BooleanClauseOccur.MUST);
			}

			if (!_articleTypeAssetCategories.isEmpty()) {
				AssetCategoryDisplay assetCategoryDisplay =
					new AssetCategoryDisplay(null, _articleTypeAssetCategories);

				BooleanQuery articleTypeQuery =
					assetCategoryDisplay.createSearchFilterQuery(searchContext);

				journalArticleQuery.add(
					articleTypeQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery keywordsQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			String keywords = searchContext.getKeywords();

			if (Validator.isNotNull(keywords)) {
				keywordsQuery.addExactTerm(Field.ASSET_TAG_NAMES, keywords);

				_addSearchLocalizedTerms(
					keywordsQuery, searchContext.getLocale(), keywords);
			}

			if (keywordsQuery.hasClauses()) {
				journalArticleQuery.add(keywordsQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery languageIdQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			String[] languageIds = (String[])searchContext.getAttribute(
				"languageIds");

			for (String languageId : languageIds) {
				languageIdQuery.addTerm(Field.LANGUAGE_ID, languageId);
			}

			if (languageIdQuery.hasClauses()) {
				journalArticleQuery.add(
					languageIdQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery permissionTypeQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			long[] permissionTypes = (long[])searchContext.getAttribute(
				"permissionTypes");

			for (long permissionType : permissionTypes) {
				permissionTypeQuery.addTerm("permissionType", permissionType);
			}

			if (permissionTypeQuery.hasClauses()) {
				journalArticleQuery.add(
					permissionTypeQuery, BooleanClauseOccur.MUST);
			}
		}

		return journalArticleQuery;
	}

	private BooleanQuery _createKBArticleQuery(SearchContext searchContext)
		throws Exception {

		BooleanQuery kbArticleQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		if (_articleTypeAssetCategories.isEmpty() || _officialDocumentation) {
			kbArticleQuery.addRequiredTerm(
				Field.ENTRY_CLASS_NAME, KBArticle.class.getName());

			BooleanQuery keywordsQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			String keywords = searchContext.getKeywords();

			if (Validator.isNotNull(keywords)) {
				keywordsQuery.addExactTerm(Field.ASSET_TAG_NAMES, keywords);

				keywordsQuery.addTerm("headers", keywords);

				keywordsQuery.addTerms(_keywordFields, keywords);
			}

			if (keywordsQuery.hasClauses()) {
				kbArticleQuery.add(keywordsQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery languageIdQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			String[] languageIds = (String[])searchContext.getAttribute(
				"languageIds");

			for (String languageId : languageIds) {
				languageIdQuery.addTerm(Field.LANGUAGE_ID, languageId);
			}

			if (languageIdQuery.hasClauses()) {
				kbArticleQuery.add(languageIdQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery permissionTypeQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			long[] permissionTypes = (long[])searchContext.getAttribute(
				"permissionTypes");

			for (long permissionType : permissionTypes) {
				permissionTypeQuery.addTerm("permissionType", permissionType);
			}

			if (permissionTypeQuery.hasClauses()) {
				kbArticleQuery.add(
					permissionTypeQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery searchFilterQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			if (!_searchFilterAssetCategories.isEmpty()) {
				AssetCategoryDisplay assetCategoryDisplay =
					new AssetCategoryDisplay(
						null, _searchFilterAssetCategories);

				searchFilterQuery =
					assetCategoryDisplay.createSearchFilterQuery(searchContext);
			}

			if (searchFilterQuery.hasClauses()) {
				kbArticleQuery.add(searchFilterQuery, BooleanClauseOccur.MUST);
			}
		}

		return kbArticleQuery;
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

	private static final String[] _keywordFields = {
		Field.ASSET_CATEGORY_TITLES, Field.CONTENT, Field.DESCRIPTION,
		Field.TITLE
	};

	private final List<AssetCategory> _articleTypeAssetCategories =
		new ArrayList<>();
	private boolean _officialDocumentation;
	private final List<AssetCategory> _searchFilterAssetCategories =
		new ArrayList<>();

}