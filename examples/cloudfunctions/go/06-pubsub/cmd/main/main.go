package main

import (
	"context"

	helloworld "algogrit.com/hellops"
	"github.com/GoogleCloudPlatform/functions-framework-go/funcframework"
)

func main() {
	funcframework.RegisterEventFunctionContext(context.Background(), "/", helloworld.HelloPubSub)

	funcframework.Start("8000")
}
