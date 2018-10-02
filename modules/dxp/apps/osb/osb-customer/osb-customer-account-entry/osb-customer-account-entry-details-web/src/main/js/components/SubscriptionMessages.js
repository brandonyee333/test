import React from 'react';
import PropTypes from 'prop-types';

import Alert from './Alert';
import Modal from './Modal';

class SubscriptionAlert extends React.Component {
	static propTypes = {
		id: PropTypes.oneOfType([PropTypes.number, PropTypes.string]),
		message: PropTypes.object,
		onClose: PropTypes.func
	};

	state = {
		showModal: false
	};

	handleCloseAlert = () => {
		const {id, onClose} = this.props;

		onClose(id);
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

	render() {
		const {id, message} = this.props;
		const {showModal} = this.state;

		return (
			<div id={id}>
				<Alert
					type={message.severity === 'urgent' ? 'danger' : message.severity}
					onClose={this.handleCloseAlert}
				>
					<span className="lead">
						{`${Liferay.Language.get('subscription-message')}:`}
					</span>

					{' '}
					{message.title}
					{' '}

					<a className="semibold" onClick={this.handleShowModal}>
						{Liferay.Language.get('view-message')}
					</a>
				</Alert>

				<Modal
					footer={
						<button className="btn btn-primary pull-right" onClick={this.handleCloseModal}>
							{Liferay.Language.get('okay')}
						</button>
					}
					header={Liferay.Language.get('subscription-message')}
					onClose={this.handleCloseModal}
					show={showModal}
					size="lg"
				>
					{message.content}
				</Modal>
			</div>
		);
	}
}

export default class SubscriptionMessages extends React.Component {
	state = {
		messages: this.props.messages
	};

	static propTypes = {
		messages: PropTypes.arrayOf(PropTypes.object).isRequired
	};

	handleCloseAlert = (currentId) => {
		const newMessages = this.state.messages.filter(
			message => message.id !== currentId
		);

		this.setState(
			{
				messages: newMessages
			}
		);
	};

	render() {
		return (
			<div>
				{
					this.state.messages.map(
						(message, index) => (
							<SubscriptionAlert
								key={index}
								id={message.id}
								message={message}
								onClose={this.handleCloseAlert}
							/>
						)
					)
				}
			</div>
		);
	}
}