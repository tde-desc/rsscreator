const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

export default class EmbeddedPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = { embeddedPage : {} }
    }
    componentDidMount() {
        /*client({ method: 'GET', path: '/api/rest/embeddedpage?url=http%3A%2F%2Fwww.google.de' }).done(response => {
            this.setState({ embeddedPage: response.entity })
        })*/
    }
    render() {
        return (
            <iframe src="/api/rest/embeddedpage?url=http%3A%2F%2Fwww.google.de" width="100%" height="500" sandbox="allow-same-origin allow-scripts">
            </iframe>
        )
    }
}