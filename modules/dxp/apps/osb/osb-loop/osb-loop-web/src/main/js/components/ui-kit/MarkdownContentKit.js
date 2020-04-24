import Component from 'metal-jsx';

import Kit from './Kit';
import MarkdownContent from '../MarkdownContent';

const MESSAGE = '<h1>Headings:</h1><p>## Heading2</p><h2>Heading2</h2><p>### Heading3</p><h3>Heading3</h3><h1>Lists:</h1><p>* Item 1<br>* Item 2<br>* Item 3</p> <ul> <li>Item 1</li> <li>Item 2</li> <li>Item 3</li> </ul><h1>Text Styles</h1><p>You can bold and italicize text with either * or _:</p><p>_italics_ <em>italics</em><br>__bold__ <strong>bold</strong><br>**_Both_** <strong><em>Both</em></strong></p><h1>Links</h1><p>You can include links now without having to paste the entire URL:<br>[Liferay!](your URL here)<br><a href="https://www.liferay.com" rel="noopener noreferrer" target="_blank">Liferay!</a></p><h1>Block Quotes</h1><p>Prefix lines with a <code>&gt;</code> to create a blockquote:</p> <blockquote><p>This is<br>a block quote</p> </blockquote><h1>Code</h1><p>You can use backticks (``) to highlight code inline:</p><p>`print(\'Hello World\')` <code>print(\'Hello World\')</code></p><p>Sandwich your code between backticks like this for blocks:</p><p>```<br>print(\'Hello World\')<br>```</p> <pre><code>my @greeting = (\'Hello\', \'World\'); print for @greeting; 1; </code></pre></div>';

class MarkdownContentKit extends Component {
	render() {
		return (
			<Kit header="MarkdownContent">
				<MarkdownContent message={MESSAGE} />
			</Kit>
		);
	}
}

export default MarkdownContentKit;