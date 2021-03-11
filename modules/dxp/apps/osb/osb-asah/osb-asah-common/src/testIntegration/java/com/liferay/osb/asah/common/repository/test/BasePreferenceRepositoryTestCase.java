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
		setUpRepository(new Preference("key", "value"));
	}

	@Test
	public void testFindByKey() {
		Assert.assertEquals(
			entityModels.get(0), _preferenceRepository.findByKey("key"));
	}

	@Override
	protected CrudRepository<Preference, Long> getCrudRepository() {
		return _preferenceRepository;
	}

	@Autowired
	private PreferenceRepository _preferenceRepository;

}