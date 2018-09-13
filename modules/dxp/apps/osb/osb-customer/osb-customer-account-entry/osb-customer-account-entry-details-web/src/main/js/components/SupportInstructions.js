import React from 'react';
import PropTypes from 'prop-types';

import CKEditor from 'react-ckeditor-component';

import Button from './Button';
import Modal from './Modal';

export default class SupportInstructions extends React.Component {
	static propTypes = {
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

	handleUpdate = () => {
		this.setState(
			{
				instructions: CKEDITOR.instances.editor1.getData(),
				showModal: false
			}
		);
	};

	render() {
		const {instructions, showModal} = this.state;

		const modalConfig = {
			body: (
				<CKEditor
					content={instructions}
				/>
			),
			footer: (
				<div className="btn-row">
					<Button
						display="default"
						onClick={this.handleCloseModal}
						value="cancel"
					>
						{Liferay.Language.get('cancel')}
					</Button>

					<Button onClick={this.handleUpdate} value="save">
						{Liferay.Language.get('save')}
					</Button>
				</div>
			)
		};

		return (
			<React.Fragment>
				<h3 className="card-header">
					{Liferay.Language.get('support-instructions')}

					{instructions ? (
						<Button
							display="default"
							onClick={this.handleShowModal}
							size="sm"
							value="edit"
						>
							{Liferay.Language.get('edit')}
						</Button>
					) : (
						<Button icon={true} onClick={this.handleShowModal} value="add">
							<svg className="lexicon-icon lexicon-icon-plus">
								<use xlinkHref="#plus" />
							</svg>
						</Button>
					)}

					{showModal && (
						<Modal
							body={modalConfig.body}
							footer={modalConfig.footer}
							header={Liferay.Language.get('edit-support-instructions')}
							onClose={this.handleCloseModal}
							show={showModal}
						/>
					)}
				</h3>

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