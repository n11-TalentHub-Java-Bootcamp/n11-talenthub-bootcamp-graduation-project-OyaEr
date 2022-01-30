import './App.css';
import React from 'react'; 
import MenuBar from './components/MenuBar/MenuBar';
import {Route, Routes} from "react-router-dom";
import HomePage from './pages/HomePage';
import CreditResultsPage from './pages/CreditResultsPage';
import NewCustomerPage from './pages/NewCustomerPage';
import UpdateCustomerPage from './pages/UpdateCustomerPage';
import InfoPage from './pages/InfoPage';
//import background from './assets/background.png';

class App extends React.Component {
  render(){
  return (
    <div className="App">
      <MenuBar/>
      <Routes>
        <Route path="/" element={<HomePage></HomePage>}/>
        <Route path="/results" element={<CreditResultsPage></CreditResultsPage>}/>
        <Route path="/new-customer" element={<NewCustomerPage></NewCustomerPage>}/>
        <Route path="/update-customer" element={<UpdateCustomerPage></UpdateCustomerPage>}/>
        <Route path="/sent-info" element={<InfoPage></InfoPage>}/>
      </Routes>
    </div>
  );}
}

export default App;
