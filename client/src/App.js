import React, {useEffect, useState} from 'react';
import {Route} from 'react-router-dom';
import './App.css';
import Register from "./views/Register";
import Home from "./views/Home";
import Profile from "./views/Profile";
import Login from "./views/Login";
import Admin from "./views/Admin";
import { AuthContext } from "./context/AuthContext";

import NavBar from "./views/Navbar"
import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert,Button,ButtonGroup,Nav,Navbar} from "react-bootstrap";



function App() {
    const [ole , setOle] = useState('');
    const [user, setUser] = useState('');

    useEffect(() => {
        if (localStorage.getItem("user")) {
            setOle(localStorage.getItem('user'))
        };
    }, []);

  return (

    <div className="App">
      <AuthContext.Provider value={ {user, setUser} }>
        <NavBar/>
        {ole.length !== 0 && <Alert variant={'info'}>Username: {ole}</Alert>}
        <Route exact path="/" component={Home}/>
        <Route exact path="/profile" component={Profile}/>
        <Route exact path="/admin" component={Admin}/>
        <Route exact path="/signin" component={Login}/>
        <Route exact path="/signup" component={Register}/>
      </AuthContext.Provider>
    </div>
  );
}

export default App;
