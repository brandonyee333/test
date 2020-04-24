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

package com.liferay.osb.testray.web.internal.hook.portlet;

import com.liferay.osb.testray.web.constants.TestrayPortletKeys;
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
 * @author Ethan Bustad
 */
public class TestrayPortletURLWrapper extends BaseTestrayPortletURLWrapper {

	public static final String PORTLET_NAMESPACE =
		StringPool.UNDERLINE + TestrayPortletKeys.TESTRAY +
			StringPool.UNDERLINE;

	public TestrayPortletURLWrapper(
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

	public TestrayPortletURLWrapper(
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

	public TestrayPortletURLWrapper(
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

	public TestrayPortletURLWrapper(LiferayPortletURL liferayPortletURL) {
		super(liferayPortletURL);
	}

	public TestrayPortletURLWrapper(
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

	public TestrayPortletURLWrapper(
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

	public TestrayPortletURLWrapper(
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
		TestrayPortletURLWrapper.class);

}