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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.InterestDog;
import com.liferay.osb.asah.common.dog.UserSessionDog;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoIndividualsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	private static final Pattern _pattern = Pattern.compile(
		".*(score eq '(false|true)').*");

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private InterestDog _interestDog;

	@Autowired
	private UserSessionDog _userSessionDog;

}