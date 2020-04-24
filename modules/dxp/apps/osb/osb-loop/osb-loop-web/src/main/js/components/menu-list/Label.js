import Component from 'metal-jsx';

class Label extends Component {
	render() {
		return (
			<li class="menu-label-container">
				{this.props.children}
			</li>
		);
	}
}

export default Label;