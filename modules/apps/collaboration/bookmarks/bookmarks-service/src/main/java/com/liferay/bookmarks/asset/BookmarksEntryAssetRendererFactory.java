/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksEntryLocalService;
import com.liferay.bookmarks.service.permission.BookmarksEntryPermissionChecker;
import com.liferay.bookmarks.service.permission.BookmarksResourcePermissionChecker;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + BookmarksPortletKeys.BOOKMARKS,
	service = AssetRendererFactory.class
)
public class BookmarksEntryAssetRendererFactory
	extends BaseAssetRendererFactory<BookmarksEntry> {

	public static final String TYPE = "bookmark";

	public BookmarksEntryAssetRendererFactory() {
		setClassName(BookmarksEntry.class.getName());
		setLinkable(true);
		setPortletId(BookmarksPortletKeys.BOOKMARKS);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<BookmarksEntry> getAssetRenderer(
			long classPK, int type)
		throws PortalException {

		BookmarksEntry entry = _bookmarksEntryLocalService.getEntry(classPK);

		BookmarksEntryAssetRenderer bookmarksEntryAssetRenderer =
			new BookmarksEntryAssetRenderer(entry);

		bookmarksEntryAssetRenderer.setAssetRendererType(type);
		bookmarksEntryAssetRenderer.setServletContext(_servletContext);

		return bookmarksEntryAssetRenderer;
	}

	@Override
	public String getClassName() {
		return BookmarksEntry.class.getName();
	}

	@Override
	public String getIconCssClass() {
		return "bookmarks";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public PortletURL getURLAdd(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, long classTypeId) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			liferayPortletRequest, getGroup(liferayPortletRequest),
			BookmarksPortletKeys.BOOKMARKS, 0, 0, PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/bookmarks/edit_entry");
		portletURL.setParameter(
			"folderId",
			String.valueOf(BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID));
		portletURL.setParameter("showFolderSelector", Boolean.TRUE.toString());

		return portletURL;
	}

	@Override
	public PortletURL getURLView(
		LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) {

		LiferayPortletURL liferayPortletURL =
			liferayPortletResponse.createLiferayPortletURL(
				BookmarksPortletKeys.BOOKMARKS, PortletRequest.RENDER_PHASE);

		try {
			liferayPortletURL.setWindowState(windowState);
		}
		catch (WindowStateException wse) {
		}

		return liferayPortletURL;
	}

	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {

		return BookmarksResourcePermissionChecker.contains(
			permissionChecker, groupId, ActionKeys.ADD_ENTRY);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return BookmarksEntryPermissionChecker.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.bookmarks.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference(unbind = "-")
	protected void setBookmarksEntryLocalService(
		BookmarksEntryLocalService bookmarksEntryLocalService) {

		_bookmarksEntryLocalService = bookmarksEntryLocalService;
	}

	private BookmarksEntryLocalService _bookmarksEntryLocalService;

	@Reference
	private Portal _portal;

	private ServletContext _servletContext;

}