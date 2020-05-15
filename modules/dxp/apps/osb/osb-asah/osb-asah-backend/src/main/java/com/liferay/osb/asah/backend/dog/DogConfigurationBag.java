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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Marcellus Tavares
 */
public class DogConfigurationBag {

	public DogConfigurationBag(List<DogConfiguration> dogConfigurations) {
		Stream<DogConfiguration> stream = dogConfigurations.stream();

		stream.forEach(this::_cacheDogConfiguration);
	}

	public DogConfiguration getDogConfiguration(AssetType assetType) {
		return Optional.ofNullable(
			_dogConfigurations.get(assetType)
		).orElseThrow(
			() -> new IllegalStateException(
				"There is no configuration for asset type " +
					assetType.getValue())
		);
	}

	private void _cacheDogConfiguration(DogConfiguration dogConfiguration) {
		_dogConfigurations.put(
			dogConfiguration.getAssetType(), dogConfiguration);
	}

	private final Map<AssetType, DogConfiguration> _dogConfigurations =
		new HashMap<>();

}