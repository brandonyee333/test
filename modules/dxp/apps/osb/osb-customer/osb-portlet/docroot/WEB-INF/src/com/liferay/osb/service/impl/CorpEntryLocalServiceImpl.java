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
import com.liferay.osb.CorpEntryContactEmailAddressException;
import com.liferay.osb.CorpEntryDescriptionException;
import com.liferay.osb.CorpEntryFaxNumberException;
import com.liferay.osb.CorpEntryLogoException;
import com.liferay.osb.CorpEntryMembershipException;
import com.liferay.osb.CorpEntryNameException;
import com.liferay.osb.CorpEntryProfileEmailAddressException;
import com.liferay.osb.corp.merge.CorpEntryMergeHelper;
import com.liferay.osb.corp.merge.CorpEntryMergeHelperFactoryUtil;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpEntryConstants;
import com.liferay.osb.service.base.CorpEntryLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.GroupFriendlyURLException;
import com.liferay.portal.PhoneNumberException;
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
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackRegistryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.Role;
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
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 * @author Rachael Koestartyo
 */
public class CorpEntryLocalServiceImpl extends CorpEntryLocalServiceBaseImpl {

	public CorpEntry addCorpEntry(
			long userId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String dossieraAccountKey, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Corp entry

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		String defaultLanguageId = ParamUtil.getString(
			serviceContext, "descriptionDefaultLanguageId",
			LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		validate(
			0, name, descriptionMap, defaultLanguageId, logoFile, countryId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website);

		long corpEntryId = counterLocalService.increment();

		CorpEntry corpEntry = corpEntryPersistence.create(corpEntryId);

		corpEntry.setUserId(userId);
		corpEntry.setUserName(user.getFullName());
		corpEntry.setCreateDate(now);
		corpEntry.setModifiedDate(now);
		corpEntry.setName(name);

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		corpEntry.setDescriptionMap(descriptionMap, locale);

		String organizationName = getOrganizationName(
			corpEntry.getCorpEntryId(), corpEntry.getName());

		Organization organization = organizationLocalService.addOrganization(
			OSBConstants.USER_DEFAULT_USER_ID,
			OSBConstants.ORGANIZATION_CORPORATION_PARENT_ID, organizationName,
			OrganizationConstants.TYPE_REGULAR_ORGANIZATION, true,
			corpEntry.getRegionId(), corpEntry.getCountryId(),
			OSBConstants.ORGANIZATION_FULL_MEMBER_STATUS_ID,
			corpEntry.getDescription(), false, new ServiceContext());

		corpEntry.setOrganizationId(organization.getOrganizationId());

		if ((logoFile != null) && logoFile.exists()) {
			byte[] logoBytes = scaleLogo(logoFile);

			AssetAttachment assetAttachment =
				assetAttachmentLocalService.addAssetAttachment(
					userId, CorpEntry.class.getName(), corpEntryId,
					logoFile.getName(), AssetAttachmentConstants.TYPE_MEDIA, 0,
					logoBytes);

			corpEntry.setLogoId(assetAttachment.getAssetAttachmentId());
		}

		if (Validator.isNotNull(street1)) {
			Address address = updateAddress(
				0, userId, corpEntryId, street1, street2, street3, city, zip,
				regionId, countryId);

			corpEntry.setAddressId(address.getAddressId());
		}

		corpEntry.setContactEmailAddress(contactEmailAddress);
		corpEntry.setProfileEmailAddress(profileEmailAddress);
		corpEntry.setPhoneNumber(phoneNumber);
		corpEntry.setFaxNumber(faxNumber);
		corpEntry.setWebsite(website);
		corpEntry.setDossieraAccountKey(dossieraAccountKey);

		int status = ParamUtil.getInteger(
			serviceContext, "status", WorkflowConstants.STATUS_PENDING);

		corpEntry.setStatus(status);

		corpEntry.setStatusByUserId(userId);
		corpEntry.setStatusByUserName(user.getFullName());
		corpEntry.setStatusDate(now);
		corpEntry.setStatusMessage(StringPool.BLANK);

		corpEntryPersistence.update(corpEntry, false);

		// Group

		Group group = corpEntry.getGroup();

		userGroupRoleLocalService.addUserGroupRoles(
			corpEntry.getUserId(), group.getGroupId(),
			PortletPropsValues.CORP_ENTRY_ROLE_IDS_DEFAULT);

		// Indexer

		reindex(corpEntry);

		return corpEntry;
	}

	@Override
	public CorpEntry deleteCorpEntry(CorpEntry corpEntry)
		throws PortalException, SystemException {

		// Corp entry

		corpEntryPersistence.remove(corpEntry);

		// Organization

		List<User> users = userLocalService.getOrganizationUsers(
			corpEntry.getOrganizationId());

		long[] userIds = new long[users.size()];

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			userIds[i] = user.getUserId();
		}

		userLocalService.unsetOrganizationUsers(
			corpEntry.getOrganizationId(), userIds);

		organizationLocalService.deleteOrganization(
			corpEntry.getOrganizationId());

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CorpEntry.class);

