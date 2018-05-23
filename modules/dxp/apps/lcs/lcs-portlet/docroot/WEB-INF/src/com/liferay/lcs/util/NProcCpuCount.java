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

package com.liferay.lcs.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Mladen Cikara
 */
public class NProcCpuCount {

	public int getTotalCores() throws Exception {
		Runtime runtime = Runtime.getRuntime();

		Process process = runtime.exec("nproc");

		return GetterUtil.getInteger(StringUtil.read(process.getInputStream()));
	}

}