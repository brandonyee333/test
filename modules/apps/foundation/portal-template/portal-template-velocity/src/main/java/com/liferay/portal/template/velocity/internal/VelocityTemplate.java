/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.velocity.internal;

import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.TemplateResourceCache;
import com.liferay.portal.template.AbstractSingleResourceTemplate;
import com.liferay.portal.template.TemplateContextHelper;
import com.liferay.portal.template.TemplateResourceThreadLocal;
import com.liferay.taglib.util.VelocityTaglib;
import com.liferay.taglib.util.VelocityTaglibImpl;

import java.io.Writer;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;

/**
 * @author Tina Tian
 */
public class VelocityTemplate extends AbstractSingleResourceTemplate {

	public VelocityTemplate(
		TemplateResource templateResource,
		TemplateResource errorTemplateResource, Map<String, Object> context,
		VelocityEngine velocityEngine,
		TemplateContextHelper templateContextHelper, boolean privileged,
		boolean restricted, TemplateResourceCache templateResourceCache) {

		super(
			templateResource, errorTemplateResource, context,
			templateContextHelper, restricted, templateResourceCache);

		_velocityEngine = velocityEngine;
		_privileged = privileged;
		_restricted = restricted;

		_velocityContext = new VelocityContext(super.context);
	}

	@Override
	public void prepareTaglib(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		VelocityTaglib velocityTaglib = new VelocityTaglibImpl(
			httpServletRequest.getServletContext(), httpServletRequest,
			httpServletResponse, context);

		context.put("taglibLiferay", velocityTaglib);

		// Legacy support

		context.put("theme", velocityTaglib);
	}

	@Override
	protected void handleException(Exception exception, Writer writer)
		throws TemplateException {

		put("exception", exception.getMessage());

		if (templateResource instanceof StringTemplateResource) {
			StringTemplateResource stringTemplateResource =
				(StringTemplateResource)templateResource;

			put("script", stringTemplateResource.getContent());
		}

		if (exception instanceof ParseErrorException) {
			ParseErrorException pee = (ParseErrorException)exception;

			put("column", pee.getColumnNumber());
			put("line", pee.getLineNumber());
		}

		try {
			processTemplate(errorTemplateResource, writer);
		}
		catch (Exception e) {
			throw new TemplateException(
				"Unable to process Velocity template " +
					errorTemplateResource.getTemplateId(),
				e);
		}
	}

	@Override
	protected void processTemplate(
			TemplateResource templateResource, Writer writer)
		throws Exception {

		TemplateResourceThreadLocal.setTemplateResource(
			TemplateConstants.LANG_TYPE_VM, templateResource);

		if (_restricted) {
			RestrictedTemplateThreadLocal.setRestricted(true);
		}

		try {
			Template template = null;

			if (_privileged) {
				template = AccessController.doPrivileged(
					new TemplatePrivilegedExceptionAction(templateResource));
			}
			else {
				template = _velocityEngine.getTemplate(
					getTemplateResourceUUID(templateResource),
					TemplateConstants.DEFAUT_ENCODING);
			}

			template.merge(_velocityContext, writer);
		}
		catch (PrivilegedActionException pae) {
			throw pae.getException();
		}
		finally {
			TemplateResourceThreadLocal.setTemplateResource(
				TemplateConstants.LANG_TYPE_VM, null);

			if (_restricted) {
				RestrictedTemplateThreadLocal.setRestricted(false);
			}
		}
	}

	private final boolean _privileged;
	private final boolean _restricted;
	private final VelocityContext _velocityContext;
	private final VelocityEngine _velocityEngine;

	private class TemplatePrivilegedExceptionAction
		implements PrivilegedExceptionAction<Template> {

		public TemplatePrivilegedExceptionAction(
			TemplateResource templateResource) {

			_templateResource = templateResource;
		}

		@Override
		public Template run() throws Exception {
			return _velocityEngine.getTemplate(
				getTemplateResourceUUID(_templateResource),
				TemplateConstants.DEFAUT_ENCODING);
		}

		private final TemplateResource _templateResource;

	}

}