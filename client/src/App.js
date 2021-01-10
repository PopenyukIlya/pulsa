import React, {useEffect, useState} from 'react';
import { Route } from 'react-router-dom';
import './App.css';
import Register from "./views/Register";
import Home from "./views/Home";
import Profile from "./views/Profile";
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
  const [ole, setOle] = useState('');
  const [user, setUser] = useState('');

  useEffect(() => {
    if (localStorage.getItem("user")) {
      setOle(localStorage.getItem('user'))
    };
  }, []);

  return (
    <div>
      <div className="Main">
        <AuthContext.Provider value={ {user, setUser} }>
          <NavBar/>
          {ole.length !== 0 && <Alert variant={'info'}>Username: {ole}</Alert>}
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
          <Route exact path="/profile" component={Profile}/>
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
