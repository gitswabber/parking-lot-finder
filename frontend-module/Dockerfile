FROM node:13.7.0-alpine

WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json /app/package.json
RUN npm install --silent

ARG API_BASE_URL
ENV API_BASE_URL=${API_BASE_URL}
#RUN npm install react-scripts@3.0.1 -g --silent

#RUN npm run build

CMD ["npm", "start"]