		indexer.delete(corpEntry);

		return corpEntry;
	}

	@Override
	public CorpEntry deleteCorpEntry(long corpEntryId)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		return deleteCorpEntry(corpEntry);
	}

	public CorpEntry fetchCorpEntry(String dossieraAccountKey)
		throws SystemException {

		return corpEntryPersistence.fetchByDossieraAccountKey(
			dossieraAccountKey);
	}

	public CorpEntry fetchOrganizationCorpEntry(long organizationId)
		throws SystemException {

		return corpEntryPersistence.fetchByOrganizationId(organizationId);
	}

	public CorpEntry getCorpEntry(String dossieraAccountKey)
		throws PortalException, SystemException {

		return corpEntryPersistence.findByDossieraAccountKey(
			dossieraAccountKey);
	}

	public List<CorpEntry> getCorpGroupCorpEntries(
			long corpGroupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return corpGroupPersistence.getCorpEntries(
			corpGroupId, start, end, obc);
	}

	public List<CorpEntry> getCorpGroupCorpEntries(
			long userId, long corpGroupId, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		List<CorpEntry> corpEntries = new ArrayList<CorpEntry>();

		for (CorpEntry corpEntry : getCorpGroupCorpEntries(corpGroupId)) {
			if (!organizationLocalService.hasUserOrganization(
					userId, corpEntry.getOrganizationId())) {

				continue;
			}

			if ((status != WorkflowConstants.STATUS_ANY) &&
				(status != corpEntry.getStatus())) {

				continue;
			}

			corpEntries.add(corpEntry);
		}

		corpEntries = ListUtil.sort(corpEntries, obc);

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			return Collections.unmodifiableList(corpEntries);
		}

		return Collections.unmodifiableList(
			ListUtil.subList(corpEntries, start, end));
	}

	public CorpEntry getOrganizationCorpEntry(long organizationId)
		throws PortalException, SystemException {

		return corpEntryPersistence.findByOrganizationId(organizationId);
	}

	public List<CorpEntry> getUserCorpEntries(
			long userId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return getUserCorpEntries(
			userId, null, WorkflowConstants.STATUS_ANY, start, end, obc);
	}

	public List<CorpEntry> getUserCorpEntries(
			long userId, long roleId, int status, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		Role role = roleLocalService.getRole(roleId);

		return getUserCorpEntries(
			userId, role.getName(), status, start, end, obc);
	}

	public List<CorpEntry> getUserCorpEntries(
			long userId, String roleName, int type, int status, int start,
			int end, OrderByComparator obc)
		throws PortalException, SystemException {

		List<CorpEntry> corpEntries = new ArrayList<CorpEntry>();

		List<Organization> organizations = userPersistence.getOrganizations(
			userId);

		for (Organization organization : organizations) {
			CorpEntry corpEntry = corpEntryPersistence.fetchByOrganizationId(
				organization.getOrganizationId());

			if (corpEntry == null) {
				continue;
			}

			if (Validator.isNotNull(roleName) &&
				!userGroupRoleLocalService.hasUserGroupRole(
					userId, organization.getGroupId(), roleName)) {

				continue;
			}

			if ((status != WorkflowConstants.STATUS_ANY) &&
				(status != corpEntry.getStatus())) {

				continue;
			}

			long corpEntryId = corpEntry.getCorpEntryId();

			if ((type == CorpEntryConstants.TYPE_ORPHAN) &&
				corpGroupLocalService.hasCorpEntryCorpGroups(corpEntryId)) {

				continue;
			}

			corpEntries.add(corpEntry);
		}

		corpEntries = ListUtil.sort(corpEntries, obc);

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			return Collections.unmodifiableList(corpEntries);
		}

		return Collections.unmodifiableList(
			ListUtil.subList(corpEntries, start, end));
	}

	public List<CorpEntry> getUserCorpEntries(
			long userId, String roleName, int status, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return getUserCorpEntries(
			userId, roleName, CorpEntryConstants.TYPE_ANY, status, start, end,
			obc);
	}

	public boolean hasUserCorpEntry(long userId, long corpEntryId)
		throws SystemException {

		CorpEntry corpEntry = corpEntryPersistence.fetchByPrimaryKey(
			corpEntryId);

		if (corpEntry == null) {
			return false;
		}

		return organizationLocalService.hasUserOrganization(
			userId, corpEntry.getOrganizationId());
	}

	public boolean hasUserCorpEntryRole(
			long userId, long corpEntryId, long roleId)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		Organization organization = organizationPersistence.findByPrimaryKey(
			corpEntry.getOrganizationId());

		return userGroupRoleLocalService.hasUserGroupRole(
			userId, organization.getGroupId(), roleId);
	}

	public boolean hasUserCorpEntryRole(
			long userId, long corpEntryId, String roleName)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		Organization organization = organizationPersistence.findByPrimaryKey(
			corpEntry.getOrganizationId());

		return userGroupRoleLocalService.hasUserGroupRole(
			userId, organization.getGroupId(), roleName);
	}

	public CorpEntry mergeCorpEntry(
			long userId, long corpEntryId, long mergeCorpEntryId, String name,
			Map<Locale, String> descriptionMap, long logoId, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String dossieraAccountKey,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Corp entry

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		String defaultLanguageId = ParamUtil.getString(
			serviceContext, "descriptionDefaultLanguageId",
			LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		validate(
			0, name, descriptionMap, defaultLanguageId, null, countryId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website);

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		corpEntry.setModifiedDate(now);
		corpEntry.setName(name);

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		corpEntry.setDescriptionMap(descriptionMap, locale);

		corpEntry.setLogoId(logoId);

		updateAddress(
			corpEntry.getAddressId(), corpEntry.getUserId(), corpEntryId,
			street1, street2, street3, city, zip, regionId, countryId);

		corpEntry.setContactEmailAddress(contactEmailAddress);
		corpEntry.setProfileEmailAddress(profileEmailAddress);
		corpEntry.setPhoneNumber(phoneNumber);
		corpEntry.setFaxNumber(faxNumber);
		corpEntry.setWebsite(website);
		corpEntry.setDossieraAccountKey(dossieraAccountKey);

		corpEntryPersistence.update(corpEntry, false);

		CorpEntry mergeCorpEntry = corpEntryPersistence.findByPrimaryKey(
			mergeCorpEntryId);

		mergeCorpEntry.setStatus(WorkflowConstants.STATUS_MERGED);
		mergeCorpEntry.setStatusByUserId(user.getUserId());
		mergeCorpEntry.setStatusByUserName(user.getFullName());
		mergeCorpEntry.setStatusDate(now);
		mergeCorpEntry.setStatusMessage(
			String.valueOf(corpEntry.getCorpEntryId()));

		corpEntryPersistence.update(mergeCorpEntry, false);

		// Helpers

		List<CorpEntryMergeHelper> corpEntryMergeHelpers =
			CorpEntryMergeHelperFactoryUtil.getCorpEntryMergeHelpers(
				corpEntry, mergeCorpEntry, serviceContext);

		for (CorpEntryMergeHelper corpEntryMergeHelper :
				corpEntryMergeHelpers) {

			corpEntryMergeHelper.run();
		}

		// Indexer

		reindex(corpEntry);
		reindex(mergeCorpEntry);

		return corpEntry;
	}

	public List<CorpEntry> search(
			String name, int start, int end, OrderByComparator obc)
		throws SystemException {

		return search(
			name, WorkflowConstants.STATUS_ANY, null, start, end, obc);
	}

	public List<CorpEntry> search(
			String name, int status, long[] notCorpEntryIds, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			name, status, notCorpEntryIds);

		return dynamicQuery(dynamicQuery, start, end, obc);
	}

	public int searchCount(String name) throws SystemException {
		return searchCount(name, WorkflowConstants.STATUS_ANY, null);
	}

	public int searchCount(String name, int status, long[] notCorpEntryIds)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			name, status, notCorpEntryIds);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public CorpEntry updateCorpEntry(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String dossieraAccountKey, ServiceContext serviceContext)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
				corpEntryId);

		String defaultLanguageId = ParamUtil.getString(
				serviceContext, "descriptionDefaultLanguageId",
				LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		validate(
			corpEntry.getCorpEntryId(), name, descriptionMap, defaultLanguageId,
			logoFile, countryId, contactEmailAddress, profileEmailAddress,
			phoneNumber, faxNumber, website);

		corpEntry.setModifiedDate(new Date());
		corpEntry.setName(name);

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		corpEntry.setDescriptionMap(descriptionMap, locale);

		if ((logoFile != null) && logoFile.exists()) {
			byte[] logoBytes = scaleLogo(logoFile);

			AssetAttachment assetAttachment =
				assetAttachmentLocalService.addAssetAttachment(
					corpEntry.getUserId(), CorpEntry.class.getName(),
					corpEntryId, logoFile.getName(),
					AssetAttachmentConstants.TYPE_MEDIA, 0, logoBytes);

			corpEntry.setLogoId(assetAttachment.getAssetAttachmentId());
		}

		if (Validator.isNotNull(street1)) {
			Address address = updateAddress(
				corpEntry.getAddressId(), corpEntry.getUserId(), corpEntryId,
				street1, street2, street3, city, zip, regionId, countryId);

			corpEntry.setAddressId(address.getAddressId());
		}

		corpEntry.setContactEmailAddress(contactEmailAddress);
		corpEntry.setProfileEmailAddress(profileEmailAddress);
		corpEntry.setPhoneNumber(phoneNumber);
		corpEntry.setFaxNumber(faxNumber);
		corpEntry.setWebsite(website);
		corpEntry.setDossieraAccountKey(dossieraAccountKey);

		corpEntryPersistence.update(corpEntry, false);

		if (corpEntry.getOrganizationId() > 0) {
			String organizationName = getOrganizationName(
				corpEntry.getCorpEntryId(), corpEntry.getName());

			Organization organization = corpEntry.getOrganization();

			organizationLocalService.updateOrganization(
				organization.getCompanyId(), organization.getOrganizationId(),
				organization.getParentOrganizationId(), organizationName,
				organization.getType(), organization.getRecursable(), regionId,
				countryId, organization.getStatusId(),
				corpEntry.getDescription(), true, null);
		}

		reindex(corpEntry);

		return corpEntry;
	}

	public void updateSite(
			long corpEntryId, int status, boolean autoFriendlyURL)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		Group group = corpEntry.getGroup();

		if (status == WorkflowConstants.STATUS_APPROVED) {
			groupLocalService.updateSite(group.getGroupId(), true);
		}
		else {
			groupLocalService.updateSite(group.getGroupId(), false);
		}

		if ((status == WorkflowConstants.STATUS_APPROVED) && autoFriendlyURL) {
			try {
				groupLocalService.updateFriendlyURL(
					group.getGroupId(), getFriendlyURL(corpEntry, false));
			}
			catch (GroupFriendlyURLException gfue) {
				int type = gfue.getType();

				if (type == GroupFriendlyURLException.DUPLICATE) {
					groupLocalService.updateFriendlyURL(
						group.getGroupId(), getFriendlyURL(corpEntry, true));
				}
				else {
					groupLocalService.updateFriendlyURL(
						group.getGroupId(),
						"/pages-" + corpEntry.getCorpEntryId());
				}
			}
		}

		Message message = new Message();

		Map<String, String> updateOrganizationLayouts =
			new HashMap<String, String>();

		updateOrganizationLayouts.put(
			"groupId", String.valueOf(corpEntry.getGroupId()));

		if (status == WorkflowConstants.STATUS_APPROVED) {
			updateOrganizationLayouts.put("site", Boolean.TRUE.toString());
		}
		else {
			updateOrganizationLayouts.put("site", Boolean.FALSE.toString());
		}

		message.put("updateOrganizationLayouts", updateOrganizationLayouts);

		sendMessage("liferay/osb_hook", message);
	}

	public CorpEntry updateStatus(
			long userId, long corpEntryId, int status, String statusMessage)
		throws PortalException, SystemException {

		// Corp entry

		User user = userPersistence.findByPrimaryKey(userId);

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		corpEntry.setStatus(status);
		corpEntry.setStatusByUserId(userId);
		corpEntry.setStatusByUserName(user.getFullName());
		corpEntry.setStatusDate(new Date());
		corpEntry.setStatusMessage(statusMessage);

		corpEntryPersistence.update(corpEntry, false);

		// Indexer

		reindex(corpEntry);

		return corpEntry;
	}

	public void validateMembership(long userId, long corpEntryId, long roleId)
		throws PortalException, SystemException {

		if (!hasUserCorpEntry(userId, corpEntryId)) {
			throw new CorpEntryMembershipException();
		}

		if ((roleId > 0) &&
			!hasUserCorpEntryRole(userId, corpEntryId, roleId)) {

			throw new CorpEntryMembershipException();
		}
	}

	protected DynamicQuery buildDynamicQuery(
		String name, int status, long[] notCorpEntryIds) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CorpEntry.class, getClassLoader());

		Junction junction = RestrictionsFactoryUtil.conjunction();

		if (Validator.isNotNull(name)) {
			Property property = PropertyFactoryUtil.forName("name");

			junction.add(
				property.like(StringUtil.quote(name, StringPool.PERCENT)));
		}

		if (status != WorkflowConstants.STATUS_ANY) {
			Property statusProperty = PropertyFactoryUtil.forName("status");

			junction.add(statusProperty.eq(status));
		}

		if ((notCorpEntryIds != null) && (notCorpEntryIds.length > 0)) {
			DynamicQuery subselectDynamicQuery =
				DynamicQueryFactoryUtil.forClass(
					CorpEntry.class, getClassLoader());

			subselectDynamicQuery.setProjection(
				ProjectionFactoryUtil.property("corpEntryId"));

			Property corpGroupIdProperty = PropertyFactoryUtil.forName(
				"corpEntryId");

			subselectDynamicQuery.add(in(corpGroupIdProperty, notCorpEntryIds));

			junction.add(corpGroupIdProperty.notIn(subselectDynamicQuery));
		}

		return dynamicQuery.add(junction);
	}

	protected String getFriendlyURL(CorpEntry corpEntry, boolean appendId) {
		String friendlyURL = StringUtil.trim(
			StringUtil.replace(
				corpEntry.getName(), StringPool.PERIOD, StringPool.SPACE));

		friendlyURL = "/pages-" + friendlyURL;

		if (appendId) {
			friendlyURL =
				friendlyURL + StringPool.DASH + corpEntry.getCorpEntryId();
		}

		return friendlyURL;
	}

	protected String getOrganizationName(long corpEntryId, String name) {
		return _ORGANIZATION_NAMESPACE + " (" + corpEntryId + ") " + name;
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

	protected void reindex(final CorpEntry corpEntry) {
		Callable<Void> callable = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
					CorpEntry.class);

				indexer.reindex(corpEntry);

				return null;
			}

		};

		TransactionCommitCallbackRegistryUtil.registerCallback(callable);
	}

	protected byte[] scaleLogo(File file) throws PortalException {
		try {
			byte[] bytes = FileUtil.getBytes(file);

			if (StringUtil.endsWith(file.getName(), "svg")) {
				return bytes;
			}

			ImageBag imageBag = ImageToolUtil.read(bytes);

			RenderedImage renderedImage = imageBag.getRenderedImage();

			int maxHeight = PortletPropsValues.CORP_ENTRY_LOGO_MAX_HEIGHT;
			int maxWidth = PortletPropsValues.CORP_ENTRY_LOGO_MAX_WIDTH;

			if ((renderedImage.getHeight() <= maxHeight) &&
				(renderedImage.getWidth() <= maxWidth)) {

				return bytes;
			}

			renderedImage = ImageToolUtil.scale(
				renderedImage, maxHeight, maxWidth);

			return ImageToolUtil.getBytes(renderedImage, imageBag.getType());
		}
		catch (IOException ioe) {
			throw new CorpEntryLogoException(ioe);
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

	protected Address updateAddress(
			long addressId, long userId, long corpEntryId, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId)
		throws PortalException, SystemException {

		if (addressId > 0) {
			Address address = addressLocalService.getAddress(addressId);

			return addressLocalService.updateAddress(
				address.getAddressId(), street1, street2, street3, city, zip,
				regionId, countryId, address.getTypeId(), address.getMailing(),
				address.isPrimary());
		}
		else {
			return addressLocalService.addAddress(
				userId, CorpEntry.class.getName(), corpEntryId, street1,
				street2, street3, city, zip, regionId, countryId, 0, true,
				true);
		}
	}

	protected void validate(
			long corpEntryId, String name, File logoFile, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			throw new CorpEntryNameException();
		}

		if ((corpEntryId <= 0) && ((logoFile == null) || !logoFile.exists())) {
			throw new CorpEntryLogoException(
				CorpEntryLogoException.TYPE_LOGO_INVALID);
		}

		if ((corpEntryId <= 0) || ((logoFile != null) && logoFile.exists())) {
			boolean validExtension = false;

			for (String extension :
					PortletPropsValues.CORP_ENTRY_LOGO_EXTENSIONS) {

				if (StringUtil.endsWith(logoFile.getName(), extension)) {
					validExtension = true;

					break;
				}
			}

			if (!validExtension) {
				throw new CorpEntryLogoException(
					CorpEntryLogoException.TYPE_LOGO_EXTENSION);
			}
		}

		if (countryId > 0) {
			countryService.getCountry(countryId);
		}

		if (Validator.isNotNull(contactEmailAddress) &&
			!Validator.isEmailAddress(contactEmailAddress)) {

			throw new CorpEntryContactEmailAddressException();
		}

		if (Validator.isNotNull(profileEmailAddress) &&
			!Validator.isEmailAddress(profileEmailAddress)) {

			throw new CorpEntryProfileEmailAddressException();
		}

		if (Validator.isNotNull(phoneNumber) &&
			!Validator.isPhoneNumber(phoneNumber)) {

			throw new PhoneNumberException();
		}

		if (Validator.isNotNull(faxNumber) &&
			!Validator.isPhoneNumber(faxNumber)) {

			throw new CorpEntryFaxNumberException();
		}

		if (Validator.isNotNull(website) && !Validator.isUrl(website)) {
			throw new WebsiteURLException();
		}
	}

	protected void validate(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			String defaultLanguageId, File logoFile, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website)
		throws PortalException, SystemException {

		Locale locale = LocaleUtil.fromLanguageId(defaultLanguageId);

		if (descriptionMap.isEmpty() || (locale == null) ||
			!descriptionMap.containsKey(locale) ||
			Validator.isNull(descriptionMap.get(locale))) {

			throw new CorpEntryDescriptionException();
		}

		validate(
			corpEntryId, name, logoFile, countryId, contactEmailAddress,
			profileEmailAddress, phoneNumber, faxNumber, website);
	}

	private static final String _ORGANIZATION_NAMESPACE = "CorpEntry";

}