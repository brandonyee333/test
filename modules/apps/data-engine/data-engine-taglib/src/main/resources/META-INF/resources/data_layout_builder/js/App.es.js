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

import {ClayModalProvider} from '@clayui/modal';
import React, {useContext, useEffect, useState} from 'react';
import {DndProvider} from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';

import AppContext from './AppContext.es';
import AppContextProvider from './AppContextProvider.es';
import MultiPanelSidebar from './components/sidebar/MultiPanelSidebar.es';
import DataLayoutBuilder from './data-layout-builder/DataLayoutBuilder.es';
import DataLayoutBuilderContextProvider from './data-layout-builder/DataLayoutBuilderContextProvider.es';
import DataLayoutBuilderSidebar from './data-layout-builder/DataLayoutBuilderSidebar.es';
import DataLayoutBuilderDragAndDrop from './drag-and-drop/DataLayoutBuilderDragAndDrop.es';

const parseProps = ({
	dataDefinitionId,
	dataLayoutId,
	fieldTypesModules,
	groupId,
	...props
}) => ({
	...props,
	dataDefinitionId: Number(dataDefinitionId),
	dataLayoutId: Number(dataLayoutId),
	fieldTypesModules: fieldTypesModules.split(','),
	groupId: Number(groupId),
});

// The order should be considered for this Mock!
// Separators must be concatenated with an uuid.

const SIDEBAR_PANELS = {
	hello: {
		icon: 'globe',
		label: 'hello',
		panelId: 'hello',
		pluginEntryPoint:
			'journal-web@4.0.6/js/ddm_structure/panels/BasicHelloWorld.es',
	},
	separator2132123: {
		panelId: 'separator2132123',
		separator: true,
	},
	fields: {
		icon: 'grid',
		label: 'fields',
		panelId: 'fields',
		pluginEntryPoint:
			'data-engine-taglib@2.0.7/data_layout_builder/js/plugins/fields-sidebar/index.es',
	},
	separator3122413: {
		panelId: 'separator3122413',
		separator: true,
	},
	properties: {
		panelId: 'properties',
		icon: 'cog',
		label: 'Properties',
		pluginEntryPoint:
			'journal-web@4.0.6/js/ddm_structure/panels/BasicInfo.es',
	},
	dxp: {
		panelId: 'dxp',
		icon: 'desktop',
		label: 'Liferay Home',
		isLink: true,
		url: 'https://localhost:8080',
	},
};

const AppSidebar = ({sidebarPanels}) => {
	if (sidebarPanels) {
		return <MultiPanelSidebar sidebarPanels={sidebarPanels} />;
	}

	return <DataLayoutBuilderSidebar />;
};

const AppContent = ({
	dataLayoutBuilder,
	setDataLayoutBuilder,
	sidebarPanels,
	...props
}) => {
	// Mocking sidebarPanels
	sidebarPanels = SIDEBAR_PANELS;

	const [state, dispatch] = useContext(AppContext);

	useEffect(() => {
		if (dataLayoutBuilder) {
			dataLayoutBuilder.emit('contextUpdated', state);
		}
	}, [dataLayoutBuilder, state]);

	return (
		<>
			<DataLayoutBuilder
				appContext={[state, dispatch]}
				onLoad={setDataLayoutBuilder}
				{...parseProps(props)}
			/>

			{dataLayoutBuilder && (
				<DataLayoutBuilderContextProvider
					dataLayoutBuilder={dataLayoutBuilder}
				>
					<AppSidebar sidebarPanels={sidebarPanels} />

					<DataLayoutBuilderDragAndDrop
						dataLayoutBuilder={dataLayoutBuilder}
					/>
				</DataLayoutBuilderContextProvider>
			)}
		</>
	);
};

const App = props => {
	const {
		contentType,
		dataDefinitionId,
		dataLayoutId,
		fieldTypesModules,
		groupId,
	} = parseProps(props);

	const [loaded, setLoaded] = useState(false);
	const [dataLayoutBuilder, setDataLayoutBuilder] = useState(null);

	useEffect(() => {
		Liferay.Loader.require(...fieldTypesModules, () => {
			setLoaded(true);
		});
	}, [fieldTypesModules]);

	return (
		<DndProvider backend={HTML5Backend}>
			<ClayModalProvider>
				{loaded && (
					<AppContextProvider
						contentType={contentType}
						dataDefinitionId={dataDefinitionId}
						dataLayoutBuilder={dataLayoutBuilder}
						dataLayoutId={dataLayoutId}
						groupId={groupId}
					>
						<AppContent
							dataLayoutBuilder={dataLayoutBuilder}
							setDataLayoutBuilder={setDataLayoutBuilder}
							{...props}
						/>
					</AppContextProvider>
				)}
			</ClayModalProvider>
		</DndProvider>
	);
};

export default function(props) {
	return <App {...props} panels={[['fields'], ['properties']]} />;
}
