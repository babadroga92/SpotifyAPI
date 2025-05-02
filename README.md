# 🎧 Spotify API Testing Framework

This project focuses on **automated testing of the Spotify Web API**, specifically for Playlist endpoints. It leverages modern Java tools and frameworks to ensure clean architecture, reusability, and comprehensive test coverage.

🔗 [Spotify Web API Documentation](https://developer.spotify.com/documentation/web-api)

---

## ✅ Features

### ✔️ Implemented Test Cases
- Create Playlist
- Fetch Playlist
- Update Playlist
- Negative Test Scenarios for All of the Above

---

## 🛠️ Technologies Used

- **Rest Assured** – For HTTP requests and response validation
- **TestNG** – Test execution and suite organization
- **Allure Reports** – For detailed, interactive test reporting
- **Hamcrest** – Cleaner and more expressive assertions
- **Jackson API** – JSON (de)serialization
- **Lombok** – Reduces boilerplate (getters, setters, constructors)

---

## 💡 Design Highlights

- **Singleton Pattern** – Ensures one instance of API requests across tests
- **Layered Architecture** – Clear separation of test logic and API interactions
- **Reusable Rest Assured Requests** – Cleaner and more maintainable API usage
- **Auto Access Token Renewal** – Automatically handles Spotify authentication

---

## 🗂️ Project Structure

📦 src
┣ 📂 main
┃ ┗ 📂 java
┃ ┗ 📂 api → API logic and request classes
┣ 📂 test
┃ ┣ 📂 java
┃ ┃ ┗ 📂 tests → All test cases
┃ ┗ 📂 resources
┃ ┗ 📄 config.properties → Configuration file
┗ 📄 pom.xml


---

## ⚙️ Setup Instructions

To run tests locally:

1. Clone the repo
2. Add your credentials to `src/test/resources/config.properties`:

```properties
client_id=your_client_id
client_secret=your_client_secret
refresh_token=your_refresh_token
grant_type=refresh_token
user_id=your_user_id


