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

package com.liferay.osb.asah.stream.curator.bot.nanite.base.test;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.BaseModel;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class DefaultBaseNanite extends BaseNanite<DefaultAssetModel> {

	@Override
	public String getCollectionName() {
		return "default-base";
	}

	@Override
	protected BinaryOperator<DefaultAssetModel> getBinaryOperator() {
		return (oldValue, newValue) -> {
			mergeModels(oldValue, newValue);

			return oldValue;
		};
	}

	@Override
	protected Predicate<DefaultAssetModel> getFilterPredicate() {
		return defaultAssetModel -> StringUtils.isNotBlank(
			defaultAssetModel.getId());
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(BaseNanite.class);
	}

	@Override
	protected Supplier<DefaultAssetModel> getModelSupplier() {
		return DefaultAssetModel::new;
	}

	@Override
	protected Function<DefaultAssetModel, String>
		getPrimaryKeyGeneratorFunction() {

		return BaseModel::getId;
	}

	@Override
	protected QueryBuilder getQueryBuilder() {
		return BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("foo"));
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, DefaultAssetModel defaultAssetModel) {

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		defaultAssetModel.setId(eventProperties.get("baseId"));
	}

}