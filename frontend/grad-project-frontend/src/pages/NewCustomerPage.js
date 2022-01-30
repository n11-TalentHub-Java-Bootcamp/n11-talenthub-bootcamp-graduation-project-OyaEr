import React from 'react'; 
import serialize from 'form-serialize';
import FormArea from '../components/FormArea/FormArea';
import InformArea from '../components/InformArea/InformArea';

class NewCustomerPage extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      isFormSubmit: false,
      response: 'success',
      message: ''
    }
  }  

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
          this.setState({
            response: 'success',
            isFormSubmit: true
          });
        }
        return response.json();
      }).then( res =>  {
        console.log(res);
        if(res.message){
          this.setState({
            response: 'danger',
            isFormSubmit: true,
            message: res.message
          }); 
        }
      })
      .catch(error => {
        console.log(error);
        this.setState({
          response: 'danger',
          isFormSubmit: true
        });
      });
  }

  render(){
    return (
      <>
      {
        this.state.isFormSubmit ?  <InformArea type="customer-result" variant={this.state.response} />  : <FormArea onSubmit={this.handleFormSubmit} type="full" />
      }
      </>
    );
  }
}

export default NewCustomerPage;