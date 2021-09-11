# Hello World

## Go

```bash
cd go/
```

### `Gopher` function

```bash
gcloud functions deploy Gopher --runtime go113 --trigger-http --allow-unauthenticated
```

```bash
gcloud functions delete Gopher
```
