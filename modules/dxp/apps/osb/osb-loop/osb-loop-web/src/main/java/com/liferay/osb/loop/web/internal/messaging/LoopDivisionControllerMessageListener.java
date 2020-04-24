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

package com.liferay.osb.loop.web.internal.messaging;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.osb.loop.web.internal.controller.LoopAlloyControllerImpl;
import com.liferay.petra.string.StringPool;

/**
 * @author Timothy Bell
 */
public class LoopDivisionControllerMessageListener
	extends BaseLoopControllerMessageListener {

	public static LoopDivisionControllerMessageListener getInstance(
		AlloyController alloyController) {

		_instance.setAlloyController(
			LoopAlloyControllerImpl.createMockLoopAlloyControllerImpl(
				(LoopAlloyControllerImpl)alloyController));

		return _instance;
	}

	@Override
	protected String getMethodName(String apiName) throws Exception {
		String methodName = StringPool.BLANK;

		if (apiName.equals("add")) {
			methodName = "syncAddLoopDivision";
		}
		else if (apiName.equals("delete")) {
			methodName = "syncDeleteLoopDivision";
		}
		else if (apiName.equals("update")) {
			methodName = "syncUpdateLoopDivision";
		}
		else {
			throw new Exception(
				"The Loop division sync api name does not exist for " +
					apiName);
		}

		return methodName;
	}

	private static final LoopDivisionControllerMessageListener _instance =
		new LoopDivisionControllerMessageListener();

}