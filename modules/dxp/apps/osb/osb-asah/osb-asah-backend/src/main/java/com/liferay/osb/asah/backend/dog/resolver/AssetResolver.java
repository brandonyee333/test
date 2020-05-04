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

package com.liferay.osb.asah.backend.dog.resolver;

import com.liferay.osb.asah.backend.model.AssetId;
import com.liferay.osb.asah.backend.model.AssetMetric;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author Marcellus Tavares
 */
public class AssetResolver<T extends AssetMetric> {

	public static <T extends AssetMetric> Builder<T> builder(
		String assetIdFieldName, String... assetFieldNames) {

		Objects.requireNonNull(assetIdFieldName, "Asset ID field name is null");

		return new Builder<>(assetIdFieldName, assetFieldNames);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetResolver)) {
			return false;
		}

		AssetResolver<?> assetResolver = (AssetResolver<?>)obj;

		if (Objects.equals(_assetFieldNames, assetResolver._assetFieldNames) &&
			Objects.equals(
				_assetIdFieldName, assetResolver._assetIdFieldName) &&
			Objects.equals(
				_fieldSetterBiConsumer, assetResolver._fieldSetterBiConsumer) &&
			Objects.equals(
				_searchableFieldNames, assetResolver._searchableFieldNames) &&
			Objects.equals(
				_setterBiConsumer, assetResolver._setterBiConsumer) &&
			Objects.equals(_supplier, assetResolver._supplier)) {

			return true;
		}

		return false;
	}

	public String[] getAssetFieldNames() {
		String[] fieldNames = new String[_assetFieldNames.size()];

		return _assetFieldNames.toArray(fieldNames);
	}

	public AssetId getAssetId(String assetId) {
		return AssetId.of(_assetIdFieldName, assetId);
	}

	public String getAssetIdFieldName() {
		return _assetIdFieldName;
	}

	public BiConsumer<T, Object> getFieldSetterBiConsumer(String fieldName) {
		return _fieldSetterBiConsumer.get(fieldName);
	}

	public Set<String> getSearchableFieldNames() {
		return _searchableFieldNames;
	}

	public BiConsumer<T, String> getSetterBiConsumer() {
		return _setterBiConsumer;
	}

	public Supplier<T> getSupplier() {
		return _supplier;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_assetFieldNames, _assetIdFieldName, _fieldSetterBiConsumer,
			_searchableFieldNames, _setterBiConsumer, _supplier);
	}

	public static final class Builder<T extends AssetMetric> {

		public AssetResolver<T> build() {
			return _assetResolver;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Builder)) {
				return false;
			}

			Builder<?> builder = (Builder<?>)obj;

			if (Objects.equals(_assetResolver, builder._assetResolver)) {
				return true;
			}

			return false;
		}

		public Builder<T> fieldSetterBiConsumer(
			String fieldName, BiConsumer<T, Object> fieldSetterBiConsumer) {

			_assetResolver._fieldSetterBiConsumer.put(
				fieldName, fieldSetterBiConsumer);

			return this;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_assetResolver);
		}

		public Builder<T> searchableFieldNames(String... fieldNames) {
			Collections.addAll(
				_assetResolver._searchableFieldNames, fieldNames);

			return this;
		}

		public Builder<T> setterBiConsumer(
			BiConsumer<T, String> setterBiConsumer) {

			_assetResolver._setterBiConsumer = setterBiConsumer;

			return this;
		}

		public Builder<T> supplier(Supplier<T> supplier) {
			_assetResolver._supplier = supplier;

			return this;
		}

		private Builder(String assetIdFieldName, String... assetFieldNames) {
			_assetResolver = new AssetResolver<>(
				assetIdFieldName, assetFieldNames);

			_buildDefaultSetterBiConsumers();
		}

		private void _buildDefaultSetterBiConsumers() {
			fieldSetterBiConsumer(
				"nestedMetrics",
				(assetMetric, value) -> {
					if (value != null) {
						assetMetric.setAssetMetrics((List<AssetMetric>)value);
					}
				});

			fieldSetterBiConsumer(
				"title",
				(assetMetric, value) -> {
					if (value != null) {
						assetMetric.setAssetTitle(value.toString());
					}
				});

			fieldSetterBiConsumer(
				"urls",
				(assetMetric, value) -> {
					if (value != null) {
						assetMetric.setURLs((List<String>)value);
					}
				});
		}

		private final AssetResolver<T> _assetResolver;

	}

	private AssetResolver(String assetIdFieldName, String... assetFieldNames) {
		_assetIdFieldName = assetIdFieldName;

		if (assetFieldNames != null) {
			Collections.addAll(_assetFieldNames, assetFieldNames);
		}
	}

	private Set<String> _assetFieldNames = new HashSet<>();
	private final String _assetIdFieldName;
	private Map<String, BiConsumer<T, Object>> _fieldSetterBiConsumer =
		new HashMap<>();
	private Set<String> _searchableFieldNames = new HashSet<>();
	private BiConsumer<T, String> _setterBiConsumer = T::setAssetId;
	private Supplier<T> _supplier;

}