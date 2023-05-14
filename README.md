# MescFIT

Check out the [Frontend code](https://github.com/EduarddoEscobar/MescFIT/tree/main/src/main/frontend)!

# Tools and Technologies

1. Java [17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. Spring Boot [2.6.7](https://spring.io/)
3. Postgres [14.4](https://www.postgresql.org/)
4. Apache Maven [3.8.5](https://maven.apache.org/download.cgi)
5. [Docker](https://www.docker.com/)

# How To Run

## Clone the repo

```bash
git clone git@github.com:EduarddoEscobar/MescFIT.git
cd MescFIT
```

## Building

```bash
mvn clean install
```

## Running The Application

```bash
cd target
java -jar mescfit.jar
```

## Testimonials

| Method   | URL                | Description                              |
|----------|--------------------|------------------------------------------|
| [GET]    | /testimonials      | Returns an array of all the testimonials |
| [GET]    | /testimonials/{id} | Returns the testimonial with given id    |
| [POST]   | /testimonials      | Returns the new testimonial object       |
| [PUT]    | /testimonials/{id} | Returns the updated testimonial object   |
| [DELETE] | /testimonials/{id} | Returns the updated testimonial object   |

## Categories

| Method   | URL              | Description                     |
|----------|------------------|---------------------------------|
| [GET]    | /categories      | Returns all the categories      |
| [POST]   | /categories      | Returns the new category object |
| [DELETE] | /categories/{id} | Returns the deleted category    |

