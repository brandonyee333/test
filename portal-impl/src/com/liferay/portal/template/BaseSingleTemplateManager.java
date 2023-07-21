/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template;

import com.liferay.portal.kernel.security.pacl.NotPrivileged;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateResource;

import java.security.AccessControlContext;
import java.security.AccessController;

import java.util.List;
import java.util.Map;

/**
 * @author Leonardo Barros
 */
public abstract class BaseSingleTemplateManager extends BaseTemplateManager {

	@NotPrivileged
	@Override
	public Template getTemplate(
		List<TemplateResource> templateResources, boolean restricted) {

		return getTemplate(templateResources, null, restricted);
	}

	@NotPrivileged
	@Override
	public Template getTemplate(
		List<TemplateResource> templateResources,
		TemplateResource errorTemplateResource, boolean restricted) {

		throw new UnsupportedOperationException(
			"Template type does not support multi templates");
	}

	@NotPrivileged
	@Override
	public Template getTemplate(
		TemplateResource templateResource, boolean restricted) {

		return getTemplate(templateResource, null, restricted);
	}

	@NotPrivileged
	@Override
	public Template getTemplate(
		TemplateResource templateResource,
		TemplateResource errorTemplateResource, boolean restricted) {

		AccessControlContext accessControlContext = getAccessControlContext();

		if (accessControlContext == null) {
			return doGetTemplate(
				templateResource, errorTemplateResource, restricted,
				getHelperUtilities(restricted), false);
		}

		Map<String, Object> helperUtilities = AccessController.doPrivileged(
			new DoGetHelperUtilitiesPrivilegedAction(
				templateContextHelper, getTemplateControlContextClassLoader(),
				restricted),
			accessControlContext);

		Template template = AccessController.doPrivileged(
			new DoGetSingleTemplatePrivilegedAction(
				templateResource, errorTemplateResource, restricted,
				helperUtilities));

		return new PrivilegedTemplateWrapper(accessControlContext, template);
	}

	protected abstract Template doGetTemplate(
		TemplateResource templateResource,
		TemplateResource errorTemplateResource, boolean restricted,
		Map<String, Object> helperUtilities, boolean privileged);

	protected class DoGetSingleTemplatePrivilegedAction
		extends DoGetAbstractTemplatePrivilegedAction {

		public DoGetSingleTemplatePrivilegedAction(
			TemplateResource templateResource,
			TemplateResource errorTemplateResource, boolean restricted,
			Map<String, Object> helperUtilities) {

			super(errorTemplateResource, restricted, helperUtilities);

			_templateResource = templateResource;
		}

		@Override
		public Template run() {
			return doGetTemplate(
				_templateResource, errorTemplateResource, restricted,
				helperUtilities, true);
		}

		private final TemplateResource _templateResource;

	}

}