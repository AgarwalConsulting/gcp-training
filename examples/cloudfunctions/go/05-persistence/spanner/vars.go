package spanner

import (
	"sync"

	"cloud.google.com/go/spanner"
)

var clientOnce sync.Once
var client *spanner.Client
