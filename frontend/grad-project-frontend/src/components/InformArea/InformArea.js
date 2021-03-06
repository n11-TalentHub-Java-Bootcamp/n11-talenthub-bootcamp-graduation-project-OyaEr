import {Alert} from 'react-bootstrap';
import {Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';

const InformArea = (props) => {
  if(props.type ==  "customer-result"){
    return (
      <Alert variant={props.variant}>
        <Alert.Heading>{ props.variant == 'success' ? "Başarılı!" : "Hata!"}</Alert.Heading>
        <p>
          { props.variant == 'success' ? "Kredi Başvurunuz alındı. Sonuçları görmek için" : "Başvuru sırasında bir hata oldu lütfen aşağıdaki linkten yeniden deneyin"}
        </p>
        <hr />
        <p className="mb-0">
          { props.variant == 'success' ? <Alert.Link href="/results">Başvuru Sonucumu Görüntüle</Alert.Link> : <Alert.Link href="/new-customer">Yeniden Başvuru Yap</Alert.Link> }.
        </p>
      </Alert>
    );
  }
  else if(props.type ==  "credit-application-result"){
    return(
      props.variant == 'success' ?
      <>
        {
          props.data.map((result) =>{
            return(
              <div key = {result}>
                <p>creditStatus: {result.creditStatus} creditLimit: {result.creditLimit} applicationDate: {result.applicationDate}</p>
              </div>
            );
          })
        }
      </> 
      :
      <Alert variant={props.variant}>
        <Alert.Heading>Hata!</Alert.Heading>
        <p>Başvuru sonucunuz görüntülenemiyor lütfen bilgilerinizi kontrol edin.</p>
      </Alert>
    );
  }
}

export default InformArea;