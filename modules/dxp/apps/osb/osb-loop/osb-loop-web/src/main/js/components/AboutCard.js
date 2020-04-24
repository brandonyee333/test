import Component, {Config} from 'metal-jsx';

import Card from './card';
import MarkdownContent from './MarkdownContent';

class AboutCard extends Component {
	render() {
		const {details, message} = this.props;

		return (
			<Card elementClasses="about-card-container">
				<Card.Header>{Liferay.Language.get('about')}</Card.Header>

				{message && !!message.length &&
					<MarkdownContent message={message} />
				}

				{details &&
					<div class="details">
						<table>
							{
								Object.keys(details).map(
									key => {
										const value = details[key];

										return value && (
											<tr key={key}>
												<th>{key}</th>
												<td>{value}</td>
											</tr>
										);
									}
								)
							}
						</table>
					</div>
				}
			</Card>
		);
	}
}

AboutCard.PROPS = {
	details: Config.object(),
	message: Config.string().value('')
};

export default AboutCard;