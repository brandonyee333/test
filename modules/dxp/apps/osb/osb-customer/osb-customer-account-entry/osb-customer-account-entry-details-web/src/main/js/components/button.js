import React from 'react';
import PropTypes from 'prop-types';

const Button = props =>
	props.href ? (
		<a className="btn btn-default" href={props.href}>
			{props.children}
		</a>
	) : (
		<button
			className="btn btn-default"
			onClick={props.onClick}
			value={props.value}
		>
			{props.children}
		</button>
	);

Button.propTypes = {
	children: PropTypes.node.isRequired,
	href: PropTypes.string,
	onClick: PropTypes.func,
	value: PropTypes.string
};

export default Button;