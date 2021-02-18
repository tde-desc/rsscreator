import FeedConfigs from './feedconfigs'
import NewFeedConfig from './newfeedconfig'
import EmbeddedPage from './embeddedpage'

const React = require('react');
const ReactDOM = require('react-dom');

ReactDOM.render(
	<FeedConfigs />,
	document.getElementById('feedConfigs')
)
ReactDOM.render(
	<NewFeedConfig />,
	document.getElementById('newFeedConfig')
)
ReactDOM.render(
	<EmbeddedPage />,
	document.getElementById('embeddedPage')
)