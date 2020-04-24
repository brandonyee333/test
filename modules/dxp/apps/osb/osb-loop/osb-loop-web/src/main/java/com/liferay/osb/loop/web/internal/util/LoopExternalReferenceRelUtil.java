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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.model.LoopExternalReferenceRel;
import com.liferay.osb.loop.service.LoopExternalReferenceRelLocalServiceUtil;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopExternalReferenceRelUtil {

	public static void addLoopExternalReferenceRel(
			AlloyController alloyController, long classNameId, long classPK,
			String externalReferenceName, String externalReferencePK)
		throws Exception {

		LoopExternalReferenceRel loopExternalReferenceRel =
			LoopExternalReferenceRelLocalServiceUtil.
				createLoopExternalReferenceRel(0);

		loopExternalReferenceRel.setClassNameId(classNameId);
		loopExternalReferenceRel.setClassPK(classPK);
		loopExternalReferenceRel.setExternalReferenceName(
			externalReferenceName);
		loopExternalReferenceRel.setExternalReferencePK(externalReferencePK);

		alloyController.updateModelIgnoreRequest(loopExternalReferenceRel);
	}

	public static void deleteLoopExternalReferenceRels(
			long classNameId, long classPK)
		throws Exception {

		List<LoopExternalReferenceRel> loopExternalReferenceRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"classNameId", classNameId, "classPK", classPK});

		for (LoopExternalReferenceRel loopExternalReferenceRel :
				loopExternalReferenceRels) {

			LoopExternalReferenceRelLocalServiceUtil.
				deleteLoopExternalReferenceRel(loopExternalReferenceRel);
		}
	}

	public static LoopExternalReferenceRel fetchLoopExternalReferenceRel(
			String externalReferenceName, String externalReferencePK)
		throws Exception {

		List<LoopExternalReferenceRel> loopExternalReferenceRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"externalReferenceName", externalReferenceName,
					"externalReferencePK", externalReferencePK
				});

		if (loopExternalReferenceRels.isEmpty()) {
			return null;
		}

		return loopExternalReferenceRels.get(0);
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopExternalReferenceRel.class.getName());

}