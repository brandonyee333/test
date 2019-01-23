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

package com.liferay.osb.customer.metrics.models;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModel;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class ProductEntryMetricsModel extends BaseMetricsModel<ProductEntry> {

	@Override
	public Class getModelClass() {
		return ProductEntry.class;
	}

	@Override
	public Map<String, Object> transformAttributes(
		BaseModel<ProductEntry> model) {

		Map<String, Object> attributes = model.getModelAttributes();

		Integer environment = (Integer)attributes.get("environment");

		if (environment != null) {
			attributes.put(
				"environment",
				ProductEntryConstants.getEnvironmentLabel(environment));
		}

		return attributes;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}