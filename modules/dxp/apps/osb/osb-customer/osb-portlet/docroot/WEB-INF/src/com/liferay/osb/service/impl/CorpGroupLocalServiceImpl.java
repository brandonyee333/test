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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.CorpGroupDescriptionException;
import com.liferay.osb.CorpGroupEmailAddressException;
import com.liferay.osb.CorpGroupLogoException;
import com.liferay.osb.CorpGroupNameException;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpGroup;
import com.liferay.osb.service.base.CorpGroupLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.GroupFriendlyURLException;
import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackRegistryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Ryan Park
 * @author Rachael Koestartyo
 */
public class CorpGroupLocalServiceImpl extends CorpGroupLocalServiceBaseImpl {

	public CorpGroup addCorpGroup(
			long userId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String emailAddress, String website,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Corp group

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		String defaultLanguageId = ParamUtil.getString(
			serviceContext, "descriptionDefaultLanguageId",
			LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		validate(
			0, name, descriptionMap, defaultLanguageId, logoFile, emailAddress,
			website);

		long corpGroupId = counterLocalService.increment();

		CorpGroup corpGroup = corpGroupPersistence.create(corpGroupId);

		corpGroup.setUserId(userId);
		corpGroup.setUserName(user.getFullName());
		corpGroup.setCreateDate(now);
		corpGroup.setModifiedDate(now);
		corpGroup.setName(name);

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		corpGroup.setDescriptionMap(descriptionMap, locale);

		String organizationName = getOrganizationName(
			corpGroup.getCorpGroupId(), corpGroup.getName());

		Organization organization = organizationLocalService.addOrganization(
			OSBConstants.USER_DEFAULT_USER_ID,
			OSBConstants.ORGANIZATION_CORPORATION_GROUP_PARENT_ID,
			organizationName, OrganizationConstants.TYPE_REGULAR_ORGANIZATION,
			true, 0, 0, OSBConstants.ORGANIZATION_FULL_MEMBER_STATUS_ID,
			corpGroup.getDescription(), true, new ServiceContext());

		corpGroup.setOrganizationId(organization.getOrganizationId());

		if ((logoFile != null) && logoFile.exists()) {
			byte[] logoBytes = scaleLogo(logoFile);

			AssetAttachment assetAttachment =
				assetAttachmentLocalService.addAssetAttachment(
					userId, CorpGroup.class.getName(), corpGroupId,
					logoFile.getName(), AssetAttachmentConstants.TYPE_MEDIA, 0,
					logoBytes);

			corpGroup.setLogoId(assetAttachment.getAssetAttachmentId());
		}

		corpGroup.setEmailAddress(emailAddress);
		corpGroup.setWebsite(website);

		corpGroupPersistence.update(corpGroup, false);

		// Group

		Group group = corpGroup.getGroup();

		userGroupRoleLocalService.addUserGroupRoles(
			corpGroup.getUserId(), group.getGroupId(),
			new long[] {OSBConstants.ROLE_OSB_CORP_ADMIN_ID});

		// Organization

		userLocalService.addOrganizationUsers(
			organization.getOrganizationId(),
			new long[] {corpGroup.getUserId()});

		return corpGroup;
	}

	public CorpGroup fetchOrganizationCorpGroup(long organizationId)
		throws SystemException {

		return corpGroupPersistence.fetchByOrganizationId(organizationId);
	}

	public List<CorpGroup> getCorpEntryCorpGroups(
			long corpEntryId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return corpEntryPersistence.getCorpGroups(corpEntryId, start, end, obc);
	}

	public CorpGroup getOrganizationCorpGroup(long organizationId)
		throws PortalException, SystemException {

		return corpGroupPersistence.findByOrganizationId(organizationId);
	}

	public List<CorpGroup> getUserCorpGroups(
			long userId, int status, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		List<CorpGroup> corpGroups = new UniqueList<CorpGroup>();

		List<Organization> organizations = userPersistence.getOrganizations(
			userId);

		for (Organization organization : organizations) {
			CorpGroup corpGroup = corpGroupPersistence.fetchByOrganizationId(
				organization.getOrganizationId());

			if (corpGroup != null) {
				corpGroups.add(corpGroup);
			}
		}

		List<CorpEntry> corpEntries = corpEntryLocalService.getUserCorpEntries(
			userId, null, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (CorpEntry corpEntry : corpEntries) {
			List<CorpGroup> corpEntryCorpGroups = getCorpEntryCorpGroups(
				corpEntry.getCorpEntryId());

			corpGroups.addAll(corpEntryCorpGroups);
		}

		corpGroups = ListUtil.sort(corpGroups, obc);

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			return Collections.unmodifiableList(corpGroups);
		}

		return Collections.unmodifiableList(
			ListUtil.subList(corpGroups, start, end));
	}

	public List<CorpGroup> search(
			String name, int start, int end, OrderByComparator obc)
		throws SystemException {

		return search(name, null, start, end, obc);
	}

	public List<CorpGroup> search(
			String name, long[] notCorpGroupIds, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(name, notCorpGroupIds);

		return dynamicQuery(dynamicQuery, start, end, obc);
	}

	public int searchCount(String name) throws SystemException {
		return searchCount(name, null);
	}

	public int searchCount(String name, long[] notCorpGroupIds)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(name, notCorpGroupIds);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public CorpGroup updateCorpGroup(
			long corpGroupId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String emailAddress, String website,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CorpGroup corpGroup = corpGroupPersistence.findByPrimaryKey(
			corpGroupId);

		String defaultLanguageId = ParamUtil.getString(
			serviceContext, "descriptionDefaultLanguageId",
			LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		validate(
			corpGroup.getCorpGroupId(), name, descriptionMap, defaultLanguageId,
			logoFile, emailAddress, website);

		corpGroup.setModifiedDate(new Date());
		corpGroup.setName(name);

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		corpGroup.setDescriptionMap(descriptionMap, locale);

		if ((logoFile != null) && logoFile.exists()) {
			byte[] logoBytes = scaleLogo(logoFile);

			AssetAttachment assetAttachment =
				assetAttachmentLocalService.addAssetAttachment(
					corpGroup.getUserId(), CorpGroup.class.getName(),
					corpGroupId, logoFile.getName(),
					AssetAttachmentConstants.TYPE_MEDIA, 0, logoBytes);

			corpGroup.setLogoId(assetAttachment.getAssetAttachmentId());
		}

		corpGroup.setEmailAddress(emailAddress);
		corpGroup.setWebsite(website);

		corpGroupPersistence.update(corpGroup, false);

		if (corpGroup.getOrganizationId() > 0) {
			String organizationName = getOrganizationName(
				corpGroup.getCorpGroupId(), corpGroup.getName());

			Organization organization = corpGroup.getOrganization();

			organizationLocalService.updateOrganization(
				organization.getCompanyId(), organization.getOrganizationId(),
				organization.getParentOrganizationId(), organizationName,
				organization.getType(), organization.getRecursable(), 0, 0,
				organization.getStatusId(), corpGroup.getDescription(), true,
				null);
		}

		return corpGroup;
	}

	public void updateSite(long corpGroupId, boolean autoFriendlyURL)
		throws PortalException, SystemException {

		CorpGroup corpGroup = corpGroupPersistence.findByPrimaryKey(
			corpGroupId);

		if (autoFriendlyURL) {
			Group group = corpGroup.getGroup();

			try {
				groupLocalService.updateFriendlyURL(
					group.getGroupId(), getFriendlyURL(corpGroup, false));
			}
			catch (GroupFriendlyURLException gfue) {
				int type = gfue.getType();

				if (type == GroupFriendlyURLException.DUPLICATE) {
					groupLocalService.updateFriendlyURL(
						group.getGroupId(), getFriendlyURL(corpGroup, true));
				}
				else {
					groupLocalService.updateFriendlyURL(
						group.getGroupId(),
						"/pages-" + corpGroup.getCorpGroupId());
				}
			}
		}

		Message message = new Message();

		Map<String, String> updateOrganizationLayouts =
			new HashMap<String, String>();

		updateOrganizationLayouts.put(
			"groupId", String.valueOf(corpGroup.getGroupId()));

		updateOrganizationLayouts.put("site", Boolean.TRUE.toString());

		message.put("updateOrganizationLayouts", updateOrganizationLayouts);

		sendMessage("liferay/osb_hook", message);
	}

	protected DynamicQuery buildDynamicQuery(
		String name, long[] notCorpGroupIds) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CorpGroup.class, getClassLoader());

		Junction junction = RestrictionsFactoryUtil.conjunction();

		if (Validator.isNotNull(name)) {
			Property property = PropertyFactoryUtil.forName("name");

			junction.add(
				property.like(StringUtil.quote(name, StringPool.PERCENT)));
		}

		if ((notCorpGroupIds != null) && (notCorpGroupIds.length > 0)) {
			DynamicQuery subselectDynamicQuery =
				DynamicQueryFactoryUtil.forClass(
					CorpGroup.class, getClassLoader());

			subselectDynamicQuery.setProjection(
				ProjectionFactoryUtil.property("corpGroupId"));

			Property corpGroupIdProperty = PropertyFactoryUtil.forName(
				"corpGroupId");

			subselectDynamicQuery.add(in(corpGroupIdProperty, notCorpGroupIds));

			junction.add(corpGroupIdProperty.notIn(subselectDynamicQuery));
		}

		return dynamicQuery.add(junction);
	}

