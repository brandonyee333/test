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
import com.liferay.alloy.mvc.AlloySearchResult;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.alloy.mvc.BaseAlloyControllerImpl;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopAuditEntryLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.composite.LoopTopicComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.image.LoopImageUtil;
import com.liferay.osb.loop.web.internal.permission.LoopPermission;
import com.liferay.osb.loop.web.internal.util.AssetEntrySetUtil;
import com.liferay.osb.loop.web.internal.util.LoopDivisionUtil;
import com.liferay.osb.loop.web.internal.util.LoopHttpServletRequestWrapper;
import com.liferay.osb.loop.web.internal.util.LoopJobTitleUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopPortletPreferencesUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch6.constants.ElasticsearchSearchContextAttributes;

import java.io.File;
import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Timothy Bell
 */
public class LoopAlloyControllerImpl extends BaseAlloyControllerImpl {

	public static LoopAlloyControllerImpl createMockLoopAlloyControllerImpl(
		LoopAlloyControllerImpl loopAlloyControllerImpl) {

		LoopAlloyControllerImpl mockLoopAlloyControllerImpl =
			new LoopAlloyControllerImpl();

		mockLoopAlloyControllerImpl.alloyServiceInvoker =
			loopAlloyControllerImpl.alloyServiceInvoker;

		Company company = loopAlloyControllerImpl.company;

		mockLoopAlloyControllerImpl.company = company;

		mockLoopAlloyControllerImpl.indexer = loopAlloyControllerImpl.indexer;
		mockLoopAlloyControllerImpl.indexerClassName =
			loopAlloyControllerImpl.indexerClassName;

		mockLoopAlloyControllerImpl.request = new DynamicServletRequest(
			loopAlloyControllerImpl.request, false);

		try {
			mockLoopAlloyControllerImpl.user =
				UserLocalServiceUtil.getDefaultUser(company.getCompanyId());

			ThemeDisplay themeDisplay = loopAlloyControllerImpl.themeDisplay;

			ThemeDisplay mockThemeDisplay = (ThemeDisplay)themeDisplay.clone();

			mockThemeDisplay.setUser(mockLoopAlloyControllerImpl.user);

			mockLoopAlloyControllerImpl.themeDisplay = mockThemeDisplay;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		return mockLoopAlloyControllerImpl;
	}

	public void addFeatured() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		BaseModel<?> baseModel = fetchBaseModel();

		String featuredClassPKsString =
			LoopPortletPreferencesUtil.getPreference(
				themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY, featuredPreferenceKey);

		List<Long> featuredClassPKs = ListUtil.toList(
			StringUtil.split(featuredClassPKsString, 0L));

		validateAddFeatured(baseModel, featuredClassPKs);

		if (featuredClassPKs.contains(baseModel.getPrimaryKeyObj())) {
			respondWith("The item is already featured.");

			return;
		}

		long featuredClassPK = GetterUtil.getLong(baseModel.getPrimaryKeyObj());

		featuredClassPKs.add(0, featuredClassPK);

		if (featuredClassPKs.size() >
				LoopWebConfigurationValues.FEATURED_CONTENT_MAX_COUNT) {

			featuredClassPKs.remove(featuredClassPKs.size() - 1);
		}

		LoopPortletPreferencesUtil.setPreferences(
			themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, featuredPreferenceKey,
			ListUtil.toString(featuredClassPKs, (String)null));

		respondWith(getModelJSONObject(featuredClassPK));
	}

	public void deleteModel(BaseModel<?> baseModel) throws Exception {
		if (baseModel instanceof LoopDivision ||
			baseModel instanceof LoopPerson || baseModel instanceof LoopTopic) {

			LoopAuditEntry loopAuditEntry =
				LoopAuditEntryLocalServiceUtil.createLoopAuditEntry(0);

			String name = StringPool.BLANK;

			if (baseModel instanceof LoopDivision) {
				LoopDivision loopDivision = (LoopDivision)baseModel;

				Organization organization =
					OrganizationLocalServiceUtil.getOrganization(
						loopDivision.getOrganizationId());

				name = organization.getName();
			}
			else if (baseModel instanceof LoopPerson) {
				LoopPerson loopPerson = (LoopPerson)baseModel;

				User user = UserLocalServiceUtil.getUser(
					loopPerson.getPersonUserId());

				name = user.getFullName();
			}
			else {
				LoopTopic loopTopic = (LoopTopic)baseModel;

				name = loopTopic.getName();
			}

			updateModel(
				loopAuditEntry, "classNameId",
				PortalUtil.getClassNameId(baseModel.getModelClass()), "classPK",
				baseModel.getPrimaryKeyObj(), "name", name);
		}

		alloyServiceInvoker.deleteModel(baseModel);
	}

	public void removeFeatured() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		BaseModel<?> baseModel = fetchBaseModel();

