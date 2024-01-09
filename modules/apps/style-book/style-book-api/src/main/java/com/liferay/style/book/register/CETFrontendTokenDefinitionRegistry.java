package com.liferay.style.book.register;

import com.liferay.client.extension.type.ThemeCSSCET;
import com.liferay.frontend.token.definition.FrontendTokenDefinition;

public interface CETFrontendTokenDefinitionRegistry {

	public ThemeCSSCET getTokenDefinition(
		String externalReferenceCode);

}
