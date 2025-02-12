# Coworking - Backend

## Описание

Coworking — это приложение для управления бронированием столиков в каворкингах. 
Оно позволяет пользователям (customer) выбирать и арендовать столики на определенное 
время, а также управлять своими бронями. Менеджеры (manager) могут создавать, изменять и 
удалять свои рабочие пространства (workspaces). Интеграция с картой Яндекса.

## Функциональность

- **Роли пользователей**:
    - **Кастомер**: может выбирать столики на карте и арендовать их, управлять своими бронями.
    - **Менеджер**: может создавать, изменять и удалять свои рабочие пространства.

## Технологии

- **Java 19**
- **Spring Boot 3.3.1**
- **PostgreSQL** с использованием **Liquibase** для управления миграциями базы данных
- **Spring Security** для аутентификации и авторизации
- **MapStruct** для маппинга объектов
- **Docker** и **Docker Compose** для контейнеризации приложения
- **JUnit** и **Testcontainers** для тестирования
