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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutStagingHandler;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.SystemEventLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.LayoutRevisionUtil;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntry;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntryThreadLocal;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.exportimport.staging.ProxiedLayoutsThreadLocal;
import com.liferay.portlet.exportimport.staging.StagingAdvicesThreadLocal;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.core.annotation.Order;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
@Order(1)
public class LayoutLocalServiceStagingAdvice implements MethodInterceptor {

	public LayoutLocalServiceStagingAdvice() {
		if (_log.isDebugEnabled()) {
			_log.debug("Instantiating " + hashCode());
		}
	}

	public void deleteLayout(
			LayoutLocalService layoutLocalService, Layout layout,
			boolean updateLayoutSet, ServiceContext serviceContext)
		throws PortalException {

		long layoutSetBranchId = ParamUtil.getLong(
			serviceContext, "layoutSetBranchId");

		if (layoutSetBranchId > 0) {
			LayoutRevisionLocalServiceUtil.deleteLayoutRevisions(
				layoutSetBranchId, layout.getPlid());

			List<LayoutRevision> notIncompleteLayoutRevisions =
				LayoutRevisionUtil.findByP_NotS(
					layout.getPlid(), WorkflowConstants.STATUS_INCOMPLETE);

			if (!notIncompleteLayoutRevisions.isEmpty()) {
				return;
			}

			LayoutRevisionLocalServiceUtil.deleteLayoutLayoutRevisions(
				layout.getPlid());
		}

		if (SystemEventHierarchyEntryThreadLocal.push(
				Layout.class, layout.getPlid()) == null) {

			layoutLocalService.deleteLayout(
				layout, updateLayoutSet, serviceContext);
		}
		else {
			try {
				layoutLocalService.deleteLayout(
					layout, updateLayoutSet, serviceContext);

				SystemEventHierarchyEntry systemEventHierarchyEntry =
					SystemEventHierarchyEntryThreadLocal.peek();

				SystemEventLocalServiceUtil.addSystemEvent(
					0, layout.getGroupId(), Layout.class.getName(),
					layout.getPlid(), layout.getUuid(), null,
					SystemEventConstants.TYPE_DELETE,
					systemEventHierarchyEntry.getExtraData());
			}
			finally {
				SystemEventHierarchyEntryThreadLocal.pop(
					Layout.class, layout.getPlid());
			}
		}
	}

	public void deleteLayout(
			LayoutLocalService layoutLocalService, long groupId,
			boolean privateLayout, long layoutId, ServiceContext serviceContext)
		throws PortalException {

		Layout layout = layoutLocalService.getLayout(
			groupId, privateLayout, layoutId);

		deleteLayout(layoutLocalService, layout, true, serviceContext);
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (!StagingAdvicesThreadLocal.isEnabled()) {
			return methodInvocation.proceed();
		}

		Method method = methodInvocation.getMethod();

		String methodName = method.getName();

		boolean showIncomplete = false;

		if (!_layoutLocalServiceStagingAdviceMethodNames.contains(methodName)) {
			return wrapReturnValue(methodInvocation.proceed(), showIncomplete);
		}

		if (methodName.equals("createLayout")) {
			return methodInvocation.proceed();
		}

		Object returnValue = null;

		Class<?>[] parameterTypes = method.getParameterTypes();

		Object thisObject = methodInvocation.getThis();
		Object[] arguments = methodInvocation.getArguments();

		if (methodName.equals("deleteLayout")) {
			if (arguments.length == 3) {
				deleteLayout(
					(LayoutLocalService)thisObject, (Layout)arguments[0],
					(Boolean)arguments[1], (ServiceContext)arguments[2]);
			}
			else if (arguments.length == 4) {
				deleteLayout(
					(LayoutLocalService)thisObject, (Long)arguments[0],
					(Boolean)arguments[1], (Long)arguments[2],
					(ServiceContext)arguments[3]);
			}
			else {
				returnValue = methodInvocation.proceed();
			}
		}
		else if (methodName.equals("getLayouts")) {
			if (arguments.length == 6) {
				showIncomplete = (Boolean)arguments[3];
			}
			else if (Arrays.equals(parameterTypes, _GET_LAYOUTS_TYPES)) {
				showIncomplete = true;
			}

			returnValue = methodInvocation.proceed();
		}
		else {
			returnValue = methodInvocation.proceed();
		}

		returnValue = wrapReturnValue(returnValue, showIncomplete);

		return returnValue;
	}

	protected Layout getProxiedLayout(Layout layout) {
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

	protected Layout unwrapLayout(Layout layout) {
		LayoutStagingHandler layoutStagingHandler =
			LayoutStagingUtil.getLayoutStagingHandler(layout);

		if (layoutStagingHandler == null) {
			return layout;
		}

		return layoutStagingHandler.getLayout();
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

		return getProxiedLayout(layout);
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

	protected Object wrapReturnValue(
		Object returnValue, boolean showIncomplete) {

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

	private LayoutRevision _getLayoutRevision(Layout layout) {
		LayoutStagingHandler layoutStagingHandler =
			LayoutStagingUtil.getLayoutStagingHandler(layout);

		if ((layoutStagingHandler == null) ||
			!LayoutStagingUtil.isBranchingLayout(layout)) {

			return null;
		}

		return layoutStagingHandler.getLayoutRevision();
	}

	private static final Class<?>[] _GET_LAYOUTS_TYPES = {
		Long.TYPE, Boolean.TYPE, Long.TYPE
	};

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutLocalServiceStagingAdvice.class);

	private static final Set<String>
		_layoutLocalServiceStagingAdviceMethodNames = new HashSet<>();

	static {
		_layoutLocalServiceStagingAdviceMethodNames.add("createLayout");
		_layoutLocalServiceStagingAdviceMethodNames.add("deleteLayout");
		_layoutLocalServiceStagingAdviceMethodNames.add("getLayouts");
	}

}