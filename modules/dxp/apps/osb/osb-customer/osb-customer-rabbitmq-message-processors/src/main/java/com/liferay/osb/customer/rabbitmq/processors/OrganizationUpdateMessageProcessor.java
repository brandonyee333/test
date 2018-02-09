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

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.osb.customer.rabbitmq.configuration.RabbitMQConfiguration;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.util.PortalInstances;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true)
public class OrganizationUpdateMessageProcessor implements MessageProcessor {

	@Override
	public String getQueue() {
		return _configuration.queue();
	}

	@Override
	public String[] getRoutingKeys() {
		return _routingKeys;
	}

	@Override
	public void process(
		String routingKey, String message, Map<String, Object> properties) {

		try {
			updateOrganization(message);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			RabbitMQConfiguration.class, properties);
	}

	protected void updateOrganization(String message) throws Exception {
		JSONObject jsonObject = _jsonFactory.createJSONObject(message.trim());

		String uuid = jsonObject.getString("uuid");

		Organization organization =
			_organizationLocalService.fetchOrganizationByUuidAndCompanyId(
				uuid, _companyId);

		if (organization == null) {
			return;
		}

		String name = jsonObject.getString("name");

		Group group = organization.getGroup();

		_organizationLocalService.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(), name,
			organization.getType(), organization.getRegionId(),
			organization.getCountryId(), organization.getStatusId(),
			organization.getComments(), true, null, group.isSite(), null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationUpdateMessageProcessor.class);

	private static final long _companyId =
		PortalInstances.getDefaultCompanyId();

	private volatile RabbitMQConfiguration _configuration;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	private final String[] _routingKeys = {"entity.organization.update"};

}