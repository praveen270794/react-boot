import React from 'react';
import { Button, ButtonGroup } from 'reactstrap';
import { Link } from 'react-router-dom';

const group = (props)=> {
    const address = `${props.address || ''} ${props.city || ''} ${props.stateOrProvince || ''}`;
      return <tr key={props.id}>
        <td style={{whiteSpace: 'nowrap'}}>{props.name}</td>
        <td>{address}</td>
        <td>{props.events.map(event => {
          return <div key={event.id}>{new Intl.DateTimeFormat('en-US', {
            year: 'numeric',
            month: 'long',
            day: '2-digit'
          }).format(new Date(event.date))}: {event.title}</div>
        })}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/groups/" + props.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={props.remove}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>;
}

export default group;