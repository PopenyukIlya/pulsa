import React, {useState} from 'react';
import '../App.css';
import {Button, Form,Card,Alert} from 'react-bootstrap';
import axios from 'axios';

function Register() {
    const [username,setUsername]=useState('');
    const [password,setPassword]=useState('');
    const [error,setError]=useState('');
    const [congratulations,setCongratulations]=useState('');

    function doRegister(){
        if (username === '' || password === '') {
            setError('Fill in all the fields.');
        } else {
            axios.post("http://localhost:8080/api/auth/signup",
                {username,password},
                {withCredentials:true})
                .then(result => {
                    if (result.status === 200 && result.data.success) {
                        setUsername(username);
                        setCongratulations('Congratulations')
                    }else  {
                        setError(result.data.msg);
                        console.log(result.data)
                    }
                }).catch(e => {
                    setError('Some error');
                    console.log(e)
                });
        }
    }

    return (
        <div className="App">
            {error.length !== 0 && <Alert variant={'danger'}>{error}</Alert>}
            {congratulations.length !== 0 && <Alert variant={'success'}>{congratulations}</Alert>}
           <Card className="w-50 text-center mx-auto mt-4 p-3">
               <Form>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text"
                                  placeholder="Username"
                                  value={username}
                                  onChange={e=>{setUsername(e.target.value)}}/>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        value={password}
                    onChange={e=>{setPassword(e.target.value)}}/>
                </Form.Group>
                <Button size="lg" variant="primary" onClick={doRegister}>
                    Sign up
                </Button>
            </Form>
           </Card>
        </div>
    );
}

export default Register;
