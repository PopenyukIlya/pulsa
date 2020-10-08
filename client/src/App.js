import React from 'react';
import {Route} from 'react-router-dom';
import './App.css';
import Register from "./views/Register";
import Home from "./views/Home";
import Login from "./views/Login";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from 'react-bootstrap/Navbar'


function App() {
  return (
    <div className="App">
  
     <Route exact path="/" component={Home}/>
        <Route exact path="/signin" component={Login}/>
        <Route exact path="/signup" component={Register}/>
    </div>
  );
}

export default App;
