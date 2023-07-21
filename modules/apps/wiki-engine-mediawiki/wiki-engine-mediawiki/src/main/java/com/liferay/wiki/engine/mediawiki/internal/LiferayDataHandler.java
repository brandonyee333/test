/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;

import java.sql.Connection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jamwiki.model.Namespace;
import org.jamwiki.model.Topic;
import org.jamwiki.model.TopicType;

/**
 * @author Jonathan Potter
 */
public class LiferayDataHandler extends DummyDataHandler {

	@Override
	public Namespace lookupNamespace(
		String virtualWiki, String namespaceString) {

		String label = _fileNamespace.getLabel(virtualWiki);

		if (StringUtil.equalsIgnoreCase(label, namespaceString)) {
			return _fileNamespace;
		}

		return null;
	}

	@Override
	public Namespace lookupNamespaceById(int namespaceId) {
		return Namespace.DEFAULT_NAMESPACES.get(namespaceId);
	}

	@Override
	public Topic lookupTopic(
		String virtualWiki, String topicName, boolean deleteOK,
		Connection conn) {

		Topic topic = new Topic(virtualWiki, topicName);

		topic.setTopicType(TopicType.IMAGE);

		return topic;
	}

	@Override
	public String lookupTopicName(String virtualWiki, String topicName) {
		long nodeId = getNodeId(virtualWiki);

		try {
			WikiPage page = WikiPageLocalServiceUtil.getPage(
				nodeId, topicName, true);

			return page.getTitle();
		}
		catch (Exception e) {
		}

		return null;
	}

	protected long getNodeId(String virtualWiki) {
		Matcher matcher = _pattern.matcher(virtualWiki);

		if (matcher.find()) {
			return GetterUtil.getLong(matcher.group(1));
		}

		return 0;
	}

	private static final Pattern _pattern = Pattern.compile(
		"Special:Node:(\\d+)");

	private final Namespace _fileNamespace = Namespace.DEFAULT_NAMESPACES.get(
		Namespace.FILE_ID);

}