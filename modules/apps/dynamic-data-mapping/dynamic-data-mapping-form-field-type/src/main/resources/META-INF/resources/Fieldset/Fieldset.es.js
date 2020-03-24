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

import './FieldsetRegister.soy';

import classnames from 'classnames';
import React, {useEffect, useRef} from 'react';

import {FieldBaseProxy} from '../FieldBase/ReactFieldBase.es';
import getConnectedReactComponentAdapter from '../util/ReactComponentAdapter.es';
import {connectStore} from '../util/connectStore.es';
import FieldType from './FieldType.es';
import templates from './FieldsetAdapter.soy';

class NoRender extends React.Component {
	shouldComponentUpdate() {
		return false;
	}

	render() {
		const {forwardRef, ...otherProps} = this.props;

		return <div ref={forwardRef} {...otherProps} />;
	}
}

// This is a proxy to maintain compatibility with the previous Fieldset, being able to
// call any registered field type. This should probably be removed by a more friendly
// implementation when we remove the implementation of calling the fields dynamically
// through soy.
const FieldTypeProxy = ({context, events, field, fieldIndex, spritemap}) => {
	const component = useRef(null);
	const container = useRef(null);

	useEffect(() => {
		if (!component.current && container.current) {
			component.current = new FieldType(
				{
					events,
					field,
					fieldIndex,
					parentContext: context,
					spritemap,
				},
				container.current
			);
		}

		return () => {
			if (component.current) {
				component.current.dispose();
			}
		};
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	return <NoRender forwardRef={container} />;
};

const Fieldset = ({
	columnSize,
	context,
	fields,
	label,
	onChange,
	showLabel,
	spritemap,
}) => (
	<fieldset className="border-bottom border-top">
		{showLabel && label && <legend>{label}</legend>}

		<div className="form-group-autofit">
			{fields.map((field, index) => (
				<div
					className={classnames(
						`col-md-${columnSize}`,
						'form-group-item',
						{
							hide: !field.visible,
						}
					)}
					key={`${index}${field.fieldName}`}
				>
					<div
						className={classnames(
							'clearfix lfr-ddm-form-field-container',
							{
								hide: field.visible === false,
							}
						)}
					>
						<FieldTypeProxy
							context={context}
							events={{
								fieldEdited: onChange,
							}}
							field={field}
							fieldIndex={index}
							spritemap={spritemap}
						/>
					</div>
				</div>
			))}
		</div>
	</fieldset>
);

const FieldsetProxy = connectStore(
	({
		columnSize,
		context,
		emit,
		label,
		nestedFields,
		showLabel,
		spritemap,
		...otherProps
	}) => (
		<div className="liferay-ddm-form-field-fieldset">
			<FieldBaseProxy
				{...otherProps}
				showLabel={false}
				spritemap={spritemap}
			>
				<Fieldset
					columnSize={columnSize}
					context={context}
					fields={nestedFields}
					label={label}
					onChange={event =>
						emit('fieldEdited', event.originalEvent, event.value)
					}
					showLabel={showLabel}
					spritemap={spritemap}
				/>
			</FieldBaseProxy>
		</div>
	)
);

const ReactFieldsetAdapter = getConnectedReactComponentAdapter(
	FieldsetProxy,
	templates
);

export {ReactFieldsetAdapter};
export default ReactFieldsetAdapter;
