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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public class ElasticsearchDXPEntityRepositoryTest
	extends BaseDXPEntityRepositoryTestCase {

	@Override
	@Test
	public void testCountByTypeAndModifiedDateBetween() {
		Assertions.assertThrows(
			UnsupportedOperationException.class,
			super::testCountByTypeAndModifiedDateBetween);
	}

	@Override
	@Test
	public void testExistsById() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testExistsById);
	}

	@Override
	@Test
	public void testFindById() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testFindById);
	}

	@Override
	@Test
	public void testFindByTypeAndModifiedDateBetween() {
		Assertions.assertThrows(
			UnsupportedOperationException.class,
			super::testFindByTypeAndModifiedDateBetween);
	}

}