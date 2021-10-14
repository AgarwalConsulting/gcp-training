package helloworld_test

import (
	"context"
	"testing"

	"algogrit.com/hellops"
)

func TestHelloPubSub(t *testing.T) {
	msg := helloworld.PubSubMessage{[]byte("Gaurav")}

	helloworld.HelloPubSub(context.Background(), msg)
}
