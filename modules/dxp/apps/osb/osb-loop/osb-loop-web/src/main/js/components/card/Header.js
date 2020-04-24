import Component from 'metal-jsx';

class CardHeader extends Component {
	render() {
		return (
			<div class="card-header-container">
				{this.props.children}
			</div>
		);
	}
}

export default CardHeader;