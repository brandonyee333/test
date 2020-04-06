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

import React, {useEffect, useMemo, useRef, useState} from 'react';

import {Editor} from './Editor';

const DEFAULT_CONFIG = {
	filebrowserBrowseUrl: '',
	filebrowserFlashBrowseUrl: '',
	filebrowserImageBrowseLinkUrl: '',
	filebrowserImageBrowseUrl: '',
	filebrowserUploadUrl: null
};

const getToolbarSet = toolbarSet => {
	if (Liferay.Util.isPhone()) {
		toolbarSet = 'phone';
	}
	else if (Liferay.Util.isTablet()) {
		toolbarSet = 'tablet';
	}

	return toolbarSet;
};

const DefaultEditor = ({
	className,
	cssClass,
	contents,
	editorConfig,
	editorName,
	editorOptions,
	initialShowEditor,
	initialToolbarSet,
	initMethod,
	inlineEdit,
	inlineEditSaveURL,
	name,
	textareaName,
	toggleControlsStatus,
}) => {
	const editorRef = useRef();

	const [toolbarSet, setToolbarSet] = useState();
	const [showEditor, setShowEditor] = useState(initialShowEditor);

	const config = useMemo(() => {
		return {
			...DEFAULT_CONFIG,
			toolbar: toolbarSet,
			...editorConfig,
		}
	}, [DEFAULT_CONFIG, editorConfig, toolbarSet]);

	useEffect(() => {
		setToolbarSet(getToolbarSet(initialToolbarSet));
	}, [initialToolbarSet]);

	useEffect(() => {
		if (!window[name]) {
			window[name] = {
				getHTML() {
					return editorRef.current.editor.getData();
				},
				getText() {
					return editorRef.current.editor.getData();
				},
				setHTML(data) {
					editorRef.current.editor.setData(data);
				},
			};
		}

		Liferay.on(`${name}selectItem`, event => {
			CKEDITOR.tools.callFunction(event.ckeditorfuncnum, event.value);
		});
	}, []);

	return (
		<div className={cssClass} id={`${name}Container`}>
			{showEditor &&
				((inlineEdit && toggleControlsStatus === 'visible') ||
					!inlineEdit) && (
					<Editor
						className="lfr-editable"
						config={config}
						data={contents}
						id={name}
						onBeforeLoad={CKEDITOR => {
							CKEDITOR.disableAutoInline = true;
							CKEDITOR.dtd.$removeEmpty.i = 0;
							CKEDITOR.dtd.$removeEmpty.span = 0;
						}}
						ref={editorRef}
						type={inlineEdit ? 'inline' : 'classic'}
					/>
				)}
		</div>
	);
};

export default DefaultEditor;
