/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.impl;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.wiki.engine.WikiEngine;

import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Iván Zaera
 */
@Component(immediate = true, service = WikiEngineTracker.class)
public class WikiEngineTracker {

	public Collection<String> getFormats() {
		return _serviceTrackerMap.keySet();
	}

	public WikiEngine getWikiEngine(String format) {
		List<WikiEngine> wikiEngines = _serviceTrackerMap.getService(format);

		if (wikiEngines == null) {
			return null;
		}

		return wikiEngines.get(0);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, WikiEngine.class, null,
			new ServiceReferenceMapper<String, WikiEngine>() {

				@Override
				public void map(
					ServiceReference<WikiEngine> serviceReference,
					Emitter<String> emitter) {

					WikiEngine wikiEngine = _bundleContext.getService(
						serviceReference);

					try {
						emitter.emit(wikiEngine.getFormat());
					}
					finally {
						_bundleContext.ungetService(serviceReference);
					}
				}

			});
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private BundleContext _bundleContext;
	private ServiceTrackerMap<String, List<WikiEngine>> _serviceTrackerMap;

}