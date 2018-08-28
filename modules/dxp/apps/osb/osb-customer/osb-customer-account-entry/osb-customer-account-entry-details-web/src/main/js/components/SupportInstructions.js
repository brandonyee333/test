import React from 'react';
import PropTypes from 'prop-types';

import Button from './Button';
import CKEditor from "react-ckeditor-component";
import Modal from './Modal';
import Parser from 'html-react-parser';

export default class SupportInstructions extends React.Component {
	static propTypes = {
		initialInstructions: PropTypes.node.isRequired
	};

	constructor(props) {
		super(props);

		this.state = {
			editorInstructions: this.props.initialInstructions,
			instructions: this.props.initialInstructions,
			modalTriggered: false,
			newInstructions: this.props.initialInstructions
		};
	};

	closeAddInstructionsModal = () => {
		let instructions = this.state.instructions;

		this.setState(
			{
				editorInstructions: instructions,
				modalTriggered: false
			}
		);
	};

	onChange = (e) => {
		let editorData = e.editor.getData();
		let newInstructions = this.state.newInstructions;

		newInstructions = editorData;

		this.setState(
			{
				editorInstructions: newInstructions,
				newInstructions
			}
		);
	};

	triggerAddInstructionsModal = () => this.setState(
		{
			modalTriggered: true
		}
	);

	triggerEditInstructionsModal = () => this.setState(
		{
			modalTriggered: true
		}
	);

	updateInstructions = () => {
		let newInstructions = this.state.newInstructions;

		this.setState(
			{
				instructions: newInstructions,
				modalTriggered: false
			}
		);
	};

	render() {
		const {editorInstructions} = this.state;
		const {instructions} = this.state;
		const {modalTriggered} = this.state;

		const modalContent = {
			body: (
				<React.Fragment>
					<CKEditor
						config={{
							basicEntities: false
						}}
						content={editorInstructions}
						events={{
							"change": this.onChange
						}}
					/>
				</React.Fragment>
			),
			footer: (
				<React.Fragment>
					<Button display="default" onClick={this.closeAddInstructionsModal} value="cancel">
						{Liferay.Language.get('cancel')}
					</Button>

					<Button onClick={this.updateInstructions} value="save">
						{Liferay.Language.get('save')}
					</Button>
				</React.Fragment>
			)
		}

		return (
			<React.Fragment>
				<h3 className="card-header">
					{Liferay.Language.get('support-instructions')}

					{instructions ? (
						<Button display="default" onClick={this.triggerEditInstructionsModal} size="sm" value="edit">
							{Liferay.Language.get('edit')}
						</Button>

					) : (
						<Button onClick={this.triggerAddInstructionsModal} size="sm" value="add">
							+
						</Button>
					)}

					{modalTriggered && (
						<Modal
							body={modalContent.body}
							footer={modalContent.footer}
							header={Liferay.Language.get('edit-support-instructions')}
							onClose={this.closeAddInstructionsModal}
							show={modalTriggered}
						/>
					)}
				</h3>

				{instructions ? (
					<div className="card-body">
						{Parser(instructions)}
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