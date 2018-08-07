import React from 'react';
import PropTypes from 'prop-types';

import getCN from 'classnames';

export default class Button extends React.Component {
	static defaultProps = {
		display: 'primary',
		type: 'button'
	};

	static propTypes = {
		children: PropTypes.node.isRequired,
		display: PropTypes.oneOf(['disabled', 'link', 'primary']),
		href: PropTypes.string,
		onClick: PropTypes.oneOfType([PropTypes.func, PropTypes.string]),
		type: PropTypes.oneOf(['button', 'reset', 'submit']),
		value: PropTypes.string
	};

	render() {
		const {children, display, href, onClick, type, value} = this.props;

		const className = getCN('btn', `${!href ? `btn-${display}` : ''}`, {
			'btn-link': href
		});

		return href ? (
			<a className={className} href={href}>
				{children}
			</a>
		) : (
			<button className={className} onClick={onClick} type={type} value={value}>
				{children}
			</button>
		);
	}
}