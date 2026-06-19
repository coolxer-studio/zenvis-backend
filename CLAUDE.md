# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Package the application (includes ProGuard obfuscation)
mvn clean package

# Package without obfuscation (faster, for development)
mvn clean package -Dproguard.skip=true

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
# Or
java -jar target/application.jar --spring.profiles.active=dev
```

## Project Overview

This is a Spring Boot 3.2.0 web service (Java 17) that provides an intelligent inspection platform with AI capabilities. It integrates with Alibaba's DashScope LLM for AI features including chat, code completion, and intelligent inspection agents.

## Architecture

### Dual Datasource Configuration

The application uses two separate databases with JPA:

- **MySQL** (`com.coolxer.dao.mysql`): Configuration data, user management, sessions, rules
  - Entity packages: `com.coolxer.dao.mysql.entity`
  - Repository packages: `com.coolxer.dao.mysql.repository`
  - Transaction manager: `mysqlTransactionManager`

- **ClickHouse** (`com.coolxer.dao.clickhouse`): Time-series data, events, assets, risks
  - Entity packages: `com.coolxer.dao.clickhouse.entity`
  - Repository packages: `com.coolxer.dao.clickhouse.repository`
  - Transaction manager: `clickHouseTransactionManager` (Primary)

### Layer Structure

```
src/main/java/com/coolxer/
├── controller/          # REST API endpoints
│   ├── business/        # Business domain controllers
│   │   ├── asset/       # Asset management (API, App, File, Host, IoT, Mobile, PC, etc.)
│   │   ├── operation/   # Operational events (ANR, Crash, Network, Performance, etc.)
│   │   └── risk/        # Risk management (Attack, Baseline, Data, Vulnerability, etc.)
│   ├── dih/             # AI/DIH chat and session endpoints
│   ├── retrieval/       # Data retrieval endpoints
│   └── system/          # System management (User, Role, Menu, Dashboard, etc.)
├── service/             # Business logic layer (interface + impl pattern)
│   ├── dih/             # AI services (Chat, CodeComplete, Agent)
│   │   └── agent/       # ReAct Agent for intelligent inspection
│   ├── retrieval/       # Data query and retrieval services
│   └── ...
├── dao/                 # Data access layer
│   ├── mysql/           # MySQL entities and repositories
│   └── clickhouse/      # ClickHouse entities and repositories
├── configuration/       # Spring configurations
│   └── datasouce/       # Dual datasource JPA configurations
├── aop/                 # Cross-cutting concerns (logging, exception handling, auth)
└── commons/             # Shared utilities, enums, exceptions
```

### AI Integration (Spring AI Alibaba)

The application uses Spring AI Alibaba framework for AI capabilities:

- **DashScope Chat Model**: Primary LLM integration for chat and reasoning
- **Vector Store**: Redis-based vector storage for RAG (Retrieval Augmented Generation)
- **Chat Memory**: MySQL-backed conversation history persistence
- **ReAct Agent**: Intelligent inspection agent with tool orchestration

Key AI services:
- `AIChatService`: Streaming chat with RAG support
- `AIAgentService`: ReAct agent for intelligent system inspection
- `AIGeneralCompleteService`: General AI completion tasks

### API Structure

APIs follow the pattern: `/api/v1/{module}/{resource}`

Main API modules:
- `/api/v1/dih/`: AI chat and agent endpoints
- `/api/v1/business/asset/`: Asset management
- `/api/v1/business/operation/`: Operational events
- `/api/v1/business/risk/`: Risk management

## Git Commit Convention

Follow conventional commits format: `<type>: <description>`

Allowed types:
- `feat`: New feature
- `fix`: Bug fix
- `test`: Test-related changes
- `docs`: Documentation changes (including comments)
- `style`: Code formatting (no logic change)
- `refactor`: Code refactoring
- `chore`: Dependencies, config, build scripts

Description requirements:
- Lowercase first letter, no trailing punctuation
- Under 50 characters, specific and clear

## Key Configuration Files

- `pom.xml`: Maven dependencies and build configuration
- `proguard.cfg`: ProGuard obfuscation rules
- `src/main/resources/application.properties`: Base configuration
- `src/main/resources/application-{profile}.properties`: Profile-specific config
- `src/main/resources/models.yaml`: AI model configurations
- `src/main/resources/agent_prompt/`: Agent prompt templates
- `src/main/resources/schema/`: JSON schemas for data validation

## Development Notes

- The project uses ProGuard for code obfuscation during packaging
- Bean names use full class path (see `UniqueBeanNameGenerator` in Application.java)
- Service layer follows interface-implementation pattern with `impl` subdirectory
- Lombok is used extensively for reducing boilerplate
- Redis is used for both caching and vector storage (RAG)
