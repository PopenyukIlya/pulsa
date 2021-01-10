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
    axios.get("http://localhost:8080/api/quiz",
      {headers: authHeader()}
    ).then(res => {
      if (res.data.error) {
        alert(res.data.error);
      } else {
        setTests(res.data);
        setLoading(false);
      }
    }).catch(err => {
      history.replace('/signin');

    });
  }, [])

  console.log(tests)

  const deleteTest = (event, id) => {
    setLoading(true);
    event.stopPropagation();
    // axios.delete("http://localhost:8080/api/quiz/" + id,
    //   {headers: authHeader()}
    // ).then(res => {

      // if (res.status === 200) {
        tests.splice(tests.findIndex((i) => {
          return i.id === id;
        }), 1);
        setTests(tests)
      // }
      console.log(tests);
      setLoading(false);
    // }).catch(err => console.log(err.message))
  }

  return (
    <div>
      {(!loading) ?
      <div>
        <div className={classes.Wrapper}>
          <h1>Tests</h1>
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
                <span
                  className={classes.CrossTest}
                  onClick={event => deleteTest(event, test.id)}
                />
              </div>
            ))}
            <Button className={classes.Button} onClick={() => setModalIsShown(true)}>Create test</Button>
        </div> 
      </div> :
      <div className={classes.Loading}>
        <ReactLoading type={"spinningBubbles"} color="#000000" />
      </div>}
    </div>
  )
}

export default App;
