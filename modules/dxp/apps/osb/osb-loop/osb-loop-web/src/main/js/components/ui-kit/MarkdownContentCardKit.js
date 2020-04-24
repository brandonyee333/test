import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import MarkdownContentCard from '../MarkdownContentCard';
import Kit from './Kit';

const CONTENT = '<a href="#">Test</a><p>Loop is neat.</p><ul><li>Type some Markdown on the left</li><li>See HTML in the right</li><li>Magic</li></ul>';

const styles = {minWidth: '300px'};

class MarkdownContentCardKit extends Component {
	render() {
		return (
			<Kit card={false} header="MarkdownContentCard">
				<ElementContainer header="No Content">
					<MarkdownContentCard
						content=""
						editURL="#"
						headerTitle="Title"
						iconName="info-card"
						noContentMessage="No Content"
						style={styles}
					/>
				</ElementContainer>

				<ElementContainer header="Basic Content">
					<MarkdownContentCard
						content={CONTENT}
						editURL="#"
						headerTitle="Title"
						iconName="info-card"
						noContentMessage="No Content"
						style={styles}
					/>
				</ElementContainer>

				<ElementContainer header="Lots of Content">
					<MarkdownContentCard
						content={CONTENT.repeat(5)}
						editURL="#"
						headerTitle="Title"
						iconName="info-card"
						noContentMessage="No Content"
						style={styles}
						truncatedContent={CONTENT}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default MarkdownContentCardKit;