import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert,Button,ButtonGroup,Nav,Navbar} from "react-bootstrap";
import { Redirect } from 'react-router-dom';
import { useAuth } from "../context/AuthContext";

function NavBar() {

    const { user, setUser } = useAuth();
    const [store, setStore] = useState(getStore());

    function getStore() {
        if ('user' in localStorage) {
            return JSON.parse(localStorage.getItem('user'))
        } else {
            return false
        }
    }
    
    useEffect(() => {
        setStore(getStore())
    }, [user])

    function logout() {
        localStorage.removeItem("user");
        setStore(false)
    }

    return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
        <Navbar.Brand href="/">Pulse</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">
                <Nav.Link href="/">Main</Nav.Link>
               
                { store ?
                    JSON.parse(localStorage.getItem("user")).roles.includes("USER") ? <Nav.Link href="/profile">Profile</Nav.Link> : null
                    :
                    null
                }   
                  { store ?
                    JSON.parse(localStorage.getItem("user")).roles.includes("ADMIN") ? <Nav.Link href="/profile">Profile</Nav.Link> : null
                    :
                    null
                }
                
                { store ?
                    JSON.parse(localStorage.getItem("user")).roles.includes("ADMIN") ? <Nav.Link href="/admin">Admin</Nav.Link> : null
                    :
                    null
                }       
            </Nav>
        </Navbar.Collapse>
        <ButtonGroup aria-label="Basic example">
            {store ? null :<Button href="/signin" className="mr-2" variant="info">Login</Button>}
            {store ? null : <Button href="/signup" variant="light">Register</Button>}
            {store ? <Button onClick={logout} variant="light">Logout</Button> : null}
        </ButtonGroup>
        </Navbar>
    )
}

export default NavBar;