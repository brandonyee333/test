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

import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class MetricTypeDog {

	@Autowired
	public MetricTypeDog(List<DogConfiguration> dogConfigurations) {
		Stream<DogConfiguration> stream = dogConfigurations.stream();

		stream.forEach(this::_cacheDogConfiguration);
	}

	public MetricType getMetricType(AssetType assetType, String name) {
		return Optional.ofNullable(
			_metricTypeResolverFunctions.get(assetType)
		).map(
			metricTypeResolver -> metricTypeResolver.apply(name)
		).orElseThrow(
			IllegalArgumentException::new
		);
	}

	private void _cacheDogConfiguration(DogConfiguration dogConfiguration) {
		_metricTypeResolverFunctions.put(
			dogConfiguration.getAssetType(),
			dogConfiguration.getMetricTypeResolverFunction());
	}

	private final Map<AssetType, Function<String, MetricType>>
		_metricTypeResolverFunctions = new HashMap<>();

}