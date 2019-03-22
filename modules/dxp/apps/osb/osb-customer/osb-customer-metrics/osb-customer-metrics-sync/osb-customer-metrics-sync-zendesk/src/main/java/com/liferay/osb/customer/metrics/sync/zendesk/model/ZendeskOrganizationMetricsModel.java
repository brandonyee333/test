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

package com.liferay.osb.customer.metrics.sync.zendesk.model;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MetricsModel.class)
public class ZendeskOrganizationMetricsModel
	extends ZendeskBaseMetricsModel<ZendeskOrganization> {

	@Override
	public Class getModelClass() {
		return ZendeskOrganization.class;
	}

}