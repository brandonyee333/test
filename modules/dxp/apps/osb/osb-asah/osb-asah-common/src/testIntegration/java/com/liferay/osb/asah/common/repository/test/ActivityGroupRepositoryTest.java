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

import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahPostgreSQLSpring4ClassRunner;

import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Inácio Nery
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahPostgreSQLSpring4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.postgresql.enabled=true")
public class ActivityGroupRepositoryTest
	extends BaseActivityGroupRepositoryTestCase {
}