import React from 'react';
import PropTypes from 'prop-types';

import getCN from 'classnames';

Modal.propTypes = {
	children: PropTypes.node.isRequired,
	footer: PropTypes.node,
	header: PropTypes.node,
	onClose: PropTypes.func.isRequired,
	show: PropTypes.bool.isRequired,
	size: PropTypes.oneOf(['full-screen', 'lg', 'sm'])
};

export default function Modal({children, footer, header, onClose, show, size}) {
	const className = getCN(
		'modal-dialog',
		`${size ? `modal-${size}` : ''}`
	);

	return show && (
		<div className="modal show" role="dialog" tabIndex="-1">
			<div className={className}>
				<div className="modal-content">
					{header && (
						<div className="modal-header">
							<div className="modal-title">
								{header}
							</div>

							<button aria-label="Close" className="close" onClick={onClose} type="button">
								<svg className="lexicon-icon lexicon-icon-times">
									<use xlinkHref="#times" />
								</svg>
							</button>
						</div>
					)}

					<div className="modal-body">
						{children}
					</div>

					{footer && (
						<div className="modal-footer">
							{footer}
						</div>
					)}
				</div>
			</div>
		</div>
	);
}