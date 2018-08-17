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
 * The extended model interface for the ZendeskArticleAttachment service. Represents a row in the &quot;OSBCustomer_ZendeskArticleAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachmentModel
 * @see com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleAttachmentImpl
 * @see com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleAttachmentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleAttachmentImpl")
@ProviderType
public interface ZendeskArticleAttachment extends ZendeskArticleAttachmentModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.customer.zendesk.documentation.model.impl.ZendeskArticleAttachmentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ZendeskArticleAttachment, Long> ZENDESK_ARTICLE_ATTACHMENT_ID_ACCESSOR =
		new Accessor<ZendeskArticleAttachment, Long>() {
			@Override
			public Long get(ZendeskArticleAttachment zendeskArticleAttachment) {
				return zendeskArticleAttachment.getZendeskArticleAttachmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ZendeskArticleAttachment> getTypeClass() {
				return ZendeskArticleAttachment.class;
			}
		};
}