# saucedemo-automation

## Framework de Automatización con Playwright + Cucumber + Java

Este proyecto es un **framework de pruebas automatizadas** construido con **Playwright** y **Cucumber (BDD)** en **Java**.
El objetivo es implementar buenas prácticas de automatización y cubrir escenarios reales utilizando **SauceDemo** como aplicación de prueba.

---

## Características principales

- **Cucumber (BDD)**: Permite describir escenarios en lenguaje natural (Gherkin).
- **Playwright**: Automatización moderna para navegadores rápidos y confiables.
- **Page Object Model (POM)**: Abstracción de los locators y acciones de UI.
- **Page Factory**: Manejo avanzado de objetos de página.
- **Bundle Resources**: Gestión de propiedades y configuraciones externas.
- **Soporte para múltiples entornos** (dev, qa, stage, prod).
- **Paralelismo**: Ejecución concurrente de escenarios para mayor eficiencia.
- **Reejecución de casos fallidos** (retry mechanism).
- **Reportes con Allure Report**.
- **Integración continua con Jenkins**.
- **Ejecuciones en contenedores Docker** para portabilidad.

---

## Estructura del proyecto

saucedemo-automation/\
│── src/\
│ ├── test/java/com/saucedemo/\
│ │ ├── hooks/ # Configuración global (Playwright + Allure)\
│ │ ├── pages/ # Page Objects + Page Factory\
│ │ ├── runners/ # Clases Runner de Cucumber\
│ │ ├── steps/ # Glue code\
│ │ ├── utils/ # Utilidades y helpers\
│ ├── test/features/\
│ │ ├──features/ # Escenarios en Gherkin\
│\
│── pom.xml # Dependencias y plugins Maven\
│── README.md # Este archivo\

---

## Tecnologías utilizadas

- **Java** 17+ (gestionado con `jenv`)
- **Playwright** `1.48.0`
- **Cucumber-Java** `7.20.1`
- **Cucumber-JUnit** `7.20.1`
- **Allure-Cucumber7-JVM** `2.30.0`
- **Maven Surefire Plugin** `3.2.5`
- **Allure Maven Plugin** `2.12.0`

---

## Instalación y ejecución

### Clonar el repositorio

git clone https://github.com/luisnegrete/saucedemo-automation.git
cd saucedemo-automation

### Instalar dependencias

mvn clean install

### Ejecutar pruebas

Ejecutar todos los escenarios:
mvn test

Ejecutar pruebas por tag:
mvn test -Dcucumber.filter.tags="@login"

### Generar reportes Allure

mvn allure:report
mvn allure:serve

### Jenkins

Integración con Jenkins pipeline.
Soporte para ejecución en paralelo.
Publicación automática de reportes Allure.

### Ejemplo de escenario (Gherkin)

`Feature: User Authentication test`\
`@US0101-01 @smoke`\
`Scenario: @US0101-01_Login success`\
`Given User is on login page`\
`When User enter the username as "standard_user"`\
`And User enter the password as "secret_sauce"`\
`And User click on the login button`\
`Then User should see inventory page`

## Próximos pasos

- Integración con Base de datos de prueba.
- Reportes de historias de usuario vs escenarios.
- Ejecución distribuida con Selenium Grid / Playwright Grid.

### Autor

Proyecto creado por José Luis Negrete Juárez\
Contacto: jose.negrete@globant.com\
GitHub: https://github.com/luisnegrete
