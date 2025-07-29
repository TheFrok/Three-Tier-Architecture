import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const API_URL = 'http://localhost:8080/persons';

function PersonList() {
  const [persons, setPersons] = useState([]);
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

  const handleDelete = async (id) => {
    try {
      await axios.delete(`${API_URL}/${id}`);
      fetchPersons();
    } catch (error) {
      console.error("Error deleting person:", error);
    }
  };

  const filteredPersons = persons.filter(person => {
    const searchTermLower = searchTerm.toLowerCase();
    return Object.values(person).some(value =>
      String(value).toLowerCase().includes(searchTermLower)
    );
  });

  return (
    <div className="card mt-4">
      <div className="card-header d-flex justify-content-between align-items-center">
        Contact List
        <div>
          <Link to="/add" className="btn btn-primary me-2">Add Person</Link>
          <button className="btn btn-secondary" onClick={() => setSorted(!sorted)}>
            {sorted ? 'Unsort' : 'Sort by Name'}
          </button>
        </div>
      </div>
      <div className="card-body">
        <div className="mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="table-responsive">
          <table className="table">
            <thead>
              <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Date of Birth</th>
                <th>Age</th>
                <th>Country</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredPersons.map((person) => (
                <tr key={person.id}>
                  <td>{person.firstName} {person.lastName}</td>
                  <td>{person.email}</td>
                  <td>{person.phoneNumber}</td>
                  <td>{person.address1}, {person.address2}, {person.city}, {person.state} {person.zipCode}</td>
                  <td>{person.dateOfBirth}</td>
                  <td>{person.age}</td>
                  <td>{person.country}</td>
                  <td>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleDelete(person.id)}
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

export default PersonList;
