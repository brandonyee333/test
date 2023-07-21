/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the JournalArticleImage service. Represents a row in the &quot;JournalArticleImage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImageModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.journal.model.impl.JournalArticleImageImpl"
)
@ProviderType
public interface JournalArticleImage
	extends JournalArticleImageModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.journal.model.impl.JournalArticleImageImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JournalArticleImage, Long>
		ARTICLE_IMAGE_ID_ACCESSOR = new Accessor<JournalArticleImage, Long>() {

			@Override
			public Long get(JournalArticleImage journalArticleImage) {
				return journalArticleImage.getArticleImageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JournalArticleImage> getTypeClass() {
				return JournalArticleImage.class;
			}

		};

}