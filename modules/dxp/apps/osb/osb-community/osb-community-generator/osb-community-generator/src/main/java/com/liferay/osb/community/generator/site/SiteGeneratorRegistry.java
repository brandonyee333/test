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

package com.liferay.osb.community.generator.site;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Ryan Park
 */
@Component(immediate = true, service = SiteGeneratorRegistry.class)
public class SiteGeneratorRegistry {

	public SiteGenerator getSiteGenerator(String key) {
		return _siteGeneratorServiceTrackerMap.getService(key);
	}

	public Collection<SiteGenerator> getSiteGenerators() {
		return _siteGeneratorServiceTrackerMap.values();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_siteGeneratorServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, SiteGenerator.class,
				"(osb.community.site.generator.key=*)",
				new SiteGeneratorServiceReferenceMapper());
	}

	private ServiceTrackerMap<String, SiteGenerator>
		_siteGeneratorServiceTrackerMap;

	private class SiteGeneratorServiceReferenceMapper
		implements ServiceReferenceMapper<String, SiteGenerator> {

		@Override
		public void map(
			ServiceReference<SiteGenerator> serviceReference,
			Emitter<String> emitter) {

			String siteGeneratorKey = (String)serviceReference.getProperty(
				"osb.community.site.generator.key");

			if (Validator.isNotNull(siteGeneratorKey)) {
				emitter.emit(siteGeneratorKey);
			}
		}

	}

}