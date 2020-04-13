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

package com.liferay.osb.community.lang;

import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jamie Sammons
 */
@Component(immediate = true, service = ResourceBundleLoader.class)
public class ResourceBundleLoaderComponent implements ResourceBundleLoader {

	@Override
	public ResourceBundle loadResourceBundle(Locale locale) {
		return _aggregateResourceBundleLoader.loadResourceBundle(locale);
	}

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.osb.community.lang)",
		unbind = "-"
	)
	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_aggregateResourceBundleLoader = new AggregateResourceBundleLoader(
			resourceBundleLoader,
			ResourceBundleLoaderUtil.getPortalResourceBundleLoader());
	}

	private AggregateResourceBundleLoader _aggregateResourceBundleLoader;

}