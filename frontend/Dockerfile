# ============================================================================
# Frontend Angular 18 - Application EMS
# Build multi-étapes : compilation Angular puis serveur Node.js minimal
# ============================================================================

FROM node:20-alpine AS build
WORKDIR /app

# Installation des dépendances
COPY package.json ./
RUN npm install

# Compilation de l'application Angular (configuration production)
COPY . .
RUN npm run build

# ============================================================================

FROM node:20-alpine
WORKDIR /app

ENV NODE_ENV=production
ENV PORT=4200

# Serveur statique Node.js (aucune dépendance externe)
COPY server.js .
COPY --from=build /app/dist/ems-frontend/browser ./dist/ems-frontend/browser

USER node
EXPOSE 4200
CMD ["node", "server.js"]
