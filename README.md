# Mywebsite_CinemaPeak

CinemaPeak foundation with:

- **Latest UI stack**: React 19 + Vite 6 + TypeScript for fast UI iteration and stable Playwright selectors.
- **Java API stack**: Spring Boot 3.5 REST API for movie orders.

## Project structure

- `frontend/` Playwright-friendly order form UI.
- `backend/` RESTful Order API (`/api/orders`, `/api/orders/{id}`, `/api/users/{userId}/orders`).

## API purchase rules

- `PHYSICAL_CD` / `PHYSICAL_DVD`: shipping address required.
- `DIGITAL_DOWNLOAD`: shipping address forbidden, download link generated.
- `STREAMING_ACCESS`: shipping address/download link absent, streaming enabled.

## Run locally

### Frontend

```bash
cd frontend
npm install
npm run dev
```

### Backend

```bash
cd backend
mvn spring-boot:run
```
