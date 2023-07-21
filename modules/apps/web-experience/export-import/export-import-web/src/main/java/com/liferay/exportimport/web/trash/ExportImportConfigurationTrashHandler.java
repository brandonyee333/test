/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.web.trash;

import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.trash.BaseTrashHandler;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.kernel.model.TrashEntry;

import javax.portlet.PortletRequest;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Reference;

/**
 * @author     Levente Hudák
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.exportimport.web.internal.trash.ExportImportConfigurationTrashHandler}
 */
@Deprecated
public class ExportImportConfigurationTrashHandler extends BaseTrashHandler {

	@Override
	public void deleteTrashEntry(long classPK) throws PortalException {
		_exportImportConfigurationLocalService.deleteExportImportConfiguration(
			classPK);
	}

	@Override
	public String getClassName() {
		return ExportImportConfiguration.class.getName();
	}

	@Override
	public String getRestoreMessage(
		PortletRequest portletRequest, long classPK) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return themeDisplay.translate("export-import-template");
	}

	@Override
	public TrashEntry getTrashEntry(long classPK) throws PortalException {
		ExportImportConfiguration exportImportConfiguration =
			_exportImportConfigurationLocalService.getExportImportConfiguration(
				classPK);

		return exportImportConfiguration.getTrashEntry();
	}

	@Override
	public TrashRenderer getTrashRenderer(long classPK) throws PortalException {
		ExportImportConfiguration exportImportConfiguration =
			_exportImportConfigurationLocalService.getExportImportConfiguration(
				classPK);

		ExportImportConfigurationTrashRenderer
			exportImportConfigurationTrashRenderer =
				new ExportImportConfigurationTrashRenderer(
					exportImportConfiguration);

		exportImportConfigurationTrashRenderer.setServletContext(
			servletContext);

		return exportImportConfigurationTrashRenderer;
	}

	@Override
	public boolean isInTrash(long classPK) throws PortalException {
		ExportImportConfiguration exportImportConfiguration =
			_exportImportConfigurationLocalService.getExportImportConfiguration(
				classPK);

		return exportImportConfiguration.isInTrash();
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK)
		throws PortalException {

		_exportImportConfigurationLocalService.
			restoreExportImportConfigurationFromTrash(userId, classPK);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.exportimport.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException {

		ExportImportConfiguration exportImportConfiguration =
			_exportImportConfigurationLocalService.getExportImportConfiguration(
				classPK);

		Group group = _groupLocalService.getGroup(
			exportImportConfiguration.getGroupId());

		return GroupPermissionUtil.contains(permissionChecker, group, actionId);
	}

	@Reference(unbind = "-")
	protected void setExportImportConfigurationLocalService(
		ExportImportConfigurationLocalService
			exportImportConfigurationLocalService) {

		_exportImportConfigurationLocalService =
			exportImportConfigurationLocalService;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	protected ServletContext servletContext;

	private ExportImportConfigurationLocalService
		_exportImportConfigurationLocalService;
	private GroupLocalService _groupLocalService;

}