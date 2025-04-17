Spotify API Testing Framework

This project focuses on testing the Spotify Web API, specifically for the Playlist endpoints. It utilizes a variety of tools and frameworks to ensure comprehensive testing and seamless integration.
Link to Spotify documentation --> Link tohttps://developer.spotify.com/documentation/web-api  

Features
Implemented Tests:

  Create Playlist
  Fetch Playlist
  Update Playlist
  Negative Tests for each of the above

Technologies Used:

  Rest Assured: For HTTP requests and response validation
  TestNG: For running tests with test suite organization
  Allure Reports: To generate detailed, interactive test reports
  Hamcrest: For better assertion syntax
  Jackson API: For JSON serialization and deserialization
  Lombok: To reduce boilerplate code

Singleton Design Pattern: Ensures a single instance of API requests
Separation of Layers: Clean architecture separating the API layer from the test layer
Reusable Rest Assured Requests: Simplified API interaction for tests
Automated Access Token Renewal: Ensures the API remains authenticated during tests

Project Structure
  API Layer: Contains the logic for interacting with the Spotify API
  Test Layer: Contains all the test cases and execution scripts
  POJOs: Plain Old Java Objects (POJOs) for serialization and deserialization of request/response data

Setup
To run the tests locally, you need to configure your credentials, and add the config.properties file under /src/test/resources with the following values:
  client_id={your_client_id}
  client_secret={your_client_secret}
  refresh_token={your_refresh_token}
  grant_type=refresh_token
  user_id={your_user_id}
