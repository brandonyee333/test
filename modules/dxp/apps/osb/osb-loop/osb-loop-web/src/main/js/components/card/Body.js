import Component from 'metal-jsx';

class CardBody extends Component {
	render() {
		return (
			<div class="card-body-container">
				{this.props.children}
			</div>
		);
	}
}

export default CardBody;