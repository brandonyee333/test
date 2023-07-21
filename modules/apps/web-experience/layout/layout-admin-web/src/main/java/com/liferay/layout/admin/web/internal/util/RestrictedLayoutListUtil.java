/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.util;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.util.LayoutDescription;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

/**
 * @author Daniel Couso
 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
 *             com.liferay.portal.util.LayoutListUtil}
 */
@Deprecated
public class RestrictedLayoutListUtil {

	public static List<LayoutDescription> getLayoutDescriptions(
		long groupId, boolean privateLayout, String rootNodeName,
		Locale locale) {

		List<LayoutDescription> layoutDescriptions = new ArrayList<>();

		List<Layout> layouts = new ArrayList<>(
			LayoutLocalServiceUtil.getLayouts(groupId, privateLayout));

		Deque<ObjectValuePair<Layout, Integer>> deque = new LinkedList<>();

		Layout rootLayout = new LayoutImpl();

		rootLayout.setPlid(LayoutConstants.DEFAULT_PLID);
		rootLayout.setName(rootNodeName);

		deque.push(new ObjectValuePair<Layout, Integer>(rootLayout, 0));

		ObjectValuePair<Layout, Integer> objectValuePair = null;

		while ((objectValuePair = deque.pollFirst()) != null) {
			Layout currentLayout = objectValuePair.getKey();

			long currentLayoutPlid = 0;

			if (currentLayout.isTypePortlet()) {
				currentLayoutPlid = currentLayout.getPlid();
			}

			Integer currentDepth = objectValuePair.getValue();

			layoutDescriptions.add(
				new LayoutDescription(
					currentLayoutPlid, currentLayout.getName(locale),
					currentDepth));

			ListIterator<Layout> listIterator = layouts.listIterator(
				layouts.size());

			while (listIterator.hasPrevious()) {
				Layout previousLayout = listIterator.previous();

				if (previousLayout.getParentLayoutId() ==
						currentLayout.getLayoutId()) {

					listIterator.remove();

					deque.push(
						new ObjectValuePair<Layout, Integer>(
							previousLayout, currentDepth + 1));
				}
			}
		}

		if (!layoutDescriptions.isEmpty()) {
			layoutDescriptions.remove(0);
		}

		return layoutDescriptions;
	}

}