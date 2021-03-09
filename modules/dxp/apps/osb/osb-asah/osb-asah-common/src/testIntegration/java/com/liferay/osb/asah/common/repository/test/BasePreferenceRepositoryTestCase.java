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

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Inácio Nery
 */
public abstract class BasePreferenceRepositoryTestCase
	extends BaseRepositoryTestCase<Preference> {

	@Before
	public void setUp() {
		_preference = _preferenceRepository.save(
			new Preference("key", "value"));
	}

	@Test
	public void testFindByKey() {
		assertModel(_preferenceRepository.findByKey("key"));
	}

	@Override
	protected void assertModel(Preference preference) {
		Assert.assertNotNull(preference);
		Assert.assertEquals("key", preference.getKey());
		Assert.assertEquals("value", preference.getValue());
	}

	@Override
	protected CrudRepository<Preference, Long> getCrudRepository() {
		return _preferenceRepository;
	}

	@Override
	protected List<Preference> getModels() {
		return Collections.singletonList(_preference);
	}

	private Preference _preference;

	@Autowired
	private PreferenceRepository _preferenceRepository;

}