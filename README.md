# MescFIT Backend
Check out the [Frontend code](https://github.com/EduarddoEscobar/MescFIT-FE)!

# Tools and Technologies
1. Java [17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. Spring Boot [2.6.7](https://spring.io/)
3. Postgres [14.4](https://www.postgresql.org/)
4. [Docker](https://www.docker.com/)

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

