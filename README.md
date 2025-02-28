
# Proiect GlobalWaves  - Etapa 2

Project Description

This project involves the development of a music streaming application similar to Spotify. It simulates various user actions, including those specific to artists and hosts, using input files. The project extends the functionalities implemented in the first stage, maintaining the admin's perspective over users and application elements.



## Screenshots

![while using Spotify🎧](https://www.bing.com/images/search?view=detailV2&ccid=EtWmX1Cy&id=BFD4EAA506613BE0BDE8BA9482DCBB591C8A5A95&thid=OIP.EtWmX1Cy1bgGC6tZKVjrowHaHa&mediaurl=https%3a%2f%2fi.pinimg.com%2f736x%2fe1%2f6a%2fb9%2fe16ab971bf9b33654bfaf7e6d8e10cf0.jpg&cdnurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.12d5a65f50b2d5b8060bab592958eba3%3frik%3dlVqKHFm73IKUug%26pid%3dImgRaw%26r%3d0&exph=720&expw=720&q=Music+Memes&simid=608033753309580487&FORM=IRPRST&ck=A1B732A70DDFE6BA588A928D52866838&selectedIndex=61&itb=0&ajaxhist=0&ajaxserp=0)


## Q&A Section

Q1: What is the primary purpose of this application?
A: This application is designed to simulate a music streaming service similar to Spotify. It features a range of functionalities that mimic user interactions, including actions specific to artists and hosts.

Q2: How does the application handle different user roles?
A: The application includes different types of users like normal users, artists, and hosts. Each user type has specific commands and functionalities. For example, artists can add albums, while hosts can add podcasts.

Q3: Can you explain the 'Page System' in this application?
A: The application features a Page System that simulates a graphical interface. Users can navigate between various pages like HomePage, LikedContentPage (for normal users), ArtistPage (for artists), and HostPage (for hosts), each offering different functionalities.

Q4: How are new features from Stage I integrated into Stage II?
A: All functionalities and commands implemented in Stage I remain valid in Stage II. Stage II extends these functionalities, incorporating new features and user types.

Q5: What functionalities are available on the Artist and Host pages?
A: On the ArtistPage, artists can manage their albums, merchandise, and events. The HostPage allows hosts to manage their podcasts and announcements.


# Key features

- User Types: Includes normal users, artists, and hosts with specific functionalities.

- Page System: Simulates a minimal graphical interface with different pages for users, such as HomePage and LikedContentPage.

- Artist and Host Pages: Individual pages for artists and hosts, allowing them to manage albums, merchandise, and events.

- Command System: Utilizes commands for normal users to navigate between pages and interact with the application.

- Extended Search Bar: Enhanced search functionalities for albums, artists, and hosts.

- Music Player: For normal users to play albums with standard music player functionalities like shuffle and repeat.

- Admin Commands: Include adding and deleting users, and showing albums and podcasts.


## Enhancements:
- User Pages: HomePage shows the top 5 liked songs and followed playlists. LikedContentPage displays all liked songs and followed playlists of a user.
- Artist and Host Pages: Artists can manage their albums and events, while hosts can manage their podcasts and announcements.
Page Commands: Includes ChangePage and PrintCurrentPage for navigating and displaying page content.
- New User Entities: Introduction of artist and host user types.
Audio File Collections: Addition of albums as a collection of songs created by artists.
- General Commands: Includes getTop5Albums, getTop5Artists, getAllUsers, and getOnlineUsers.
##  Modular Architecture

* Singleton Pattern 🙎‍♀️
UserSingleton: This class ensures that there is only one instance of UserSingleton throughout the application. It holds a list of users and provides methods to interact with this list.

😄 Key Elements:
Private Constructor: The constructor is private, preventing instantiation from other classes.

😄 Static Instance: The instance variable holds the single instance of the class. It is initialized to null and is created upon the first call to getInstance().

😄 getInstance Method: This static method creates the UserSingleton instance if it doesn't exist and returns it.

*Visitor Pattern 👯‍♂️

😄 PageVisitor Interface: This interface defines visit methods for different types of pages (like HomePage, LikedContentPage, ArtistPage, HostPage).

😄 Key Elements:
Visitor Interface: The PageVisitor interface defines a visit method for each type of page. Each method takes a different type of page as its parameter.

😄 Accept Method in Pages: Each page class would have an accept method that takes a PageVisitor and calls the visit method corresponding to its class.

😄 The Visitor pattern allows you to add new operations to existing class hierarchies without changing the classes. It's particularly useful when dealing with a collection of different objects with distinct and evolving functionalities.

