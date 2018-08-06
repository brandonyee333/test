import React from 'react';
import PropTypes from 'prop-types';

const Accordion = props => (
	<div aria-orientation="vertical" className="panel-group" role="tablist">
		{props.items.map(item => (
			<AccordionItem key={item.key} body={item.body} title={item.title} />
		))}
	</div>
);

Accordion.propTypes = {
	items: PropTypes.array.isRequired
};

class AccordionItem extends React.Component {
	state = {
		expanded: false
	};

	static propTypes = {
		body: PropTypes.node.isRequired,
		key: PropTypes.number,
		title: PropTypes.node.isRequired
	};

	handleClick = () => {
		this.setState({expanded: !this.state.expanded});
	};

	render() {
		const {body, key, title} = this.props;
		const {expanded} = this.state;

		return (
			<div className="panel" id={key}>
				<a
					className="panel-header panel-header-link"
					href="javascript:;"
					onClick={this.handleClick}
					role="tab"
				>
					<div className="panel-title">{title}</div>
				</a>

				{expanded && (
					<div role="tabpanel">
						<div className="panel-body">{body}</div>
					</div>
				)}
			</div>
		);
	}
}

export default Accordion;