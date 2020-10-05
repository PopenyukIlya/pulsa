import React, {useState} from 'react';
import '../App.css';
import {Button, Form,Card} from 'react-bootstrap';
import axios from 'axios';

function Register() {
    const [username,setUsername]=useState('');
    const [password,setPassword]=useState('');
    function doRegister(){
        axios.post(")

    }
    return (
        <div className="App">
           <Card className="w-50 text-center mx-auto mt-4 p-3">
               <Form>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
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
                    Register
                </Button>
            </Form>
           </Card>
        </div>
    );
}

export default Register;
