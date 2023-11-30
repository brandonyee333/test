/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.client.extension.type;

import com.liferay.client.extension.type.annotation.CETProperty;
import com.liferay.client.extension.type.annotation.CETType;
import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Andrea Sbarra
 */
@CETType(description = "This is a description.", name = "customElement")
@ProviderType
public interface CustomCheckoutStepCET extends CET {

    @CETProperty(
        defaultValue = "", name = "checkoutStepLabel",
        type = CETProperty.Type.String
    )
    String getCheckoutStepLabel();

    @CETProperty(
        defaultValue = "", name = "checkoutStepName",
        type = CETProperty.Type.String
    )
    String getCheckoutStepName();

    @CETProperty(
        defaultValue = "", name = "checkoutStepOrder",
        type = CETProperty.Type.String
    )
    int getCheckoutStepOrder();
}
