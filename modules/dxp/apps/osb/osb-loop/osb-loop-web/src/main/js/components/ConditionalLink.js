import Component, {Config} from 'metal-jsx';

import ExternalLink from './ExternalLink';

class ConditionalLink extends Component {
	render() {
		const {children, elementClasses, externalLink, href, useSpan} = this.props;

		let content;

		if (useSpan) {
			content = (
				<span {...this.otherProps()}>
					{children}
				</span>
			);
		}
		else if (externalLink) {
			content = (
				<ExternalLink
					{...this.otherProps()}
					elementClasses={elementClasses}
					href={href}
					ref="externalLink"
				>
					{children}
				</ExternalLink>
			);
		}
		else {
			content = (
				<a href={href} {...this.otherProps()}>
					{children}
				</a>
			);
		}

		return content;
	}
}

ConditionalLink.PROPS = {
	externalLink: Config.bool().value(false),
	href: Config.string(),
	useSpan: Config.bool().value(false)
};

export default ConditionalLink;