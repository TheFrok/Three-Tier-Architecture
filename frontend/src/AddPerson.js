import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const API_URL = 'http://localhost:8080/persons';

const initialFormData = {
  firstName: '',
  lastName: '',
  email: '',
  dateOfBirth: '',
  age: '',
  phoneNumber: '',
  address1: '',
  address2: '',
  city: '',
  zipCode: '',
  state: '',
  country: ''
};

function AddPerson() {
  const [formData, setFormData] = useState(initialFormData);
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(API_URL, formData);
      navigate('/');
    } catch (error) {
      console.error("Error adding person:", error);
    }
  };

  const handleClear = () => {
    setFormData(initialFormData);
  };

  return (
    <div className="card">
      <div className="card-header">Add Person</div>
      <div className="card-body">
        <form onSubmit={handleSubmit}>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label htmlFor="firstName" className="form-label">First Name</label>
              <input type="text" className="form-control" id="firstName" name="firstName" value={formData.firstName} onChange={handleInputChange} required />
            </div>
            <div className="col-md-6 mb-3">
              <label htmlFor="lastName" className="form-label">Last Name</label>
              <input type="text" className="form-control" id="lastName" name="lastName" value={formData.lastName} onChange={handleInputChange} required />
            </div>
          </div>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input type="email" className="form-control" id="email" name="email" value={formData.email} onChange={handleInputChange} required />
            </div>
            <div className="col-md-6 mb-3">
              <label htmlFor="phoneNumber" className="form-label">Phone Number</label>
              <input type="text" className="form-control" id="phoneNumber" name="phoneNumber" value={formData.phoneNumber} onChange={handleInputChange} />
            </div>
          </div>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label htmlFor="dateOfBirth" className="form-label">Date of Birth</label>
              <input type="date" className="form-control" id="dateOfBirth" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleInputChange} />
            </div>
            <div className="col-md-6 mb-3">
              <label htmlFor="age" className="form-label">Age</label>
              <input type="number" className="form-control" id="age" name="age" value={formData.age} onChange={handleInputChange} />
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="address1" className="form-label">Address 1</label>
            <input type="text" className="form-control" id="address1" name="address1" value={formData.address1} onChange={handleInputChange} />
          </div>
          <div className="mb-3">
            <label htmlFor="address2" className="form-label">Address 2</label>
            <input type="text" className="form-control" id="address2" name="address2" value={formData.address2} onChange={handleInputChange} />
          </div>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label htmlFor="city" className="form-label">City</label>
              <input type="text" className="form-control" id="city" name="city" value={formData.city} onChange={handleInputChange} />
            </div>
            <div className="col-md-6 mb-3">
              <label htmlFor="state" className="form-label">State</label>
              <input type="text" className="form-control" id="state" name="state" value={formData.state} onChange={handleInputChange} />
            </div>
          </div>
          <div className="row">
            <div className="col-md-6 mb-3">
              <label htmlFor="zipCode" className="form-label">Zip Code</label>
              <input type="text" className="form-control" id="zipCode" name="zipCode" value={formData.zipCode} onChange={handleInputChange} />
            </div>
            <div className="col-md-6 mb-3">
              <label htmlFor="country" className="form-label">Country</label>
              <input type="text" className="form-control" id="country" name="country" value={formData.country} onChange={handleInputChange} />
            </div>
          </div>
          <button type="submit" className="btn btn-primary">Add Person</button>
          <button type="button" className="btn btn-secondary ms-2" onClick={handleClear}>Clear</button>
        </form>
      </div>
    </div>
  );
}

export default AddPerson;
