/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.alloy.mvc.sample.web.internal.hook.portlet;

import com.liferay.alloy.mvc.sample.web.internal.constants.AlloyMVCSamplePortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletModeFactory;
import com.liferay.portal.kernel.portlet.WindowStateFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.lang.reflect.Constructor;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class AlloyMVCSamplePortletURLWrapper
	extends BaseAlloyMVCSamplePortletURLWrapper {

	public static final String PORTLET_NAMESPACE =
		StringPool.UNDERLINE + AlloyMVCSamplePortletKeys.ALLOY_MVC_SAMPLE +
			StringPool.UNDERLINE;

	public AlloyMVCSamplePortletURLWrapper(
		HttpServletRequest request, Portlet portlet, Layout layout,
		String lifecycle) {

		this(
			getLiferayPortletURL(
				new Class<?>[] {
					HttpServletRequest.class, Portlet.class, Layout.class,
					String.class
				},
				new Object[] {request, portlet, layout, lifecycle}));
	}

	public AlloyMVCSamplePortletURLWrapper(
		HttpServletRequest request, String portletId, Layout layout,
		String lifecycle) {

		this(
			getLiferayPortletURL(
				new Class<?>[] {
					HttpServletRequest.class, String.class, Layout.class,
					String.class
				},
				new Object[] {request, portletId, layout, lifecycle}));
	}

	public AlloyMVCSamplePortletURLWrapper(
		HttpServletRequest request, String portletId, long plid,
		String lifecycle) {

		this(
			getLiferayPortletURL(
				new Class<?>[] {
					HttpServletRequest.class, String.class, long.class,
					String.class
				},
				new Object[] {request, portletId, plid, lifecycle}));
	}

	public AlloyMVCSamplePortletURLWrapper(
		LiferayPortletURL liferayPortletURL) {

		super(liferayPortletURL);
	}

	public AlloyMVCSamplePortletURLWrapper(
		PortletRequest portletRequest, Portlet portlet, Layout layout,
		String lifecycle) {

		this(
			getLiferayPortletURL(
				new Class<?>[] {
					PortletRequest.class, Portlet.class, Layout.class,
					String.class
				},
				new Object[] {portletRequest, portlet, layout, lifecycle}));
	}

	public AlloyMVCSamplePortletURLWrapper(
		PortletRequest portletRequest, String portletId, Layout layout,
		String lifecycle) {

		this(
			getLiferayPortletURL(
				new Class<?>[] {
					PortletRequest.class, String.class, Layout.class,
					String.class
				},
				new Object[] {portletRequest, portletId, layout, lifecycle}));
	}

	public AlloyMVCSamplePortletURLWrapper(
		PortletRequest portletRequest, String portletId, long plid,
		String lifecycle) {

		this(
			getLiferayPortletURL(
				new Class<?>[] {
					PortletRequest.class, String.class, long.class, String.class
				},
				new Object[] {portletRequest, portletId, plid, lifecycle}));
	}

	public void setPortletMode(String portletMode) throws PortletModeException {
		setPortletMode(PortletModeFactory.getPortletMode(portletMode));
	}

	public void setWindowState(String windowState) throws WindowStateException {
		setWindowState(WindowStateFactory.getWindowState(windowState));
	}

	@Override
	public String toString() {
		String string = super.toString();

		return string.replace(PORTLET_NAMESPACE, StringPool.BLANK);
	}

	protected static LiferayPortletURL getLiferayPortletURL(
		Class<?>[] parameterTypes, Object[] parameters) {

		try {
			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			Class<? extends LiferayPortletURL> portletURLImplClass =
				(Class<? extends LiferayPortletURL>)portalClassLoader.loadClass(
					"com.liferay.portlet.PortletURLImpl");

			Constructor<? extends LiferayPortletURL> portletURLImplConstructor =
				portletURLImplClass.getConstructor(parameterTypes);

			return portletURLImplConstructor.newInstance(parameters);
		}
		catch (Exception e) {
			_log.error(e, e);

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AlloyMVCSamplePortletURLWrapper.class);

}