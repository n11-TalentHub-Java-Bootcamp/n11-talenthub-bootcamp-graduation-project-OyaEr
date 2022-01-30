<div id="top"></div>


<h3 align="center">n11 TalentHub Graduation Project </h3>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#backend-prerequisites">Backend Prerequisites</a></li>
        <li><a href="#frontend-prerequisites">Frontend Prerequisites</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


This project has been prepared as a graduation project for n11 Talenthub Java Spring Boot Bootcamp.
The content of the project is a bank loan application system. It is basically an application that creates customers and returns credit results based on customer information.

The backend of the project was prepared using Java Spring Boot and the frontend was prepared using React.js.PostgreSQL was also used as the database.


<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

This section list any major frameworks/libraries/technologies/databases used in this project.

* [Java Spring Boot](https://spring.io/projects/spring-boot)
* [React.js](https://tr.reactjs.org/)
* [NPM](https://nodejs.org/en/)
* [PostgreSQL](https://www.postgresql.org/)
* [Maven](https://maven.apache.org/)
* [Swagger](https://swagger.io/)
* [Postman](https://www.postman.com/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Visual Studio Code](https://code.visualstudio.com/)


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Backend Prerequisites

Before get start you need to be sure that written technologies below must be downloaded on your system.

* [Java JDK](https://www.oracle.com/java/technologies/downloads/#java11-windows)
* [PostgreSQL](https://www.postgresql.org/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Configuration details use by maven is in [pom.xml](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-OyaEr/blob/main/pom.xml) file. 
The other application properties are listed in [application.properties](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-OyaEr/blob/main/src/main/resources/application.properties) file. If you want to use your local database you must change related fields about PostgreSQL in application.properties.

After all this you can run this project by opening it on IntelliJ IDEA and simply build and run. 
To perform API you can use [Postman Collections](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-OyaEr/tree/main/src/main/resources/postmanCollection) under the directory named resources.
Finally you can access the [swagger-docs](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-OyaEr/blob/main/swagger-docs.json) file here.

### Frontend Prerequisites

Before get start you need to be sure that written technologies below must be downloaded on your system.

* [NPM](https://nodejs.org/en/)
* [Visual Studio Code](https://code.visualstudio.com/)

Run this comment written below in the directory [grad-project-frontend](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-OyaEr/tree/main/frontend/grad-project-frontend).

1.Install NPM packages
   ```sh
   npm install
   ```
2.npm
  ```sh
  npm start
  ```

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Usage
When the user enters the web page, the home screen welcomes the user. There are two options under the "Credit Application" in the navigation bar. By clicking the "New Customer" option, the user can register in the system and apply for a credit. When the user is registered to the system customer information is received.

The credit score service is assumed to be written before according the their income, and the credit score of the relevant person is taken and the credit result and credit limit are shown to the user.

If the user clicks on the "Registered Customer" option, they can update their income, assurance and phone number information and apply for a credit. If the customer has an approved credit application, the system will not approve it again.

After the user clicks on the "Check Credit Results" option, they can view the credit results with their ID number and date of birth. If the date of birth and the ID number do not match, the user will not be able to see the credit results.

Important Note: The user should enter the date of birth as a format of "yyyy-mm-dd".

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Şayegan Oya Erdayı - [@linkedin](https://www.linkedin.com/in/oyaerdayi/) - oyaerdayi@gmail.com

Project Link: [https://github.com/OyaEr/n11-talenthub-bootcamp-graduation-project-OyaEr](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-OyaEr)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Free For Dev](https://free-for.dev/#/)
* [Design Patterns](https://refactoring.guru/design-patterns)
* [Databases](https://towardsdatascience.com/datastore-choices-sql-vs-nosql-database-ebec24d56106)
* [Logging](https://www.sentinelone.com/blog/the-10-commandments-of-logging/)
* [Baeldung.com](https://www.baeldung.com/)

<p align="right">(<a href="#top">back to top</a>)</p>