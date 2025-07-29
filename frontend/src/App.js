import React, { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const API_URL = 'http://localhost:8080/persons';

function App() {
  const [persons, setPersons] = useState([]);
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });
  const [sorted, setSorted] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchPersons();
  }, [sorted]);

  const fetchPersons = async () => {
    try {
      const response = await axios.get(API_URL, { params: { sorted } });
      setPersons(response.data);
    } catch (error) {
      console.error("Error fetching persons:", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(API_URL, formData);
      fetchPersons();
      handleClear();
    } catch (error) {
      console.error("Error adding person:", error);
    }
  };

  const handleDelete = async (firstName, lastName) => {
    try {
      await axios.delete(API_URL, { params: { firstName, lastName } });
      fetchPersons();
    } catch (error) {
      console.error("Error deleting person:", error);
    }
  };

  const handleClear = () => {
    setFormData({ firstName: '', lastName: '', email: '' });
  };

  const filteredPersons = persons.filter(person => {
    const searchTermLower = searchTerm.toLowerCase();
    return (
      person.firstName.toLowerCase().includes(searchTermLower) ||
      person.lastName.toLowerCase().includes(searchTermLower) ||
      person.email.toLowerCase().includes(searchTermLower)
    );
  });

  return (
    <div className="container mt-5">
      <h1>Contact Manager</h1>
      <div className="card">
        <div className="card-header">Add Person</div>
        <div className="card-body">
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="firstName" className="form-label">First Name</label>
              <input
                type="text"
                className="form-control"
                id="firstName"
                name="firstName"
                value={formData.firstName}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="lastName" className="form-label">Last Name</label>
              <input
                type="text"
                className="form-control"
                id="lastName"
                name="lastName"
                value={formData.lastName}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary">Add Person</button>
            <button type="button" className="btn btn-secondary ms-2" onClick={handleClear}>Clear</button>
          </form>
        </div>
      </div>

      <div className="card mt-4">
        <div className="card-header d-flex justify-content-between align-items-center">
          Contact List
          <button className="btn btn-secondary" onClick={() => setSorted(!sorted)}>
            {sorted ? 'Unsort' : 'Sort by Name'}
          </button>
        </div>
        <div className="card-body">
          <div className="mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by name or email..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          <table className="table">
            <thead>
              <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredPersons.map((person) => (
                <tr key={person.id}>
                  <td>{person.firstName}</td>
                  <td>{person.lastName}</td>
                  <td>{person.email}</td>
                  <td>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleDelete(person.firstName, person.lastName)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default App;
