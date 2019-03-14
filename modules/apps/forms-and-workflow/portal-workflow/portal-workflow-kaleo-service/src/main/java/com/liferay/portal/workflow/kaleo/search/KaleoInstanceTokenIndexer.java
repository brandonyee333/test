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

package com.liferay.portal.workflow.kaleo.search;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.DateRangeTermFilter;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.internal.search.KaleoInstanceTokenField;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstanceTokenQuery;

import java.util.Date;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author István András Dézsi
 */
@Component(immediate = true, service = Indexer.class)
public class KaleoInstanceTokenIndexer extends BaseIndexer<KaleoInstanceToken> {

	public static final String CLASS_NAME = KaleoInstanceToken.class.getName();

	public KaleoInstanceTokenIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.UID, KaleoInstanceTokenField.KALEO_INSTANCE_ID);
		setDefaultSelectedLocalizedFieldNames(
			KaleoInstanceTokenField.ASSET_DESCRIPTION,
			KaleoInstanceTokenField.ASSET_TITLE);

		setPermissionAware(false);
		setFilterSearch(false);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		KaleoInstanceTokenQuery kaleoInstanceTokenQuery =
			(KaleoInstanceTokenQuery)searchContext.getAttribute(
				"kaleoInstanceTokenQuery");

		if (kaleoInstanceTokenQuery == null) {
			return;
		}

		appendCompletedTerm(contextBooleanFilter, kaleoInstanceTokenQuery);
		appendCompletionDateRangeTerm(
			contextBooleanFilter, kaleoInstanceTokenQuery);
		appendKaleoInstanceIdTerm(
			contextBooleanFilter, kaleoInstanceTokenQuery);
		appendKaleoInstanceTokenIdTerm(
			contextBooleanFilter, kaleoInstanceTokenQuery);
		appendParentKaleoInstanceTokenIdTerm(
			contextBooleanFilter, kaleoInstanceTokenQuery);
		appendUserIdTerm(contextBooleanFilter, kaleoInstanceTokenQuery);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		KaleoInstanceTokenQuery kaleoInstanceTokenQuery =
			(KaleoInstanceTokenQuery)searchContext.getAttribute(
				"kaleoInstanceTokenQuery");

		if (kaleoInstanceTokenQuery == null) {
			return;
		}

		appendAssetDescriptionTerm(
			searchQuery, kaleoInstanceTokenQuery.getAssetDescription(),
			searchContext);
		appendAssetTitleTerm(
			searchQuery, kaleoInstanceTokenQuery.getAssetTitle(),
			searchContext);
		appendClassNameTerm(
			searchQuery, kaleoInstanceTokenQuery.getClassName(), searchContext);
		appendCurrentKaleoNodeNameTerm(
			searchQuery, kaleoInstanceTokenQuery.getCurrentKaleoNodeName(),
			searchContext);
		appendKaleoDefinitionNameTerm(
			searchQuery, kaleoInstanceTokenQuery.getKaleoDefinitionName(),
			searchContext);
	}

	protected void appendAssetDescriptionTerm(
			BooleanQuery booleanQuery, String assetDescription,
			SearchContext searchContext)
		throws Exception {

		if (Validator.isNull(assetDescription)) {
			return;
		}

		String assetDescriptionLocalizedName =
			LocalizationUtil.getLocalizedName(
				KaleoInstanceTokenField.ASSET_DESCRIPTION,
				searchContext.getLanguageId());

		searchContext.setAttribute(
			assetDescriptionLocalizedName, assetDescription);

		addSearchLocalizedTerm(
			booleanQuery, searchContext,
			KaleoInstanceTokenField.ASSET_DESCRIPTION, false);
	}

	protected void appendAssetTitleTerm(
			BooleanQuery booleanQuery, String assetTitle,
			SearchContext searchContext)
		throws Exception {

		if (Validator.isNull(assetTitle)) {
			return;
		}

		String assetTitleLocalizedName = LocalizationUtil.getLocalizedName(
			KaleoInstanceTokenField.ASSET_TITLE, searchContext.getLanguageId());

		searchContext.setAttribute(assetTitleLocalizedName, assetTitle);

		addSearchLocalizedTerm(
			booleanQuery, searchContext, KaleoInstanceTokenField.ASSET_TITLE,
			false);
	}

	protected void appendClassNameTerm(
			BooleanQuery booleanQuery, String className,
			SearchContext searchContext)
		throws Exception {

		if (Validator.isNull(className)) {
			return;
		}

		searchContext.setAttribute(
			KaleoInstanceTokenField.CLASS_NAME, className);

		addSearchTerm(
			booleanQuery, searchContext, KaleoInstanceTokenField.CLASS_NAME,
			false);
	}

	protected void appendCompletedTerm(
		BooleanFilter booleanFilter,
		KaleoInstanceTokenQuery kaleoInstanceTokenQuery) {

		Boolean completed = kaleoInstanceTokenQuery.isCompleted();

		if (completed == null) {
			return;
		}

		booleanFilter.addRequiredTerm(
			KaleoInstanceTokenField.COMPLETED, completed);
	}

	protected void appendCompletionDateRangeTerm(
		BooleanFilter booleanFilter,
		KaleoInstanceTokenQuery kaleoInstanceTokenQuery) {

		Date completionDateGT = kaleoInstanceTokenQuery.getCompletionDateGT();

		Date completionDateLT = kaleoInstanceTokenQuery.getCompletionDateLT();

		if ((completionDateGT == null) && (completionDateLT == null)) {
			return;
		}

		DateRangeTermFilter dateRangeTermFilter = new DateRangeTermFilter(
			KaleoInstanceTokenField.COMPLETION_DATE, false, false,
			completionDateGT.toString(), completionDateLT.toString());

		booleanFilter.add(dateRangeTermFilter, BooleanClauseOccur.MUST);
	}

	protected void appendCurrentKaleoNodeNameTerm(
			BooleanQuery booleanQuery, String currentKaleoNodeName,
			SearchContext searchContext)
		throws Exception {

		if (Validator.isNull(currentKaleoNodeName)) {
			return;
		}

		searchContext.setAttribute(
			KaleoInstanceTokenField.CURRENT_KALEO_NODE_NAME,
			currentKaleoNodeName);

		addSearchTerm(
			booleanQuery, searchContext,
			KaleoInstanceTokenField.CURRENT_KALEO_NODE_NAME, false);
	}

	protected void appendKaleoDefinitionNameTerm(
			BooleanQuery booleanQuery, String kaleoDefinitionNameTerm,
			SearchContext searchContext)
		throws Exception {

		if (Validator.isNull(kaleoDefinitionNameTerm)) {
			return;
		}

		searchContext.setAttribute(
			KaleoInstanceTokenField.KALEO_DEFINITION_NAME,
			kaleoDefinitionNameTerm);

		addSearchTerm(
			booleanQuery, searchContext,
			KaleoInstanceTokenField.KALEO_DEFINITION_NAME, false);
	}

	protected void appendKaleoInstanceIdTerm(
		BooleanFilter booleanFilter,
		KaleoInstanceTokenQuery kaleoInstanceTokenQuery) {

		Long kaleoInstanceId = kaleoInstanceTokenQuery.getKaleoInstanceId();

		if (kaleoInstanceId == null) {
			return;
		}

		booleanFilter.addRequiredTerm(
			KaleoInstanceTokenField.KALEO_INSTANCE_ID, kaleoInstanceId);
	}

	protected void appendKaleoInstanceTokenIdTerm(
		BooleanFilter booleanFilter,
		KaleoInstanceTokenQuery kaleoInstanceTokenQuery) {

		Long kaleoInstanceTokenId =
			kaleoInstanceTokenQuery.getKaleoInstanceTokenId();

		if (kaleoInstanceTokenId == null) {
			return;
		}

		booleanFilter.addRequiredTerm(
			KaleoInstanceTokenField.KALEO_INSTANCE_TOKEN_ID,
			kaleoInstanceTokenId);
	}

	protected void appendParentKaleoInstanceTokenIdTerm(
		BooleanFilter booleanFilter,
		KaleoInstanceTokenQuery kaleoInstanceTokenQuery) {

		Long parentKaleoInstanceTokenId =
			kaleoInstanceTokenQuery.getParentKaleoInstanceTokenId();

		if (parentKaleoInstanceTokenId == null) {
			return;
		}

		booleanFilter.addRequiredTerm(
			KaleoInstanceTokenField.PARENT_KALEO_INSTANCE_TOKEN_ID,
			parentKaleoInstanceTokenId);
	}

	protected void appendUserIdTerm(
		BooleanFilter booleanFilter,
		KaleoInstanceTokenQuery kaleoInstanceTokenQuery) {

		Long userId = kaleoInstanceTokenQuery.getUserId();

		if (userId == null) {
			return;
		}

		booleanFilter.addRequiredTerm(Field.USER_ID, userId);
	}

	@Override
	protected void doDelete(KaleoInstanceToken kaleoInstanceToken)
		throws Exception {

		deleteDocument(
			kaleoInstanceToken.getCompanyId(),
			kaleoInstanceToken.getKaleoInstanceTokenId());
	}

	@Override
	protected Document doGetDocument(KaleoInstanceToken kaleoInstanceToken)
		throws Exception {

		Document document = getBaseModelDocument(
			CLASS_NAME, kaleoInstanceToken);

		document.addKeyword(
			KaleoInstanceTokenField.CLASS_NAME,
			kaleoInstanceToken.getClassName());
		document.addKeyword(Field.CLASS_PK, kaleoInstanceToken.getClassPK());
		document.addKeyword(
			KaleoInstanceTokenField.COMPLETED,
			kaleoInstanceToken.isCompleted());
		document.addDate(
			KaleoInstanceTokenField.COMPLETION_DATE,
			kaleoInstanceToken.getCompletionDate());
		document.addKeyword(
			KaleoInstanceTokenField.CURRENT_KALEO_NODE_NAME,
			kaleoInstanceToken.getCurrentKaleoNodeName());
		document.addKeyword(
			KaleoInstanceTokenField.KALEO_INSTANCE_ID,
			kaleoInstanceToken.getKaleoInstanceId());
		document.addKeyword(
			KaleoInstanceTokenField.KALEO_INSTANCE_TOKEN_ID,
			kaleoInstanceToken.getKaleoInstanceTokenId());
		document.addKeyword(
			KaleoInstanceTokenField.PARENT_KALEO_INSTANCE_TOKEN_ID,
			kaleoInstanceToken.getParentKaleoInstanceTokenId());

		try {
			KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

			document.addKeyword(
				KaleoInstanceTokenField.KALEO_DEFINITION_NAME,
				kaleoInstance.getKaleoDefinitionName());
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}
		}

		AssetEntry assetEntry = getAssetEntry(kaleoInstanceToken);

		if (assetEntry == null) {
			return document;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String siteDefaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		String[] titleLanguageIds = getLanguageIds(
			siteDefaultLanguageId, assetEntry.getTitle());

		for (String titleLanguageId : titleLanguageIds) {
			document.addText(
				LocalizationUtil.getLocalizedName(
					KaleoInstanceTokenField.ASSET_TITLE, titleLanguageId),
				assetEntry.getTitle(titleLanguageId));
		}

		String[] descriptionLanguageIds = getLanguageIds(
			siteDefaultLanguageId, assetEntry.getDescription());

		for (String descriptionLanguageId : descriptionLanguageIds) {
			document.addText(
				LocalizationUtil.getLocalizedName(
					KaleoInstanceTokenField.ASSET_DESCRIPTION,
					descriptionLanguageId),
				assetEntry.getDescription(descriptionLanguageId));
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(KaleoInstanceToken kaleoInstanceToken)
		throws Exception {

		Document document = getDocument(kaleoInstanceToken);

		indexWriterHelper.updateDocument(
			getSearchEngineId(), kaleoInstanceToken.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		KaleoInstanceToken entry =
			kaleoInstanceTokenLocalService.getKaleoInstanceToken(classPK);

		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexKaleoInstanceTokens(companyId);
	}

	protected AssetEntry getAssetEntry(KaleoInstanceToken kaleoInstanceToken) {
		try {
			AssetRendererFactory<?> assetRendererFactory =
				getAssetRendererFactory(kaleoInstanceToken.getClassName());

			AssetRenderer<?> assetRenderer =
				assetRendererFactory.getAssetRenderer(
					kaleoInstanceToken.getClassPK());

			return assetEntryLocalService.getEntry(
				assetRenderer.getClassName(), assetRenderer.getClassPK());
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}

		return null;
	}

	protected AssetRendererFactory<?> getAssetRendererFactory(
		String className) {

		return AssetRendererFactoryRegistryUtil.
			getAssetRendererFactoryByClassName(className);
	}

	protected String[] getLanguageIds(
		String defaultLanguageId, String content) {

		String[] languageIds = LocalizationUtil.getAvailableLanguageIds(
			content);

		if (languageIds.length == 0) {
			languageIds = new String[] {defaultLanguageId};
		}

		return languageIds;
	}

	protected void reindexKaleoInstanceTokens(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			kaleoInstanceTokenLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);

		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<KaleoInstanceToken>() {

				@Override
				public void performAction(
					KaleoInstanceToken kaleoInstanceToken) {

					try {
						Document document = getDocument(kaleoInstanceToken);

						indexableActionableDynamicQuery.addDocuments(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index kaleoInstanceToken " +
									kaleoInstanceToken.
										getKaleoInstanceTokenId(),
								pe);
						}
					}
				}

			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected ClassNameLocalService classNameLocalService;

	@Reference
	protected IndexWriterHelper indexWriterHelper;

	@Reference
	protected KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService;

	@Reference
	protected Portal portal;

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoInstanceTokenIndexer.class);

}