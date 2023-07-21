/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ZendeskArticle service. Represents a row in the &quot;OSBCustomer_ZendeskArticle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleImpl"
)
@ProviderType
public interface ZendeskArticle extends PersistedModel, ZendeskArticleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ZendeskArticle, Long>
		ZENDESK_ARTICLE_ID_ACCESSOR = new Accessor<ZendeskArticle, Long>() {

			@Override
			public Long get(ZendeskArticle zendeskArticle) {
				return zendeskArticle.getZendeskArticleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ZendeskArticle> getTypeClass() {
				return ZendeskArticle.class;
			}

		};

	public String getRemoteHtmlURL(String zendeskLocale);

}