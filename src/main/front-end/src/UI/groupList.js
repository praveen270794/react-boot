import React, { Component } from 'react';
import { Button, Container, Table } from 'reactstrap';
import axios from 'axios';
import Group from '../Componant/group';
import { Link, withRouter } from 'react-router-dom';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
class GroupList extends Component {

    constructor(props) {
      super(props);
      const {cookies} = props;
      this.state = {groups: [], csrfToken: cookies.get('XSRF-TOKEN'), isLoading: true};
      this.remove = this.remove.bind(this);
    }

    static propTypes = {
      cookies: instanceOf(Cookies).isRequired
    };

    componentDidMount(){
        axios.get(`http://localhost:8080/api/groups`,{credentials: 'include'})
          .then(res => {
            console.log(res.data);
            this.setState({ groups:res.data , isLoading: false });
          })
          .catch(() => this.props.history.push('/'));
      }

   remove(id) {

    axios.delete(`http://localhost:8080/api/group/${id}`,
          {
            headers: {
              'X-XSRF-TOKEN': this.state.csrfToken,
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            credentials: 'include'
          })
          .then(res => {
            console.log(res.data);
            let updatedGroups = [...this.state.groups].filter(i => i.id !== id);
            this.setState({groups: updatedGroups});
          })
          .catch(err=>console.error(err));
  }

  render() {
    const {groups, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const groupList = groups.map(group => <Group key={group.id} 
        address={group.address}
        name={group.name}
        city={group.city}
        stateOrProvince={group.stateOrProvince}
        id={group.id}
        events={group.events}
        remove={() => this.remove(group.id)}/>);
        
      

    return (
      <div>
       
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/groups/new">Add Group</Button>
          </div>
          <h3>My Events Gruop Tour</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="20%">Location</th>
              <th>Events</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {groupList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default withCookies(withRouter(GroupList));