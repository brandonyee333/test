/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.TreeModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the JournalArticle service. Represents a row in the &quot;JournalArticle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleModel
 * @generated
 */
@ImplementationClassName("com.liferay.journal.model.impl.JournalArticleImpl")
@ProviderType
public interface JournalArticle
	extends JournalArticleModel, PersistedModel, TreeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.journal.model.impl.JournalArticleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JournalArticle, Long> ID_ACCESSOR =
		new Accessor<JournalArticle, Long>() {

			@Override
			public Long get(JournalArticle journalArticle) {
				return journalArticle.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JournalArticle> getTypeClass() {
				return JournalArticle.class;
			}

		};
	public static final Accessor<JournalArticle, String> ARTICLE_ID_ACCESSOR =
		new Accessor<JournalArticle, String>() {

			@Override
			public String get(JournalArticle journalArticle) {
				return journalArticle.getArticleId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<JournalArticle> getTypeClass() {
				return JournalArticle.class;
			}

		};

	@Override
	public String buildTreePath()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getArticleImageId(
		String elInstanceId, String elName, String languageId);

	public String getArticleImageURL(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

	public JournalArticleResource getArticleResource()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getArticleResourceUuid()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getContentByLocale(String languageId);

	public com.liferay.dynamic.data.mapping.model.DDMStructure getDDMStructure()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.dynamic.data.mapping.model.DDMTemplate getDDMTemplate()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.portal.kernel.xml.Document getDocument();

	public JournalFolder getFolder()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.portal.kernel.model.Layout getLayout();

	public String getSmallImageType()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #getDDMStructureKey()}
	 */
	@Deprecated
	public String getStructureId();

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #getDDMTemplateKey()}
	 */
	@Deprecated
	public String getTemplateId();

	public boolean hasApprovedVersion();

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public boolean isTemplateDriven();

	public void setDefaultLanguageId(String defaultLanguageId);

	public void setDocument(com.liferay.portal.kernel.xml.Document document);

	public void setSmallImageType(String smallImageType);

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #setDDMStructureKey(String)}
	 */
	@Deprecated
	public void setStructureId(String ddmStructureKey);

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #setDDMTemplateKey(String)}
	 */
	@Deprecated
	public void setTemplateId(String ddmTemplateKey);

}