# 📧 Smart AI Email Assistant

**Smart AI Email Assistant** is a professional full-stack application that leverages a **Spring Boot** backend and a **Chrome Extension** to provide context-aware, AI-generated email replies using the **Google Gemini 3 Flash** model.


## ✨ Core Backend Functionalities
* **Asynchronous AI Integration**: Utilizes Spring WebFlux's `WebClient` for high-performance, non-blocking communication with the Gemini 3 Flash API.
* **Intelligent Prompt Engineering**: System-level instructions optimized to return precise, single-reply content without unnecessary metadata.
* **Advanced Tone Modulation**: Backend support for varied response styles: **Professional**, **Friendly**, **Casual**, **Concise**, **Hinglish**, and **Romantic**.
* **Robust JSON Processing**: Implements Jackson `ObjectMapper` for efficient tree-node parsing of complex AI response structures.

## 🛠️ Technical Stack
* **Backend**: Java 21, Spring Boot 3.x, Spring WebFlux.
* **AI Model**: Google Gemini 3 Flash.
* **Frontend Integration**: Vanilla JavaScript (Chrome Manifest V3).
* **Communication**: RESTful API with cross-origin resource sharing (CORS) management.

## 📂 Project Architecture

### Backend Structure
```text
├── email.assistant.src/main/java/com/keshav/email/assistant
│    ├── config
│    │    └── WebClientConfig.java
│    ├── Controller
│    │    └── EmailGeneratorController.java
│    ├── Entity
│    │    └── EmailRequest.java
│    ├── Service
│    │    └── EmailGeneratorService.java
│    └── SmartEmailAssistantApplication.java
└── src/main/resources
     └── application.yml
```
### Frontend Structure
```text
└── EMAIL ASSISTANT EXTENSION
     ├── manifest.json
     ├── content.js
     ├── popup.html
     └── popup.js
```
## ⚙️ Local Setup Instructions

### 1. Backend Configuration

1. **Clone the repository** to your local machine:
   ```bash
   git clone [https://github.com/your-username/smart-email-assistant.git](https://github.com/your-username/smart-email-assistant.git) ```

2. Open the project in IntelliJ IDEA.

3. Locate src/main/resources/application.yml and insert your Gemini API Key:
```yaml
   gemini:
     api:
       key: YOUR_API_KEY_HERE
       url: [https://generativelanguage.googleapis.com](https://generativelanguage.googleapis.com)
 ```
4. Run the application to start the local server on http://localhost:8080.

### 2. Extension Installation

1. Navigate to chrome://extensions/ in your browser.

2. Enable Developer Mode (toggle in the top-right corner).

3. Click Load Unpacked and select the EMAIL ASSISTANT EXTENSION folder from your project directory.

4. Refresh your Gmail tab to initialize the assistant.


## 📖 How to Use

1. Open any email in Gmail and click Reply.

2. Select your desired tone from the dropdown next to the AI Reply button (Options: Hinglish, Romantic, Professional, etc.).

3. Click Smart-Email-Assistant.

4. The assistant will fetch the context, call the Spring Boot API, and automatically populate the reply box with a high-quality response.


## 👨‍💻 Developed By

**Keshav Bansal**
