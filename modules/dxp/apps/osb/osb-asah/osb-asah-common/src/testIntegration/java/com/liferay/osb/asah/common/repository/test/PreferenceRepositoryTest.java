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

import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Inácio Nery
 */
@Import(JDBCTestConfiguration.class)
public class PreferenceRepositoryTest
	extends BaseRepositoryTestCase<Preference, String> {

	@BeforeEach
	public void setUp() {
		Preference preference1 = new Preference("id1", "value1");

		preference1.setIsNew(Boolean.TRUE);

		setUpRepository(preference1);

		preference1.setIsNew(Boolean.FALSE);
	}

	@Override
	protected PagingAndSortingRepository<Preference, String>
		getPagingAndSortingRepository() {

		return _preferenceRepository;
	}

	@Autowired
	private PreferenceRepository _preferenceRepository;

}