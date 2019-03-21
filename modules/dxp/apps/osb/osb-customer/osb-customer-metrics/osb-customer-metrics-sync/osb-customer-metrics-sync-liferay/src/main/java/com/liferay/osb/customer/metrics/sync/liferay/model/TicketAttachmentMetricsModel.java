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

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
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
	public Map<String, Object> getAttributes(TicketAttachment ticketAttachment) {
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