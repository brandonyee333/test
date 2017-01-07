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

package com.liferay.portal.service.impl;

import com.liferay.exportimport.kernel.staging.LayoutStagingUtil;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutStagingHandler;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portlet.exportimport.staging.ProxiedLayoutsThreadLocal;
import com.liferay.portlet.exportimport.staging.StagingAdvicesThreadLocal;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class LayoutLocalServiceStagingAdvice implements MethodInterceptor {

	public LayoutLocalServiceStagingAdvice() {
		if (_log.isDebugEnabled()) {
			_log.debug("Instantiating " + hashCode());
		}
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (!StagingAdvicesThreadLocal.isEnabled()) {
			return methodInvocation.proceed();
		}

		Method method = methodInvocation.getMethod();

		String methodName = method.getName();

		if (methodName.equals("createLayout")) {
			return methodInvocation.proceed();
		}

		boolean showIncomplete = false;

		if (methodName.equals("getLayouts")) {
			Object[] arguments = methodInvocation.getArguments();

			if (arguments.length == 6) {
				showIncomplete = (Boolean)arguments[3];
			}
			else if (Arrays.equals(
						method.getParameterTypes(), _GET_LAYOUTS_TYPES)) {

				showIncomplete = true;
			}
		}

		Object returnValue = methodInvocation.proceed();

		if (returnValue instanceof Layout) {
			returnValue = wrapLayout((Layout)returnValue);
		}
		else if (returnValue instanceof List<?>) {
			List<?> list = (List<?>)returnValue;

			if (!list.isEmpty()) {
				Object object = list.get(0);

				if (object instanceof Layout) {
					returnValue = wrapLayouts(
						(List<Layout>)returnValue, showIncomplete);
				}
			}
		}

		return returnValue;
	}

	protected Layout wrapLayout(Layout layout) {
		LayoutStagingHandler layoutStagingHandler =
			LayoutStagingUtil.getLayoutStagingHandler(layout);

		if (layoutStagingHandler != null) {
			return layout;
		}

		if (!LayoutStagingUtil.isBranchingLayout(layout)) {
			return layout;
		}

		Map<Layout, Object> proxiedLayouts =
			ProxiedLayoutsThreadLocal.getProxiedLayouts();

		Object proxiedLayout = proxiedLayouts.get(layout);

		if (proxiedLayout != null) {
			return (Layout)proxiedLayout;
		}

		proxiedLayout = ProxyUtil.newProxyInstance(
			ClassLoaderUtil.getPortalClassLoader(),
			new Class<?>[] {Layout.class}, new LayoutStagingHandler(layout));

		proxiedLayouts.put(layout, proxiedLayout);

		return (Layout)proxiedLayout;
	}

	protected List<Layout> wrapLayouts(
		List<Layout> layouts, boolean showIncomplete) {

		if (layouts.isEmpty()) {
			return layouts;
		}

		Layout firstLayout = layouts.get(0);

		Layout wrappedFirstLayout = wrapLayout(firstLayout);

		if (wrappedFirstLayout == firstLayout) {
			return layouts;
		}

		long layoutSetBranchId = 0;

		if (!showIncomplete) {
			long userId = 0;

			try {
				userId = GetterUtil.getLong(PrincipalThreadLocal.getName());

				if (userId > 0) {
					User user = UserLocalServiceUtil.getUser(userId);

					LayoutSet layoutSet = firstLayout.getLayoutSet();

					layoutSetBranchId = StagingUtil.getRecentLayoutSetBranchId(
						user, layoutSet.getLayoutSetId());
				}
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("No layout set branch found for user " + userId);
				}
			}
		}

		List<Layout> wrappedLayouts = new ArrayList<>(layouts.size());

		for (int i = 0; i < layouts.size(); i++) {
			Layout wrappedLayout = wrapLayout(layouts.get(i));

			if (showIncomplete ||
				!StagingUtil.isIncomplete(wrappedLayout, layoutSetBranchId)) {

				wrappedLayouts.add(wrappedLayout);
			}
		}

		return wrappedLayouts;
	}

	private static final Class<?>[] _GET_LAYOUTS_TYPES = {
		Long.TYPE, Boolean.TYPE, Long.TYPE
	};

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutLocalServiceStagingAdvice.class);

}