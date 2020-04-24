import Component from 'metal-jsx';

class ExternalLink extends Component {
	render() {
		return (
			<a {...this.otherProps()} rel="noopener noreferrer" target="_blank">
				{this.props.children}
			</a>
		);
	}
}

export default ExternalLink;