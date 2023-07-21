/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portlet.blogs.service.permission.BlogsEntryPermission;
import com.liferay.portlet.blogs.service.permission.BlogsPermission;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + BlogsPortletKeys.BLOGS,
	service = AssetRendererFactory.class
)
public class BlogsEntryAssetRendererFactory
	extends BaseAssetRendererFactory<BlogsEntry> {

	public static final String TYPE = "blog";

	public BlogsEntryAssetRendererFactory() {
		setClassName(BlogsEntry.class.getName());
		setLinkable(true);
		setPortletId(BlogsPortletKeys.BLOGS);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<BlogsEntry> getAssetRenderer(long classPK, int type)
		throws PortalException {

		BlogsEntry entry = _blogsEntryLocalService.getEntry(classPK);

		BlogsEntryAssetRenderer blogsEntryAssetRenderer =
			new BlogsEntryAssetRenderer(entry);

		blogsEntryAssetRenderer.setAssetRendererType(type);
		blogsEntryAssetRenderer.setServletContext(_servletContext);

		return blogsEntryAssetRenderer;
	}

	@Override
	public AssetRenderer<BlogsEntry> getAssetRenderer(
			long groupId, String urlTitle)
		throws PortalException {

		BlogsEntry entry = _blogsEntryLocalService.getEntry(groupId, urlTitle);

		return new BlogsEntryAssetRenderer(entry);
	}

	@Override
	public String getClassName() {
		return BlogsEntry.class.getName();
	}

	@Override
	public String getIconCssClass() {
		return "blogs";
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
			BlogsPortletKeys.BLOGS, 0, 0, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/blogs/edit_entry");

		return portletURL;
	}

	@Override
	public PortletURL getURLView(
		LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) {

		LiferayPortletURL liferayPortletURL =
			liferayPortletResponse.createLiferayPortletURL(
				BlogsPortletKeys.BLOGS, PortletRequest.RENDER_PHASE);

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

		return BlogsPermission.contains(
			permissionChecker, groupId, ActionKeys.ADD_ENTRY);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return BlogsEntryPermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.blogs.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference(unbind = "-")
	protected void setBlogsEntryLocalService(
		BlogsEntryLocalService blogsEntryLocalService) {

		_blogsEntryLocalService = blogsEntryLocalService;
	}

	private BlogsEntryLocalService _blogsEntryLocalService;

	@Reference
	private Portal _portal;

	private ServletContext _servletContext;

}