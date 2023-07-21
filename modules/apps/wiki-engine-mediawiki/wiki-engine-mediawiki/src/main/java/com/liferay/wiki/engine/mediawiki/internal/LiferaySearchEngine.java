/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal;

import java.util.List;

import org.jamwiki.SearchEngine;
import org.jamwiki.model.SearchResultEntry;
import org.jamwiki.model.Topic;

/**
 * @author Jonathan Potter
 */
public class LiferaySearchEngine implements SearchEngine {

	@Override
	public void addToIndex(Topic arg0) {
	}

	public void addToIndex(Topic topic, List<String> links) {
	}

	@Override
	public void commit(String arg0) {
	}

	@Override
	public void deleteFromIndex(Topic topic) {
	}

	public List<SearchResultEntry> findLinkedTo(
		String virtualWiki, String topicName) {

		return null;
	}

	@Override
	public List<SearchResultEntry> findResults(
		String virtualWiki, String text) {

		return null;
	}

	@Override
	public void refreshIndex() {
	}

	@Override
	public void setAutoCommit(boolean autoCommit) {
	}

	@Override
	public void shutdown() {
	}

	@Override
	public void updateInIndex(Topic topic) {
	}

	public void updateInIndex(Topic topic, List<String> links) {
	}

}