import React, {useState} from 'react';
import '../App.css';
import authHeader from "../service/auth-header";
import axios from 'axios';

function Admin() {
    let result=function () {
        axios.get("http://localhost:8080/api/test/admin",
            {headers: authHeader()}) .then(result => {console.log(result)
        })};
    return (
        <div className="App">

            <h1>Profile</h1>
            <h3>{result()}</h3>
        </div>
    );
}

export default Admin;
