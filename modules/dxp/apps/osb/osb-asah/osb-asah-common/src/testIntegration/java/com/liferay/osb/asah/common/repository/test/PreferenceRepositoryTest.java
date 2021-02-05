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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.model.Preference;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Inácio Nery
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class PreferenceRepositoryTest {

	@Before
	public void setUp() {
		_preference = _preferenceRepository.save(
			new Preference("key", "value"));
	}

	@After
	public void tearDown() {
		_preferenceRepository.deleteAll();
	}

	@Test
	public void testCount() {
		Assert.assertEquals(1, _preferenceRepository.count());
	}

	@Test
	public void testDelete() {
		_preferenceRepository.delete(_preference);

		Assert.assertEquals(0, _preferenceRepository.count());
	}

	@Test
	public void testDeleteAll1() {
		_preferenceRepository.deleteAll();

		Assert.assertEquals(0, _preferenceRepository.count());
	}

	@Test
	public void testDeleteAll2() {
		Iterable<Preference> preferences = new ArrayList<>(
			Arrays.asList(_preference));

		_preferenceRepository.deleteAll(preferences);

		Assert.assertEquals(0, _preferenceRepository.count());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(
			Arrays.asList(_preference), _preferenceRepository.findAll());
	}

	@Test
	public void testFindByKey() {
		_assertPreference(_preferenceRepository.findByKey("key"));
	}

	@Test
	public void testSave() {
		_assertPreference(_preference);
	}

	@Test
	public void testSaveAll() {
		List<Preference> preferences = Arrays.asList(_preference);

		Assert.assertEquals(
			preferences, _preferenceRepository.saveAll(preferences));
	}

	private void _assertPreference(Preference preference) {
		Assert.assertNotNull(preference);
		Assert.assertEquals("key", preference.getKey());
		Assert.assertEquals("value", preference.getValue());
	}

	private Preference _preference;

	@Autowired
	private PreferenceRepository _preferenceRepository;

}