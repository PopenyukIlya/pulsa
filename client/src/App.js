import React, {useEffect, useState} from 'react';
import { Route } from 'react-router-dom';
import './App.css';
import Register from "./views/Register";
import Home from "./views/Home";
import Results from "./features/Results";
import Login from "./views/Login";
import Admin from "./views/Admin";
import NotFound from './features/NotFound';
import UserTest from './features/UserTest';
import TestResults from './features/TestResults';
import EditTest from './features/EditTest';
import IndexPage from './features/IndexPage';

import NavBar from "./views/Navbar"
import 'bootstrap/dist/css/bootstrap.min.css';
import { Alert, Button, ButtonGroup, Nav, Navbar } from "react-bootstrap";
import { AuthContext } from "./context/AuthContext";
import Trees from './features/svg/Trees/Trees';

const App = () => {
  const [user, setUser] = useState('');

  return (
    <div>
      <div className="Main">
        <AuthContext.Provider value={ {user, setUser} }>
          <NavBar/>
          <Route path="/" exact>
            <IndexPage />
          </Route>
          <Route path="/user_test/:id">
            <UserTest />
          </Route>
          <Route path="/test_results/:id">
            <TestResults />
          </Route>
          <Route path="/edit_test/:id">
            <EditTest />
          </Route>
          <Route exact path="/results" component={Results}/>
          <Route exact path="/admin" component={Admin}/>
          <Route exact path="/signin" component={Login}/>
          <Route exact path="/signup" component={Register}/>
        </AuthContext.Provider>
      </div>
      <Trees />
    </div>
  );
}

export default App;
