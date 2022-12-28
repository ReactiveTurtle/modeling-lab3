#STAGE 1
FROM node:14.17.0-alpine AS build
WORKDIR /usr/src/app
COPY /site/package.json /site/package-lock.json ./
RUN npm install -g @angular/cli@11.2.15
RUN npm install
COPY /site .
RUN npm run build

#STAGE 2
FROM nginx:1.17.1-alpine
COPY /site/nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/site /usr/share/nginx/html
