package hello

import (
	"fmt"
	"net/http"
	"strings"

	"github.com/golang-jwt/jwt"
)

const hmacSampleSecret = "somesupersecretkey"

func JWTHello(w http.ResponseWriter, req *http.Request) {
	bearerToken := req.Header.Get("Authorization")
	tokenString := strings.Split(bearerToken, " ")[1]

	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {

		if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
		}

		return []byte(hmacSampleSecret), nil
	})

	if err != nil {
		w.WriteHeader(http.StatusUnauthorized)
		fmt.Fprintln(w, err)
		return
	}

	if claims, ok := token.Claims.(jwt.MapClaims); ok && token.Valid {
		fmt.Fprintf(w, "Hello, %s from %s", claims["name"], claims["location"])
	} else {
		w.WriteHeader(http.StatusUnauthorized)
		fmt.Fprintln(w, "Invalid claim or token!")
	}
}
