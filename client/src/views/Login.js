import React, {useState} from 'react';
import '../App.css';
import {Button, Form,Card,Alert} from 'react-bootstrap';
import axios from 'axios';

function Login() {
    const [username,setUsername]=useState('');
    const [password,setPassword]=useState('');
    const [error,setError]=useState('');

    function doSignIn(){
        if (username === '' || password === '') {
            setError('Fill in all the fields.');
        } else {
            axios.post("http://localhost:8080/api/auth/signin",
                {username,password},
                {withCredentials:true})
                .then(response => { console.log(response.data)
                    if (response.status === 200 && response.data.resp.success) {
                        localStorage.setItem("user", JSON.stringify(response.data.accessToken));
                        setUsername(username);
                    }else  {
                        setError(response.data.resp.msg);
                        console.log(response.data)
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
                    <Button size="lg" variant="primary" onClick={doSignIn}>
                        Sign in
                    </Button>
                </Form>
            </Card>
        </div>
    );
}

export default Login;