	protected String getFriendlyURL(CorpGroup corpGroup, boolean appendId) {
		String friendlyURL = StringUtil.trim(
			StringUtil.replace(
				corpGroup.getName(), StringPool.PERIOD, StringPool.SPACE));

		friendlyURL = "/pages-" + friendlyURL;

		if (appendId) {
			friendlyURL =
				friendlyURL + StringPool.DASH + corpGroup.getCorpGroupId();
		}

		return friendlyURL;
	}

	protected String getOrganizationName(long corpGroupId, String name) {
		return _ORGANIZATION_NAMESPACE + " (" + corpGroupId + ") " + name;
	}

	protected Criterion in(Property property, Object values) {
		List<Object> list = new ArrayList<Object>();

		if (values instanceof boolean[]) {
			list.addAll(ListUtil.toList((boolean[])values));
		}
		else if (values instanceof int[]) {
			list.addAll(ListUtil.toList((int[])values));
		}
		else if (values instanceof long[]) {
			list.addAll(ListUtil.toList((long[])values));
		}

		return property.in(list);
	}

	protected byte[] scaleLogo(File file) throws PortalException {
		try {
			byte[] bytes = FileUtil.getBytes(file);

			ImageBag imageBag = ImageToolUtil.read(bytes);

			RenderedImage renderedImage = imageBag.getRenderedImage();

			int maxHeight = PortletPropsValues.CORP_GROUP_LOGO_MAX_HEIGHT;
			int maxWidth = PortletPropsValues.CORP_GROUP_LOGO_MAX_WIDTH;

			if ((renderedImage.getHeight() <= maxHeight) &&
				(renderedImage.getWidth() <= maxWidth)) {

				return bytes;
			}

			renderedImage = ImageToolUtil.scale(
				renderedImage, maxHeight, maxWidth);

			return ImageToolUtil.getBytes(renderedImage, imageBag.getType());
		}
		catch (IOException ioe) {
			throw new CorpGroupLogoException(ioe);
		}
	}

