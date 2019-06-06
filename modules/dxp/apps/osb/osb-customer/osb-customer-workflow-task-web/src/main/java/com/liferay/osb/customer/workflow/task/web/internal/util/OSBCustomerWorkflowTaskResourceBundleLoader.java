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

package com.liferay.osb.customer.workflow.task.web.internal.util;

import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"bundle.symbolic.name=com.liferay.portal.workflow.task.web",
		"resource.bundle.base.name=content.Language",
		"servlet.context.name=portal-workflow-task-web"
	},
	service = ResourceBundleLoader.class
)
public class OSBCustomerWorkflowTaskResourceBundleLoader
	implements ResourceBundleLoader {

	@Override
	public ResourceBundle loadResourceBundle(Locale locale) {
		return _resourceBundleLoader.loadResourceBundle(locale);
	}

	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return loadResourceBundle(LocaleUtil.fromLanguageId(languageId));
	}

	private final ResourceBundleLoader _resourceBundleLoader =
		new CacheResourceBundleLoader(
			new ClassResourceBundleLoader(
				"content.Language",
				OSBCustomerWorkflowTaskResourceBundleLoader.class.
					getClassLoader()));

}