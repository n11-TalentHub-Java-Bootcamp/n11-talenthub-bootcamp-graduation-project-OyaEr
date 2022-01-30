import React from 'react'; 
import serialize from 'form-serialize';
import FormArea from '../components/FormArea/FormArea';
import InformArea from '../components/InformArea/InformArea';

class CreditResultsPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isFormSubmit: false,
      message: '',
      response: 'danger',
      type: 'credit-application-result',
      results: []
    }
  }

  handleFormSubmit = (e) => {
    e.preventDefault();

    const customerInfo = serialize(e.target, { hash: true })

    const uri = "http://localhost:8080/api/v1/creditAppInfos";

    const requestOptions = {
      method: "POST",
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
        results: res,
        type: 'credit-application-result',
      });
      
    })
    .catch(error => {
      console.log(error);
      this.setState({
        type: 'customer-result',
        response: 'danger',
      });
    });
    //console.log(customerInfo);
    this.setState({
      isFormSubmit: true
    });
  }

  render() {
    return (
      <>
      {
        this.state.isFormSubmit ?  <InformArea type={this.state.type} variant={this.state.response} data={this.state.results} />  : <FormArea onSubmit={this.handleFormSubmit} type="only-idNum-birthDate" />
      }
      </>
    );
  }
}

export default CreditResultsPage;