		validateDeleteFeatured();

		String featuredClassPKsString =
			LoopPortletPreferencesUtil.getPreference(
				themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY, featuredPreferenceKey);

		List<Long> featuredClassPKs = ListUtil.toList(
			StringUtil.split(featuredClassPKsString, 0L));

		featuredClassPKs.remove(baseModel.getPrimaryKeyObj());

		LoopPortletPreferencesUtil.setPreferences(
			themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, featuredPreferenceKey,
			ListUtil.toString(featuredClassPKs, (String)null));

		respondWith(
			"The item was successfully removed from the featured list.");
	}

	public void setFeatured() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		String featuredClassPKsString = ParamUtil.getString(
			request, featuredPreferenceKey);

		List<Long> featuredClassPKs = ListUtil.toList(
			StringUtil.split(featuredClassPKsString, 0L));

		ListUtil.distinct(featuredClassPKs);

		validateSetFeatured(featuredClassPKs);

		LoopPortletPreferencesUtil.setPreferences(
			themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, featuredPreferenceKey,
			ListUtil.toString(featuredClassPKs, (String)null));

		respondWith("The featured items were successfully set.");
	}

	public void setFeaturedPreferenceKey(String featuredPreferenceKey) {
		this.featuredPreferenceKey = featuredPreferenceKey;
	}

	@Override
	public void updateModelIgnoreRequest(
			BaseModel<?> baseModel, Object... properties)
		throws Exception {

		super.updateModelIgnoreRequest(baseModel, properties);

		indexModel(baseModel);
	}

	public void viewFeatured() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String featuredClassPKsString =
			LoopPortletPreferencesUtil.getPreference(
				themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY, featuredPreferenceKey);

		long[] featuredClassPKs = StringUtil.split(featuredClassPKsString, 0L);

		for (long featuredClassPK : featuredClassPKs) {
			jsonArray.put(getModelJSONObject(featuredClassPK));
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("results", jsonArray);
		jsonObject.put("total", jsonArray.length());

		respondWith(jsonObject);
	}

	protected JSONObject doSearch(
			Indexer indexer, AlloyServiceInvoker alloyServiceInvoker,
			Map<String, Serializable> attributes, Sort[] sorts)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray resultsJSONArray = JSONFactoryUtil.createJSONArray();

		if (attributes == null) {
			attributes = new HashMap<>();
		}

		long type = ParamUtil.getLong(request, "type");

		if (type > 0) {
			attributes.put(Field.TYPE, type);
		}

		attributes.put(
			ElasticsearchSearchContextAttributes.
				ATTRIBUTE_KEY_SEARCH_REQUEST_PREFERENCE,
			String.valueOf(themeDisplay.getUserId()));

		String keywords = ParamUtil.getString(request, "keywords");
		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		if ((getAPIVersion() < 4) && (start == QueryUtil.ALL_POS) &&
			(end != QueryUtil.ALL_POS)) {

			start = 0;
		}

		AlloySearchResult alloySearchResult = search(
			indexer, alloyServiceInvoker, request, portletRequest, attributes,
			keywords, sorts, start, end);

		for (BaseModel<?> baseModel : alloySearchResult.getBaseModels()) {
			long classNameId = PortalUtil.getClassNameId(
				baseModel.getModelClassName());
			long classPK = (Long)baseModel.getPrimaryKeyObj();

			if (classNameId == PortalUtil.getClassNameId(AssetEntrySet.class)) {
				AssetEntrySet assetEntrySet =
					AssetEntrySetLocalServiceUtil.getAssetEntrySet(classPK);

				int childAssetEntrySetsLimit = ParamUtil.getInteger(
					request, "childAssetEntrySetsLimit");
				int likedParticipantsLimit = ParamUtil.getInteger(
					request, "likedParticipantsLimit");

				if (assetEntrySet.getType() !=
						LoopAssetEntrySetConstants.TYPE_PAGE) {

					if (childAssetEntrySetsLimit == 0) {
						childAssetEntrySetsLimit =
							LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
					}

					if (likedParticipantsLimit == 0) {
						likedParticipantsLimit =
							LoopConstants.
								ASSET_ENTRY_SET_LIKED_PARTICIPANTS_LIMIT;
					}
				}

				resultsJSONArray.put(
					AssetEntrySetUtil.getAssetEntrySetJSONObject(
						themeDisplay, assetEntrySet, 0,
						childAssetEntrySetsLimit, likedParticipantsLimit));
			}
			else {
				resultsJSONArray.put(
					LoopUtil.getCompositeJSONObject(
						themeDisplay, classNameId, classPK, null));
			}
		}

		jsonObject.put("results", resultsJSONArray);

		jsonObject.put("total", alloySearchResult.getSize());

		return jsonObject;
	}

	@Override
	protected void executeResource(Method method) throws Exception {
		Class<?> tempClass = clazz;

		clazz = clazz.getSuperclass();

		super.executeResource(method);

		if (pollBaseModel != null) {
			LoopUtil.pollIndexState(
				themeDisplay.getCompanyId(), pollBaseModel.getModelClassName(),
				(Long)pollBaseModel.getPrimaryKeyObj(), pollHitsLength);
		}

		pollBaseModel = null;
		pollHitsLength = 0;

		clazz = tempClass;
	}

	protected BaseModel<?> fetchAttachedBaseModel() {
		return null;
	}

	protected BaseModel<?> fetchBaseModel() {
		return null;
	}

	protected int getAPIVersion() {
		return request.getIntHeader("api-version");
	}

	protected JSONObject getModelJSONObject(long classPK) throws Exception {
		return JSONFactoryUtil.createJSONObject();
	}

	protected ServiceContext getServiceContext() throws Exception {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			portletRequest);

		serviceContext.setScopeGroupId(themeDisplay.getCompanyGroupId());

		return serviceContext;
	}

	@Override
	protected boolean hasPermission() {
		try {
			BaseModel<?> baseModel = null;

			String id = ParamUtil.getString(request, "id");

			if (Validator.isNotNull(id)) {
				if (StringUtil.equalsIgnoreCase(controllerPath, "divisions")) {
					baseModel = LoopDivisionUtil.fetchLoopDivision(
						request, themeDisplay);
				}
				else if (StringUtil.equalsIgnoreCase(controllerPath, "feed") ||
						 StringUtil.equalsIgnoreCase(controllerPath, "pages")) {

					baseModel =
						AssetEntrySetLocalServiceUtil.fetchAssetEntrySet(
							GetterUtil.getLong(id));
				}
				else if (StringUtil.equalsIgnoreCase(
							controllerPath, "job_titles")) {

					baseModel = LoopJobTitleUtil.fetchLoopJobTitle(
						request, themeDisplay);
				}
				else if (StringUtil.equalsIgnoreCase(
							controllerPath, "people")) {

					baseModel = LoopPersonUtil.fetchLoopPerson(
						request, themeDisplay);
				}
				else if (StringUtil.equalsIgnoreCase(
							controllerPath, "topics")) {

					baseModel = LoopTopicUtil.fetchLoopTopic(
						request, themeDisplay);
				}

				if (permissioned &&
					!LoopPermission.contains(
						themeDisplay, baseModel, actionPath)) {

					return false;
				}

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}

		if (StringUtil.equalsIgnoreCase(controllerPath, "pages")) {
			BaseModel<?> baseModel = fetchAttachedBaseModel();

			if (baseModel == null) {
				return false;
			}

			return LoopPermission.contains(
				themeDisplay, baseModel, actionPath + "Pages");
		}

		return LoopPermission.contains(
			themeDisplay, controllerPath, actionPath);
	}

	@Override
	protected void initIndexer() {
		if (!controllerPath.equals("home")) {
			super.initIndexer();
		}
	}

	@Override
	protected void initServletVariables() {
		super.initServletVariables();

		request = new LoopHttpServletRequestWrapper(request);
	}

	@Override
	protected void initThemeDisplayVariables() {
		super.initThemeDisplayVariables();

		themeDisplay.setRequest(request);

		try {
			LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
				themeDisplay.getUserId());

			request.setAttribute(
				"currentLoopPersonComposite",
				new LoopPersonComposite(curLoopPerson, themeDisplay));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void redirectTo(String redirect) {
		if (StringUtil.matches(actionPath, "update")) {
			Pattern pattern = Pattern.compile(
				LoopConstants.URL_LOOP_REGEX_URL_NAME);

			Matcher matcher = pattern.matcher(redirect);

			try {
				if (matcher.find()) {
					if (StringUtil.matches(controllerPath, "divisions")) {
						LoopDivision loopDivision =
							LoopDivisionUtil.fetchLoopDivision(
								request, themeDisplay);

						LoopDivisionComposite loopDivisionComposite =
							new LoopDivisionComposite(
								loopDivision, themeDisplay);

						redirect = loopDivisionComposite.getDisplayURL();
					}
					else if (StringUtil.matches(controllerPath, "people")) {
						LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
							request, themeDisplay);

						LoopPersonComposite loopPersonComposite =
							new LoopPersonComposite(loopPerson, themeDisplay);

						redirect = loopPersonComposite.getDisplayURL();
					}
					else if (StringUtil.matches(controllerPath, "topics")) {
						LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
							request, themeDisplay);

						LoopTopicComposite loopTopicComposite =
							new LoopTopicComposite(loopTopic, themeDisplay);

						redirect = loopTopicComposite.getDisplayURL();
					}
				}
			}
			catch (Exception e) {
			}
		}

		super.redirectTo(redirect);
	}

	protected BaseModel<?> setImage(
			BaseModel<?> baseModel, String keyword, String[] imageTypes,
			String imageTypeKey)
		throws Exception {

		String imagePayload = BeanPropertiesUtil.getString(
			baseModel, "imagePayload");

		JSONObject imagePayloadJSONObject = JSONFactoryUtil.createJSONObject(
			imagePayload);

		if (imagePayloadJSONObject.getJSONObject(keyword) != null) {
			LoopImageUtil.deleteDLFileEntries(
				imagePayloadJSONObject.getJSONObject(keyword));
		}

		String keywordPayload = ParamUtil.getString(request, keyword);

		String param = StringPool.BLANK;
		long classNameId = 0;

		if (baseModel instanceof LoopDivision) {
			param = "loopDivisionId";

			classNameId = PortalUtil.getClassNameId(LoopDivision.class);
		}
		else if (baseModel instanceof LoopPerson) {
			param = "loopPersonId";

			classNameId = PortalUtil.getClassNameId(LoopPerson.class);
		}
		else if (baseModel instanceof LoopTopic) {
			param = "loopTopicId";

			classNameId = PortalUtil.getClassNameId(LoopTopic.class);
		}
		else {
			throw new AlloyException("the-model-does-not-exist");
		}

		long classPK = BeanPropertiesUtil.getLong(baseModel, param);

		LoopImageUtil.updateDLFileEntry(
			keywordPayload, classPK, LoopConstants.IMAGE_TYPE_RAW);

		JSONObject keywordPayloadJSONObject =
			LoopImageUtil.getKeywordPayloadJSONObject(
				keywordPayload, classNameId, classPK, imageTypes, imageTypeKey);

		imagePayloadJSONObject.put(keyword, keywordPayloadJSONObject);

		updateModelIgnoreRequest(
			baseModel, "imagePayload", imagePayloadJSONObject.toString());

		LoopImageUtil.addResourcePermissions(keywordPayloadJSONObject);

		return baseModel;
	}

	protected void uploadImage(
			UploadPortletRequest uploadPortletRequest, Class<?> clazz)
		throws Exception {

		File file = uploadPortletRequest.getFile("file");

		validateUploadImage(file);

		JSONObject imagePayloadJSONObject =
			LoopImageUtil.uploadTempImagePayload(
				themeDisplay.getUserId(), PortalUtil.getClassNameId(clazz),
				file);

		if (isRespondingTo("json")) {
			respondWith(imagePayloadJSONObject);

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	protected void validateAddFeatured(
			BaseModel<?> baseModel, List<Long> featuredClassPKs)
		throws Exception {

		validateModel(baseModel);
		validateUpdateFeatured();

		boolean autoDelete = ParamUtil.getBoolean(request, "autoDelete");

		if (!autoDelete &&
			(featuredClassPKs.size() >=
				LoopWebConfigurationValues.FEATURED_CONTENT_MAX_COUNT)) {

			throw new AlloyException(
				"you-have-exceeded-the-maximum-number-of-featured-content");
		}
	}

	protected void validateDeleteFeatured() throws Exception {
		validateUpdateFeatured();
	}

	protected void validateModel(BaseModel<?> baseModel) throws Exception {
		if (baseModel == null) {
			throw new AlloyException("the-model-does-not-exist");
		}
	}

	protected void validateSetFeatured(List<Long> featuredClassPKs)
		throws Exception {

		validateUpdateFeatured();

		if (featuredClassPKs.size() >
				LoopWebConfigurationValues.FEATURED_CONTENT_MAX_COUNT) {

			throw new AlloyException(
				"you-have-exceeded-the-maximum-number-of-featured-content");
		}

		for (long featuredClassPK : featuredClassPKs) {
			BaseModel<?> baseModel = alloyServiceInvoker.fetchModel(
				featuredClassPK);

			validateModel(baseModel);
		}
	}

	protected void validateUpdateFeatured() throws Exception {
		LoopPerson loopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPerson, themeDisplay);

		if (!loopPersonComposite.getPermissionUpdateFeaturedContent()) {
			throw new AlloyException(
				"you-do-not-have-permission-to-access-the-requested-resource");
		}
	}

	protected void validateUploadImage(File file) throws Exception {
		String extension =
			StringPool.PERIOD + FileUtil.getExtension(file.getName());

		if (!ArrayUtil.contains(
				LoopWebConfigurationValues.IMAGE_EXTENSIONS, extension)) {

			throw new AlloyException("the-image-type-is-invalid");
		}
	}

	protected String featuredPreferenceKey;
	protected BaseModel<?> pollBaseModel;
	protected int pollHitsLength;

	private static final Log _log = LogFactoryUtil.getLog(
		LoopAlloyControllerImpl.class);

}