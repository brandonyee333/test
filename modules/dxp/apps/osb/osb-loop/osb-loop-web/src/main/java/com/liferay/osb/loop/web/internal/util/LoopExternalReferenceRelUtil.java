/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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