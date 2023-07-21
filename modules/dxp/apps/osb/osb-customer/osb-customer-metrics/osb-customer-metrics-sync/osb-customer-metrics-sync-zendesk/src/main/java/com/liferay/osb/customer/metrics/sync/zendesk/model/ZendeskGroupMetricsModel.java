/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.zendesk.model;

import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.zendesk.model.ZendeskGroup;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class ZendeskGroupMetricsModel
	extends ZendeskBaseMetricsModel<ZendeskGroup> {

	@Override
	public Class getModelClass() {
		return ZendeskGroup.class;
	}

}