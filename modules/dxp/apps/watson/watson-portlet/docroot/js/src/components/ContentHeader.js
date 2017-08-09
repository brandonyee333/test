function ContentHeader({headerStringLeft, headerStringRight}) {
	return (
		<span class="header-main">
			<span class="header-left">{headerStringLeft}</span>
			<span class="header-right">{headerStringRight}</span>
		</span>

	);
}

export default ContentHeader;