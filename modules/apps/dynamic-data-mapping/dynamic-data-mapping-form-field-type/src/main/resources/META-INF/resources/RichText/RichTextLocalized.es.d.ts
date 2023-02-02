export function RichTextLocalizedd(props: IProps): JSX.Element;

interface IProps extends React.InputHTMLAttributes<HTMLInputElement> {
	ariaLabels?: {
		default: string;
		openLocalizations: string;
		translated: string;
		untranslated: string;
	};
	editorConfig: CKEDITOR.config;
	helpMessage?: string;
	label: string;
	onSelectedLocaleChange: (val: any) => void;
	onTranslationsChange: (val: any) => void;
	selectedLocale: any;
	translations: any;
}