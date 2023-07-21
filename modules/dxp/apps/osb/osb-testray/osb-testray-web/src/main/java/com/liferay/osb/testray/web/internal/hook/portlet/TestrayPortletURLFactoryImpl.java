/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.web.internal.hook.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.impl.VirtualLayout;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(immediate = true, service = PortletURLFactory.class)
@DoPrivileged
public class TestrayPortletURLFactoryImpl implements PortletURLFactory {

	@Activate
	public void activate() throws Exception {
		_originalPortletURLFactory =
			PortletURLFactoryUtil.getPortletURLFactory();

		PortletURLFactoryUtil portletURLFactoryUtil =
			new PortletURLFactoryUtil();

		portletURLFactoryUtil.setPortletURLFactory(this);
	}

	public LiferayPortletURL create(
		HttpServletRequest request, Portlet portlet, Layout layout,
		String lifecycle) {

		return new TestrayPortletURLWrapper(
			request, portlet, layout, lifecycle);
	}

	public LiferayPortletURL create(
		HttpServletRequest request, Portlet portlet, String lifecycle) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout == null) {
			layout = _getLayout(
				(Layout)request.getAttribute(WebKeys.LAYOUT),
				themeDisplay.getPlid());
		}

		return new TestrayPortletURLWrapper(
			request, portlet, layout, lifecycle);
	}

	@Override
	public LiferayPortletURL create(
		HttpServletRequest request, String portletId, Layout layout,
		String lifecycle) {

		return new TestrayPortletURLWrapper(
			request, portletId, layout, lifecycle);
	}

	@Override
	public LiferayPortletURL create(
		HttpServletRequest request, String portletId, long plid,
		String lifecycle) {

		return new TestrayPortletURLWrapper(
			request, portletId, plid, lifecycle);
	}

	public LiferayPortletURL create(
		HttpServletRequest request, String portletId, String lifecycle) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout != null) {
			return new TestrayPortletURLWrapper(
				request, portletId, layout, lifecycle);
		}

		return new TestrayPortletURLWrapper(
			request, portletId, themeDisplay.getPlid(), lifecycle);
	}

	public LiferayPortletURL create(
		PortletRequest portletRequest, Portlet portlet, Layout layout,
		String lifecycle) {

		return new TestrayPortletURLWrapper(
			portletRequest, portlet, layout, lifecycle);
	}

	public LiferayPortletURL create(
		PortletRequest portletRequest, Portlet portlet, long plid,
		String lifecycle) {

		return new TestrayPortletURLWrapper(
			portletRequest, portlet,
			_getLayout(
				(Layout)portletRequest.getAttribute(WebKeys.LAYOUT), plid),
			lifecycle);
	}

	@Override
	public LiferayPortletURL create(
		PortletRequest portletRequest, String portletId, Layout layout,
		String lifecycle) {

		return new TestrayPortletURLWrapper(
			portletRequest, portletId, layout, lifecycle);
	}

	@Override
	public LiferayPortletURL create(
		PortletRequest portletRequest, String portletId, long plid,
		String lifecycle) {

		return new TestrayPortletURLWrapper(
			portletRequest, portletId, plid, lifecycle);
	}

	public LiferayPortletURL create(
		PortletRequest portletRequest, String portletId, String lifecycle) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout != null) {
			return new TestrayPortletURLWrapper(
				portletRequest, portletId, layout, lifecycle);
		}

		return new TestrayPortletURLWrapper(
			portletRequest, portletId, themeDisplay.getPlid(), lifecycle);
	}

	@Deactivate
	public void deactivate() {
		PortletURLFactoryUtil portletURLFactoryUtil =
			new PortletURLFactoryUtil();

		portletURLFactoryUtil.setPortletURLFactory(_originalPortletURLFactory);
	}

	private Layout _getLayout(Layout layout, long plid) {
		if ((layout != null) && (layout.getPlid() == plid) &&
			(layout instanceof VirtualLayout)) {

			return layout;
		}

		if (plid > 0) {
			return _layoutLocalService.fetchLayout(plid);
		}

		return null;
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	private PortletURLFactory _originalPortletURLFactory;

}