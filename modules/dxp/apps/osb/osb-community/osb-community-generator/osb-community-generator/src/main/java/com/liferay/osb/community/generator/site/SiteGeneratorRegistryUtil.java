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

import java.util.Collection;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component
public class SiteGeneratorRegistryUtil {

	public static SiteGenerator getSiteGenerator(String key) {
		return _siteGeneratorRegistry.getSiteGenerator(key);
	}

	public static Collection<SiteGenerator> getSiteGenerators() {
		return _siteGeneratorRegistry.getSiteGenerators();
	}

	@Reference(unbind = "-")
	protected void setSiteGeneratorRegistry(
		SiteGeneratorRegistry siteGeneratorRegistry) {

		_siteGeneratorRegistry = siteGeneratorRegistry;
	}

	private static SiteGeneratorRegistry _siteGeneratorRegistry;

}