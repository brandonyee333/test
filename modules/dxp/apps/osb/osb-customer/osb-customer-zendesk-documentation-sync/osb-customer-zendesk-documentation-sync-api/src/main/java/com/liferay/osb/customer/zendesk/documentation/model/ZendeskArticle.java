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

package com.liferay.osb.customer.zendesk.documentation.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ZendeskArticle service. Represents a row in the &quot;OSBCustomer_ZendeskArticle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleModel
 * @see com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleImpl
 * @see com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleImpl")
@ProviderType
public interface ZendeskArticle extends ZendeskArticleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ZendeskArticle, Long> ZENDESK_ARTICLE_ID_ACCESSOR =
		new Accessor<ZendeskArticle, Long>() {
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
}