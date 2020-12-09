/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.template.freemarker.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionCustomizer;
import com.liferay.portal.kernel.dao.orm.SessionWrapper;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.template.TemplateResourceThreadLocal;
import com.liferay.portal.template.freemarker.configuration.FreeMarkerEngineConfiguration;

import java.io.Serializable;

import java.util.Dictionary;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Tina Tian
 */
@Component(
	configurationPid = "com.liferay.portal.template.freemarker.configuration.FreeMarkerEngineConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = SessionCustomizer.class
)
public class FreemarkerSessionCustomizer implements SessionCustomizer {

	@Override
	public Session customize(Session session) {
		if (_freeMarkerEngineConfiguration.modifyDatabaseEnabled()) {
			return session;
		}

		return new FreemarkerSessionWrapper(session);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_freeMarkerEngineConfiguration = ConfigurableUtil.createConfigurable(
			FreeMarkerEngineConfiguration.class, properties);
	}

	private volatile FreeMarkerEngineConfiguration
		_freeMarkerEngineConfiguration;

	private class FreemarkerSessionWrapper extends SessionWrapper {

		public void clear() throws ORMException {
			_check();

			super.clear();
		}

		public void delete(Object object) throws ORMException {
			_check();

			super.delete(object);
		}

		public void flush() throws ORMException {
			_check();

			super.flush();
		}

		public Object merge(Object object) throws ORMException {
			_check();

			return super.merge(object);
		}

		public Serializable save(Object object) throws ORMException {
			_check();

			return super.save(object);
		}

		public void saveOrUpdate(Object object) throws ORMException {
			_check();

			super.saveOrUpdate(object);
		}

		private FreemarkerSessionWrapper(Session session) {
			super(session);
		}

		private void _check() {
			TemplateResource templateResource =
				TemplateResourceThreadLocal.getTemplateResource(
					TemplateConstants.LANG_TYPE_FTL);

			if (templateResource != null) {
				throw new IllegalStateException(
					"Modifying database is not allowed when rendering " +
						"freemarker template " +
							templateResource.getTemplateId());
			}
		}

	}

}