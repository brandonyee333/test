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

package com.liferay.osb.asah.backend.constants;

import com.liferay.osb.asah.backend.test.util.BaseBeanTestCase;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class DataConstantsTest extends BaseBeanTestCase<DataConstants> {

	@Override
	@Test
	public void testEqualsAndHashCode() {
		EqualsVerifier<? extends Object> equalsVerifier =
			EqualsVerifier.forClass(DataConstants.class);

		equalsVerifier.suppress(
			Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.NONFINAL_FIELDS,
			Warning.STRICT_INHERITANCE);

		equalsVerifier.verify();
	}

	@Override
	protected DataConstants newInstance() {
		return new DataConstants();
	}

}