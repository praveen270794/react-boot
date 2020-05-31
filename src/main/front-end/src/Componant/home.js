import React, { Component } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import axios from 'axios';
import { withCookies } from 'react-cookie';

class Home extends Component {

    state = {
        isLoading: true,
        isAuthenticated: false,
        user: undefined
      };
    
    constructor(props) {
        super(props);
        const {cookies} = props;
        this.state.csrfToken = cookies.get('XSRF-TOKEN');
        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
    }
    componentDidMount() {
        axios.get('localhost:8080/api/user', {credentials: 'include'})
        .then(res => {
            console.log(res);
            console.log(res.data);
            if(res.data === ""){
                this.setState({isAuthenticated: false});
            }else{
                this.setState({isAuthenticated: true, user : res.data});
            }
        })
        .catch(err=>console.error(err));
    }

    login() {
        let port = (window.location.port ? ':' + window.location.port : '');
        if (port === ':3000') {
          port = ':8080';
        }
        this.setState({isAuthenticated: true})
        //window.location.href = '//' + window.location.hostname + port + '/private';
    }

    logout() {
        axios.post('localhost:8080/api/logout', {method: 'POST', credentials: 'include',
          headers: {'X-XSRF-TOKEN': this.state.csrfToken}})
          .then(response => {
            window.location.href = response.data.logoutUrl + "?id_token_hint=" +
              response.data.idToken + "&post_logout_redirect_uri=" + window.location.origin;
          });
    }

    render() {
        const message = this.state.user ?
        <h2>Welcome, {this.state.user.name}!</h2> :
        <p>Please log in to manage your Event Tour.</p>;

        const button = this.state.isAuthenticated ?
        <div>
            <Button color="link"><Link to="/groups">Manage Events Tour</Link></Button>
            <br/>
            <Button color="link" onClick={this.logout}>Logout</Button>
        </div> :
        <Button color="primary" onClick={this.login}>Login</Button>;

        return (
            <Container fluid>
                {message}
                {button}
            </Container>
        );
    }
}

export default withCookies(Home);