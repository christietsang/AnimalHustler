<div id="top"></div>

[![Contributors][contributors-shield]][contributors-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/christietsang/2522-Term-Project-ChristieBelal">
    <img src="images/cow.gif" alt="sunglassesCow" width="180" height="180">
  </a>

  <h1 align="center">Animal Hustler</h1>

  <p align="center">
    A game where you feed as many cows as possible before the timer runs out!
  </p>
</div>

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
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<p align="center">
  <a>
  <img src="images/mainmenu.png" alt="main-menu" width="350" height="300">
  </a>
</p>


Your parents have left town and have left you in charge of running the family farm for three months.
The good news is that you keep the profits of whatever you earn!  Can you make enough tuition and pocket money to attend school in the fall?
Move your avatar around the map using W,A,S,D controls and run into a cow to feed it.  The faster you feed it, the more coins you get!  Beware of nasty surprises if you don't feed a cow in time.  As the game progresses, cows will spawn faster and faster.  Try and feed them all!


<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [JavaFX](https://openjfx.io/)
* [FXGL](https://github.com/AlmasB/FXGL/wiki/FXGL-11)
* [MySQL](https://www.mysql.com/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* Java 8-17, Win/Mac/Linux/Android 8+/iOS 11.0+/Web
* [MySQL Server](https://dev.mysql.com/downloads/)
* Any MySQL database installed on localhost
* [JDBC Driver for MySQL (Connector/J)](https://www.mysql.com/products/connector/)



### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/christietsang/2522-Term-Project-ChristieBelal.git
   ```
2. Right click on the Java folder and mark the directory as "Sources Root".


3. Right click on the pom.xml file and select "Add as a Maven Project".

4. [Install the requisite MySQL software and install the JDBC driver](https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-installing.html)

5. In DatabaseHandler.java, replace "root", and "admin" with your own username and password for your local MySQL database.


<p align="right">(<a href="#top">back to top</a>)</p>


<!-- ROADMAP -->
## Roadmap

- [X] Create map & corresponding bounding boxes
- [X] Establish asset library
- [X] Create logic for character control, animation, and animal spawn
- [X] Create a main menu with custom login
- [x] Establish in-game timer & currency system
- [x] Progressively up in-game character speed and cow spawn time


<p align="right">(<a href="#top">back to top</a>)</p>


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Christie Tsang - [@tsang_christie](https://twitter.com/tsang_christie) - [LinkedIn](https://www.linkedin.com/in/christietsang/)

Belal Kourkmas - [LinkedIn](https://www.linkedin.com/in/belal-kourkmas/)

Project Link: [https://github.com/christietsang/2522-Term-Project-ChristieBelal](https://github.com/christietsang/2522-Term-Project-ChristieBelal)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Markdown Guide](https://www.markdownguide.org/basic-syntax/#reference-style-links)
* [FXGL Library](https://github.com/AlmasB/FXGL)
* [Examples of Existing FXGL Games](https://github.com/AlmasB/FXGLGames)
* [FXGL Wiki](https://github.com/AlmasB/FXGL/wiki/FXGL-11)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/christietsang/2522-Term-Project-ChristieBelal.svg?style=for-the-badge
[contributors-url]: https://github.com/christietsang/2522-Term-Project-ChristieBelal/graphs/contributors
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/christietsang/2522-Term-Project-ChristieBelal/blob/main/LICENSE
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/belalk/
[product-screenshot]: images/mainmenu.png
