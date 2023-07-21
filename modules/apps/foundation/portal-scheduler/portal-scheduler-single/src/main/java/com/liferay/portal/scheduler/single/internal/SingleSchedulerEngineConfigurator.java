/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.single.internal;

import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(
	enabled = false, immediate = true,
	service = SingleSchedulerEngineConfigurator.class
)
public class SingleSchedulerEngineConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		Dictionary<String, Object> schedulerEngineDictionary =
			new HashMapDictionary<>();

		schedulerEngineDictionary.put("scheduler.engine.proxy", Boolean.TRUE);

		_schedulerEngineServiceRegistration = bundleContext.registerService(
			SchedulerEngine.class, _schedulerEngine, schedulerEngineDictionary);
	}

	@Deactivate
	protected void deactivate() {
		if (_schedulerEngineServiceRegistration != null) {
			_schedulerEngineServiceRegistration.unregister();
		}
	}

	@Reference(target = "(scheduler.engine.proxy.bean=true)", unbind = "-")
	protected void setSchedulerEngine(SchedulerEngine schedulerEngine) {
		_schedulerEngine = schedulerEngine;
	}

	private SchedulerEngine _schedulerEngine;
	private volatile ServiceRegistration<SchedulerEngine>
		_schedulerEngineServiceRegistration;

}