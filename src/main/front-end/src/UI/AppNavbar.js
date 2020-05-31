import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

class AppNavbar extends Component {
    state = {isOpen: false};

  toggle = () => {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  render() {
    return <Navbar color="dark" dark expand="md">
      <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
      <NavbarToggler onClick={this.toggle}/>
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="ml-auto" navbar>
          <NavItem>
            <NavLink
              href="https://magicbricks.com">Magicbricks</NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="https://post.magicbricks.com">Post New Property</NavLink>
          </NavItem>
        </Nav>
      </Collapse>
    </Navbar>;
  }
}

export default AppNavbar;