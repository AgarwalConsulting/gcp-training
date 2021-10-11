package main

import (
	"context"
	"log"

	"github.com/GoogleCloudPlatform/functions-framework-go/funcframework"

	"algogrit.com/hello"
)

func main() {
	ctx, _ := context.WithCancel(context.Background())

	err := funcframework.RegisterHTTPFunctionContext(ctx, "/jwt", hello.JWTHello)

	if err != nil {
		log.Fatalln("Unable to register:", err)
	}

	funcframework.Start("8000")
}
