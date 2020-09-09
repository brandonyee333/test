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

package com.liferay.osb.asah.backend.model;

import com.liferay.osb.asah.backend.test.util.BaseBeanTestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Supplier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class JournalMetricTest extends BaseBeanTestCase<JournalMetric> {

	public JournalMetricTest() {
		super(
			new HashMap<Class<?>, Supplier<?>>() {
				{
					put(Metric.class, () -> new Metric(null));
				}
			},
			Arrays.asList(
				"getAssetType", "getAvailableMetrics", "getDefaultMetric"));
	}

	@Override
	@Test
	public void testEqualsAndHashCode() {
		EqualsVerifier<? extends Object> equalsVerifier =
			EqualsVerifier.forClass(JournalMetric.class);

		equalsVerifier.suppress(
			Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE);

		Metric metric = new Metric(null);

		metric.setMetrics(Collections.emptyList());

		equalsVerifier.withPrefabValues(Metric.class, metric, new Metric(null));

		JournalMetric journalMetric = new JournalMetric();

		journalMetric.setAssetMetrics(Collections.emptyList());

		equalsVerifier.withPrefabValues(
			AssetMetric.class, journalMetric, new JournalMetric());

		equalsVerifier.withRedefinedSuperclass();

		equalsVerifier.verify();
	}

	@Test
	public void testGetAssetType() {
		JournalMetric journalMetric = newInstance();

		Assert.assertEquals(
			AssetType.JOURNAL.getValue(), journalMetric.getAssetType());
	}

	@Test
	public void testGetDefaultMetric() {
		JournalMetric journalMetric = newInstance();

		Assert.assertEquals(
			new Metric(JournalMetricType.VIEWS),
			journalMetric.getDefaultMetric());
	}

	@Override
	protected JournalMetric newInstance() {
		return new JournalMetric();
	}

}