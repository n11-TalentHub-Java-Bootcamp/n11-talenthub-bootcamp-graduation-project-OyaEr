import {Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';

const MenuBar = (props) =>{
  return(
<Navbar bg="light" expand="lg">
<Container>
    <Navbar.Brand href="/">N11 Talenthub Graduation Project</Navbar.Brand>
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
            <Nav.Link href="/results">Kredi Sonuç Sorgula</Nav.Link>
              <NavDropdown title="Kredi Başvurusu" id="basic-nav-dropdown">
                <NavDropdown.Item href="/new-customer">Yeni Müşteri</NavDropdown.Item>
                <NavDropdown.Item href="/update-customer">Mevcut Müşteri</NavDropdown.Item>
              </NavDropdown>
        </Nav>
    </Navbar.Collapse>
</Container>
</Navbar>
  )
}

export default MenuBar;