package http

import (
	"encoding/json"
	"fmt"
	"net/http"

	"algogrit.com/emp-server/entities"
)

func (h *EmployeeHandler) Index(w http.ResponseWriter, req *http.Request) {
	emp, err := h.v1.Index()
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, err)
	}

	w.Header().Add("Content-Type", "application/json")
	json.NewEncoder(w).Encode(emp)
}

func (h *EmployeeHandler) Create(w http.ResponseWriter, req *http.Request) {
	var newEmp entities.Employee
	err := json.NewDecoder(req.Body).Decode(&newEmp)

	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Fprint(w, err)
		return
	}

	emp, err := h.v1.Create(newEmp)

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		fmt.Fprintln(w, err)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(*emp)
}
