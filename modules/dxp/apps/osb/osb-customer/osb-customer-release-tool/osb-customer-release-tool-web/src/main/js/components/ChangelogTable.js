import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import Button from './Button';
import Modal from './Modal';

class Ticket extends Component {
	static propTypes = {
		tickets: PropTypes.array.isRequired
	};

	state = {
		id: '',
		showModal: false
	};

	handleCloseModal = () => {
		this.setState(
			{
				id: '',
				showModal: false
			}
		);
	};

	handleShowModal = id => {
		this.setState(
			{
				id: id,
				showModal: true
			}
		);
	};

	render() {
		const {tickets} = this.props;
		const {id, showModal} = this.state;

		return tickets.map(
			ticket => (
				<TicketDetail
					key={`${ticket.key}${ticket.release}`}
					handleCloseModal={this.handleCloseModal}
					handleShowModal={this.handleShowModal}
					id={`${ticket.key}${ticket.release}`}
					showModal={id === `${ticket.key}${ticket.release}` ? showModal : false}
					ticket={ticket}
				/>
			)
		);
	}
}

class TicketDetail extends Component {
	static propTypes = {
		handleCloseModal: PropTypes.func.isRequired,
		handleShowModal: PropTypes.func.isRequired,
		id: PropTypes.oneOfType([PropTypes.number, PropTypes.string]),
		showModal: PropTypes.bool.isRequired,
		ticket: PropTypes.object.isRequired
	};

	handleShowModal = () => {
		const {handleShowModal, id} = this.props;

		handleShowModal(id);
	};

	render() {
		const {handleCloseModal, id, showModal, ticket} = this.props;

		return (
			<tr className="journal-article-row" id={id}>
				<td className="lfr-summary-column table-cell-content">
					<Button display="link" onClick={this.handleShowModal} type="button">
						{ticket.summary}
					</Button>

					<Modal
						header={Liferay.Language.get('details')}
						onClose={handleCloseModal}
						show={showModal}
					>
						<div className="ticket-detail">
							<div className="small-title">{ticket.summary}</div>

							<h3>{Liferay.Language.get('description')}</h3>

							<div dangerouslySetInnerHTML={{__html: ticket.description}} />

							<h3>{Liferay.Language.get('components')}</h3>

							{ticket.components.toString().replace(',', ', ')}

							<h3>{Liferay.Language.get('release')}</h3>

							{ticket.release}

							<h3>{Liferay.Language.get('key')}</h3>

							<a href={ticket.url}>{ticket.key}</a>
						</div>
					</Modal>
				</td>
				<td className="lfr-component-column">
					{ticket.components.toString().replace(',', ', ')}
				</td>
				<td className="lfr-release-column">
					{ticket.release}
				</td>
				<td className="lfr-key-column">
					<a href={ticket.url}>{ticket.key}</a>
				</td>
			</tr>
		);
	}
}

export const tableBody = results => <Ticket tickets={results} />;

export const tableHeader = (orderBy, handleSort) => (
	<Fragment>
		<th className="lfr-summary-column table-cell-content">
			{Liferay.Language.get('summary')}
		</th>
		<th className="lfr-component-column">
			{Liferay.Language.get('component')}
		</th>
		<th className="lfr-release-column">
			{Liferay.Language.get('release')}{' '}
			<svg
				className={`arrow-down ${orderBy} lexicon-icon sorting-indicator`}
				onClick={handleSort}
				role="button"
			>
				<use xlinkHref="#arrow-down" />
			</svg>
		</th>
		<th className="lfr-key-column">{Liferay.Language.get('key')}</th>
	</Fragment>
);