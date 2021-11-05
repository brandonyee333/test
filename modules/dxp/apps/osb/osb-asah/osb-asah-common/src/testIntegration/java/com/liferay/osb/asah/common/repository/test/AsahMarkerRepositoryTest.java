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

import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Marcellus Tavares
 */
@Import(JDBCTestConfiguration.class)
public class AsahMarkerRepositoryTest
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_asahMarker1 = _asahMarkerRepository.save(
			new AsahMarker("SessionNanite"));
		_asahMarker2 = _asahMarkerRepository.save(
			new AsahMarker("Upgrade", JSONUtil.put("dataSourceId", "1")));
	}

	@Test
	public void testFindById() {
		String asahMarkerId = _asahMarker1.getId();

		Assertions.assertNotNull(asahMarkerId);

		Assertions.assertEquals(
			Optional.of(_asahMarker1),
			_asahMarkerRepository.findById(asahMarkerId));
	}

	private AsahMarker _asahMarker1;
	private AsahMarker _asahMarker2;

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

}