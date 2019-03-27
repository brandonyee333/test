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

package com.liferay.osb.admin.servlet;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.osb.admin.asset.AccountEntryAssetRendererFactory;
import com.liferay.osb.admin.asset.OrderEntryAssetRendererFactory;
import com.liferay.osb.service.permission.OSBCommonPermission;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.permission.CommonPermission;
import com.liferay.portal.kernel.service.permission.CommonPermissionUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AdminServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_servletContext = servletContextEvent.getServletContext();

		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() {

		// Asset renderers

		if (_accountEntryAssetRendererFactory != null) {
			AssetRendererFactoryRegistryUtil.unregister(
				_accountEntryAssetRendererFactory);
		}

		if (_orderEntryAssetRendererFactory != null) {
			AssetRendererFactoryRegistryUtil.unregister(
				_orderEntryAssetRendererFactory);
		}

		// Common permission

		CommonPermissionUtil commonPermissionUtil =
			(CommonPermissionUtil)PortalBeanLocatorUtil.locate(
				CommonPermissionUtil.class.getName());

		OSBCommonPermission osbCommonPermission =
			(OSBCommonPermission)CommonPermissionUtil.getCommonPermission();

		commonPermissionUtil.setCommonPermission(
			osbCommonPermission.getCommonPermission());

		// OSGi

		_moduleServiceLifecycleServiceRegistration.unregister();
	}

	@Override
	@SuppressWarnings("unused")
	protected void doPortalInit() throws Exception {

		// Asset renderers

		_accountEntryAssetRendererFactory =
			new AccountEntryAssetRendererFactory();

		AssetRendererFactoryRegistryUtil.register(
			_accountEntryAssetRendererFactory);

		_orderEntryAssetRendererFactory = new OrderEntryAssetRendererFactory();

		AssetRendererFactoryRegistryUtil.register(
			_orderEntryAssetRendererFactory);

		// Common permission

		CommonPermissionUtil commonPermissionUtil =
			(CommonPermissionUtil)PortalBeanLocatorUtil.locate(
				CommonPermissionUtil.class.getName());

		CommonPermission originalCommonPermission =
			CommonPermissionUtil.getCommonPermission();

		CommonPermission osbCommonPermission = new OSBCommonPermission(
			originalCommonPermission);

		commonPermissionUtil.setCommonPermission(osbCommonPermission);

		// Expando

		AdminServletContextListenerExpandoHelper.setup();

		// Upgrade

		AdminServletContextListenerUpgradeHelper.setup(
			_servletContext.getServletContextName());

		// OSGi

		Registry registry = RegistryUtil.getRegistry();

		Map<String, Object> properties = new HashMap<>();

		properties.put("module.service.lifecycle", "osb.portlet.initialized");

		_moduleServiceLifecycleServiceRegistration = registry.registerService(
			ModuleServiceLifecycle.class,
			new ModuleServiceLifecycle() {
			},
			properties);
	}

	private AccountEntryAssetRendererFactory _accountEntryAssetRendererFactory;
	private ServiceRegistration<ModuleServiceLifecycle>
		_moduleServiceLifecycleServiceRegistration;
	private OrderEntryAssetRendererFactory _orderEntryAssetRendererFactory;
	private ServletContext _servletContext;

}