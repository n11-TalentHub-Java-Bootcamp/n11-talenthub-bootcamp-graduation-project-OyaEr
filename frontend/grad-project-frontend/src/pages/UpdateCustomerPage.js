import React from 'react'; 
import serialize from 'form-serialize';
import FormArea from '../components/FormArea/FormArea';
import InformArea from '../components/InformArea/InformArea';

class UpdateCustomerPage extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      isFormSubmit: false,
      response: 'success',
    }
  }  

  handleFormSubmit = (e) =>{
    e.preventDefault();
  
    const customerInfo = serialize(e.target, {hash:true})

    const uri = "http://localhost:8080/api/v1/customers/"+customerInfo.idNum;

    const requestOptions = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(customerInfo)
    }

    fetch(uri,requestOptions)
    .then(response => {
      return response.json();
    }).then( res =>  {
      console.log(res);
      //status bak
      this.setState({
        response: 'success',
      });
      
    })
    .catch(error => {
      console.log(error);
      this.setState({
        response: 'danger',
      });
    });
    //console.log(customerInfo);
    this.setState({
      isFormSubmit: true
    });
    
  }

  render(){
    return (
      <>
      {
        this.state.isFormSubmit ?  <InformArea type="customer-result" variant={this.state.response} />  : <FormArea onSubmit={this.handleFormSubmit} type="update-customer" />
      }
      </>
    );
  }
}

export default UpdateCustomerPage;