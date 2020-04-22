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

package com.liferay.post.upgrade.fix.LPS_70608.osgi.commands;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.post.upgrade.fix.osgi.commands.BasePostUpgradeFixOSGiCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alberto Chaparro
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=" + PostUpgradeFixOSGiCommands.FUNCTION,
		"osgi.command.scope=" + PostUpgradeFixOSGiCommands.SCOPE
	},
	service = PostUpgradeFixOSGiCommands.class
)
public class PostUpgradeFixOSGiCommands extends BasePostUpgradeFixOSGiCommands {

	public static final String FUNCTION = "LPS_70608";

	public void LPS_70608() {
		execute();
	}

	protected String addImageContentAttributes(String content)
		throws Exception {

		Document document = SAXReaderUtil.read(content);

		document = document.clone();

		XPath xPath = SAXReaderUtil.createXPath(
			"//dynamic-element[@type='image']");

		List<Node> imageNodes = xPath.selectNodes(document);

		for (Node imageNode : imageNodes) {
			Element imageElement = (Element)imageNode;

			List<Element> dynamicContentElements = imageElement.elements(
				"dynamic-content");

			for (Element dynamicContentElement : dynamicContentElements) {
				String id = dynamicContentElement.attributeValue("id");

				if (Validator.isNotNull(id)) {
					continue;
				}

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					dynamicContentElement.getText());

				String data = jsonObject.getString("data");

				dynamicContentElement.setText(data);

				id = _http.getParameter(data, "img_id", false);

				if (id.equals(StringPool.BLANK)) {
					continue;
				}

				dynamicContentElement.addAttribute("alt", StringPool.BLANK);
				dynamicContentElement.addAttribute("id", id);
				dynamicContentElement.addAttribute("name", id);
				dynamicContentElement.addAttribute("title", id);
				dynamicContentElement.addAttribute("type", "journal");
			}
		}

		return document.formattedString();
	}

	@Override
	protected void doExecute() throws Exception {
		updateContentImages();
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

	protected void updateContentImages() throws Exception {
		try (Connection connection = DataAccess.getConnection();
			PreparedStatement ps1 = connection.prepareStatement(
				"select content, id_ from JournalArticle where content like " +
					"?")) {

			ps1.setString(1, "%type=\"image\"%");

			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				String content = rs.getString(1);
				long id = rs.getLong(2);

				String newContent = addImageContentAttributes(content);

				try (PreparedStatement ps =
						AutoBatchPreparedStatementUtil.concurrentAutoBatch(
							connection,
							"update JournalArticle set content = ? where id_ " +
								"= ?")) {

					ps.setString(1, newContent);
					ps.setLong(2, id);

					ps.executeUpdate();
				}
			}
		}
	}

	@Reference
	private Http _http;

}