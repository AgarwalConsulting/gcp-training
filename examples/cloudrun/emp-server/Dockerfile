FROM golang AS builder
# mkdir /app && cd /app
WORKDIR /app
COPY go.* .
RUN go mod download
COPY . .
ENV CGO_ENABLED 0
RUN go build -o server ./cmd/server

FROM scratch
WORKDIR /app
COPY --from=builder /app/server .
CMD [ "/app/server" ]
