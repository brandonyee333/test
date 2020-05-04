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

import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.test.util.BaseBeanTestCase;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 * @author André Miranda
 */
public class AssetResolverBuilderTest
	extends BaseBeanTestCase<AssetResolver.Builder<?>> {

	@Override
	@Test
	public void testEqualsAndHashCode() {
		EqualsVerifier<?> equalsVerifier = EqualsVerifier.forClass(
			AssetResolver.Builder.class);

		equalsVerifier.suppress(
			Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.NONFINAL_FIELDS,
			Warning.STRICT_INHERITANCE);

		equalsVerifier.verify();
	}

	@Test
	public void testGetAssetFieldNames() {
		String assetIdFieldName = RandomTestUtil.randomId();
		String assetFieldName1 = RandomTestUtil.randomString();
		String assetFieldName2 = RandomTestUtil.randomString();

		AssetResolver.Builder<?> builder = AssetResolver.builder(
			assetIdFieldName, assetFieldName1, assetFieldName2);

		AssetResolver<?> assetResolver = builder.build();

		Set<String> assetFieldNames = new HashSet<String>() {
			{
				add(assetFieldName1);
				add(assetFieldName2);
			}
		};

		for (String assetFieldName : assetResolver.getAssetFieldNames()) {
			Assert.assertTrue(assetFieldNames.contains(assetFieldName));
		}
	}

	@Test
	public void testGetAssetIdFieldName() {
		String assetIdFieldName = RandomTestUtil.randomId();

		AssetResolver.Builder<?> builder = AssetResolver.builder(
			assetIdFieldName);

		AssetResolver<?> assetResolver = builder.build();

		Assert.assertEquals(
			assetIdFieldName, assetResolver.getAssetIdFieldName());
	}

	@Test
	public void testGetFieldSetterBiConsumer() {
		BiConsumer<AssetMetric, Object> fieldSetterBiConsumer = (a, b) -> {
		};

		AssetResolver.Builder<AssetMetric> builder = newInstance();

		String fieldName = RandomTestUtil.randomString();

		builder.fieldSetterBiConsumer(fieldName, fieldSetterBiConsumer);

		AssetResolver<?> assetResolver = builder.build();

		Assert.assertEquals(
			fieldSetterBiConsumer,
			assetResolver.getFieldSetterBiConsumer(fieldName));
	}

	@Test
	public void testGetSearchableFieldNames() {
		String searchableFieldName1 = RandomTestUtil.randomString();
		String searchableFieldName2 = RandomTestUtil.randomString();

		Set<String> searchableFieldNames = new HashSet<String>() {
			{
				add(searchableFieldName1);
				add(searchableFieldName2);
			}
		};

		AssetResolver.Builder<?> builder = newInstance();

		builder.searchableFieldNames(
			searchableFieldName1, searchableFieldName2);

		AssetResolver<?> assetResolver = builder.build();

		Assert.assertEquals(
			searchableFieldNames, assetResolver.getSearchableFieldNames());
	}

	@Test
	public void testGetSetterBiConsumer() {
		BiConsumer<AssetMetric, String> setterBiConsumer = (a, b) -> {
		};

		AssetResolver.Builder<AssetMetric> builder = newInstance();

		builder.setterBiConsumer(setterBiConsumer);

		AssetResolver<?> assetResolver = builder.build();

		Assert.assertEquals(
			setterBiConsumer, assetResolver.getSetterBiConsumer());
	}

	@Test
	public void testGetSupplier() {
		Supplier<AssetMetric> supplier = () -> null;

		AssetResolver.Builder<AssetMetric> builder = newInstance();

		builder.supplier(supplier);

		AssetResolver<?> assetResolver = builder.build();

		Assert.assertEquals(supplier, assetResolver.getSupplier());
	}

	@Override
	protected AssetResolver.Builder<AssetMetric> newInstance() {
		return AssetResolver.builder(RandomTestUtil.randomId());
	}

}