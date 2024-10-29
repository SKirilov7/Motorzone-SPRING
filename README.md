The application is a monolithic REST API developed with Spring Boot, designed specifically for managing vehicle listings. It provides a secure platform for creating, updating, and searching through listings, utilizing best practices in security, caching, data handling, and asynchronous processing.

Security:
The application employs JWT (JSON Web Token) for authentication and authorization, ensuring secure access to endpoints. This JWT-based security framework safeguards user data, enabling token exchange for access control.

Data Caching:
To optimize performance, the application implements caching for rarely updated data. Only administrators have permission to update these cached items, and upon any update, the cache is cleared and regenerated. Otherwise, data is served directly from the existing cache, significantly enhancing response times.

Database and Data Access:
The application relies on a MySQL database to store all relevant data, including vehicle listings and user information. For data access, Spring Data JPA is used extensively, with a focus on optimizing data retrieval processes. Custom, hand-written queries are often employed to ensure optimal performance for complex data retrieval, reducing load and enhancing efficiency when interacting with the database.

Architecture and Kafka Integration:
Following a monolithic architecture, the application also includes a dedicated module to demonstrate the use of Kafka for event-driven communication. The modules share a common parent POM, with Kafka implemented to handle scheduled expiration events. A scheduler runs every five minutes to check for any listings that expired within the previous five minutes. For any expired listings, an event is sent through Kafka to a consumer module, which in turn sends a notification email to the respective user, informing them of their listing’s expiration.

Search Functionality with Specifications:
The API utilizes Spring Data JPA Specifications for advanced search capabilities, allowing clients to add various query parameters directly within the URL. This dynamic querying approach provides flexibility and power, enabling users to search listings based on multiple, customizable fields.

Image Upload with Cloudinary Integration:
Image handling is optimized through asynchronous uploading to Cloudinary, allowing up to 10 images to be uploaded concurrently. This asynchronous approach reduces overall request processing time and enhances user experience by streamlining the upload process.

Data Validation and Layered Architecture:
Data input follows strict validation protocols, ensuring that DTOs (Data Transfer Objects) received at the API level adhere to specified formats and constraints. The service layer handles all business logic, including database inserts and updates, with the API layer returning only the DTO responses back to the client, rather than direct entity objects. This structure maintains data encapsulation and improves clarity and maintainability.

Entity Classes and Projections:
Entity classes are thoroughly validated, while projections are employed for specific data retrieval operations, optimizing performance by fetching only necessary fields from the database.

Global Exception Handling:
A Global Exception Handler manages errors thrown in the service layer, providing consistent and informative error responses. This standardized error handling mechanism enhances the API’s robustness and improves client-side error management.
