# Spring-AI-projects-
# ⚡ AI Chat Service - Spring Boot API

A powerful AI-based backend service built with **Spring Boot** that provides streaming and static chat responses, cricket-related bot replies, and AI-generated images based on descriptions.

---

## 🚀 Features

- 🔄 **Stream AI Chat Responses** using WebFlux (`Flux`)
- 💬 **Standard Chat Responses** via REST
- 🏏 **Cricket Bot Integration** with intelligent contextual replies
- 🖼️ **AI-Generated Images** via OpenAI/DALL·E-style backend
- 📦 Clean and Modular REST Controllers
- ⚙️ Extensible Service Layer (`chatService`)

---

## 🧩 API Endpoints

### 1. 🔄 Stream Chat Response
**Description:**  
Returns an asynchronous **stream** (`Flux<String>`) of AI-generated chat messages based on the input.

---

### 2. 💬 Simple Chat Response
**Description:**  
Returns a simple string reply for the given input. Lightweight and suitable for quick responses.

---

### 3. 🏏 Cricket Bot Response
**Description:**  
Generates a rich cricket-specific response using the `CricketResponse` model.

**Response Format (JSON):**
```json
{
  "response": "India won the match by 7 wickets...",
  "source": "ESPN Cricinfo API",
  "timestamp": "2025-07-22T10:15:00Z"
}
GET /images?imageDescription=sunset%20on%20mars&size=512x512&numberofimages=3

src/
├── controller/
│   ├── ChatController.java
├── service/
│   ├── ChatService.java
│   ├── CricketResponse.java
├── config/
│   └── WebConfig.java
└── main/
    └── Application.java

git clone https://github.com/yourusername/ai-chat-springboot.git
cd ai-chat-springboot
mvn clean install
mvn spring-boot:run
