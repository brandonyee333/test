import React from 'react';
import PropTypes from 'prop-types';

const Modal = ({body, closeModal, footer, header, showModal}) =>
	showModal && (
		<div className="modal show" role="dialog" tabIndex="-1">
			<div className="modal-dialog">
				<div className="modal-content">
					{header && (
						<div className="modal-header">
							<div className="modal-title">{header}</div>

							<button
								aria-label="Close"
								className="close"
								onClick={closeModal}
								type="button"
							>
								<svg className="lexicon-icon lexicon-icon-times">
									<use xlinkHref="#times" />
								</svg>
							</button>
						</div>
					)}

					<div className="modal-body">{body}</div>

					{footer && <div className="modal-footer">{footer}</div>}
				</div>
			</div>
		</div>
	);

Modal.propTypes = {
	body: PropTypes.node.isRequired,
	closeModal: PropTypes.func.isRequired,
	footer: PropTypes.node,
	header: PropTypes.node,
	showModal: PropTypes.bool.isRequired
};

export default Modal;