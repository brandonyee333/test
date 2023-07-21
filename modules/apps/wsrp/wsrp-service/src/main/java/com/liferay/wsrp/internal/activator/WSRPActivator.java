/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.activator;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.wsrp.constants.WSRPPortletKeys;
import com.liferay.wsrp.internal.jmx.WSRPConsumerPortletManager;
import com.liferay.wsrp.internal.util.ExtensionHelperUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalService;

import java.util.Dictionary;
import java.util.concurrent.FutureTask;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import javax.servlet.Servlet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Shuyang Zhou
 * @author Peter Fellwock
 */
@Component(immediate = true)
public class WSRPActivator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_serviceTracker = ServiceTrackerFactory.open(
			bundleContext, MBeanServer.class,
			new MBeanServerServiceTrackerCustomizer());

		_serviceTracker.open();

		ExtensionHelperUtil.initialize();

		try {
			_wsrpConsumerPortletLocalService.destroyWSRPConsumerPortlets();
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to destroy WSRP consumer portlets", pe);
			}
		}

		_initConsumerPortletsFutureTask = new FutureTask<>(
			() -> {
				_wsrpConsumerPortletLocalService.initWSRPConsumerPortlets();

				return null;
			});

		Thread thread = new Thread(
			_initConsumerPortletsFutureTask,
			"WSRP Init Consumer Portlets Thread");

		thread.setDaemon(true);

		thread.start();

		createPortletServlet();
	}

	protected void createPortletServlet() {
		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT,
			"(osgi.http.whiteboard.context.name=wsrp-service)");
		properties.put(
			HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_NAME,
			PortletServlet.class.getName());
		properties.put(
			HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN,
			StringPool.FORWARD_SLASH + WSRPPortletKeys.WSRP_CONSUMER + "/*");

		_servletServiceRegistration = _bundleContext.registerService(
			Servlet.class,
			new PortletServlet() {
			},
			properties);
	}

	@Deactivate
	protected void deactivate() {
		_servletServiceRegistration.unregister();

		try {
			_wsrpConsumerPortletLocalService.destroyWSRPConsumerPortlets();
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to destroy WSRP consumer portlets", pe);
			}
		}

		_serviceTracker.close();

		_initConsumerPortletsFutureTask.cancel(true);
	}

	@Reference(
		target = "(javax.portlet.name=" + WSRPPortletKeys.WSRP_CONSUMER + ")",
		unbind = "-"
	)
	protected void setPortlet(Portlet portlet) {
	}

	private static final Log _log = LogFactoryUtil.getLog(WSRPActivator.class);

	private BundleContext _bundleContext;
	private FutureTask<Void> _initConsumerPortletsFutureTask;
	private ServiceTracker<MBeanServer, MBeanServer> _serviceTracker;
	private ServiceRegistration<Servlet> _servletServiceRegistration;

	@Reference
	private WSRPConsumerPortletLocalService _wsrpConsumerPortletLocalService;

	private class MBeanServerServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<MBeanServer, MBeanServer> {

		@Override
		public MBeanServer addingService(
			ServiceReference<MBeanServer> serviceReference) {

			MBeanServer mBeanServer = _bundleContext.getService(
				serviceReference);

			try {
				mBeanServer.registerMBean(
					new WSRPConsumerPortletManager(),
					new ObjectName(
						"com.liferay.wsrp:classification=wsrp,name=" +
							"WSRPConsumerPortletManager"));
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to register WSRP consumer portlet manager", e);
				}
			}

			return mBeanServer;
		}

		@Override
		public void modifiedService(
			ServiceReference<MBeanServer> serviceReference,
			MBeanServer mBeanServer) {
		}

		@Override
		public void removedService(
			ServiceReference<MBeanServer> serviceReference,
			MBeanServer mBeanServer) {
		}

	}

}