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

package com.liferay.osb.customer.release.notes.jira.util.comparator;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;

import java.util.Comparator;

/**
 * @author Samuel Kong
 */
public class JIRAComponentComparator implements Comparator<JIRAComponent> {

	@Override
	public int compare(
		JIRAComponent jiraComponent1, JIRAComponent jiraComponent2) {

		String name1 = jiraComponent1.getName();
		String name2 = jiraComponent2.getName();

		return name1.compareTo(name2);
	}

}