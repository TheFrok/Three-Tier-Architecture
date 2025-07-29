import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import PersonList from './PersonList';
import AddPerson from './AddPerson';

function App() {
  return (
    <Router>
      <div className="container mt-5">
        <h1>Contact Manager</h1>
        <Routes>
          <Route path="/" element={<PersonList />} />
          <Route path="/add" element={<AddPerson />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
