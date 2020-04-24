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

package com.liferay.osb.loop.web.internal.controller;

import com.liferay.alloy.mvc.AlloyException;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.alloy.mvc.json.web.service.JSONWebServiceMethod;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.osb.loop.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.indexer.LoopShareToIndexer;
import com.liferay.osb.loop.web.internal.messaging.LoopStatsEntryScoreDecayMessageListener;
import com.liferay.osb.loop.web.internal.util.LoopAssetEntrySetUtil;
import com.liferay.osb.loop.web.internal.util.LoopMarkdownUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Timothy Bell
 */
public class HomeController extends LoopAlloyControllerImpl {

	public HomeController() {
		setPermissioned(true);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"includeInactivePersons", "keywords", "searchLimit"},
		parameterTypes = {Boolean.class, String.class, Integer.class}
	)
	public void collatedSearch() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = _getSearchContext();

		searchContext.setAttribute(
			Field.TYPE, LoopConstants.TYPE_LOOP_DIVISION);

		JSONArray jsonArray = _search(searchContext);

		searchContext.setAttribute(Field.TYPE, LoopConstants.TYPE_LOOP_PERSON);

		jsonArray = _mergeJSONArray(jsonArray, _search(searchContext));

		searchContext.setAttribute(Field.TYPE, LoopConstants.TYPE_LOOP_TOPIC);

		jsonArray = _mergeJSONArray(jsonArray, _search(searchContext));

		respondWith(jsonArray);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {
			"childAssetEntrySetsLimit", "end", "keywords",
			"likedParticipantsLimit", "start"
		},
		parameterTypes = {
			Integer.class, Integer.class, String.class, Integer.class,
			Integer.class
		}
	)
	public void fullSearch() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String searchCategory : _SEARCH_CATEGORIES) {
			if (StringUtil.equalsIgnoreCase(
					searchCategory, _SEARCH_CATEGORY_PAGES) &&
				(getAPIVersion() < 9)) {

				continue;
			}

			String key = searchCategory;

			String className = _getClassName(searchCategory);

			if (getAPIVersion() < 9) {
				key = String.valueOf(PortalUtil.getClassNameId(className));
			}

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(className);

			Map<String, Serializable> attributes = new HashMap<>();

			if (StringUtil.equalsIgnoreCase(
					searchCategory, _SEARCH_CATEGORY_PAGES)) {

				attributes.put(
					"excludeTypes",
					new String[] {
						String.valueOf(
							LoopAssetEntrySetConstants.TYPE_ANNOUNCEMENT),
						String.valueOf(LoopAssetEntrySetConstants.TYPE_POST)
					});
			}
			else if (StringUtil.equalsIgnoreCase(
						searchCategory, _SEARCH_CATEGORY_POSTS)) {

				attributes.put(
					"excludeTypes",
					new String[] {
						String.valueOf(LoopAssetEntrySetConstants.TYPE_PAGE)
					});
			}

			Sort[] sorts = null;

			if (className.equals(LoopPerson.class.getName())) {
				sorts = new Sort[] {
					new Sort(
						"userStatusPriority_sortable", Sort.INT_TYPE, false),
					new Sort(null, Sort.SCORE_TYPE, false)
				};
			}

			jsonObject.put(
				key,
				doSearch(
					indexer, new AlloyServiceInvoker(className), attributes,
					sorts));
		}

		respondWith(jsonObject);
	}

	public void help() throws Exception {
		_validateHelp();

		String articleIdKey = ParamUtil.getString(
			request, "id",
			LoopConstants.URL_TOKEN_HELP_ARTICLE_ID +
				LoopWebConfigurationValues.LOOP_HELP_JOURNAL_ARTICLE_ID);

		renderRequest.setAttribute("articleId", articleIdKey.substring(1));
	}

	public void index() {
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {
			"classNameId", "includeInactivePersons", "keywords",
			"parentAssetEntrySetId", "queryType", "searchLimit", "type"
		},
		parameterTypes = {
			Long.class, Boolean.class, String.class, Long.class, Integer.class,
			Integer.class, Integer.class
		}
	)
	public void privateSearch() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray currentSharedToJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray newSharedToJSONArray = JSONFactoryUtil.createJSONArray();

		SearchContext searchContext = _getSearchContext();

		searchContext.setAttribute(
			Field.TYPE, ParamUtil.getInteger(request, "type"));

		JSONArray searchHitsJSONArray = _search(searchContext);

		long parentAssetEntrySetId = ParamUtil.getLong(
			request, "parentAssetEntrySetId");

		if (parentAssetEntrySetId == 0) {
			for (int i = 0; i < searchHitsJSONArray.length(); i++) {
				newSharedToJSONArray.put(searchHitsJSONArray.getJSONObject(i));
			}
		}
		else {
			List<AssetSharingEntry> assetSharingEntries =
				AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(
					PortalUtil.getClassNameId(AssetEntrySet.class),
					LoopAssetEntrySetUtil.getRootAssetEntrySetId(
						parentAssetEntrySetId));

			for (int i = 0; i < searchHitsJSONArray.length(); i++) {
				JSONObject searchHitJSONObject =
					searchHitsJSONArray.getJSONObject(i);

				long entityClassNameId = searchHitJSONObject.getLong(
					"entityClassNameId");
				long entityClassPK = searchHitJSONObject.getLong(
					"entityClassPK");

				if ((entityClassNameId == PortalUtil.getClassNameId(
						LoopTopic.class)) ||
					_isCurrentlySharedTo(
						assetSharingEntries, entityClassNameId,
						entityClassPK)) {

					currentSharedToJSONArray.put(searchHitJSONObject);
				}
				else {
					newSharedToJSONArray.put(searchHitJSONObject);
				}
			}
		}

		jsonObject.put("currentSharedToJSONArray", currentSharedToJSONArray);
		jsonObject.put("newSharedToJSONArray", newSharedToJSONArray);

		respondWith(jsonObject);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {
			"classNameId", "includeInactivePersons", "keywords", "queryType",
			"searchLimit", "type"
		},
		parameterTypes = {
			Long.class, Boolean.class, String.class, Integer.class,
			Integer.class, Integer.class
		}
	)
	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = _getSearchContext();

		searchContext.setAttribute(
			Field.TYPE, ParamUtil.getInteger(request, "type"));

		respondWith(_search(searchContext));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "modifiedTime", "start", "queryType", "type"},
		parameterTypes = {
			Integer.class, Long.class, Integer.class, Integer.class,
			Integer.class
		}
	)
	public void syncAutocomplete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(
			Field.TYPE, ParamUtil.getInteger(request, "type"));

		searchContext.setAttribute("includeAuditEntries", Boolean.TRUE);
		searchContext.setAttribute("includeInactivePersons", Boolean.TRUE);
		searchContext.setAttribute(
			"modifiedTime", ParamUtil.getLong(request, "modifiedTime"));
		searchContext.setAttribute(
			"queryType", ParamUtil.getInteger(request, "queryType"));

		searchContext.setCompanyId(themeDisplay.getCompanyId());

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		searchContext.setEnd(end);

		Sort[] sorts = {
			new Sort("modifiedTime_sortable", Sort.LONG_TYPE, false)
		};

		searchContext.setSorts(sorts);

		searchContext.setStart(ParamUtil.getInteger(request, "start"));

		respondWith(_search(searchContext));
	}

	public void testSendSyncMessage() throws Exception {
		if (!LoopWebConfigurationValues.LOOP_TESTS_ENABLED ||
			!isRespondingTo("json")) {

			return;
		}

		String message = ParamUtil.getString(request, "message");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(message);

		boolean messageSent = LoopUtil.sendSyncMessage(
			jsonObject, jsonObject.getString("entityName"));

		if (!messageSent) {
			respondWith("An error has occurred.");

			return;
		}

		respondWith("The data was successfully synced.");
	}

	public void uiKit() {
		render(controllerPath + StringPool.SLASH + "ui_kit");
	}

	public void viewMarkdownHTML() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		String markdownSource = ParamUtil.getString(request, "markdownSource");

		respondWith(
			LoopMarkdownUtil.markdownToHtml(
				themeDisplay.getCompanyId(), markdownSource, true, true));
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE)
	public void viewMinimumAPIVersion() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"version", LoopWebConfigurationValues.API_VERSION_MINIMUM);

		respondWith(jsonObject);
	}

	@Override
	protected MessageListener buildSchedulerMessageListener() {
		return LoopStatsEntryScoreDecayMessageListener.getInstance(this);
	}

	@Override
	protected Trigger getSchedulerTrigger() {
		return TriggerFactoryUtil.createTrigger(
			getSchedulerJobName(), getMessageListenerGroupName(),
			LoopWebConfigurationValues.LOOP_CRON_TRIGGER_HOME_CONTROLLER);
	}

	@Override
	protected void initIndexer() {
		indexer = LoopShareToIndexer.getInstance();

		indexerClassName = indexer.getClassName();

		Indexer existingIndexer = IndexerRegistryUtil.getIndexer(
			indexerClassName);

		if (existingIndexer != indexer) {
			if (existingIndexer != null) {
				IndexerRegistryUtil.unregister(existingIndexer);
			}

			IndexerRegistryUtil.register(LoopShareToIndexer.getInstance());
		}

		super.initIndexer();
	}

	private static String _getClassName(String searchCategory) {
		if (StringUtil.equalsIgnoreCase(
				searchCategory, _SEARCH_CATEGORY_DIVISIONS)) {

			return LoopDivision.class.getName();
		}
		else if (StringUtil.equalsIgnoreCase(
					searchCategory, _SEARCH_CATEGORY_PAGES)) {

			return AssetEntrySet.class.getName();
		}
		else if (StringUtil.equalsIgnoreCase(
					searchCategory, _SEARCH_CATEGORY_PEOPLE)) {

			return LoopPerson.class.getName();
		}
		else if (StringUtil.equalsIgnoreCase(
					searchCategory, _SEARCH_CATEGORY_POSTS)) {

			return AssetEntrySet.class.getName();
		}
		else if (StringUtil.equalsIgnoreCase(
					searchCategory, _SEARCH_CATEGORY_TOPICS)) {

			return LoopTopic.class.getName();
		}

		return StringPool.BLANK;
	}

	private SearchContext _getSearchContext() {
		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(
			Field.CLASS_NAME_ID, ParamUtil.getLong(request, "classNameId"));

		searchContext.setAttribute(
			"includeInactivePersons",
			ParamUtil.getBoolean(request, "includeInactivePersons"));
		searchContext.setAttribute(
			"queryType", ParamUtil.getInteger(request, "queryType"));

		searchContext.setCompanyId(themeDisplay.getCompanyId());

		int searchLimit = ParamUtil.getInteger(request, "searchLimit");

		if (searchLimit > 0) {
			searchContext.setEnd(searchLimit);
			searchContext.setStart(0);
		}

		String keywords = ParamUtil.getString(request, "keywords");

		searchContext.setKeywords(keywords);

		Sort[] sorts = {
			new Sort("userStatusPriority_sortable", Sort.INT_TYPE, false),
			new Sort(null, Sort.SCORE_TYPE, false),
			new Sort("classNamePriority_sortable", Sort.INT_TYPE, false),
			new Sort("name_sortable", false)
		};

		searchContext.setSorts(sorts);

		return searchContext;
	}

	private boolean _isCurrentlySharedTo(
		List<AssetSharingEntry> assetSharingEntries, long classNameId,
		long classPK) {

		for (AssetSharingEntry assetSharingEntry : assetSharingEntries) {
			if (AssetEntrySetParticipantInfoUtil.isMember(
					classNameId, classPK,
					assetSharingEntry.getSharedToClassNameId(),
					assetSharingEntry.getSharedToClassPK())) {

				return true;
			}
		}

		return false;
	}

	private JSONArray _mergeJSONArray(
		JSONArray jsonArray1, JSONArray jsonArray2) {

		for (int i = 0; i < jsonArray2.length(); i++) {
			jsonArray1.put(jsonArray2.getJSONObject(i));
		}

		return jsonArray1;
	}

	private JSONArray _search(SearchContext searchContext) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Hits hits = indexer.search(searchContext);

		if (searchContext.getStart() > hits.getLength()) {
			return JSONFactoryUtil.createJSONArray();
		}

		Document[] documents = hits.getDocs();

		for (Document document : documents) {
			long classNameId = GetterUtil.getLong(document.get("classNameId"));
			long classPK = GetterUtil.getLong(document.get("classPK"));

			jsonArray.put(
				LoopUtil.getCompositeJSONObject(
					themeDisplay, classNameId, classPK, StringPool.BLANK));
		}

		return jsonArray;
	}

	private void _validateHelp() throws Exception {
		String id = ParamUtil.getString(
			request, "id",
			LoopConstants.URL_TOKEN_HELP_ARTICLE_ID +
				LoopWebConfigurationValues.LOOP_HELP_JOURNAL_ARTICLE_ID);

		if (id.equals(LoopConstants.URL_TOKEN_HELP_ARTICLE_ID)) {
			throw new AlloyException("the-article-does-not-exist");
		}
	}

	private static final String[] _SEARCH_CATEGORIES;

	private static final String _SEARCH_CATEGORY_DIVISIONS = "divisions";

	private static final String _SEARCH_CATEGORY_PAGES = "pages";

	private static final String _SEARCH_CATEGORY_PEOPLE = "people";

	private static final String _SEARCH_CATEGORY_POSTS = "posts";

	private static final String _SEARCH_CATEGORY_TOPICS = "topics";

	static {
		_SEARCH_CATEGORIES = new String[] {
			_SEARCH_CATEGORY_DIVISIONS, _SEARCH_CATEGORY_PAGES,
			_SEARCH_CATEGORY_PEOPLE, _SEARCH_CATEGORY_POSTS,
			_SEARCH_CATEGORY_TOPICS
		};
	}

}