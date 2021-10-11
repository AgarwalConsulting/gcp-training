package http

import (
	"github.com/gorilla/mux"

	"algogrit.com/emp-server/users/service"
)

type EmployeeHandler struct {
	v1 service.UserService
	*mux.Router
}

func (h *EmployeeHandler) Route(r *mux.Router) {
	r.HandleFunc("/v1/employees", h.Index).Methods("GET")
	r.HandleFunc("/v1/employees", h.Create).Methods("POST")

	h.Router = r
}

func NewHandler(v1 service.UserService) *EmployeeHandler {
	h := EmployeeHandler{v1: v1}

	h.Route(mux.NewRouter())

	return &h
}