	protected void sendMessage(
		final String destinationName, final Message message) {

		Callable<Void> callable = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				MessageBusUtil.sendMessage(destinationName, message);

				return null;
			}

		};

		TransactionCommitCallbackRegistryUtil.registerCallback(callable);
	}

	protected void validate(
			long corpGroupId, String name, Map<Locale, String> descriptionMap,
			String defaultLanguageId, File logoFile, String emailAddress,
			String website)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new CorpGroupNameException();
		}

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		if (descriptionMap.isEmpty() || (locale == null) ||
			!descriptionMap.containsKey(locale) ||
			Validator.isNull(descriptionMap.get(locale))) {

			throw new CorpGroupDescriptionException();
		}

		if ((corpGroupId <= 0) && ((logoFile == null) || !logoFile.exists())) {
			throw new CorpGroupLogoException(
				CorpGroupLogoException.TYPE_LOGO_INVALID);
		}

		if ((corpGroupId <= 0) || ((logoFile != null) && logoFile.exists())) {
			boolean validExtension = false;

			for (String extension :
					PortletPropsValues.CORP_GROUP_LOGO_EXTENSIONS) {

				if (StringUtil.endsWith(logoFile.getName(), extension)) {
					validExtension = true;

					break;
				}
			}

			if (!validExtension) {
				throw new CorpGroupLogoException(
					CorpGroupLogoException.TYPE_LOGO_EXTENSION);
			}
		}

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new CorpGroupEmailAddressException();
		}

		if (Validator.isNotNull(website) && !Validator.isUrl(website)) {
			throw new WebsiteURLException();
		}
	}

	private static final String _ORGANIZATION_NAMESPACE = "CorpGroup";

}