import React from 'react';
import PropTypes from 'prop-types';

import CKEditor from 'react-ckeditor-component';

import Button from './Button';
import Modal from './Modal';

export default class SupportInstructions extends React.Component {
	static propTypes = {
		accountEntryId: PropTypes.string.isRequired,
		editInstructionsURL: PropTypes.string.isRequired,
		instructions: PropTypes.string.isRequired
	};

	state = {
		instructions: this.props.instructions,
		showModal: false
	};

	handleCloseModal = () =>
		this.setState(
			{
				showModal: false
			}
		);

	handleShowModal = () =>
		this.setState(
			{
				showModal: true
			}
		);

	handleUpdate = () =>
		this.setState(
			{
				instructions: CKEDITOR.instances.editor1.document.getBody().getHtml()
			}
		);

	render() {
		const {accountEntryId, editInstructionsURL} = this.props;
		const {instructions, showModal} = this.state;
		const {namespace} = window.AccountDetailsConstants;

		const modalFooter = (
			<form action={editInstructionsURL} onSubmit={this.handleUpdate} method="post">
				<input name={`${namespace}accountEntryId`} type="hidden" value={accountEntryId} />
				<input name={`${namespace}instructions`} type="hidden" value={instructions} />

				<div className="btn-row">
					<Button
						display="default"
						onClick={this.handleCloseModal}
						type="reset"
						value="cancel"
					>
						{Liferay.Language.get('cancel')}
					</Button>

					<Button type="submit" value="save">
						{Liferay.Language.get('save')}
					</Button>
				</div>
			</form>
		);

		return (
			<React.Fragment>
				<div className="card-header small-title">
					{Liferay.Language.get('support-instructions')}

					{instructions ? (
						<Button
							display="default"
							onClick={this.handleShowModal}
							size="sm"
							type="button"
							value="edit"
						>
							{Liferay.Language.get('edit')}
						</Button>
					) : (
						<Button
							icon
							onClick={this.handleShowModal}
							type="button"
							value="add"
						>
							<svg className="lexicon-icon lexicon-icon-plus">
								<use xlinkHref="#plus" />
							</svg>
						</Button>
					)}

					{showModal && (
						<Modal
							footer={modalFooter}
							header={Liferay.Language.get('edit-support-instructions')}
							onClose={this.handleCloseModal}
							show={showModal}
						>
							<CKEditor
								content={instructions}
							/>
						</Modal>
					)}
				</div>

				{instructions ? (
					<div className="card-body">
						<div dangerouslySetInnerHTML={{__html: instructions}} />
					</div>
				) : (
					<div className="no-results">
						{Liferay.Language.get('no-support-instructions')}
					</div>
				)}
			</React.Fragment>
		);
	}
}