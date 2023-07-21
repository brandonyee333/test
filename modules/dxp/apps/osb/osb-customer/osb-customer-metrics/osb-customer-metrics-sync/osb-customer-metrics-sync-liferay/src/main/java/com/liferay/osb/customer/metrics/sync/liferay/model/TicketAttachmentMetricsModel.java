/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.osb.customer.ticket.model.TicketAttachment;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MetricsModel.class)
public class TicketAttachmentMetricsModel
	extends BaseModelMetricsModel<TicketAttachment> {

	@Override
	public Map<String, Object> getAttributes(
		TicketAttachment ticketAttachment) {

		return _metricsTransformationUtil.transformSharedAttributes(
			ticketAttachment.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return TicketAttachment.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}