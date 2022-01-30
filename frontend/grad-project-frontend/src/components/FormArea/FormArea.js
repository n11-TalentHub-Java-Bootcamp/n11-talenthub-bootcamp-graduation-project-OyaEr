import serialize from 'form-serialize';
import React from 'react';

const FormArea = (props) => {
  if (props.type == "full") {
    return (
      <div className="container col-md-6 offset-md-3">
        <form className="mt-5" onSubmit={props.onSubmit}>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="inputName">TC Kimlik No</label>
              <input type="text"
                className="form-control"
                name="idNum" />
            </div>
            <div className="form-group">
              <label htmlFor="inputName">Ad Soyad</label>
              <input type="text"
                className="form-control"
                name="fullName" />
            </div>
            <div className="form-group ">
              <label htmlFor="inputRating">Doğum Tarihi</label>
              <input
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
  else if (props.type == "only-idNum-birthDate") {
    return (
      <div className="container col-md-6 offset-md-3">
        <form className="mt-5" onSubmit={props.onSubmit}>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="inputName">TC Kimlik No</label>
              <input type="text"
                className="form-control"
                name="idNum" />
            </div>
            <div className="form-group ">
              <label htmlFor="inputRating">Doğum Tarihi</label>
              <input
                className="form-control "
                name="birthDate" />
            </div>
          </div><hr></hr>
          <input type="submit" className="btn btn-danger btn-block" value="Kredi Sonucumu Getir" />
        </form>
      </div>
    );
  }
  else if (props.type == "update-customer") {
    return (
      <div className="container col-md-6 offset-md-3">
        <form className="mt-5" onSubmit={props.onSubmit}>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="inputName">TC Kimlik No</label>
              <input type="text"
                className="form-control"
                name="idNum" />
            </div>
          </div><hr></hr>
          <p>Güncellenecek Bilgiler</p>
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
            </div><hr></hr>
            <input type="submit" className="btn btn-danger btn-block" value="Güncelle ve Başvur" />
        </form>
      </div>
    );
  }
  // else if (props.type == "updatable-fields") {
  //   return(
  //     <div className="container col-md-6 offset-md-3">
  //       <form className="mt-5" onSubmit={props.onSubmit}>
  //         <div className="form-row">
  //           <div className="form-group">
  //             <label htmlFor="inputName">TC Kimlik No</label>
  //             <input type="text"
  //               className="form-control"
  //               name="idNum" />
  //           </div>
  //         </div><hr></hr>
  //         <input type="submit" className="btn btn-danger btn-block" value="Bilgilerimi Güncelle" />
  //       </form>
  //     </div>
  //   );
  // }
  else{
    return(
      <p>Wrong Parameter</p>
    )
  }



}

export default FormArea;