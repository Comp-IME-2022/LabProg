package main

import (
	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/cors"
)

func main() {
	app := fiber.New()

	app.Use(cors.New())
	app.Post("/login", loginHandler)

	app.Listen(":4000")
}

func loginHandler(c *fiber.Ctx) error {
	//Receber os dados
	type Dados struct {
		Name     string
		Mail     string
		Password string
	}

	loginInput := new(Dados)
	c.BodyParser(loginInput)

	return c.JSON(loginInput)
}
