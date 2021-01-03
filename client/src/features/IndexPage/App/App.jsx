import React, { useState, useEffect } from 'react';
import axios from 'axios';
import classes from './App.module.css'
import { useHistory } from "react-router-dom";
import authHeader from "../../../service/auth-header";
import Button from 'react-bootstrap/Button';
import ReactLoading from 'react-loading';
import CreateTestModal from '../CreateTestModal/CreateTestModal.jsx';

const App = (props) => {
  const [modalIsShown, setModalIsShown] = useState(false);
  const [tests, setTests] = useState(null);
  const [loading, setLoading] = useState(true);
  let history = useHistory();

  useEffect(() => {
    try {
      axios.get("http://localhost:8080/api/quiz",
      {headers: authHeader()}
      ).then(res => {
        if (res.data.error) {
          alert(res.data.error);
        } else {
          setTests(res.data);
          setLoading(false);
        }
      });
    } catch (err) {
      alert(err.message);
      setLoading(false);
    }
  }, [])

  return (
    <div>
      {(!loading) ?
      <div>
        <div className={classes.Wrapper}>
          <h1>IndexPage</h1>
          <CreateTestModal
            modalIsShownHandler={() => setModalIsShown(true)}
            modalIsShownCancelHandler={() => setModalIsShown(false)}
            modalIsShown={modalIsShown}
          />
            {tests.map(test => (
              <div
                className={classes.Card}
                key={test.id}
                onClick={() => history.push("user_test/" + test.id)}
              >
                {test.name}
                <Button
                  className={classes.CreateTest}
                  onClick={event => {
                    event.stopPropagation();
                    history.push("edit_test/" + test.id)}
                  }
                >
                  Edit Test
                </Button>
              </div>
            ))}
        </div>
        <Button onClick={() => setModalIsShown(true)}>Create test</Button>
      </div> :
      <div className={classes.Loading}>
        <ReactLoading type={"spinningBubbles"} color="#000000" />
      </div>}
    </div>
  )
}

export default App;
