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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.BlogMetricType;
import com.liferay.osb.asah.backend.model.DocumentLibraryMetricType;
import com.liferay.osb.asah.backend.model.FormMetricType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.PageMetricType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class MetricTypeDog {

	public MetricType getMetricType(AssetType assetType, String name) {
		return Optional.ofNullable(
			_metricTypeSuppliers.get(assetType)
		).map(
			metricTypeResolver -> metricTypeResolver.apply(name)
		).orElseThrow(
			IllegalArgumentException::new
		);
	}

	private final Map<AssetType, Function<String, MetricType>>
		_metricTypeSuppliers =
			new HashMap<AssetType, Function<String, MetricType>>() {
				{
					put(AssetType.BLOG, BlogMetricType::of);
					put(AssetType.CUSTOM, CustomAssetMetricType::of);
					put(AssetType.DOCUMENT, DocumentLibraryMetricType::of);
					put(AssetType.FORM, FormMetricType::of);
					put(AssetType.JOURNAL, JournalMetricType::of);
					put(AssetType.PAGE, PageMetricType::of);
					put(AssetType.SITE, SiteMetricType::of);
				}
			};

}