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

package com.liferay.osb.community.doc.project.service.impl;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.exception.DocProjectDescriptionException;
import com.liferay.osb.community.doc.project.exception.DocProjectIconException;
import com.liferay.osb.community.doc.project.exception.DocProjectIconExtensionException;
import com.liferay.osb.community.doc.project.exception.DocProjectNameException;
import com.liferay.osb.community.doc.project.exception.NoSuchDocProjectException;
import com.liferay.osb.community.doc.project.internal.file.util.DocProjectFileUtil;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.service.base.DocProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.File;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 * @author Yury Butrymovich
 * @author Haote Chou
 */
public class DocProjectLocalServiceImpl extends DocProjectLocalServiceBaseImpl {

	@Override
	public DocProject addDocProject(
			long userId, String name, String description, String iconFileName,
			File iconFile, boolean unlisted, String type, String typeSettings,
			int status, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(name, description, iconFileName, iconFile);

		// Doc project

		long docProjectId = counterLocalService.increment();

		DocProject docProject = docProjectPersistence.create(docProjectId);

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getDefault(), name);

		boolean active = false;

		if (status == DocProjectConstants.STATUS_LIVE) {
			active = true;
		}

		Group group = _groupLocalService.addGroup(
			userId, GroupConstants.DEFAULT_PARENT_GROUP_ID,
			DocProject.class.getName(), docProjectId,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, null,
			GroupConstants.TYPE_SITE_OPEN, true,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION,
			StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(name), true,
			false, active, null);

		docProject.setGroupId(group.getGroupId());

		docProject.setCompanyId(user.getCompanyId());
		docProject.setUserId(userId);
		docProject.setUserName(user.getFullName());
		docProject.setCreateDate(now);
		docProject.setModifiedDate(now);
		docProject.setName(name);
		docProject.setDescription(description);
		docProject.setIconFileName(getIconFileName(iconFileName));
		docProject.setUnlisted(unlisted);
		docProject.setType(type);
		docProject.setTypeSettings(typeSettings);
		docProject.setStatus(status);

		docProjectPersistence.update(docProject);

		// Assets

		if (serviceContext != null) {
			updateAsset(
				userId, docProjectId, serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
		}

		// Files

		DocProjectFileUtil.initDocProjectDirectory(docProjectId);

		DocProjectFileUtil.addDocProjectFile(
			docProjectId, docProject.getIconFileName(), iconFile);

		return docProject;
	}

	@Override
	public DocProject deleteDocProject(DocProject docProject)
		throws PortalException {

		// Doc project

		docProjectPersistence.remove(docProject);

		// Files

		DocProjectFileUtil.destroyDocProjectDirectory(
			docProject.getDocProjectId());

		// Group

		_groupLocalService.deleteGroup(docProject.getGroupId());

		return docProject;
	}

	@Override
	public DocProject deleteDocProject(long docProjectId)
		throws PortalException {

		DocProject docProject = docProjectPersistence.findByPrimaryKey(
			docProjectId);

		return deleteDocProject(docProject);
	}

	@Override
	public DocProject getDocProjectByGroupId(long groupId)
		throws NoSuchDocProjectException {

		return docProjectPersistence.findByGroupId(groupId);
	}

	@Override
	public List<DocProject> getDocProjects(
			boolean unlisted, int status, int start, int end,
			OrderByComparator obc)
		throws NoSuchDocProjectException {

		return docProjectPersistence.findByU_S(
			unlisted, status, start, end, obc);
	}

	@Override
	public void updateAsset(
			long userId, long docProjectId, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		DocProject docProject = docProjectPersistence.findByPrimaryKey(
			docProjectId);

		boolean visible = false;

		if (docProject.getStatus() == DocProjectConstants.STATUS_LIVE) {
			visible = true;
		}

		Group group = _groupLocalService.getCompanyGroup(
			docProject.getCompanyId());

		_assetEntryLocalService.updateEntry(
			userId, group.getGroupId(), null, null, DocProject.class.getName(),
			docProject.getDocProjectId(), docProject.getUuid(), 0,
			assetCategoryIds, assetTagNames, visible, visible, null, null, null,
			null, null, null, null, null, null, null, 0, 0, null);
	}

	@Override
	public DocProject updateDocProject(
			long docProjectId, String name, String description,
			String iconFileName, File iconFile, boolean unlisted, String type,
			String typeSettings, int status, ServiceContext serviceContext)
		throws PortalException {

		DocProject docProject = docProjectPersistence.findByPrimaryKey(
			docProjectId);

		boolean skipIconUpdate = false;

		String oldIconFileName = docProject.getIconFileName();

		if ((iconFile == null) || !iconFile.exists() ||
			Validator.isNull(iconFileName)) {

			iconFile = DocProjectFileUtil.getDocumentProjectFile(
				docProjectId, docProject.getIconFileName());
			iconFileName = docProject.getIconFileName();

			skipIconUpdate = true;
		}

		validate(name, description, iconFileName, iconFile);

		docProject.setModifiedDate(new Date());
		docProject.setName(name);
		docProject.setDescription(description);

		if (!skipIconUpdate) {
			docProject.setIconFileName(getIconFileName(iconFileName));
		}

		docProject.setUnlisted(unlisted);
		docProject.setType(type);
		docProject.setTypeSettings(typeSettings);
		docProject.setStatus(status);

		docProjectPersistence.update(docProject);

		// Assets

		if (serviceContext != null) {
			updateAsset(
				docProject.getUserId(), docProjectId,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
		}

		// Files

		if (!skipIconUpdate) {
			DocProjectFileUtil.deleteDocProjectFile(
				docProjectId, oldIconFileName);

			DocProjectFileUtil.addDocProjectFile(
				docProjectId, docProject.getIconFileName(), iconFile);
		}

		// Group

		Group group = _groupLocalService.getGroup(docProject.getGroupId());

		boolean active = false;

		if (status == DocProjectConstants.STATUS_LIVE) {
			active = true;
		}

		_groupLocalService.updateGroup(
			group.getGroupId(), group.getParentGroupId(), group.getNameMap(),
			group.getDescriptionMap(), group.getType(),
			group.isManualMembership(), group.getMembershipRestriction(),
			group.getFriendlyURL(), group.isInheritContent(), active, null);

		return docProject;
	}

	protected String getIconFileName(String fileName) {
		String extension = FileUtil.getExtension(fileName);

		return "icon." + extension;
	}

	protected void validate(
			String name, String description, String iconFileName, File iconFile)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new DocProjectNameException("Name is null");
		}

		if (Validator.isNull(description)) {
			throw new DocProjectDescriptionException("Description is null");
		}

		if ((iconFile == null) || !iconFile.exists()) {
			throw new DocProjectIconException("Icon file does not exist");
		}

		String extension = FileUtil.getExtension(iconFileName);

		if (!ArrayUtil.contains(_ICON_EXTENSIONS, extension)) {
			throw new DocProjectIconExtensionException(
				"Invalid icon file extension. Valid extensions: " +
					Arrays.toString(_ICON_EXTENSIONS));
		}
	}

	private static final String[] _ICON_EXTENSIONS = {"svg", "png"};

	@ServiceReference(type = AssetEntryLocalService.class)
	private AssetEntryLocalService _assetEntryLocalService;

	@ServiceReference(type = GroupLocalService.class)
	private GroupLocalService _groupLocalService;

}