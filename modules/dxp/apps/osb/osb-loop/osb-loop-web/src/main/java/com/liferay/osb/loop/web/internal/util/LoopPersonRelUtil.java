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
import com.liferay.osb.loop.model.LoopPersonRel;
import com.liferay.osb.loop.service.LoopPersonRelLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopPersonRelUtil {

	public static void addLoopPersonRel(
			AlloyController alloyController, long childLoopPersonId,
			long parentLoopPersonId, int type)
		throws Exception {

		if (childLoopPersonId <= 0) {
			return;
		}

		LoopPersonRel loopPersonRel = null;

		List<LoopPersonRel> loopPersonLoopPersonRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"childLoopPersonId", childLoopPersonId,
					"parentLoopPersonId", parentLoopPersonId
				});

		if (!loopPersonLoopPersonRels.isEmpty()) {
			loopPersonRel = loopPersonLoopPersonRels.get(0);
		}
		else {
			loopPersonRel = LoopPersonRelLocalServiceUtil.createLoopPersonRel(
				0);

			loopPersonRel.setChildLoopPersonId(childLoopPersonId);
			loopPersonRel.setParentLoopPersonId(parentLoopPersonId);
		}

		loopPersonRel.setType(type);

		alloyController.updateModelIgnoreRequest(loopPersonRel);
	}

	public static void deleteLoopPersonRel(
			long childLoopPersonId, long parentLoopPersonId)
		throws Exception {

		List<LoopPersonRel> loopPersonRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"childLoopPersonId", childLoopPersonId,
					"parentLoopPersonId", parentLoopPersonId
				});

		for (LoopPersonRel loopPersonRel : loopPersonRels) {
			LoopPersonRelLocalServiceUtil.deleteLoopPersonRel(loopPersonRel);
		}
	}

	public static LoopPersonRel getLoopPersonRel(
			long childLoopPersonId, long parentLoopPersonId)
		throws Exception {

		List<LoopPersonRel> loopPersonRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"childLoopPersonId", childLoopPersonId,
					"parentLoopPersonId", parentLoopPersonId
				});

		if (loopPersonRels.isEmpty()) {
			throw new Exception(
				"No loop person rel found for person " + childLoopPersonId);
		}

		return loopPersonRels.get(0);
	}

	public static boolean isLoopPersonManager(
			long childLoopPersonId, long parentLoopPersonId)
		throws Exception {

		long loopPersonRelsCount =
			_alloyServiceInvoker.executeDynamicQueryCount(
				new Object[] {
					"childLoopPersonId", childLoopPersonId,
					"parentLoopPersonId", parentLoopPersonId
				});

		if (loopPersonRelsCount > 0) {
			return true;
		}

		return false;
	}

	public static void setLoopPersonRel(
			AlloyController alloyController, List<Long> childLoopPersonIds,
			long parentLoopPersonId, int type)
		throws Exception {

		childLoopPersonIds = ListUtil.copy(childLoopPersonIds);

		List<LoopPersonRel> loopPersonRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"parentLoopPersonId", parentLoopPersonId});

		for (LoopPersonRel loopPersonRel : loopPersonRels) {
			if (childLoopPersonIds.contains(
					loopPersonRel.getChildLoopPersonId())) {

				childLoopPersonIds.remove(loopPersonRel.getChildLoopPersonId());

				continue;
			}

			LoopPersonRelLocalServiceUtil.deleteLoopPersonRel(loopPersonRel);
		}

		for (long childLoopPersonId : childLoopPersonIds) {
			addLoopPersonRel(
				alloyController, childLoopPersonId, parentLoopPersonId, type);
		}
	}

	public static void setLoopPersonRel(
			AlloyController alloyController, long childLoopPersonId,
			List<Long> parentLoopPersonIds, int type)
		throws Exception {

		parentLoopPersonIds = ListUtil.copy(parentLoopPersonIds);

		List<LoopPersonRel> loopPersonRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"childLoopPersonId", childLoopPersonId, "type", type
				});

		for (LoopPersonRel loopPersonRel : loopPersonRels) {
			if (parentLoopPersonIds.contains(
					loopPersonRel.getParentLoopPersonId())) {

				parentLoopPersonIds.remove(
					loopPersonRel.getParentLoopPersonId());

				continue;
			}

			LoopPersonRelLocalServiceUtil.deleteLoopPersonRel(loopPersonRel);
		}

		for (long parentLoopPersonId : parentLoopPersonIds) {
			addLoopPersonRel(
				alloyController, childLoopPersonId, parentLoopPersonId, type);
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopPersonRel.class.getName());

}