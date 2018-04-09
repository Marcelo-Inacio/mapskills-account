### MAP SKILLS - ACCOUNT - FACULDADE DE TECNOLOGIA PROF º JESSEN VIDAL

##### Projeto de autenticação de alunos que fazem parte das unidades de ensino Centro Paula Souza.

##### CONFIGURAÇÃO

Perfis
- `local, azure-qas, azure-prd`

Spring-boot : [Spring Boot](https://projects.spring.io/spring-boot/ "Spring Boot")
- Rodar localmente
`mvn spring-boot:run -Plocal`

- Gerar pacote
`mvn clean install -Plocal`

Docker : [Docker](https://www.docker.com/ "Docker")
- Construir imagem a partir do Dockerfile  
`docker build -t mapskills/mapskills-account .`

- Criar instância do container
`docker run -it -d --rm --network mapskills-network --name mapskills-account -p 8084:8084 mapskills/mapskills-account`
