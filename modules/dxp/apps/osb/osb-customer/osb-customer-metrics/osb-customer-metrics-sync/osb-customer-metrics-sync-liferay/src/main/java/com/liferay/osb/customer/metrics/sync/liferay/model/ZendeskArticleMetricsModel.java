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

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MetricsModel.class)
public class ZendeskArticleMetricsModel
	extends BaseModelMetricsModel<ZendeskArticle> {

	@Override
	public Map<String, Object> getAttributes(ZendeskArticle zendeskArticle) {
		return _metricsTransformationUtil.transformSharedAttributes(
			zendeskArticle.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return ZendeskArticle.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}