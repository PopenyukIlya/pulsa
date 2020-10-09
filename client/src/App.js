import React, {useEffect, useState} from 'react';
import {Route} from 'react-router-dom';
import './App.css';
import Register from "./views/Register";
import Home from "./views/Home";
import Profile from "./views/Profile";
import Login from "./views/Login";
import logout from "./service/auth";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert,Button,ButtonGroup,Nav,Navbar} from "react-bootstrap";



function App() {
    const [ole,setOle] = useState('');

    useEffect(() => {
        if (localStorage.getItem("user")) {
            setOle(localStorage.getItem('user'))
        };
    }, []);

  return (

    <div className="App">
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
            <Navbar.Brand href="/">Pulse</Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link href="/">Main</Nav.Link>
                    <Nav.Link href="/profile">Profile</Nav.Link>
                </Nav>
            </Navbar.Collapse>
            <ButtonGroup aria-label="Basic example">
                <Button href="/signin" className="mr-2" variant="info">Login</Button>
                <Button href="/signup" variant="light">Register</Button>
                <Button onClick={logout} variant="light">Logout</Button>
            </ButtonGroup>
        </Navbar>
        {ole.length !== 0 && <Alert variant={'info'}>Username: {ole}</Alert>}
        <Route exact path="/" component={Home}/>
        <Route exact path="/profile" component={Profile}/>
        <Route exact path="/signin" component={Login}/>
        <Route exact path="/signup" component={Register}/>
    </div>
  );
}

export default App;
