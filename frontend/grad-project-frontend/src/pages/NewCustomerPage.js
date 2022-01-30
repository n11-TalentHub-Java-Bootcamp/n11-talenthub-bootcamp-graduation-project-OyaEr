import React from 'react'; 
import serialize from 'form-serialize';
import {Navigate } from "react-router-dom";
class NewCustomerPage extends React.Component {

  handleFormSubmit = (e) =>{
    e.preventDefault();
  
    const newCustomer = serialize(e.target, {hash:true})

    const uri = "http://localhost:8080/api/v1/customers";

    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newCustomer)
    }

    fetch(uri,requestOptions)
      .then(response => {
        if (response.ok) {
          return <Navigate to={"./sent-info"}/>
        }
        return response.json()
      }).then( res =>  {
        console.log(res);
        console.log(res.status);
      })
      .catch(error => console.log(error));
  }



  render(){
    return (
      <div className="container col-md-6 offset-md-3">
        <form className="mt-5" onSubmit={this.handleFormSubmit}>
          <div className="form-row">
              <div className="form-group">
                  <label htmlFor="inputName">TC Kimlik No</label>
                  <input type="text"
                      // value={this.state.idNum}
                      className="form-control"
                      name="idNum" />
              </div>
              <div className="form-group">
                  <label htmlFor="inputName">Ad Soyad</label>
                  <input type="text"
                      // value={this.state.fullName}
                      className="form-control"
                      name="fullName" />
              </div>
              <div className="form-group ">
                  <label htmlFor="inputRating">Doğum Tarihi</label>
                  <input
                    // value={this.state.birthDate}
                    className="form-control "
                    name="birthDate" />
              </div>
              <div className="form-group ">
                  <label htmlFor="inputRating">Telefon</label>
                  <input
                    className="form-control "
                    name="phoneNum" />
              </div>
              <div className="form-group ">
                  <label htmlFor="inputRating">Aylık Gelir</label>
                  <input
                    className="form-control "
                    name="income" />
              </div>
              <div className="form-group ">
                  <label htmlFor="inputRating">Teminat</label>
                  <input
                    className="form-control "
                    name="assurance" />
              </div>
          </div><hr></hr>

            <input type="submit" className="btn btn-danger btn-block" value="Müşteri Ol ve Başvur" />
        </form>
      </div>
    );
  }
}

export default NewCustomerPage;