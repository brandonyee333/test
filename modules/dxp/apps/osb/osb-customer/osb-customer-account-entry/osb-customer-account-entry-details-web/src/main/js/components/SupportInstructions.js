import React from 'react';
import PropTypes from 'prop-types';

import CKEditor from "react-ckeditor-component";

import Button from './Button';
import Modal from './Modal';

export default class SupportInstructions extends React.Component {
	static propTypes = {
		supportInstructions: PropTypes.string.isRequired
	};

	state = {
		instructions: this.props.supportInstructions,
		modalTriggered: false,
	};

	handleCloseModal = () => this.setState(
		{
			modalTriggered: false
		}
	);

	handleDisplayModal = () => this.setState(
		{
			modalTriggered: true
		}
	);

	updateInstructions = () => {
		this.setState(
			{
				instructions: CKEDITOR.instances.editor1.getData(),
				modalTriggered: false
			}
		);
	}

	render() {
		const {instructions, modalTriggered} = this.state;

		const modalContent = {
			body: (
				<CKEditor
					config={{
						basicEntities: false
					}}
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

					<Button
						onClick={this.updateInstructions}
						value="save"
					>
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
							onClick={this.handleDisplayModal}
							size="sm"
							value="edit"
						>
							{Liferay.Language.get('edit')}
						</Button>

					) : (
						<Button
							icon={true}
							onClick={this.handleDisplayModal}
							value="add"
						>
							<svg className="lexicon-icon lexicon-icon-plus">
								<use xlinkHref="#plus" />
							</svg>
						</Button>
					)}

					{modalTriggered && (
						<Modal
							body={modalContent.body}
							footer={modalContent.footer}
							header={Liferay.Language.get('edit-support-instructions')}
							onClose={this.handleCloseModal}
							show={modalTriggered}
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