/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.corp.merge;

import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joan Kim
 */
public class CorpEntryMergeHelperFactoryUtil {

	public static List<CorpEntryMergeHelper> getCorpEntryMergeHelpers(
			CorpEntry corpEntry, CorpEntry mergeCorpEntry,
			ServiceContext serviceContext)
		throws CorpEntryMergeHelperException {

		String[] classNames =
			PortletPropsValues.CORP_ENTRY_MERGE_HELPER_CLASS_NAMES;

		List<CorpEntryMergeHelper> corpEntryMergeHelpers =
			new ArrayList<CorpEntryMergeHelper>(classNames.length);

		for (String className : classNames) {
			CorpEntryMergeHelper corpEntryMergeHelper =
				_getCorpEntryMergeHelper(className);

			corpEntryMergeHelper.setCorpEntry(corpEntry);
			corpEntryMergeHelper.setMergeCorpEntry(mergeCorpEntry);
			corpEntryMergeHelper.setServiceContext(serviceContext);

			corpEntryMergeHelpers.add(corpEntryMergeHelper);
		}

		return corpEntryMergeHelpers;
	}

	private static CorpEntryMergeHelper _getCorpEntryMergeHelper(
			String className)
		throws CorpEntryMergeHelperException {

		try {
			Class<?> clazz = Class.forName(className);

			return (CorpEntryMergeHelper)clazz.newInstance();
		}
		catch (Exception e) {
			throw new CorpEntryMergeHelperException(e);
		}
	}

}