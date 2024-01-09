package com.liferay.style.book.register;

import com.liferay.client.extension.type.ThemeCSSCET;
import com.liferay.frontend.token.definition.FrontendTokenDefinition;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(service = CETFrontendTokenDefinitionRegistry.class)
public class CETFrontendTokenDefinitionRegistryImpl
	implements CETFrontendTokenDefinitionRegistry {

	@Override
	public ThemeCSSCET getTokenDefinition(
		String externalReferenceCode) {
		return _serviceTrackerMap.getService(externalReferenceCode);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ThemeCSSCET.class,
			"external.reference.code");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, ThemeCSSCET>
		_serviceTrackerMap;
}
