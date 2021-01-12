FROM node:14-alpine

WORKDIR /app

COPY package.json /app/

RUN npm install

COPY app.js /app/

CMD ["npm", "start"]